package eu.aronnax.subtilemetry.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static eu.aronnax.subtilemetry.application.GenerateSrtSubtitleFromGoproMovieSourceUC.*;
import static org.junit.jupiter.api.Assertions.*;

class GenerateSrtSubtitleFromGoproMovieSourceUCTest {

    private GenerateSrtSubtitleFromGoproMovieSourceUC instance = new GenerateSrtSubtitleFromGoproMovieSourceUC();

    @BeforeEach
    void setUp() {
    }

    @Test
    void apply() {
        instance.apply(new Params(Path.of("src/test/resources/GX070002-small.mp4")));
    }
}