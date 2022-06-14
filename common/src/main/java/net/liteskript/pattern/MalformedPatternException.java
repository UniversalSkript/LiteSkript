package net.liteskript.pattern;

public class MalformedPatternException extends IllegalArgumentException {

    public MalformedPatternException(final String pattern, final String message, final Throwable cause) {
        super(message + "[pattern: " + pattern + "]", cause);
    }

    public MalformedPatternException(final String pattern, final String message) {
        this(pattern, message, null);
    }

}
