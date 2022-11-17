package exceptions;

@FunctionalInterface
public interface CheckedFunction {
    void apply() throws Exception;
}
