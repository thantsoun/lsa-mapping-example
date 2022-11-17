package exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomException extends Exception {

    private final CustomExceptionObject customExceptionObject;

    @JsonCreator
    public CustomException(@JsonProperty("message") String message, @JsonProperty("customExceptionObject") CustomExceptionObject customExceptionObject) {
        super(message);
        this.customExceptionObject = customExceptionObject;
    }

    public CustomExceptionObject getCustomExceptionObject() {
        return customExceptionObject;
    }

    public static class CustomExceptionInternal extends Exception {

        public CustomExceptionInternal(String message, Throwable cause) {
            super(message, cause);
        }

        public CustomExceptionInternal(String message) {
            super(message);
        }


    }

}
