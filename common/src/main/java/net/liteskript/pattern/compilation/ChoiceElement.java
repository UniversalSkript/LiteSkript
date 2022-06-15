package net.liteskript.pattern.compilation;

import net.liteskript.pattern.Instructions;

import java.nio.ByteBuffer;
import java.util.List;

public class ChoiceElement extends Element {

    public ChoiceElement(final int startIndex, final int length) {
        super(Instructions.CHOICE, startIndex, length);
    }

    @Override
    public void compile(CompileContext context, List<Element> insideElements, ByteBuffer writer) {

    }
}
