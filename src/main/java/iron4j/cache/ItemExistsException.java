package iron4j.cache;

/**
 * 3/19/14 10:55 AM
 *
 * @author Erik Beeson
 */
public class ItemExistsException extends Exception {
	private final String cacheName;
	private final String key;

	public ItemExistsException(String cacheName, String key) {
		super("Item [" + key + "] already exists in cache [" + cacheName + "]");
		this.cacheName = cacheName;
		this.key = key;
	}

	public String getCacheName() {
		return cacheName;
	}

	public String getKey() {
		return key;
	}
}
