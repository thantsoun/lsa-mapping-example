package cern.lsa.mapping.example.dto.cern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class CernConfig {

    @Autowired
    private final ObjectMapper objectMapper;

    public CernConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void registerContextDtoSubtype() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.registerSubtypes(CernNameSurname.class);
        simpleModule.setSerializerModifier(new MapSerializerModifier());
        objectMapper.registerModule(simpleModule);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void main(String[] args) {

    }

}
