package net.liteskript.pattern.compilation;

import net.liteskript.pattern.LiteSkriptPattern;
import net.liteskript.pattern.compilation.element.CompoundElement;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ElementCompiler {

    private static ByteBuffer allocByteBuffer(final List<Element> elements) {
        int total = 0;

        for (Element element : elements)
            total += element.type.instructionLength;
        if (total < 1)
            return null;
        return ByteBuffer.allocate(total);
    }

    private static void fillElementInside(final Element compoundElement, final List<Element> insideElements, final List<Element> elements) {
        insideElements.clear();
        for (Element element : elements) {
            if (compoundElement != element && element.isInsideCompound(compoundElement))
                insideElements.add(element);
        }
        Collections.reverse(insideElements);
    }


    public static char[][] createLiterals(final List<String> literals) {
        final char[][] result;
        int index;

        if (literals.isEmpty())
            return null;
        result = new char[literals.size()][];
        index = 0;
        for (String literal : literals)
            result[index++] = literal.toCharArray();
        return result;
    }

    public static LiteSkriptPattern compile(final CompileContext context) {
        final ByteBuffer writer;
        final ArrayList<Element> insideElements;
        final LiteSkriptPattern liteSkriptPattern;

        if (context == null)
            return null;
        writer = allocByteBuffer(context.elements);
        if (writer == null)
            return null;
        context.elements.sort(Element::compare);
        insideElements = new ArrayList<Element>();
        for (Element element : context.elements) {
            if (element instanceof CompoundElement)
                fillElementInside(element, insideElements, context.elements);
            else
                insideElements.clear();
            element.compile(context, insideElements, writer);
        }
        return new LiteSkriptPattern(writer.array(), createLiterals(context.literals), context.regex.toArray(new Pattern[context.regex.size()]));
    }

}
