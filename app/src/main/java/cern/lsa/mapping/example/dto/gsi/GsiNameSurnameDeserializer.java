package cern.lsa.mapping.example.dto.gsi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class GsiNameSurnameDeserializer extends StdDeserializer<GsiNameSurname> {

    protected GsiNameSurnameDeserializer() {
        super(GsiNameSurname.class);
    }

    @Override
    public GsiNameSurname deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = p.readValueAsTree();
        return GsiNameSurname.valueOf(jsonNode.get("value").asText());
    }

}
