import com.fasterxml.jackson.databind.util.StdConverter;

public class BooleanWithNameConverter extends StdConverter<BooleanWithName, BooleanWithNameDto> {

    @Override
    public BooleanWithNameDto convert(BooleanWithName obj) {
        return new BooleanWithNameDto(obj.getName(), obj.getValue());
    }
}