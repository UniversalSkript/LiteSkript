package net.liteskript.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPatternCompiler {

    @Test
    public void testSimpleCompile() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("Hello [   WorlD    ]");
        final byte[] byteCode = new byte[] {1, 0, 0, 2, 0, 9, 1, 0, 1};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(2, liteSkriptPattern.literals.length);
        assertArrayEquals("hello".toCharArray(), liteSkriptPattern.literals[0]);
        assertArrayEquals("world".toCharArray(), liteSkriptPattern.literals[1]);
    }

}
