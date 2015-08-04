package iron4j.cache;

import com.google.gson.Gson;
import iron4j.cache.internal.IronCacheService;
import iron4j.cache.internal.data.CacheItemRequest;
import iron4j.cache.internal.data.CacheItemResponse;
import iron4j.cache.internal.data.IncrementRequest;
import retrofit.client.Response;

/**
 * 3/18/14 9:07 PM
 *
 * @author Erik Beeson
 */
public class IronCache<T> {
	private final IronCacheService service;
	private final String token;
	private final String projectId;
	private final String cacheName;
	private final Class<T> type;

	private final Gson gson = new Gson();

	IronCache(IronCacheService service, String token, String projectId, String cacheName, Class<T> type) {
		this.service = service;
		this.token = token;
		this.projectId = projectId;
		this.cacheName = cacheName;
		this.type = type;

		//getCacheSize(); // ping the cache to verify it's available
	}

	protected String toJson(T item) {
		return String.class.equals(type) ? (String) item : gson.toJson(item);
	}

	@SuppressWarnings("unchecked")
	protected T fromJson(String json) {
		return String.class.equals(type) ? (T) json : gson.fromJson(json, type);
	}


	public void addItem(String key, T item) throws ItemExistsException {
		addItem(key, item, null);
	}

	public void addItem(String key, T item, Integer expiresIn) throws ItemExistsException {
		Response response = service.putItem(token, projectId, cacheName, key, new CacheItemRequest(toJson(item), expiresIn, false, true, null));
		if(response.getStatus() == 202) {
			throw new ItemExistsException(cacheName, key);
		}
	}

	public void updateItem(String key, T item) throws IronCacheNotFoundException {
		updateItem(key, item, null, null);
	}

	public void updateItem(String key, T item, Integer expiresIn) throws IronCacheNotFoundException {
		updateItem(key, item, expiresIn, null);
	}

	public void updateItem(String key, T item, String cas) throws IronCacheNotFoundException {
		updateItem(key, item, null, cas);
	}

	public void updateItem(String key, T item, Integer expiresIn, String cas) throws IronCacheNotFoundException {
		service.putItem(token, projectId, cacheName, key, new CacheItemRequest(toJson(item), expiresIn, true, false, cas));
	}

	public void addOrUpdateItem(String key, T item) {
		addOrUpdateItem(key, item, null, null);
	}

	public void addOrUpdateItem(String key, T item, Integer expiresIn) {
		addOrUpdateItem(key, item, expiresIn, null);
	}

	public void addOrUpdateItem(String key, T item, String cas) {
		addOrUpdateItem(key, item, null, cas);
	}

	public void addOrUpdateItem(String key, T item, Integer expiresIn, String cas) {
		service.putItem(token, projectId, cacheName, key, new CacheItemRequest(toJson(item), expiresIn, true, true, cas));
	}

	public IronCacheItem<T> getItem(String key) throws IronCacheNotFoundException {
		CacheItemResponse response = service.getItem(token, projectId, cacheName, key);
		return new IronCacheItem<T>(type, fromJson(response.getValue()), response.getCas());
	}

	public void deleteItem(String key) throws IronCacheNotFoundException {
		service.deleteItem(token, projectId, cacheName, key);
	}

	public int incrementItem(String key) throws IronCacheNotFoundException {
		return incrementItem(key, 1);
	}

	public int incrementItem(String key, int amount) throws IronCacheNotFoundException {
		return service.incrementItem(token, projectId, cacheName, key, new IncrementRequest(amount)).getValue();
	}


	public int getCacheSize() {
		return service.getCache(token, projectId, cacheName).getSize();
	}

	public void clearCache() {
		service.clearCache(token, projectId, cacheName);
	}

	public void deleteCache() {
		service.deleteCache(token, projectId, cacheName);
	}

	public String toString() {
		final StringBuilder sb = new StringBuilder("IronCache{");
		sb.append("name='").append(cacheName).append('\'');
		sb.append(", type=").append(type);
		sb.append('}');
		return sb.toString();
	}
}
