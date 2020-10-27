package exception;

public class FormatExceptionSubstring extends FormatException {
    private static final long serialVersionUID = -6448896951147010496L;

    protected final int positionStart;
    protected final int positionEnd;
    protected final String expectedValue;

    public FormatExceptionSubstring(final String message, final String value,
                                    final int positionStart) {
        this(message, value, positionStart, positionStart + 1);
    }

    public FormatExceptionSubstring(final String message, final String value,
                                    final int positionStart, final int positionEnd) {
        this(message, value, positionStart, positionEnd, null);
    }

    public FormatExceptionSubstring(final String message, final String value,
                                    final int positionStart, final int positionEnd,
                                    final String expectedValue) {
        super(message, value);
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
        this.expectedValue = expectedValue;
    }

    public int getPositionStart() {
        return positionStart;
    }

    public int getPositionEnd() {
        return positionEnd;
    }

    @Override
    public String getMessage() {
        String expectedValueString = "";
        if (expectedValue != null) {
            expectedValueString =
                    "  The expected value for that range is \"" + expectedValue + "\".";
        }

        String valueString;
        if (value == null) {
            valueString = "  The value is null.";
        } else {
            if (positionStart == positionEnd - 1) {
                valueString = "  The error is at character " + (positionStart + 1) + ",";
            } else {
                valueString = "  The error is between characters " + (positionStart + 1) + " and " +
                              positionEnd + ",";
            }

            try {
                valueString += " which is \"" + value.substring(positionStart, positionEnd) + "\".";
            } catch (final Exception exception) {
                valueString += " which is out of range.";
            }

            valueString += expectedValueString + "  The full value is \"" + value + "\".";
        }

        return super.getMessage() + valueString;
    }
}
