package cern.lsa.mapping.example.dto.gsi;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;

public class GsiNameSurnameSerializer extends StdSerializer<GsiNameSurname> {

    public GsiNameSurnameSerializer() {
        super(GsiNameSurname.class);
    }

    @Override
    public void serialize(GsiNameSurname value, JsonGenerator gen, SerializerProvider serializers) { }

    @Override
    public void serializeWithType(GsiNameSurname value, JsonGenerator generator, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeId = typeSer.typeId(value, START_OBJECT);
        typeSer.writeTypePrefix(generator, typeId);
        generator.writeFieldName("value");
        generator.writeString(value.toString());
        typeSer.writeTypeSuffix(generator, typeId);
    }

}
