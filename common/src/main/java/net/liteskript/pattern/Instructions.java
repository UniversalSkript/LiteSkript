package net.liteskript.pattern;

public enum Instructions {

    GROUP((byte) 0x0, 0),
    LITERAL((byte) 0x1),
    OPTIONAL((byte) 0x2),
    CHOICE((byte) 0x3),
    EXPRESSION((byte) 0x4),
    TAG((byte) 0x5),
    REGEX((byte) 0x6),
    JUMP((byte) 0x7);

    public final byte opCode;
    public final int instructionLength;

    Instructions(final byte opCode, int instructionLength) {
        this.opCode = opCode;
        this.instructionLength = instructionLength;
    }

    Instructions(final byte opCode) {
        this.opCode = opCode;
        this.instructionLength = 3;
    }

    public static Instructions fromOpCode(final byte opCode) {
        for (Instructions patternElement : Instructions.values()) {
            if (patternElement.opCode == opCode)
                return patternElement;
        }
        return null;
    }
}
