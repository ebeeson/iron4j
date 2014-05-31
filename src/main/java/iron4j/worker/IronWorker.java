package iron4j.worker;

import java.io.File;

/**
 * 1/7/14 6:41 PM
 *
 * @author Erik Beeson
 */
public interface IronWorker<C, P> {
	public void work(String id, String environment, File workDirectory, C config, P payload) throws Exception;
}
