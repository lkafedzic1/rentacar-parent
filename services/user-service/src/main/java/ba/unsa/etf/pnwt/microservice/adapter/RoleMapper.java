package ba.unsa.etf.pnwt.microservice.adapter;


import ba.unsa.etf.pnwt.microservice.controller.model.UserRole;
import ba.unsa.etf.pnwt.microservice.persistance.Role;
import org.mapstruct.Mapper;


@Mapper
public interface RoleMapper {

    UserRole domainToDto(ba.unsa.etf.pnwt.microservice.domain.model.Role domain);

    ba.unsa.etf.pnwt.microservice.domain.model.Role dtoTodomain(UserRole dto);

    Role domainToEntity(ba.unsa.etf.pnwt.microservice.domain.model.Role domain);

    ba.unsa.etf.pnwt.microservice.domain.model.Role entityToDomain(Role entity);
}
