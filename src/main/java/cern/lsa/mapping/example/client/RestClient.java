package cern.lsa.mapping.example.client;

import cern.lsa.mapping.example.domain.BeamProcess;
import cern.lsa.mapping.example.dto.AttributeDto;
import cern.lsa.mapping.example.dto.BeamProcessDto;
import cern.lsa.mapping.example.dto.StandAloneBeamProcessDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient(value = "testclient", url = "http://localhost:8080")
public interface RestClient {
    @RequestMapping(method = RequestMethod.GET, value = "/attributes", produces = "application/json")
    Collection<AttributeDto> getAttributes();
    @RequestMapping(method = RequestMethod.GET, value = "/bms", produces = "application/json")
    BeamProcessDto getBeamProcesses();
    @RequestMapping(method = RequestMethod.GET, value = "/bms2", produces = "application/json")
    StandAloneBeamProcessDto getStandAloneBeamProcesses();
}
