package net.liteskript.pattern;

import java.util.regex.Pattern;

public class LiteSkriptPattern {

    private final char[][] literals;
    private final Pattern[] regex;
    private final byte[] compiledPattern;

    public LiteSkriptPattern(final byte[] compiledPattern, final char[][] literals, final Pattern[] regex) {
        this.compiledPattern = compiledPattern;
        this.literals = literals;
        this.regex = regex;
    }

    public boolean match(final String instruction) {
        // TODO: Create interpretation methods.
        return false;
    }

}
