package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.Element;
import net.liteskript.pattern.compilation.ElementType;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexElement extends Element {

    public RegexElement(final int startIndex, final int length) {
        super(ElementType.REGEX, startIndex, length);
    }

    @Override
    public void compile(final CompileContext context, final List<Element> insideElements, final ByteBuffer writer) {
        final String patternStr = new String(context.patternChars, this.startIndex + 1, this.length -1);
        final Pattern pattern;
        int index;

        if (patternStr.isEmpty())
            return;
        try {
            pattern = Pattern.compile(patternStr);
        } catch (PatternSyntaxException ignored) {
            return;
        }
        index = context.regex.indexOf(pattern);
        if (index < 0) {
            index = context.regex.size();
            context.regex.add(pattern);
        }
        writer.put(this.type.opCode);
        writer.putShort((short) index);
    }
}
