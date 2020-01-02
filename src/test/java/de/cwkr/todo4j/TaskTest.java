package de.cwkr.todo4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void ofDescription() {
        Task task = Task.of("Send a men into space");

        assertFalse(task.isComplete());
        assertEquals("Send a men into space", task.getDescription());
        assertNull(task.getCompletionDate());
        assertNull(task.getCreationDate());
        assertNull(task.getPriority());
    }

    @Test
    void toBuilder() {
        Task task = Task.builder()
                        .complete(false)
                        .priority('A')
                        .creationDate(LocalDate.of(1962, Month.SEPTEMBER, 12))
                        .description("Send an astronaut to the Moon before the end of the decade and bring him back alive")
                        .build();

        Task doneTask = task.toBuilder()
                            .complete(true)
                            .completionDate(LocalDate.of(1969, Month.JULY, 24))
                            .build();

        assertFalse(task.isComplete());
        assertTrue(doneTask.isComplete());
        assertEquals(task.getCreationDate(), doneTask.getCreationDate());
        assertEquals(task.getDescription(), doneTask.getDescription());
    }

    @Test
    void toStringUsingBuilder() {
        Task task = Task.builder()
                        .priority('a')
                        .creationDate(LocalDate.of(1962, Month.SEPTEMBER, 12))
                        .description("Send an astronaut to the Moon before the end of the decade and bring him back alive")
                        .build();

        String str = task.toString();

        assertEquals(
            "(A) 1962-09-12 Send an astronaut to the Moon before the end of the decade and bring him back alive",
            str
        );
    }

    @Test
    void toStringUsingBuilderComplete() {
        Task task = Task.builder()
                        .complete(true)
                        .completionDate(LocalDate.of(1969, Month.JULY, 24))
                        .creationDate(LocalDate.of(1962, Month.SEPTEMBER, 12))
                        .description("Send an astronaut to the Moon before the end of the decade and bring him back alive")
                        .build();

        String str = task.toString();

        assertEquals(
            "x 1969-07-24 1962-09-12 Send an astronaut to the Moon before the end of the decade and bring him back alive",
            str
        );
    }

    @Test
    void parseDescOnly() {
        Task task = Task.parse("Send an astronaut to the Moon before the end of the decade and bring him back alive");

        assertFalse(task.isComplete());
        assertNull(task.getPriority());
        assertNull(task.getCompletionDate());
        assertNull(task.getCreationDate());
        assertEquals(
            "Send an astronaut to the Moon before the end of the decade and bring him back alive",
            task.getDescription()
        );
    }

    @Test
    void parsePrioDesc() {
        Task task = Task.parse("(A) Send an astronaut to the Moon before the end of the decade and bring him back alive");

        assertFalse(task.isComplete());
        assertEquals(Priority.of('A'), task.getPriority());
        assertNull(task.getCompletionDate());
        assertNull(task.getCreationDate());
        assertEquals(
            "Send an astronaut to the Moon before the end of the decade and bring him back alive",
            task.getDescription()
        );
    }

    @Test
    void parseCompleteDesc() {
        Task task = Task.parse("x 1969-07-24 Send an astronaut to the Moon before the end of the decade and bring him back alive");

        assertTrue(task.isComplete());
        assertNull(task.getPriority());
        assertEquals(LocalDate.of(1969, Month.JULY, 24), task.getCompletionDate());
        assertNull(task.getCreationDate());
        assertEquals(
            "Send an astronaut to the Moon before the end of the decade and bring him back alive",
            task.getDescription()
        );
    }

    @Test
    void parseCompleteCreationDateDesc() {
        Task task = Task.parse("x 1969-07-24 1962-09-12 Send an astronaut to the Moon before the end of the decade and bring him back alive");

        assertTrue(task.isComplete());
        assertNull(task.getPriority());
        assertEquals(LocalDate.of(1969, Month.JULY, 24), task.getCompletionDate());
        assertEquals(LocalDate.of(1962, Month.SEPTEMBER, 12), task.getCreationDate());
        assertEquals(
            "Send an astronaut to the Moon before the end of the decade and bring him back alive",
            task.getDescription()
        );
    }

    @Test
    void parseSingleDateDesc() {
        Task task = Task.parse("1962-09-12 Send an astronaut to the Moon before the end of the decade and bring him back alive");

        assertFalse(task.isComplete());
        assertNull(task.getPriority());
        assertNull(task.getCompletionDate());
        assertEquals(LocalDate.of(1962, Month.SEPTEMBER, 12), task.getCreationDate());
        assertEquals(
            "Send an astronaut to the Moon before the end of the decade and bring him back alive",
            task.getDescription()
        );
    }

    @Test
    void parsePrioSingleDateDesc() {
        Task task = Task.parse("(A) 1962-09-12 Send an astronaut to the Moon before the end of the decade and bring him back alive");

        assertFalse(task.isComplete());
        assertEquals(Priority.of('A'), task.getPriority());
        assertNull(task.getCompletionDate());
        assertEquals(LocalDate.of(1962, Month.SEPTEMBER, 12), task.getCreationDate());
        assertEquals(
            "Send an astronaut to the Moon before the end of the decade and bring him back alive",
            task.getDescription()
        );
    }
}
