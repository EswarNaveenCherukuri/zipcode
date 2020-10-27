package exception;

public class ZipcodeException extends RuntimeException{

	private static final long serialVersionUID = -2502366629314955818L;

    protected final String value;

    public ZipcodeException(final String message, final String value) {
        super(message);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
