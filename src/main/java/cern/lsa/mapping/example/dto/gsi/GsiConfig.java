package cern.lsa.mapping.example.dto.gsi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class GsiConfig {

    @Autowired
    private final ObjectMapper objectMapper;

    public GsiConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void registerContextDtoSubtype() {
        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.registerSubtypes(GsiBeamProcessDto.class);
        simpleModule.registerSubtypes(new NamedType(GsiBeamProcessDto.class, "Gsi Type"));
        this.objectMapper.registerModule(simpleModule);
    }
}
