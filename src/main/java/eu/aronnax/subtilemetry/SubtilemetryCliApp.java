package eu.aronnax.subtilemetry;

import eu.aronnax.subtilemetry.application.GenerateSrtSubtitleFromGoproMovieSourceUC;
import picocli.CommandLine;

import java.nio.file.Path;

import static eu.aronnax.subtilemetry.application.GenerateSrtSubtitleFromGoproMovieSourceUC.*;

@CommandLine.Command(name = "SubTelemetry", mixinStandardHelpOptions = true)
public class SubtilemetryCliApp implements Runnable {

    @CommandLine.Option(names = {"--source"}, required = true)
    private String sourcePath;

    @Override
    public void run() {
        GenerateSrtSubtitleFromGoproMovieSourceUC uc = new GenerateSrtSubtitleFromGoproMovieSourceUC();
        uc.apply(new Params(Path.of(sourcePath)));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SubtilemetryCliApp()).execute(args);
        System.exit(exitCode);
    }
}
