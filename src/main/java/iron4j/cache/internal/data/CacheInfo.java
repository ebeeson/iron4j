package iron4j.cache.internal.data;

/**
 * 3/18/14 11:34 PM
 *
 * @author Erik Beeson
 */
public class CacheInfo {
	/**
	 * cache size
	 */
	private int size;

	public CacheInfo() {
	}

	public int getSize() {
		return size;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IronCacheInfo{");
		sb.append("size=").append(size);
		sb.append('}');
		return sb.toString();
	}
}
