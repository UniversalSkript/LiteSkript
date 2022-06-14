package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.Element;
import net.liteskript.pattern.compilation.ElementType;

import java.nio.ByteBuffer;
import java.util.List;

public class LiteralElement extends Element {

    private final String literal;

    public LiteralElement(final String literal, final int startIndex, final int length) {
        super(ElementType.LITERAL, startIndex, length);
        if (literal != null)
            this.literal = literal.toLowerCase().replaceAll("\t", "").trim();
        else
            this.literal = null;
    }

    @Override
    public void compile(final CompileContext context, final List<Element> insideElements, final ByteBuffer writer) {
        int index;

        if (this.literal.isEmpty())
            return;
        index = context.literals.indexOf(this.literal);
        if (index < 0) {
            index = context.literals.size();
            context.literals.add(this.literal);
        }
        writer.put(this.type.opCode);
        writer.putShort((short) index);
    }

}
