package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.Instructions;

public class JumpElement extends CompoundElement {

    public JumpElement(final int startIndex, final int length) {
        super(Instructions.JUMP, startIndex, length);
    }

}
