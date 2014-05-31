package iron4j.cache.internal.data;

import com.google.gson.annotations.SerializedName;

/**
 * 3/18/14 9:05 PM
 *
 * @author Erik Beeson
 */
public class CacheItemRequest {
	/**
	 * The item's data
	 */
	private final String value;

	/**
	 * How long in seconds to keep the item in the cache before it is deleted. By default, items do not expire. Maximum is 2,592,000 seconds (30 days).
	 */
	@SerializedName("expires_in")
	private final Integer expiresIn;

	/**
	 * If set to true, only set the item if the item is already in the cache. If the item is not in the cache, do not create it.
	 */
	private final Boolean replace;

	/**
	 * If set to true, only set the item if the item is not already in the cache. If the item is in the cache, do not overwrite it.
	 */
	private final Boolean add;

	/**
	 * If set, the new item will only be placed in the cache if there is an existing item with a matching key and cas value. An item's cas value is automatically generated and is included when the item is retrieved.
	 */
	private final String cas;

	public CacheItemRequest(String value, Integer expiresIn, Boolean replace, Boolean add, String cas) {
		this.value = value;
		this.expiresIn = expiresIn;
		this.replace = replace;
		this.add = add;
		this.cas = cas;
	}

	public String getValue() {
		return value;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public Boolean getReplace() {
		return replace;
	}

	public Boolean getAdd() {
		return add;
	}

	public String getCas() {
		return cas;
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IronCacheItemRequest{");
		sb.append("value='").append(value).append('\'');
		sb.append(", expiresIn=").append(expiresIn);
		sb.append(", replace=").append(replace);
		sb.append(", add=").append(add);
		sb.append(", cas='").append(cas).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
