package eu.aronnax.subtilemetry;

import picocli.CommandLine;

@CommandLine.Command(name = "SubTelemetry", mixinStandardHelpOptions = true)
public class SubtilemetryCliApp implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SubtilemetryCliApp()).execute(args);
        System.exit(exitCode);
    }
}
