package net.liteskript.pattern;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.ElementCompiler;
import net.liteskript.pattern.compilation.Task;
import net.liteskript.pattern.compilation.element.GroupElement;
import net.liteskript.pattern.compilation.element.LiteralElement;
import net.liteskript.pattern.compilation.element.OptionalElement;
import net.liteskript.pattern.compilation.element.RegexElement;

public class PatternCompiler {

    private static int getNextBracket(final char[] patternChars, final int index, final char closingBracket, final char openingBracket) {
        int ignore;
        char currentChar;


        if (patternChars == null || index < 0 || index >= patternChars.length)
            return -1;
        ignore = 0;
        for (int i = index; i < patternChars.length; i++) {
            currentChar = patternChars[i];
            if (currentChar == '\\') {
                ++i;
                continue;
            }
            if (currentChar == openingBracket)
                ++ignore;
            else if (currentChar == closingBracket) {
                if (--ignore < 0)
                    return i;
            }
        }
        return -1;
    }

    private static void compileLiteral(final CompileContext context, final int startIndex, final int stopIndex) {
        final String literal;

        if (startIndex < 0 || stopIndex <= startIndex)
            return;
        literal = context.literalBuilder.toString();
        context.literalBuilder.setLength(0);
        if (literal.isEmpty())
            return;
        context.elements.add(new LiteralElement(literal, startIndex,  stopIndex - startIndex));
        context.literalBuilder.setLength(0);
    }

    public static void compileTask(final CompileContext context) throws MalformedPatternException {
        final Task task = context.tasks.remove();
        int nextIndex;
        char currentChar;

        nextIndex = task.startIndex;
        for (int i = nextIndex; i < task.endIndex; i++) {
            currentChar = context.patternChars[i];
            switch (currentChar) {
                case '[':
                    compileLiteral(context, nextIndex, i);
                    nextIndex = getNextBracket(context.patternChars, i + 1, ']', currentChar);
                    if (nextIndex == -1)
                        throw new MalformedPatternException(context.patternStr, "Missing closing bracket ']'");
                    context.elements.add(new OptionalElement(i, nextIndex - i - 1));
                    context.tasks.add(new Task(i + 1, nextIndex));
                    i = nextIndex;
                    break;
                case '(':
                    compileLiteral(context, nextIndex, i);
                    nextIndex = getNextBracket(context.patternChars, i + 1, ')', currentChar);
                    if (nextIndex == -1)
                        throw new MalformedPatternException(context.patternStr, "Missing closing bracket ')'");
                    context.elements.add(new GroupElement(i, nextIndex - i - 1));
                    context.tasks.add(new Task(i + 1, nextIndex));
                    i = nextIndex;
                    break;
                case '<':
                    compileLiteral(context, nextIndex, i);
                    nextIndex = getNextBracket(context.patternChars, i + 1, '>', currentChar);
                    if (nextIndex == -1)
                        throw new MalformedPatternException(context.patternStr, "Missing closing bracket '>'");
                    context.elements.add(new RegexElement(i, nextIndex - i - 1));
                    context.tasks.add(new Task(i + 1, nextIndex));
                    i = nextIndex;
                    break;
                case '|':
                    // TODO: Found solution for choice element.
                    break;
                case ']':
                case ')':
                case '>':
                    throw new MalformedPatternException(context.patternStr, "Unexpected closing bracket `" + currentChar + "' at " + i);
                case '\\':
                    currentChar = context.patternChars[++i];
                default:
                    context.literalBuilder.append(currentChar);
            }
        }
        compileLiteral(context, nextIndex, task.endIndex);
    }

    public static LiteSkriptPattern compile(final String pattern) throws MalformedPatternException {
        final CompileContext context;

        if (pattern == null)
            return null;
        context = new CompileContext(pattern);
        context.tasks.add(new Task(0, context.patternChars.length));
        while (!context.tasks.isEmpty())
            compileTask(context);
        return ElementCompiler.compile(context);
    }

}