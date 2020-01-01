package de.cwkr.todo4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PriorityTest {
    @Test
    void ofChar() {
        Priority priority = Priority.of('a');

        assertNotNull(priority);
        assertEquals('A', priority.getValue());
    }

    @Test
    void ofString() {
        Priority priority = Priority.of("b");

        assertNotNull(priority);
        assertEquals('B', priority.getValue());
    }

    @Test
    void ofNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Priority priority = Priority.of(null);
        });
    }

    @Test
    void ofNonLetter() {
        assertThrows(IllegalArgumentException.class, () -> {
            Priority priority = Priority.of("%");
        });
    }

    @Test
    void equality() {
        Priority prio1 = Priority.of('a');
        Priority prio2 = Priority.of("A");

        assertEquals(prio1, prio2);
    }

    @Test
    void compare() {
        Priority prio1 = Priority.of('A');
        Priority prio2 = Priority.of("z");

        assertTrue(prio1.compareTo(prio2) > 0);
    }
}
