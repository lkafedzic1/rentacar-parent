package ba.unsa.etf.pnwt.microservice.adapter;


import ba.unsa.etf.pnwt.microservice.persistance.Reservation;
import org.mapstruct.Mapper;

@Mapper
public interface ReservationMapper {

    ba.unsa.etf.pnwt.microservice.controller.model.Reservation domainToDto(ba.unsa.etf.pnwt.microservice.domain.model.Reservation domain);

    ba.unsa.etf.pnwt.microservice.domain.model.Reservation dtoTodomain(ba.unsa.etf.pnwt.microservice.controller.model.Reservation dto);

    ba.unsa.etf.pnwt.microservice.domain.model.Reservation entityToDomain(Reservation entity);

    Reservation domainToEntity(ba.unsa.etf.pnwt.microservice.domain.model.Reservation reservation);
}
