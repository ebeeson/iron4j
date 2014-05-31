package iron4j.cache.internal.data;

/**
 * 3/19/14 12:44 PM
 *
 * @author Erik Beeson
 */
public class MsgResponse {
	private String msg;

	public MsgResponse() {
	}

	public String getMsg() {
		return msg;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("MsgResponse{");
		sb.append("msg='").append(msg).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
