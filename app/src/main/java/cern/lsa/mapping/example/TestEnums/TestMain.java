package cern.lsa.mapping.example.TestEnums;

import cern.lsa.mapping.example.TestEnums.domain.*;
import cern.lsa.mapping.example.TestEnums.json.EnumAsJsonObject;
import cern.lsa.mapping.example.TestEnums.json.EnumDeserializerModifier;
import cern.lsa.mapping.example.TestEnums.json.EnumSerializerModifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            SimpleModule simpleModule = new SimpleModule();
            simpleModule.registerSubtypes(new NamedType(Colour.class, "simple_colour"));
            simpleModule.setMixInAnnotation(Colour.class, EnumAsJsonObject.class);
            simpleModule.setSerializerModifier(new EnumSerializerModifier());
            simpleModule.setDeserializerModifier(new EnumDeserializerModifier());

            objectMapper.registerModule(simpleModule);

            Person object = new Person("Thanos", "Tsounis");
            String json = objectMapper.writeValueAsString(object);
            System.out.println(json);
            System.out.println("Object = " + objectMapper.readValue(json, HasSecondName.class));

            for (MyEnum value : MyEnum.values()) {
                json = objectMapper.writeValueAsString(value);
                System.out.println(json);
                System.out.println(value + " = " + objectMapper.readValue(json, HasSecondName.class));
            }
            for (DuplicateEnum value : DuplicateEnum.values()) {
                try {
                    objectMapper.writeValueAsString(value);
                } catch (Exception e) {
                    System.out.println("Correctly received exception: " + e.getMessage());
                }
            }
            for (Colour value : Colour.values()) {
                json = objectMapper.writeValueAsString(value);
                System.out.println(json);
                System.out.println(value + " = " + objectMapper.readValue(json, Colour.class));
            }
            for (SimpleNonJsonEnum value : SimpleNonJsonEnum.values()) {
                try {
                    objectMapper.writeValueAsString(value);
                } catch (Exception e) {
                    System.out.println("Correctly received exception: " + e.getMessage());
                }
            }
            json = "{\"@type\":\"my_enum\",\"@id\":\"71cb63bf-de23-4bfd-b15b-c6f823ea7063\",\"name\":\"BLA_BLA\",\"secondName\":\"Second BLA-BLA\"}";
            System.out.println(objectMapper.readValue(json, HasSecondName.class));
            try {
                json = "{\"@type\":\"my_enum\",\"@id\":\"71cb63bf-de23-4bfd-b15b-c6f823ea7063\",\"name\":\"BLA_BLA1\",\"secondName\":\"Second BLA-BLA\"}";
                objectMapper.readValue(json, HasSecondName.class);
            } catch (Exception e) {
                System.out.println("Correctly received exception: " + e.getMessage());
            }
            json = "{\"@type\":\"simple_colour\",\"@id\":\"ef70f0ec-eb7e-4d38-b7e3-c97396958600\",\"hex\":\"#ff0000\",\"name\":\"Colour RED\"}";
            System.out.println(objectMapper.readValue(json, Colour.class));
            try {
                json = "{\"@type\":\"simple_colour\",\"@id\":\"ef70f0ec-eb7e-4d38-b7e3-c97396958600\",\"hex\":\"#ff0000\",\"name\":\"Colour REDDISH\"}";
                objectMapper.readValue(json, Colour.class);
            } catch (Exception e) {
                System.out.println("Correctly received exception: " + e.getMessage());
            }
            try {
                json = "\"Colour RED\"";
                objectMapper.readValue(json, Colour.class);
            } catch (Exception e) {
                System.out.println("Correctly received exception: " + e.getMessage());
            }
            try {
                json = "{\"@type\":\"duplicate_enum\",\"@id\":\"40d0b6de-b4de-4a23-82a0-84e2b4b8db43\",\"name\":\"BLA_BLA\",\"secondName\":\"Second BLA-BLA\"}\n";
                objectMapper.readValue(json, HasSecondName.class);
            } catch (Exception e) {
                System.out.println("Correctly received exception: " + e.getMessage());
            }
            try {
                json = "\"SIMPLE_NON_NAMED_1\"";
                objectMapper.readValue(json, SimpleNonJsonEnum.class);
            } catch (Exception e) {
                System.out.println("Correctly received exception: " + e.getMessage());
            }
            MyEnum[] enumArray = new MyEnum[]{MyEnum.BLA, MyEnum.BLA};
            json = objectMapper.writeValueAsString(enumArray);
            System.out.println(json);
            System.out.println(Arrays.deepToString(objectMapper.readValue(json, MyEnum[].class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
