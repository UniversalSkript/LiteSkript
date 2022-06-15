package net.liteskript.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPatternCompiler {

    @Test
    public void testLiteral() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("   HEllo WoRld     ");
        final byte[] byteCode = new byte[] {1, 0, 0};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(1, liteSkriptPattern.literals.length);
        assertArrayEquals("hello world".toCharArray(), liteSkriptPattern.literals[0]);
    }

    @Test
    public void testOptionalLiteral() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("   [  HEllo] the [WoRld]     ");
        final byte[] byteCode = new byte[] {2, 0, 6, 1, 0, 0, 1, 0, 1, 2, 0, 15, 1, 0, 2};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(3, liteSkriptPattern.literals.length);
        assertArrayEquals("hello".toCharArray(), liteSkriptPattern.literals[0]);
        assertArrayEquals("the".toCharArray(), liteSkriptPattern.literals[1]);
        assertArrayEquals("world".toCharArray(), liteSkriptPattern.literals[2]);
    }

    @Test
    public void testSameLiteral() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("   [  wOrld] the [WoRld]     ");
        final byte[] byteCode = new byte[] {2, 0, 6, 1, 0, 0, 1, 0, 1, 2, 0, 15, 1, 0, 0};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(2, liteSkriptPattern.literals.length);
        assertArrayEquals("world".toCharArray(), liteSkriptPattern.literals[0]);
        assertArrayEquals("the".toCharArray(), liteSkriptPattern.literals[1]);
    }

    @Test
    public void testGroup() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("(((((   ([  HEllo] (the)) ([WoRld])     )))))");
        final byte[] byteCode = new byte[] {2, 0, 6, 1, 0, 0, 1, 0, 1, 2, 0, 15, 1, 0, 2};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(3, liteSkriptPattern.literals.length);
        assertArrayEquals("hello".toCharArray(), liteSkriptPattern.literals[0]);
        assertArrayEquals("the".toCharArray(), liteSkriptPattern.literals[1]);
        assertArrayEquals("world".toCharArray(), liteSkriptPattern.literals[2]);
    }

    @Test
    public void testUnexpectedClosingOptional() {
        assertThrowsExactly(MalformedPatternException.class, () -> PatternCompiler.compile("[hello] [the] [world]]"));
    }

    @Test
    public void testMissingClosingOptional() {
        assertThrowsExactly(MalformedPatternException.class, () -> PatternCompiler.compile("[hello] [the [world]"));
    }

    @Test
    public void testUnexpectedClosingGroup() {
        assertThrowsExactly(MalformedPatternException.class, () -> PatternCompiler.compile("(((((   ([  HEllo] (the))) ([WoRld])     )))))"));
    }

    @Test
    public void testMissingClosingGroup() {
        assertThrowsExactly(MalformedPatternException.class, () -> PatternCompiler.compile("(((((   ([  HEllo] (the) ([WoRld])     )))))"));
    }

    @Test
    public void testIgnoreChar() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("(((((   ([  HEllo] (the))\\) ([WoRld])     )))))");
        final byte[] byteCode = new byte[] {2, 0, 6, 1, 0, 0, 1, 0, 1, 1, 0, 2, 2, 0, 18, 1, 0, 3};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(4, liteSkriptPattern.literals.length);
        assertArrayEquals("hello".toCharArray(), liteSkriptPattern.literals[0]);
        assertArrayEquals("the".toCharArray(), liteSkriptPattern.literals[1]);
        assertArrayEquals(")".toCharArray(), liteSkriptPattern.literals[2]);
        assertArrayEquals("world".toCharArray(), liteSkriptPattern.literals[3]);
    }


    @Test
    public void testRegex() {
        final LiteSkriptPattern liteSkriptPattern = PatternCompiler.compile("<.*>");
        final byte[] byteCode = new byte[] {6, 0, 0};

        assertArrayEquals(byteCode, liteSkriptPattern.compiledPattern);
        assertEquals(1, liteSkriptPattern.regex.length);
        assertEquals(".*", liteSkriptPattern.regex[0].toString());
    }

    @Test
    public void testUnexpectedClosingRegex() {
        assertThrowsExactly(MalformedPatternException.class, () -> PatternCompiler.compile("<.*>>"));
    }

    @Test
    public void testMissingClosingRegex() {
        assertThrowsExactly(MalformedPatternException.class, () -> PatternCompiler.compile("<.*"));
    }

}
