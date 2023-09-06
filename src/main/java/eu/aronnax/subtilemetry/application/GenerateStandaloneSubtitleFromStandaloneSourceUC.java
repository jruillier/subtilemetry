package eu.aronnax.subtelemetry.application;

import io.jenetics.jpx.GPX;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Function;

public class GenerateStandaloneSubtitleFromStandaloneSourceUC implements Function<GenerateStandaloneSubtitleFromStandaloneSourceUC.Params, GenerateStandaloneSubtitleFromStandaloneSourceUC.Result> {
    @Override
    public Result apply(Params p) {

        try {
            GPX gpx = GPX.read(p.sourcePath());
            gpx.wayPoints();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    record Params(Path sourcePath) {}

    record Result() {}
}
