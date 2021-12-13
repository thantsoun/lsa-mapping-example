package cern.lsa.mapping.example.mappers;

import org.springframework.stereotype.Service;

@Service
public class MappersHelperService {

    public String enhanceName(String name) {
        return name + "/" + "enhanced";
    }

    public String deEnhanceName(String name) {
        return name.substring(0, name.lastIndexOf('/'));
    }

    public String getDefaultCategory() {
        return "default category";
    }
}
