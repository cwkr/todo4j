package de.cwkr.todo4j;

import de.cwkr.todo4j.util.Strings;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);
    private final boolean complete;
    private final Priority priority;
    private final LocalDate completionDate;
    private final LocalDate creationDate;
    private final String description;
    private static final Pattern TODOTXT_TODO_PATTERN = Pattern.compile(
        "^(\\((?<prio>[A-Z])\\) )?((?<cred>[0-9]{4}-[0-9]{2}-[0-9]{2}) )?(?<desc>.+)$"
    );

    private static final Pattern TODOTXT_DONE_PATTERN = Pattern.compile(
        "^x (?<comd>[0-9]{4}-[0-9]{2}-[0-9]{2}) ((?<cred>[0-9]{4}-[0-9]{2}-[0-9]{2}) )?(?<desc>.+)$"
    );

    Task(boolean complete, Priority priority, LocalDate completionDate, LocalDate creationDate, String description) {
        this.complete = complete;
        this.priority = complete ? null : priority;
        this.completionDate = complete ? Objects.requireNonNull(completionDate, "Completion date must not be null") : null;
        this.creationDate = creationDate;
        this.description = Strings.requireNonBlank(description, "Description must not be blank").trim();
    }

    public static Task of(String description) {
        return new Task(false, null, null, null, description);
    }

    /**
     * Obtains an instance of {@code Task} from a text string.
     *
     * @param text the text to parse, not null
     * @return the parsed {@code Task}, not null
     * @throws TaskParseException if the text cannot be parsed
     */
    public static Task parse(String text) {
        logger.trace("parse(text = {})", text);
        Strings.requireNonBlank(text, "String must not be blank");
        boolean complete;
        Matcher matcher;
        if (text.startsWith("x")) {
            logger.debug("parsing complete task");
            complete = true;
            matcher = TODOTXT_DONE_PATTERN.matcher(text);
        } else {
            logger.debug("parsing open task");
            complete = false;
            matcher = TODOTXT_TODO_PATTERN.matcher(text);
        }
        if (!matcher.find()) {
            throw new TaskParseException("Task string not parsable");
        }
        return builder().complete(complete)
                        .priority(complete ? null : matcher.group("prio"))
                        .completionDate(complete ? matcher.group("comd") : null)
                        .creationDate(matcher.group("cred"))
                        .description(matcher.group("desc"))
                        .build();
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public boolean isComplete() {
        return complete;
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
        return new TaskBuilder(complete, priority, completionDate, creationDate, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (complete) {
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
        return complete == task.complete &&
            Objects.equals(priority, task.priority) &&
            Objects.equals(completionDate, task.completionDate) &&
            Objects.equals(creationDate, task.creationDate) &&
            description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(complete, priority, completionDate, creationDate, description);
    }
}
