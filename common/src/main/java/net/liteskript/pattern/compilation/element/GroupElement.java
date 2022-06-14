package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.Element;
import net.liteskript.pattern.compilation.ElementType;

import java.nio.ByteBuffer;
import java.util.List;

public class GroupElement extends Element {

    public GroupElement(final int startIndex, final int length) {
        super(ElementType.GROUP, startIndex, length);
    }

    @Override
    public void compile(final CompileContext context, final List<Element> insideElements, final ByteBuffer writer) {

    }

}
