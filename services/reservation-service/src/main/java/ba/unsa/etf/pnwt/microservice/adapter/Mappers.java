package ba.unsa.etf.pnwt.microservice.adapter;

import static org.mapstruct.factory.Mappers.getMapper;

public final class Mappers {

    public static final ReservationMapper RESERVATION = getMapper(ReservationMapper.class);

    private Mappers() {
    }
}
