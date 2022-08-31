package cern.lsa.mapping.example.TestEnums.json;

import cern.lsa.mapping.example.TestEnums.domain.Named;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class EnumDeserializer extends StdDeserializer<Enum<?>> {

    public EnumDeserializer(Class<? extends Enum<?>> clazz) {
        super(clazz);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Enum<?> deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = parser.readValueAsTree();
        if (TextNode.class.isAssignableFrom(treeNode.getClass())) {
            throw new EnumsInJsonException("Cannot parse simple String '" +((TextNode) treeNode).asText() + "' into enum member of the class " + handledType().getCanonicalName());
        }
        String name = ((TextNode) treeNode.get("name")).asText();
        try {
            Enum[] values = (Enum[]) handledType().getEnumConstants();
            return Arrays.stream(values) //
                    .filter(v -> name.equals(((Named)v).getName())) //
                    .findFirst() //
                    .orElseThrow(() -> new EnumsInJsonException("Could not construct enum of class: " + handledType().getCanonicalName() + " for name '" + name + "'. Unknown name!"));
        } catch (Exception e) {
            throw new EnumsInJsonException("Could not construct enum of class: " + handledType().getCanonicalName() + " for name '" + name + "'", e);
        }
    }
}
