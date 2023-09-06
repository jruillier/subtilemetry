package eu.aronnax.subtelemetry;

import picocli.CommandLine;

@CommandLine.Command(name = "SubTelemetry", mixinStandardHelpOptions = true)
public class SubTelemetryCliApp implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SubTelemetryCliApp()).execute(args);
        System.exit(exitCode);
    }
}
