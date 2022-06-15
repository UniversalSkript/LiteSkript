package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.compilation.CompileContext;
import net.liteskript.pattern.compilation.Element;
import net.liteskript.pattern.compilation.Instructions;

import java.nio.ByteBuffer;
import java.util.List;

public abstract class CompoundElement extends Element {

    public CompoundElement(final Instructions type, final int startIndex, final int length) {
        super(type, startIndex, length);
    }

    public abstract void compileCompound(final CompileContext context, final int compoundByteCount, final ByteBuffer writer);

    @Override
    public void compile(CompileContext context, List<Element> insideElements, ByteBuffer writer) {
        compileCompound(context, countByte(insideElements), writer);
    }

    protected static int countByte(final List<Element> insideElement) {
        int total;

        if (insideElement == null || insideElement.isEmpty())
            return 0;
        total = 0;
        for (Element element : insideElement)
            total += element.type.instructionLength;
        return total;
    }
}
