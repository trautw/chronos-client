package mesosphere.chronos.client.model.v2;

import java.util.Collection;

import mesosphere.chronos.client.utils.ModelUtils;

public class GetTasksResponse {
	private Collection<Task> tasks;

	public Collection<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}
