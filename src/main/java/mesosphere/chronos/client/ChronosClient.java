package mesosphere.chronos.client;

import mesosphere.chronos.client.utils.ChronosException;
import mesosphere.chronos.client.utils.ModelUtils;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class ChronosClient {
	static class MarathonHeadersInterceptor implements RequestInterceptor {
		@Override
		public void apply(RequestTemplate template) {
			template.header("Accept", "application/json");
			template.header("Content-Type", "application/json");
		}
	}
	
	static class MarathonErrorDecoder implements ErrorDecoder {
		@Override
		public Exception decode(String methodKey, Response response) {
			return new ChronosException(response.status(), response.reason());
		}
	}
	
	public static Chronos getInstance(String endpoint) {
		GsonDecoder decoder = new GsonDecoder(ModelUtils.GSON);
		GsonEncoder encoder = new GsonEncoder(ModelUtils.GSON);
		return Feign.builder().encoder(encoder).decoder(decoder)
				.errorDecoder(new MarathonErrorDecoder())
				.requestInterceptor(new MarathonHeadersInterceptor())
				.target(Chronos.class, endpoint);
	}
}
