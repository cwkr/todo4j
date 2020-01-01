package de.cwkr.todo4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void ofDescription() {
        Task task = Task.of("Send a men into space");

        assertFalse(task.isDone());
        assertEquals("Send a men into space", task.getDescription());
        assertNull(task.getCompletionDate());
        assertNull(task.getCreationDate());
        assertNull(task.getPriority());
    }

    @Test
    void toBuilder() {
        Task task = Task.builder()
                        .done(false)
                        .priority('A')
                        .completionDate(LocalDate.of(1969, Month.DECEMBER, 31))
                        .creationDate(LocalDate.of(1962, Month.SEPTEMBER, 12))
                        .description("Send an astronaut to the Moon before the end of the decade and bring him back alive")
                        .build();

        Task doneTask = task.toBuilder()
                            .done(true)
                            .build();

        assertFalse(task.isDone());
        assertTrue(doneTask.isDone());
        assertEquals(task, doneTask);
    }

    @Test
    void toStringUsingBuilder() {
        Task task = Task.builder()
                        .done(true)
                        .priority("a")
                        .completionDate(LocalDate.of(1969, Month.DECEMBER, 31))
                        .creationDate(LocalDate.of(1962, Month.SEPTEMBER, 12))
                        .description("Send an astronaut to the Moon before the end of the decade and bring him back alive")
                        .build();

        String str = task.toString();

        assertEquals(
            "x (A) 1969-12-31 1962-09-12 Send an astronaut to the Moon before the end of the decade and bring him back alive",
            str
        );
    }
}
