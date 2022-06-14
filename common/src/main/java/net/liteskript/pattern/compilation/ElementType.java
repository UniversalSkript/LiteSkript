package net.liteskript.pattern.compilation;

public enum ElementType {

    GROUP((byte) 0x0, 0),
    LITERAL((byte) 0x1),
    OPTIONAL((byte) 0x2),
    CHOICE((byte) 0x3),
    EXPRESSION_TYPE((byte) 0x4),
    TAG((byte) 0x5),
    REGEX((byte) 0x6);

    public final byte opCode;
    public final int instructionLength;

    ElementType(final byte opCode, int instructionLength) {
        this.opCode = opCode;
        this.instructionLength = instructionLength;
    }

    ElementType(final byte opCode) {
        this.opCode = opCode;
        this.instructionLength = 3;
    }

    public static ElementType fromOpCode(final byte opCode) {
        for (ElementType patternElement : ElementType.values()) {
            if (patternElement.opCode == opCode)
                return patternElement;
        }
        return null;
    }
}
