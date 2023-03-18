package eu.kaluzinski.sdjpaintro;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jdk11FeaturesTest {

    @Test
    void shouldStripMultilineNonBlankLinesOfText() {
        String testedText = "Let's prepare \n \t some text that \n takes \n\n several lines";
        List<String> lines = testedText.lines()
                .filter(Predicate.not(String::isBlank))
                .map(String::strip)
                .map(String::trim)
                .collect(Collectors.toList());
        List<String> expectedValues = List.of("Let's prepare", "some text that", "takes", "several lines");
        assertEquals(lines, expectedValues);
    }

    @Test
    void shouldReadNewlyCreatedFile() throws IOException {
        Path tempFile = Files.createTempFile(Files.createTempDirectory("tmpDirPrefix").toAbsolutePath(), "demo", ".txt");
        Path filePath = Files.writeString(tempFile, "Sample text");
        String fileContent = Files.readString(filePath);
        assertEquals(fileContent,"Sample text");
    }
}
