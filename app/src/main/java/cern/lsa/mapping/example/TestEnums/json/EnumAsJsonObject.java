package cern.lsa.mapping.example.TestEnums.json;

import com.fasterxml.jackson.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@JacksonAnnotationsInside
@JsonTypeInfo(use = NAME)
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public @interface EnumAsJsonObject {
}
