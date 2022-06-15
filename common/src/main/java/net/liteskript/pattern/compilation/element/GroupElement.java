package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.Element;
import net.liteskript.pattern.Instructions;

import java.nio.ByteBuffer;
import java.util.List;

public class GroupElement extends CompoundElement {

    public GroupElement(final int startIndex, final int length) {
        super(Instructions.GROUP, startIndex, length);
    }

    @Override
    public void compileCompound(CompileContext context, int compoundByteCount, ByteBuffer writer) {

    }

    @Override
    public void compile(final CompileContext context, final List<Element> insideElements, final ByteBuffer writer) {

    }

}
