package iron4j.worker;

import com.google.gson.Gson;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * 1/7/14 6:31 PM
 *
 * @author Erik Beeson
 */
public abstract class IronWorkerRunner {
	private IronWorkerRunner() {
	}

	public static <C, P> void run(IronWorker<C, P> worker, Class<C> configClass, Class<P> payloadClass, String[] args) throws Exception {
		run(worker, configClass, payloadClass, args, new Gson());
	}

	public static <C, P> void run(IronWorker<C, P> worker, Class<C> configClass, Class<P> payloadClass, String[] args, Gson gson) throws Exception {
		System.out.println(Arrays.toString(args));

		OptionParser parser = new OptionParser();
		ArgumentAcceptingOptionSpec<String> payloadOption = parser.accepts("payload", "work data file").withRequiredArg();
		ArgumentAcceptingOptionSpec<String> idOption = parser.accepts("id", "ID of the task that is currently being executed").withRequiredArg();
		ArgumentAcceptingOptionSpec<String> dOption = parser.accepts("d", "user-writable directory that can be used for temporary storage for the duration of the task’s execution").withRequiredArg();
		ArgumentAcceptingOptionSpec<String> eOption = parser.accepts("e", "environment").withRequiredArg();
		ArgumentAcceptingOptionSpec<String> configOption = parser.accepts("config", "config file").withRequiredArg();

		OptionSet options = parser.parse(args);
		final String id = options.valueOf(idOption);
		final String env = options.valueOf(eOption);
		final String payloadFilename = options.valueOf(payloadOption);
		final String configFilename = options.valueOf(configOption);
		final String workDirFilename = options.valueOf(dOption);

		System.out.println();
		System.out.println("             id: " + id);
		System.out.println("    environment: " + env);
		System.out.println("payloadFilename: " + payloadFilename);
		System.out.println(" configFilename: " + configFilename);
		System.out.println("workDirFilename: " + workDirFilename);

		final C config = configFilename != null ? gson.fromJson(new FileReader(configFilename), configClass) : null;
		final P payload = payloadFilename != null ? gson.fromJson(new FileReader(payloadFilename), payloadClass) : null;
		final File workDir = workDirFilename != null ? new File(workDirFilename) : null;

		worker.work(id, env, workDir, config, payload);
	}
}
