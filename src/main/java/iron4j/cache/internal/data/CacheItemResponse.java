package iron4j.cache.internal.data;

/**
 * 3/18/14 11:48 PM
 *
 * @author Erik Beeson
 */
public class CacheItemResponse {
	/**
	 * Cache name.
	 */
	private String cache;
	/**
	 * Item key.
	 */
	private String key;
	/**
	 * Item value.
	 */
	private String value;
	private String cas;

	public CacheItemResponse() {
	}

	public String getCache() {
		return cache;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getCas() {
		return cas;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("CacheItemResponse{");
		sb.append("cache='").append(cache).append('\'');
		sb.append(", key='").append(key).append('\'');
		sb.append(", value='").append(value).append('\'');
		sb.append(", cas='").append(cas).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
