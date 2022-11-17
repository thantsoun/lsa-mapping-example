import java.util.Arrays;
import java.util.stream.Collectors;

public class ParseExceptionsClasses {

    private static final String EXCEPTIONS =
            "accessDeniedException\n" +
                    "ArchiveException\n" +
                    "ArrayIndexOutOfBoundsException\n" +
                    "AuthenticationException\n" +
                    "AuthorizationException\n" +
                    "BadLocationException\n" +
                    "BeansException\n" +
                    "CalculationMakeruleRuntimeException\n" +
                    "CalibrationException\n" +
                    "CannotRedoException\n" +
                    "CannotUndoException\n" +
                    "ClassCastException\n" +
                    "ClassNotFoundException\n" +
                    "ClientException\n" +
                    "CloneNotSupportedException\n" +
                    "ConcurrencyFailureException\n" +
                    "conversionNotPossibleException\n" +
                    "convertToParameterException\n" +
                    "createCalibrationException\n" +
                    "CriticalParameterException\n" +
                    "DataAccessException\n" +
                    "DataIntegrityViolationException\n" +
                    "DataRetrievalFailureException\n" +
                    "DriveException\n" +
                    "executionException\n" +
                    "ExecutionException\n" +
                    "extractException\n" +
                    "FileNotFoundException\n" +
                    "GeneralSecurityException\n" +
                    "GenerationException\n" +
                    "getException\n" +
                    "IllegalAccessException\n" +
                    "IllegalArgumentException\n" +
                    "IllegalStateException\n" +
                    "IncaException\n" +
                    "IndexOutOfBoundsException\n" +
                    "InfoFactoryValidationException\n" +
                    "InterruptedException\n" +
                    "InvalidClassException\n" +
                    "InvalidDataAccessApiUsageException\n" +
                    "InvalidEventException\n" +
                    "InvalidEventTableEntryException\n" +
                    "InvalidEventTableException\n" +
                    "InvocationTargetException\n" +
                    "IOException\n" +
                    "JapcCacheException\n" +
                    "JAXBException\n" +
                    "JdbcUpdateAffectedIncorrectNumberOfRowsException\n" +
                    "JMadException\n" +
                    "JSONException\n" +
                    "JsonProcessingException\n" +
                    "knobAccessedException\n" +
                    "lastException\n" +
                    "LifecycleException\n" +
                    "loaderException\n" +
                    "LoginException\n" +
                    "LowLevelTimingException\n" +
                    "LsaAccessDeniedException\n" +
                    "MathIllegalArgumentException\n" +
                    "McsParseConfigFileException\n" +
                    "newException\n" +
                    "NoSuchEventTableException\n" +
                    "NoSuchEventTypeException\n" +
                    "NoSuchFieldException\n" +
                    "NoSuchMethodException\n" +
                    "NotImplementedException\n" +
                    "NoValueException\n" +
                    "NullPointerException\n" +
                    "NumberFormatException\n" +
                    "nxCalsException\n" +
                    "ObjectStreamException\n" +
                    "outOfBoundsException\n" +
                    "parameterException\n" +
                    "ParameterException\n" +
                    "ParameterNotFoundException\n" +
                    "paramOrException\n" +
                    "ParmeterException\n" +
                    "ParseException\n" +
                    "ReferenceException\n" +
                    "RemoteAccessException\n" +
                    "RemoteException\n" +
                    "RemoteLookupFailureException\n" +
                    "resolutionException\n" +
                    "RuntimeException\n" +
                    "ServletException\n" +
                    "SQLException\n" +
                    "TaskException\n" +
                    "TgmException\n" +
                    "TgmIllegalParameterException\n" +
                    "TgmIOErrorException\n" +
                    "TgmNoLongerValidException\n" +
                    "TimeoutException\n" +
                    "toRuntimeException\n" +
                    "translateException\n" +
                    "TrimException\n" +
                    "TrimSimulationException\n" +
                    "UnprocessableEntityException\n" +
                    "UnsupportedFlavorException\n" +
                    "UnsupportedOperationException\n" +
                    "ValidationException\n" +
                    "ValueRetrievalException";

    private static final String START = "@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, defaultImpl = GenericServerError.class)\n" +
            "@JsonSubTypes({\n";
    private static final String END = "})";

    public static void main(String[] args) {
        String[] exceptions = EXCEPTIONS.split("\n");
        StringBuilder result = new StringBuilder(START);
        Arrays.stream(exceptions)
                .map(ParseExceptionsClasses::firstCharacterUpperCase)
                .distinct()
                .map(ParseExceptionsClasses::getClassnameAndAlias)
                .forEach(pair -> result.append(getJacksonAnnotationForException(pair)));
        result.append(END);
        System.out.println(result.toString());
    }

    private static String firstCharacterUpperCase(String s) {
        char firstCharInUpperCase = Character.toUpperCase(s.charAt(0));
        StringBuilder result = new StringBuilder(s);
        result.setCharAt(0, firstCharInUpperCase);
        return result.toString();
    }

    private static String[] getClassnameAndAlias(String className) {
        String[] classNameComponents = className.split("(?=\\p{Lu})");
        String alias = Arrays.stream(classNameComponents)
                .map(String::toLowerCase)
                .collect(Collectors.joining("_"));
        return new String[] { className, alias };
    }

    private static String getJacksonAnnotationForException(String[] classNameAndAlias) {
        return "        @JsonSubTypes.Type(value = " + classNameAndAlias[0] + ".class, name = \"" + classNameAndAlias[1] + "\"),\n";
    }
}
