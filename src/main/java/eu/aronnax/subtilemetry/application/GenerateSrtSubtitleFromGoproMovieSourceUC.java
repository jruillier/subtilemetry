package eu.aronnax.subtilemetry.application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.logging.Logger;

import static eu.aronnax.subtilemetry.application.GenerateSrtSubtitleFromGoproMovieSourceUC.Params;
import static eu.aronnax.subtilemetry.application.GenerateSrtSubtitleFromGoproMovieSourceUC.Result;

public class GenerateSrtSubtitleFromGoproMovieSourceUC implements Function<Params, Result> {

    private static final Logger LOG = Logger.getLogger(GenerateSrtSubtitleFromGoproMovieSourceUC.class.getName());

    @Override
    public Result apply(Params params) {

        //        try {
        //            IsoFile isoFile = new IsoFile("/Users/jraronnax/Movies/GX070002.MP4");
        //            List<Box> boxes = isoFile.getBoxes();
        //
        //            // Freebox est vide
        //            FreeBox freeBox = (FreeBox) boxes.get(1);
        //            ByteBuffer byteBuffer = freeBox.getData();
        //            String freeBoxContent = new String(byteBuffer.array(), StandardCharsets.UTF_8);
        //
        //            Box boxZero = boxes.get(0);
        //
        //        } catch (IOException e) {
        //            throw new RuntimeException(e);
        //        }

        //        try (ExifTool exifTool = new ExifToolBuilder().build()) {
        //            Map<Tag, String> imageMeta = exifTool.getImageMeta(
        //                    new File("/Users/jraronnax/Movies/GX060002.MP4"),
        //                    StandardOptions.builder().withExtractEmbedded(true).withFormat(Format).build(),
        //                    Arrays.asList(StandardTag.GPS_SPEED));
        //            imageMeta.get(StandardTag.GPS_SPEED);
        //        } catch (Exception e) {
        //            throw new RuntimeException(e);
        //        }

        //        try {
        //            File mp4File = new File("/Users/jraronnax/Movies/GX070002.MP4");
        //            Metadata metadata = ImageMetadataReader.readMetadata(mp4File);
        //            Mp4Directory mp4Directory = metadata.getFirstDirectoryOfType(Mp4Directory.class);
        //            Mp4Descriptor mp4Descriptor = new Mp4Descriptor(mp4Directory);
        //            mp4Descriptor.getDescription(1);
        //        } catch (ImageProcessingException | IOException e) {
        //            throw new RuntimeException(e);
        //        }

        File tempExiftoolFormat;
        try (InputStream template = this.getClass().getResourceAsStream("/exiftool-gpx.fmt")) {
            tempExiftoolFormat = File.createTempFile("subtilemetry-exiftool-gpx_", ".fmt");
            tempExiftoolFormat.delete();
            assert template != null;
            Files.copy(template, tempExiftoolFormat.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "exiftool",
                    "-ee",
                    "-p",
                    tempExiftoolFormat.getAbsolutePath(),
                    params.sourcePath().toAbsolutePath().toString());
            File tempGpxExtraction = File.createTempFile("subtilemetry-extraction_", ".gpx");
            tempGpxExtraction.delete();
            processBuilder.redirectOutput(tempGpxExtraction);
            Process process = processBuilder.start();
            process.waitFor();
            LOG.info(tempGpxExtraction.getAbsolutePath());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public record Params(Path sourcePath) {}

    public record Result() {}
}
