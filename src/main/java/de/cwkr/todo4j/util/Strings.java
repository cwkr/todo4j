package de.cwkr.todo4j.util;

public final class Strings {

    private Strings() {
    }

    /**
     * Checks if a CharSequence is empty ("") or null.
     *
     * @param seq the CharSequence to check, may be null
     * @return {@code true} if the String is empty or null
     */
    public static boolean isEmpty(final CharSequence seq) {
        return seq == null || seq.length() == 0;
    }

    /**
     * Checks if a CharSequence is empty (""), null or whitespace only. Whitespace is defined
     * by {@link Character#isWhitespace(char)}.
     *
     * @param seq the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     */
    public static boolean isBlank(final CharSequence seq) {
        if (isEmpty(seq)) {
            return true;
        }
        for (int i = 0; i < seq.length(); i++) {
            if (!Character.isWhitespace(seq.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that the specified CharSequence is not empty (""), null or whitespace only and
     * throws a customized {@link IllegalArgumentException} if it is.
     *
     * @param seq the CharSequence to check, may be null
     * @param message detail message to be used in the event that a {@code
     *                IllegalArgumentException} is thrown
     * @return {@code seq} if not blank
     * @throws IllegalArgumentException if {@code seq} is blank
     */
    public static <T extends CharSequence> T requireNonBlank(final T seq, final String message) {
        if (isBlank(seq)) {
            throw new IllegalArgumentException(message);
        }
        return seq;
    }
}
