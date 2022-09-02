package cern.lsa.mapping.example.TestEnums;

import cern.lsa.mapping.example.TestEnums.domain.*;
import cern.lsa.mapping.example.TestEnums.json.EnumAsJsonObject;
import cern.lsa.mapping.example.TestEnums.json.EnumDeserializerModifier;
import cern.lsa.mapping.example.TestEnums.json.EnumSerializerModifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Arrays;
import java.util.List;

import static cern.lsa.mapping.example.TestEnums.domain.MyEnum.BLA;

public class TestMain2 {

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            SimpleModule simpleModule = new SimpleModule();
            simpleModule.registerSubtypes(new NamedType(Colour.class, "simple_colour"));
            simpleModule.setMixInAnnotation(Colour.class, EnumAsJsonObject.class);
            simpleModule.setSerializerModifier(new EnumSerializerModifier());
            simpleModule.setDeserializerModifier(new EnumDeserializerModifier());

            objectMapper.registerModule(simpleModule);


            AB abEnum = new AB(BLA, BLA);
            String json = objectMapper.writeValueAsString(abEnum);
            System.out.println(json);
            AB readAB = objectMapper.readValue(json, AB.class);
            CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, HasSecondName.class);
            List<HasSecondName> list = Arrays.asList(BLA, BLA, BLA);
            json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            List<HasSecondName> readList = objectMapper.readValue(json, javaType);
            Person thanos = new Person("Thanos", "Tsounis");
            list = Arrays.asList(thanos, thanos, thanos);
            json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            readList = objectMapper.readValue(json, javaType);
            list = Arrays.asList(thanos, BLA, thanos, BLA);
            json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            readList = objectMapper.readValue(json, javaType);
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class AB {

        private final HasSecondName a;
        private final HasSecondName b;

        @JsonCreator
        AB(@JsonProperty("a") HasSecondName a, @JsonProperty("b")HasSecondName b) {
            this.a = a;
            this.b = b;
        }


        public HasSecondName getA() {
            return a;
        }

        public HasSecondName getB() {
            return b;
        }

        @Override
        public String toString() {
            return "AB{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

}
