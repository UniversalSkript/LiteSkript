package net.liteskript.pattern.compilation;

import net.liteskript.pattern.Instructions;
import net.liteskript.pattern.compilation.element.CompoundElement;

import java.nio.ByteBuffer;

public class ChoiceElement extends CompoundElement {

    public ChoiceElement(final int startIndex, final int length) {
        super(Instructions.CHOICE, startIndex, length);
    }

    @Override
    public void compileCompound(CompileContext context, int compoundByteCount, ByteBuffer writer) {
        writer.put(this.type.opCode);
        writer.putShort((short) (writer.position() + compoundByteCount + 5));
    }

}
