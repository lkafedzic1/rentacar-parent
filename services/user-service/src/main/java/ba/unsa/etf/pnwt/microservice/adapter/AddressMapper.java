package ba.unsa.etf.pnwt.microservice.adapter;


import ba.unsa.etf.pnwt.microservice.controller.model.UserAddress;
import ba.unsa.etf.pnwt.microservice.persistance.Address;
import org.mapstruct.Mapper;


@Mapper
public interface AddressMapper {

    UserAddress domainToDto(ba.unsa.etf.pnwt.microservice.domain.model.Address domain);

    ba.unsa.etf.pnwt.microservice.domain.model.Address dtoTodomain(UserAddress dto);

    Address domainToEntity(ba.unsa.etf.pnwt.microservice.domain.model.Address domain);

    ba.unsa.etf.pnwt.microservice.domain.model.Address entityToDomain(Address entity);
}
