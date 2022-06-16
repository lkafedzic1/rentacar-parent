package ba.unsa.etf.pnwt.microservice.adapter;


import ba.unsa.etf.pnwt.microservice.persistance.Car;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface AdminMapper {

    ba.unsa.etf.pnwt.microservice.controller.model.Car domainToDto(ba.unsa.etf.pnwt.microservice.domain.model.Car domain);

    ba.unsa.etf.pnwt.microservice.domain.model.Car dtoTodomain(ba.unsa.etf.pnwt.microservice.controller.model.Car dto);

    ba.unsa.etf.pnwt.microservice.domain.model.Car entityToDomain(Car entity);

    Car domainToEntity(ba.unsa.etf.pnwt.microservice.domain.model.Car domain);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void update(@MappingTarget ba.unsa.etf.pnwt.microservice.domain.model.Car target, ba.unsa.etf.pnwt.microservice.domain.model.Car source);
}
