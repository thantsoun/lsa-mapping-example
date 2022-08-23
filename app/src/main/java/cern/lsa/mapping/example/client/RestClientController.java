package cern.lsa.mapping.example.client;

import cern.lsa.mapping.example.domain.*;
import cern.lsa.mapping.example.dto.NamesContext;
import cern.lsa.mapping.example.referenced.ReferencedCircularImmutable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class RestClientController {

    private final RestClientDtoTranslationService restClientDtoTranslationService;

    public RestClientController(RestClientDtoTranslationService restClientDtoTranslationService) {
        this.restClientDtoTranslationService = restClientDtoTranslationService;
    }

    @GetMapping("/map")
    public ObjectWithListMap getObjectWithListMap() {
        return restClientDtoTranslationService.getObjectWithListMap();
    }

    @GetMapping("/map-simple")
    public ObjectWithListMap getObjectWithSimpleMap() {
        return restClientDtoTranslationService.getObjectWithSimpleMap();
    }

    @GetMapping("/attributes")
    public Collection<Attribute> getAttributes() {
        return restClientDtoTranslationService.getAttributes();
    }

    @GetMapping("/bms")
    public BeamProcess getBeamProcesses() {
        return restClientDtoTranslationService.getBMs();
    }

    @GetMapping("/bms2")
    public StandAloneBeamProcess getStandAloneBeamProcesses() {
        return restClientDtoTranslationService.getStandAloneBMs();
    }

    @GetMapping("/immutables")
    public HandcraftedClass getHandCraftedClass() {
        return restClientDtoTranslationService.getHandCraftedClass();
    }

    @GetMapping("/names")
    public NamesContext getContextNames() {
        return restClientDtoTranslationService.getContextNames();
    }

    @GetMapping("/circular")
    public ReferencedCircularImmutable getReferencedCircularImmutable() {
        ReferencedCircularImmutable referencedCircularImmutable = restClientDtoTranslationService.getReferencedCircularImmutable();
        ReferencedCircularImmutable parent = referencedCircularImmutable.getParent();
        List<ReferencedCircularImmutable> children = referencedCircularImmutable.getChildren();
        ReferencedCircularImmutable childrenParent = children.get(0).getParent();
        List<ReferencedCircularImmutable> siblings = parent.getChildren();
        ReferencedCircularImmutable sameObj = parent.getChildren().get(1).getParent().getChildren().get(0);
        return referencedCircularImmutable;
    }
}
