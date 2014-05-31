package iron4j.cache;

import iron4j.cache.internal.IronCacheService;
import iron4j.cache.internal.data.CacheName;
import iron4j.cache.internal.data.MsgResponse;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 3/18/14 9:07 PM
 *
 * @author Erik Beeson
 */
public class IronCacheClient {
	private final String token;
	private final String projectId;
	private final IronCacheService service;

	public IronCacheClient(String token, String projectId) {
		this(new RestAdapter.Builder()
					 .setLog(new RestAdapter.Log() {
						 public void log(String message) {
							 System.out.println(message);
						 }
					 })
					 .setLogLevel(RestAdapter.LogLevel.FULL)
					 .setServer("https://cache-aws-us-east-1.iron.io/1")
					 .setErrorHandler(new ErrorHandler() {
						 public Throwable handleError(RetrofitError e) {

							 Response response = e.getResponse();
							 if(response.getStatus() == 404) {
								 MsgResponse msg = (MsgResponse) e.getBodyAs(MsgResponse.class);
								 return new IronCacheNotFoundException(msg != null ? msg.getMsg() : null, e, e.getUrl());
							 } else {
								 return e;
							 }
						 }
					 })
					 .build(), token, projectId);
	}

	public IronCacheClient(RestAdapter rest, String token, String projectId) {
		this.token = token;
		this.projectId = projectId;
		this.service = rest.create(IronCacheService.class);

		//getCacheNames(); // ping to verify it's available
	}

	public List<String> getCacheNames() {
		List<String> cacheNames = new ArrayList<String>();
		for(CacheName cache : service.getCaches(token, projectId)) {
			cacheNames.add(cache.getName());
		}
		return cacheNames;
	}

	public IronCache<String> getCache(String cacheName) {
		return new IronCache<String>(service, token, projectId, cacheName, String.class);
	}

	public <T> IronCache<T> getCache(String cacheName, Class<T> type) {
		return new IronCache<T>(service, token, projectId, cacheName, type);
	}

	/*
	public static void dumpResponse(Response response) {
		System.out.println(response.getStatus() + " " + response.getReason());
		for(Header header : response.getHeaders()) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
		System.out.println(response.getBody().mimeType());
		try {
			System.out.println(IOUtils.toString(response.getBody().in()));
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
	*/
}
