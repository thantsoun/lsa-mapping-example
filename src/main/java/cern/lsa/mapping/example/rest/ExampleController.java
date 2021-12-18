package cern.lsa.mapping.example.rest;

import cern.lsa.mapping.example.dto.AttributeDto;
import cern.lsa.mapping.example.dto.BeamProcessDto;
import cern.lsa.mapping.example.dto.StandAloneBeamProcessDto;
import cern.lsa.mapping.example.mappers.MapperFacade;
import cern.lsa.mapping.example.referenced.DefaultReferencedCircularImmutable;
import cern.lsa.mapping.example.referenced.ModifiableReferencedCircularImmutable;
import cern.lsa.mapping.example.referenced.ReferencedCircularImmutable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class ExampleController {

    private final MapperFacade mapperFacade;

    public ExampleController(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    @GetMapping("/attributes")
    public Collection<AttributeDto> getAttributes() {
        return Arrays.asList(
                mapperFacade.toAttributeDto(ObjectGenerator.createAttribute("attr 1", 1L)),
                mapperFacade.toAttributeDto(ObjectGenerator.createAttribute("attr 2", 2L))
        );
    }

    @GetMapping("/bms")
    public BeamProcessDto getBMs() {
        BeamProcessDto beamProcessDto = mapperFacade.toBeamProcessDto(ObjectGenerator.createBMWithHierarchies(0, "My favourite beam process"));
        return beamProcessDto;
    }

    @GetMapping("/bms2")
    public StandAloneBeamProcessDto getStandAloneBMs() {
        StandAloneBeamProcessDto standAloneBeamProcessDto = mapperFacade.toStandAloneBeamProcessDto(ObjectGenerator.createStandALoneBM(1000, "My favourite stand alone beam process"));
        return standAloneBeamProcessDto;
    }

    @GetMapping("/circular")
    public ReferencedCircularImmutable getCircular() {
        CircularImmutablesObjectGenerator circularImmutablesObjectGenerator = ObjectGenerator.getCircularObjGenerator();
        ModifiableReferencedCircularImmutable node = circularImmutablesObjectGenerator.createModifiableCircular("Dr.", "Jack", "I am the man");
        ModifiableReferencedCircularImmutable child = circularImmutablesObjectGenerator.createModifiableCircular("Jr.", "Denis", "I am the child");
        ModifiableReferencedCircularImmutable sibling = circularImmutablesObjectGenerator.createModifiableCircular("Miss", "Joan", "I am the sister");
        ModifiableReferencedCircularImmutable parent = circularImmutablesObjectGenerator.createModifiableCircular("Mr.", "Bob", "I am your father");

        circularImmutablesObjectGenerator.setChildren(node, Collections.singletonList(child));
        circularImmutablesObjectGenerator.setParent(node, parent);
        circularImmutablesObjectGenerator.setChildren(parent, Arrays.asList(node, sibling));
        circularImmutablesObjectGenerator.setParent(sibling, parent);
        circularImmutablesObjectGenerator.setParent(child, node);

        DefaultReferencedCircularImmutable nodeFinal = node.toImmutable();

        return nodeFinal;
    }
}
