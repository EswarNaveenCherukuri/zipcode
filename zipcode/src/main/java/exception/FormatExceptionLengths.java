package exception;

public class FormatExceptionLengths extends FormatException{

	private static final long serialVersionUID = -544950478852671875L;

    protected final int[] validLengths;

    public FormatExceptionLengths(final String message, final String value,
                                  final int[] validLengths) {
        super(message, value);
        this.validLengths = validLengths;
    }

    @Override
    public String getMessage() {
        String validLengthsString;
        if (validLengths.length == 1) {
            validLengthsString = "  Valid length: " + validLengths[0] + ".";
        } else if (validLengths.length == 2) {
            validLengthsString =
                    "  Valid lengths: " + validLengths[0] + " and " + validLengths[1] + ".";
        } else {
            validLengthsString = "  Valid lengths: " + validLengths[0];
            for (int validLengthsIndex = 1;
                 validLengthsIndex < validLengths.length; validLengthsIndex++) {
                validLengthsString +=
                        ", " + (validLengthsIndex == validLengths.length - 1 ? "and " : "") +
                        validLengths[validLengthsIndex];
            }
            validLengthsString += ".";
        }

        final String valueString;
        if (value == null) {
            valueString = "  The value is null.";
        } else {
            valueString = "  The value is " + value.length() + " character" +
                          (value.length() == 1 ? "" : "s") + " long.  The value is \"" + value +
                          "\".";
        }

        return super.getMessage() + validLengthsString + valueString;
    }

}
