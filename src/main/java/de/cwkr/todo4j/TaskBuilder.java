package de.cwkr.todo4j;

import java.time.LocalDate;

public class TaskBuilder {
    private boolean done;
    private Priority priority;
    private LocalDate completionDate;
    private LocalDate creationDate;
    private String description;

    public TaskBuilder() {
    }

    TaskBuilder(boolean done, Priority priority, LocalDate completionDate, LocalDate creationDate, String description) {
        this.done = done;
        this.priority = priority;
        this.completionDate = completionDate;
        this.creationDate = creationDate;
        this.description = description;
    }

    public TaskBuilder done(boolean done) {
        this.done = done;
        return this;
    }

    public TaskBuilder priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder priority(char ch) {
        this.priority = Priority.of(ch);
        return this;
    }

    public TaskBuilder priority(String str) {
        this.priority = Priority.of(str);
        return this;
    }

    public TaskBuilder completionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public TaskBuilder creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Task build() {
        return new Task(done, priority, completionDate, creationDate, description);
    }
}
