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
        ModifiableReferencedCircularImmutable node = ModifiableReferencedCircularImmutable.create().setName("Jack").setMessage("I am the man").setTitle("Dr.");
        ModifiableReferencedCircularImmutable child = ModifiableReferencedCircularImmutable.create().setName("Denis").setMessage("I am the child").setTitle("Jr.");
        ModifiableReferencedCircularImmutable sibling = ModifiableReferencedCircularImmutable.create().setName("Joan").setMessage("I am the sister").setTitle("Miss");
        ModifiableReferencedCircularImmutable parent = ModifiableReferencedCircularImmutable.create().setName("Bob").setMessage("I am the father").setTitle("Mr.");

        AtomicReference<ReferencedCircularImmutable> nodeRef = new AtomicReference<>(node);
        AtomicReference<ReferencedCircularImmutable> childRef = new AtomicReference<>(child);
        AtomicReference<ReferencedCircularImmutable> siblingRef = new AtomicReference<>(sibling);
        AtomicReference<ReferencedCircularImmutable> parentRef = new AtomicReference<>(parent);

        node.setChildrenInt(Collections.singletonList(childRef));
        node.setParentInt(parentRef);

        parent.setParentInt(null);
        parent.setChildrenInt(Arrays.asList(nodeRef, siblingRef));

        child.setParentInt(nodeRef);
        child.setChildrenInt(Collections.emptyList());

        sibling.setParentInt(parentRef);
        sibling.setChildrenInt(Collections.emptyList());

        DefaultReferencedCircularImmutable nodeFinal = node.toImmutable();

        return nodeFinal;
    }
}
