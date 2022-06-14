package net.liteskript.pattern.compilation;

import java.nio.ByteBuffer;
import java.util.List;

public abstract class Element {

    public final ElementType type;
    public final int startIndex;
    public final int length;

    public Element(final ElementType type, final int startIndex, final int length) {
        this.type = type;
        this.startIndex = startIndex;
        this.length = length;
    }

    protected boolean isInside(final Element element) {
        return this.startIndex >= element.startIndex && this.startIndex + this.length <= element.startIndex + element.length;
    }

    public abstract void compile(final CompileContext context, final List<Element> insideElements, final ByteBuffer writer);

    public static int compare(final Element element1, final Element element2) {
        if (element1.startIndex < element2.startIndex)
            return -1;
        if (element1.startIndex > element2.startIndex)
            return 1;
        return Integer.compare(element1.length, element2.length);
    }

}
