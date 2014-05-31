package iron4j.cache.internal.data;

import com.google.gson.annotations.SerializedName;

/**
 * 3/18/14 11:24 PM
 *
 * @author Erik Beeson
 */
public class CacheName {
	@SerializedName("project_id")
	private String projectId;
	private String name;

	public CacheName() {
	}

	public String getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IronCacheName{");
		sb.append("projectId='").append(projectId).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
