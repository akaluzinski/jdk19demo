package eu.kaluzinski.sdjpaintro;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
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
        assertEquals(fileContent, "Sample text");
    }

    @Test
    void shouldCallGetRequest() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://akaluzinski.github.io/"))
                .build(), ofString());
        assertEquals(httpResponse.statusCode(), 200);
    }
}
