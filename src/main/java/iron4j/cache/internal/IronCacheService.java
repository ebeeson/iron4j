package iron4j.cache.internal;

import retrofit.client.Response;
import retrofit.http.*;
import iron4j.cache.internal.data.*;

import java.util.List;

/**
 * 3/18/14 8:56 PM
 *
 * @author Erik Beeson
 */
public interface IronCacheService {
	/**
	 * List Caches
	 */
	@GET("/projects/{projectId}/caches")
	public List<CacheName> getCaches(@Query("oauth") String token, @Path("projectId") String projectId);

	/**
	 * Get Info About a Cache
	 */
	@GET("/projects/{projectId}/caches/{cacheName}")
	public CacheInfo getCache(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName);

	/**
	 * Delete a Cache
	 */
	@DELETE("/projects/{projectId}/caches/{cacheName}")
	public Response deleteCache(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName);

	/**
	 * Clear a Cache
	 */
	@POST("/projects/{projectId}/caches/{cacheName}/clear")
	public Response clearCache(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName);

	/**
	 * Put an Item into a Cache
	 */
	@PUT("/projects/{projectId}/caches/{cacheName}/items/{key}")
	public Response putItem(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName, @Path("key") String key, @Body CacheItemRequest item);

	/**
	 * Increment an Item's value
	 */
	@POST("/projects/{projectId}/caches/{cacheName}/items/{key}/increment")
	public IncrementResponse incrementItem(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName, @Path("key") String key, @Body IncrementRequest body);

	/**
	 * Get an Item from a Cache
	 */
	@GET("/projects/{projectId}/caches/{cacheName}/items/{key}")
	public CacheItemResponse getItem(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName, @Path("key") String key);

	/**
	 * Delete an Item from a Cache
	 */
	@DELETE("/projects/{projectId}/caches/{cacheName}/items/{key}")
	public Response deleteItem(@Query("oauth") String token, @Path("projectId") String projectId, @Path("cacheName") String cacheName, @Path("key") String key);
}
