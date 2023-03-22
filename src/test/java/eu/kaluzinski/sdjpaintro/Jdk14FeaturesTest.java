package eu.kaluzinski.sdjpaintro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

record User(String name, String lastName) {
    public User(String name) {
        this(name, "Unknown");
    }
}

public class Jdk14FeaturesTest {

    @Test
    public void shouldCreateRecordWithJEP359() {
        User user1 = new User("  Adi", "Name");
        User user2 = new User("  Adi", "Name");
        assertEquals("User[name=  Adi, lastName=Name]", user1.toString());
        assertEquals(user1.hashCode(), user2.hashCode());
        assertEquals(user1, user2);
    }

    @Test
    public void shouldCreateRecordWithDefaultValueJEP359() {
        User user = new User("  Adi");
        assertEquals(user.lastName(), "Unknown");
        assertEquals("User[name=  Adi, lastName=Unknown]", user.toString());
    }

}
