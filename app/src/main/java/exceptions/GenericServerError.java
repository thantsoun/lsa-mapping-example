package exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static exceptions.ThrowableMixIn.EXCEPTION_CLASS;

public class GenericServerError extends Throwable {

    private final String originalExceptionClassname;

    @JsonCreator
    public GenericServerError(@JsonProperty("message") String message, @JsonProperty("cause") Throwable cause, @JsonProperty(EXCEPTION_CLASS) String originalExceptionClassname) {
        super(message, cause);
        this.originalExceptionClassname = originalExceptionClassname;
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        return (message != null) ? (originalExceptionClassname + ": " + message) : originalExceptionClassname;
    }
}
