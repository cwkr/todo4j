package de.cwkr.todo4j;

import de.cwkr.todo4j.util.Strings;
import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private final boolean done;
    private final Priority priority;
    private final LocalDate completionDate;
    private final LocalDate creationDate;
    private final String description;

    Task(boolean done, Priority priority, LocalDate completionDate, LocalDate creationDate, String description) {
        this.done = done;
        this.priority = priority;
        this.completionDate = completionDate;
        this.creationDate = creationDate;
        this.description = Strings.requireNonBlank(description, "Description must not be blank");
    }

    public static Task of(String description) {
        return new Task(false, null, null, null, description);
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public boolean isDone() {
        return done;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public TaskBuilder toBuilder() {
        return new TaskBuilder(done, priority, completionDate, creationDate, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (done) {
            sb.append('x').append(' ');
        }
        if (priority != null) {
            sb.append(priority.toString()).append(' ');
        }
        if (completionDate != null) {
            sb.append(completionDate.toString()).append(' ');
        }
        if (creationDate != null) {
            sb.append(creationDate.toString()).append(' ');
        }
        sb.append(description);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(priority, task.priority) &&
            Objects.equals(completionDate, task.completionDate) &&
            Objects.equals(creationDate, task.creationDate) &&
            description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, completionDate, creationDate, description);
    }
}
