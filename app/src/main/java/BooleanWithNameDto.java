import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public class BooleanWithNameDto {

    private final boolean value;
    private final String name;

    @JsonCreator
    BooleanWithNameDto(@JsonProperty("name") String name, @JsonProperty("value") boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean getValue() {
        return value;
    }
}