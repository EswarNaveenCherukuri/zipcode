package exception;

public class FormatExceptionRange extends FormatException {
    private static final long serialVersionUID = -6486229222203685224L;

    protected final long valueLong;
    protected final long minimum;
    protected final long maximum;

    public FormatExceptionRange(final String message, final long value, final long minimum,
                                final long maximum) {
        super(message, Long.toString(value));
        this.valueLong = value;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public String getMessage() {
        String rangeError = "";
        if (valueLong > maximum) {
            rangeError = "  The value exceeds the maximum range by " + (maximum - valueLong) + ".";
        } else if (valueLong < minimum) {
            rangeError = "  The value exceeds the minimum range by " + (minimum - valueLong) + ".";
        }

        return super.getMessage() + "  Valid range: " + minimum + " to " + maximum + "." +
               rangeError + "  The value is \"" + value + "\".";
    }
}
