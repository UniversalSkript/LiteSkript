package net.liteskript.pattern.compilation;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class CompileContext {

    public final String patternStr;
    public final char[] patternChars;
    public final LinkedList<String> literals;
    public final LinkedList<Pattern> regex;
    public final LinkedList<Task> tasks;
    public final LinkedList<Element> elements;
    public final StringBuilder literalBuilder;

    public CompileContext(final String patternStr) {
        this.patternStr = patternStr;
        this.patternChars = patternStr.toCharArray();
        this.literals = new LinkedList<String>();
        this.regex = new LinkedList<Pattern>();
        this.tasks = new LinkedList<Task>();
        this.elements = new LinkedList<Element>();
        this.literalBuilder = new StringBuilder();
    }

    public int startByteCodeIndex(final Element targetElement) {
        final int index;
        final List<Element> elements;
        int total;

        if (targetElement == null)
            return -1;
        index = this.elements.indexOf(targetElement);
        if (index < 0)
            return -1;
        else if (index < 1)
            return 0;
        elements = this.elements.subList(0, index - 1);
        total = 0;
        for (Element element : elements)
            total += element.type.instructionLength;
        return total;
    }

}
