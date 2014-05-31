package iron4j.cache.internal.data;

/**
 * 3/19/14 12:05 AM
 *
 * @author Erik Beeson
 */
public class IncrementRequest {
	/**
	 * The amount to increment the value, as an integer. If negative, the value will be decremented.
	 */
	private int amount;

	/**
	 * @param amount The amount to increment the value. If negative, the value will be decremented.
	 */
	public IncrementRequest(int amount) {
		this.amount = amount;
	}

	/**
	 * @return The amount to increment the value. If negative, the value will be decremented.
	 */
	public int getAmount() {
		return amount;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IncrementRequest{");
		sb.append("amount=").append(amount);
		sb.append('}');
		return sb.toString();
	}
}
