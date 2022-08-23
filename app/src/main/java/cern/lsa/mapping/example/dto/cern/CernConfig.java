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
        this.objectMapper.registerModule(simpleModule);
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        this.objectMapper.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
    }
}
