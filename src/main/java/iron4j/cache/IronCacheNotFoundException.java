package iron4j.cache;

/**
 * 3/19/14 10:56 AM
 *
 * @author Erik Beeson
 */
public class IronCacheNotFoundException extends Exception {
	private final String url;

	public IronCacheNotFoundException(String message, Throwable cause, String url) {
		super(message == null ? "Not found." : message, cause);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
