package net.liteskript.pattern.compilation.element;

import net.liteskript.pattern.Instructions;

public class OptionalElement extends CompoundElement {

    public OptionalElement(int startIndex, int length) {
        super(Instructions.OPTIONAL, startIndex, length);
    }

}
