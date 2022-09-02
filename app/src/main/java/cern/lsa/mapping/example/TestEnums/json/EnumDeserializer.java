package cern.lsa.mapping.example.TestEnums.json;

import cern.lsa.mapping.example.TestEnums.domain.Named;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class EnumDeserializer<T extends Enum<T> & Named> extends StdDeserializer<T> {

    private static final String NAME = "name";
    private static final String ID = "@id";

    private final SimpleObjectIdResolver simpleObjectIdResolver = new SimpleObjectIdResolver();
    private final ObjectIdGenerator<UUID> objectIdGenerator = new ObjectIdGenerators.UUIDGenerator();
    private final UUIDDeserializer uuidDeserializer = new UUIDDeserializer();
    private final T[] values;

    public EnumDeserializer(Class<T> clazz) {
        super(clazz);
        values = clazz.getEnumConstants();
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec objectCodec = parser.getCodec();
        TreeNode treeNode = parser.readValueAsTree();
        String name = ((TextNode)treeNode.get(NAME)).asText();
        UUID id = findId(treeNode, objectCodec, deserializationContext);
        try {
            T value = Arrays.stream(values) //
                    .filter(v -> Objects.equals(name, v.getName())) //
                    .findFirst() //
                    .orElseThrow(() -> new EnumsInJsonException("Could not construct enum of class: " + handledType().getCanonicalName() + " for name '" + name + "'. Unknown name!"));
            bindItemToId(deserializationContext, id, value);
            return value;
        } catch (Exception e) {
            throw new EnumsInJsonException("Could not construct enum of class: " + handledType().getCanonicalName() + " for name '" + name + "'", e);
        }
    }

    private UUID findId(TreeNode treeNode, ObjectCodec objectCodec, DeserializationContext deserializationContext) throws IOException {
        TreeNode idNode = treeNode.get(ID);
        if (Objects.isNull(idNode)) {
            return null;
        }
        JsonParser jsonParser = idNode.traverse(objectCodec);
        jsonParser.nextToken();
        return uuidDeserializer.deserialize(jsonParser, deserializationContext);
    }

    private void bindItemToId(DeserializationContext deserializationContext, UUID id, T value) throws IOException {
        if (Objects.isNull(id)) {
            return;
        }
        ReadableObjectId roid = deserializationContext.findObjectId(id, objectIdGenerator, simpleObjectIdResolver);
        roid.bindItem(value);
    }

}
