package cern.lsa.mapping.example.rest;

import cern.lsa.mapping.example.domain.*;
import cern.lsa.mapping.example.dto.*;
import cern.lsa.mapping.example.dto.cern.CernNameSurname;
import cern.lsa.mapping.example.dto.gsi.GsiNameSurname;
import cern.lsa.mapping.example.mappers.MapperFacade;
import cern.lsa.mapping.example.referenced.ModifiableReferencedCircularImmutable;
import cern.lsa.mapping.example.referenced.ReferencedCircularImmutable;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;

@RestController
public class ExampleController {

    @Autowired
    private ObjectMapper objectMapper;
    private final MapperFacade mapperFacade;

    public ExampleController(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    @GetMapping("/map")
    public ObjectWithListMap getObjectWithListMap() {
        try {
            Object obj = ObjectGenerator.getKeyListMap();
            System.out.println(objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ObjectGenerator.createObjectWithListMap();
    }

    @GetMapping("/map-raw")
    public Map<MapKey, List<MapValue>> getRawListMap() {
        return ObjectGenerator.createRawListMap();
    }


    @GetMapping("/list")
    public List<Object> getList() {
        int length = 10;
        MapKey thanos = new MapKeyImpl("Thanos", 1);
        List<Object> ret = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            ret.add(thanos);
        }
        return ret;
    }

    @GetMapping("/array")
    public Object[] getArray() {
        int length = 10;
        MapKey thanos = new MapKeyImpl("Thanos", 1);
        Object[] ret = new Object[length];
        for (int i = 0; i < length; i++) {
            ret[i] = thanos;
        }
        return ret;
    }

    @GetMapping("/map-weird")
    public Map<Map<String, List<String>>, List<List<String>>> getWeirdMap() {
        Map<Map<String, List<String>>, List<List<String>>> weirdMap = new HashMap<>();
        List<List<String>> value = Arrays.asList(Arrays.asList("1", "2"), Arrays.asList("3", "4", "5"));
        weirdMap.put(null, value);
        Map<String, List<String>> key = Stream.of(new String[][]{
                {"Hello", "World"},
                {"John", "Doe"},
        }).collect(Collectors.toMap(data -> data[0], data -> singletonList(data[1])));
//        weirdMap.put(key, null);
        weirdMap.put(key, value);
        return weirdMap;
    }

    @GetMapping("/map-simple")
    public ObjectWithSimpleMap getObjectWithSimpleMap() {
        return ObjectGenerator.createObjectWithSimpleMap();
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

        circularImmutablesObjectGenerator.setChildren(node, singletonList(child));
        circularImmutablesObjectGenerator.setParent(node, parent);
        circularImmutablesObjectGenerator.setChildren(parent, Arrays.asList(node, sibling));
        circularImmutablesObjectGenerator.setParent(sibling, parent);
        circularImmutablesObjectGenerator.setParent(child, node);

        return node.toImmutable();
    }

    @GetMapping("/immutables")
    public HandcraftedClassDto getImmutables() {
        ImmutableParentClass parent = ObjectGenerator.createImmutableParentClass("I am the parent", Arrays.asList("Parent A", "Another Parent"));
        ImmutableSiblingClass sibling = ObjectGenerator.createImmutableSiblingClass("I am the sibling");
        ImmutableChildClass child = ObjectGenerator.createImmutableChildClass("I am the child", "child surname", Arrays.asList("Child A", "Another Child"), sibling);
        HandcraftedClass handcraftedClass = new HandcraftedClass();
        handcraftedClass.setName("hand crafted class");
        handcraftedClass.setParent(parent);
        handcraftedClass.setChild(child);
        return  mapperFacade.toHandCraftedDto(handcraftedClass);
    }

    @GetMapping("/names")
    public NamesContext getNamesContext() {
        return new NamesContext(
                new CernNameSurname("Thanos", "Tsounis", "Lukasz"),
                GsiNameSurname.BOB_DYLAN,
                new CommonNameSurname("Roman", "Gorbonosov")
        );
    }
}
