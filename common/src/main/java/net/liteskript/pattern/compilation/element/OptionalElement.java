package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.Element;
import net.liteskript.pattern.Instructions;

import java.nio.ByteBuffer;
import java.util.List;

public class OptionalElement extends CompoundElement {

    public OptionalElement(int startIndex, int length) {
        super(Instructions.OPTIONAL, startIndex, length);
    }

    @Override
    public void compileCompound(final CompileContext context, final int compoundByteCount, final ByteBuffer writer) {
        writer.put(this.type.opCode);
        writer.putShort((short) (writer.position() + compoundByteCount + 2));
    }

    @Override
    public void compile(CompileContext context, List<Element> insideElements, ByteBuffer writer) {
        super.compile(context, insideElements, writer);
    }
}
