package exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, visible = true, property = ThrowableMixIn.EXCEPTION_CLASS)
@JsonTypeIdResolver(ThrowableTypeIdResolver.class)
@JsonIgnoreProperties(ignoreUnknown = true)
interface ThrowableMixIn {
    String EXCEPTION_CLASS = "@exceptionClass";
}
