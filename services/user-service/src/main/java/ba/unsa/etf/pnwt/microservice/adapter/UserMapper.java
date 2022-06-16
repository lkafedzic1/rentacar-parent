package ba.unsa.etf.pnwt.microservice.adapter;


import ba.unsa.etf.pnwt.microservice.persistance.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper
public interface UserMapper {

    ba.unsa.etf.pnwt.microservice.controller.model.User domainToDto(ba.unsa.etf.pnwt.microservice.domain.model.User domain);

    ba.unsa.etf.pnwt.microservice.domain.model.User dtoTodomain(ba.unsa.etf.pnwt.microservice.controller.model.User dto);

    User domainToEntity(ba.unsa.etf.pnwt.microservice.domain.model.User domain);

    ba.unsa.etf.pnwt.microservice.domain.model.User entityToDomain(User entity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void update(@MappingTarget ba.unsa.etf.pnwt.microservice.domain.model.User target, ba.unsa.etf.pnwt.microservice.domain.model.User user);
}
