package exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;

public class TestJacksonExceptions {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.setMixInAnnotation(Throwable.class, ThrowableMixIn.class);
        objectMapper.registerModule(simpleModule);
        callMethod(TestJacksonExceptions::callMethodThatThrowsMappedException, "mapped");
        callMethod(TestJacksonExceptions::callMethodThatThrowsUnmappedException, "unmapped_cause");
        callMethod(TestJacksonExceptions::callMethodThatThrowsUnmappedError, "unmapped_all");
        callMethod(TestJacksonExceptions::callMethodThatThrowsCustomException, "custom_exception_with_object");
        callMethod(TestJacksonExceptions::callMethodThatThrowsCustomExceptionInternal, "custom_exception_internal");
        readTestFile("test_exception");
    }

    private static void callMethod(CheckedFunction method, String filename) {
        try {
            method.apply();
        } catch (Throwable throwable) {
            try {
                String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(throwable);
                writeJsonToFile(filename + ".json", json);
                writeExceptionToFile(filename, throwable, objectMapper.readerFor(Throwable.class).readValue(json));
            } catch (IOException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
    }

    private static void readTestFile(String filename) {
        try {
            Throwable throwable = objectMapper.readerFor(Throwable.class).readValue(new File(filename + ".json"));
            writeExceptionToFile(filename, throwable, throwable);
        } catch (IOException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
    }

    private static void callMethodThatThrowsMappedException() throws IOException {
        try {
            methodThrowMappedException();
        } catch (Exception e) {
            throw new IOException("I/O", e);
        }
    }

    private static void callMethodThatThrowsCustomExceptionInternal() throws CustomException.CustomExceptionInternal {
        try {
            methodThrowMappedException();
        } catch (Exception e) {
            throw new CustomException.CustomExceptionInternal("Custom exception internal", e);
        }
    }

    private static void callMethodThatThrowsCustomException() throws CustomException {
        try {
            callMethodThatThrowsCustomException2();
        } catch (Exception e) {
            CustomException customException = new CustomException("Custom exception", new CustomExceptionObject("Thanos", "Tsounis"));
            customException.initCause(e);
            throw customException;
        }
    }

    private static void callMethodThatThrowsCustomException2() throws CustomException {
        throw  new CustomException("Custom exception", new CustomExceptionObject("Roman", "Gorbonosov"));
    }

    private static void callMethodThatThrowsUnmappedException() throws IOException {
        try {
            methodThrowUnmappedException();
        } catch (Exception e) {
            throw new IOException("I/O", e);
        }
    }

    private static void callMethodThatThrowsUnmappedError() {
        try {
            methodThrowUnmappedException();
        } catch (Exception e) {
            IllegalAccessError illegalAccessError = new IllegalAccessError("Some error");
            illegalAccessError.initCause(e);
            throw illegalAccessError;
        }
    }

    private static void methodThrowMappedException() {
        throw new IllegalArgumentException("Wrong argument");
    }

    private static void methodThrowUnmappedException() {
        throw new IllegalStateException("Wrong state");
    }

    private static void writeJsonToFile(String filename, String str) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeExceptionToFile(String filename, Throwable throwable, Throwable readThrowable) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename + ".exception"))) {
            writer.write("******* " + throwable.getClass().getName()  + " / " + throwable.getCause().getClass().getName() + " *******\n");
            readThrowable.printStackTrace(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
