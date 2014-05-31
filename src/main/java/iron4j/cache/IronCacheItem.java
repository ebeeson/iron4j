package iron4j.cache;

/**
 * 3/19/14 9:48 AM
 *
 * @author Erik Beeson
 */
public class IronCacheItem<T> {
	private final Class<T> type;
	private final T value;
	private final String cas;

	IronCacheItem(Class<T> type, T value, String cas) {
		this.type = type;
		this.value = value;
		this.cas = cas;
	}

	public Class<T> getType() {
		return type;
	}

	public T getValue() {
		return value;
	}

	public String getCas() {
		return cas;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IronCacheItem{");
		sb.append("type=").append(type);
		sb.append(", value=").append(value);
		sb.append(", cas='").append(cas).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
