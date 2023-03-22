package eu.kaluzinski.sdjpaintro;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Jdk13FeaturesTest {

    @Test
    public void shouldYieldValueFromSwitchCaseExpressionJEP354() {
        LocalDate date = LocalDate.of(2023, 11, 11);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        String typeOfDay = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY: {
                yield "Working Day";
            }
            case SATURDAY, SUNDAY: {
                yield "Weekend";
            }

        };

        assertEquals("Weekend", typeOfDay);
    }

    @Test
    public void shouldSupportTextBlocksJEP355() {
        String multiLineBlockText = """
            {
                "name" : "Adrian",
            }
            """;

        assertFalse(multiLineBlockText.isBlank());
        assertTrue(multiLineBlockText.contains("Adrian"));
    }

}
