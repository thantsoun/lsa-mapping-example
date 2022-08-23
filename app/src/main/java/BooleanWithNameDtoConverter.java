import com.fasterxml.jackson.databind.util.StdConverter;

public class BooleanWithNameDtoConverter extends StdConverter<BooleanWithNameDto, BooleanWithName> {

    @Override
    public BooleanWithName convert(BooleanWithNameDto obj) {
        return new BooleanWithName(obj.getName(), obj.getValue());
    }


}