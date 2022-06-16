package net.liteskript.pattern.compilation;

import net.liteskript.pattern.Instructions;
import net.liteskript.pattern.compilation.element.CompoundElement;

import java.nio.ByteBuffer;
import java.util.List;

public abstract class Element {

    public final Instructions type;
    public final int startIndex;
    public final int length;

    public Element(final Instructions type, final int startIndex, final int length) {
        this.type = type;
        this.startIndex = startIndex;
        this.length = length;
    }

    protected boolean isInsideCompound(final Element compoundElement) {
        return this.startIndex >= compoundElement.startIndex && this.startIndex + this.length <= compoundElement.startIndex + compoundElement.length + 1;
    }

    public abstract void compile(final CompileContext context, final List<Element> insideElements, final ByteBuffer writer);

    public static int compare(final Element element1, final Element element2) {
        if (element1.startIndex < element2.startIndex)
            return -1;
        if (element1.startIndex > element2.startIndex)
            return 1;
        if (element1.length > element2.length)
            return -1;
        if (element1.length < element2.length)
            return 1;
        if (element1 instanceof CompoundElement && !(element2 instanceof CompoundElement))
            return -1;
        if (!(element1 instanceof CompoundElement) && element2 instanceof CompoundElement)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + this.startIndex + ", " + this.length + ")";
    }

}
