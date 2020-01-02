package de.cwkr.todo4j;

import de.cwkr.todo4j.util.Strings;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskBuilder {
    private boolean complete;
    private Priority priority;
    private LocalDate completionDate;
    private LocalDate creationDate;
    private String description;

    public TaskBuilder() {
    }

    TaskBuilder(boolean complete, Priority priority, LocalDate completionDate, LocalDate creationDate, String description) {
        this.complete = complete;
        this.priority = priority;
        this.completionDate = completionDate;
        this.creationDate = creationDate;
        this.description = description;
    }

    public TaskBuilder complete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public TaskBuilder priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder priority(char priority) {
        this.priority = Priority.of(priority);
        return this;
    }

    public TaskBuilder priority(String priority) {
        if(Strings.isBlank(priority)) {
            this.priority = null;
        } else {
            this.priority = Priority.of(priority);
        }
        return this;
    }

    public TaskBuilder completionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public TaskBuilder completionDate(String completionDate) {
        if(Strings.isBlank(completionDate)) {
            this.completionDate = null;
        } else {
            this.completionDate = LocalDate.parse(completionDate, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return this;
    }

    public TaskBuilder creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder creationDate(String creationDate) {
        if(Strings.isBlank(creationDate)) {
            this.creationDate = null;
        } else {
            this.creationDate = LocalDate.parse(creationDate, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Task build() {
        return new Task(complete, priority, completionDate, creationDate, description);
    }
}
