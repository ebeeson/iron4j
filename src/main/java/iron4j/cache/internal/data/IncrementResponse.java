package iron4j.cache.internal.data;

/**
 * 3/18/14 11:42 PM
 *
 * @author Erik Beeson
 */
public class IncrementResponse {
	private int value;

	public IncrementResponse() {
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IncrementResponse{");
		sb.append("value=").append(value);
		sb.append('}');
		return sb.toString();
	}
}
