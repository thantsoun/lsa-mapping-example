import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    public void testConverter() throws JsonProcessingException {
        BooleanWithName booleanWithName = new BooleanWithName("dummy", true);
        ObjectMapper objectMapper = new ObjectMapper();
        BooleanWithName[] value = { booleanWithName, booleanWithName };
        BooleanWithName[] readValue = objectMapper.readValue(objectMapper.writeValueAsString(value), BooleanWithName[].class);
        Assertions.assertEquals(readValue[0], readValue[1]);
    }
}
