package net.liteskript.pattern;

import java.util.regex.Pattern;

public class LiteSkriptPattern {

    protected final char[][] literals;
    protected final Pattern[] regex;
    protected final byte[] compiledPattern;

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
