package eu.kaluzinski.sdjpaintro;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Jdk12FeaturesTest {

    @Test
    void shouldReverseGivenTextUsingTransform() {
        String text = "Example";
        String transformed = text.transform(value ->
                new StringBuilder(value)
                        .reverse()
                        .toString()
        );
        assertEquals(transformed, "elpmaxE");
    }

    @Test
    public void shouldNotFindMismatchInSameFiles() throws IOException {
        var content = "Test Java 12 features";

        long mismatch = Files.mismatch(
                createTextFile("fileA", content),
                createTextFile("fileB", content)
        );
        assertEquals(-1, mismatch);
    }

    @Test
    public void shouldFindMismatchInSameFiles() throws IOException {
        var content = "Test Java 12 features";

        long mismatch = Files.mismatch(
                createTextFile("fileA", content),
                createTextFile("fileB", "%s\t".formatted(content))
        );
        assertEquals(content.length(), mismatch);
    }

    @Test
    public void shouldCreateShortLocaleBasedCompactNumberInstances() {
        Locale localePlPl = new Locale("pl", "PL");
        NumberFormat shortNumberFormat =
                NumberFormat.getCompactNumberInstance(localePlPl, NumberFormat.Style.SHORT);
        shortNumberFormat.setMaximumFractionDigits(2);
        assertEquals("2,59\u00a0tys.", shortNumberFormat.format(2592));

        NumberFormat longNumberFormat =
                NumberFormat.getCompactNumberInstance(localePlPl, NumberFormat.Style.LONG);
        longNumberFormat.setMaximumFractionDigits(2);
        assertEquals("2,59 tysiące", longNumberFormat.format(2592));
    }

    @Test
    public void shouldReturnValueFromSwitchCaseExpression() {
        LocalDate date = LocalDate.of(2023, 11, 11);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        String typeOfDay = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
            case SATURDAY, SUNDAY -> "Weekend";
        };

        assertEquals("Weekend", typeOfDay);
    }

    @Test
    void shouldDetectTypeInPatternMatching() {
        Object obj = "test";
        if (obj instanceof String s) {
            int length = s.length();
            assertEquals(4, length);
        }else{
            fail("You shall not pass.");
        }
    }

    private Path createTextFile(String prefix, String content) throws IOException {
        Path filePath = Files.createTempFile(prefix, ".txt");
        Files.writeString(filePath, content);
        return filePath;
    }
}
