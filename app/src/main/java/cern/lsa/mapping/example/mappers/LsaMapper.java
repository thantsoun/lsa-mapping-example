package cern.lsa.mapping.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.CLASS)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public @interface LsaMapper {
}
