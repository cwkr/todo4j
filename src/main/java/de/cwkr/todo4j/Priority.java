package de.cwkr.todo4j;

import de.cwkr.todo4j.util.Strings;
import java.util.Objects;

public class Priority implements Comparable<Priority> {
    private final char value;

    Priority(char value) {
        if (!Character.isLetter(value)) {
            throw new IllegalArgumentException("Priority character must be a letter in range A-Z");
        }
        this.value = Character.toUpperCase(value);
    }

    public char getValue() {
        return value;
    }

    public static Priority of(char ch) {
        return new Priority(ch);
    }

    public static Priority of(String ch) {
        Strings.requireNonBlank(ch, "Priority string must not be blank");
        return new Priority(ch.trim().charAt(0));
    }

    @Override
    public String toString() {
        return "(" + value + ")";
    }

    @Override
    public int compareTo(Priority other) {
        return Character.compare(value, other.value) * -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Priority priority = (Priority) obj;
        return value == priority.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
