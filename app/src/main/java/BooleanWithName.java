import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(converter = BooleanWithNameConverter.class)
@JsonDeserialize(converter = BooleanWithNameDtoConverter.class)
public class BooleanWithName {

    private final boolean value;
    private final String name;

    BooleanWithName(String name, boolean value) {
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
