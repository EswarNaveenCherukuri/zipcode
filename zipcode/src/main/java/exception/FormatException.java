package exception;

public class FormatException extends RuntimeException {
    private static final long serialVersionUID = -2502366629314955818L;

    protected final String value;

    protected FormatException(final String message, final String value) {
        super(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
