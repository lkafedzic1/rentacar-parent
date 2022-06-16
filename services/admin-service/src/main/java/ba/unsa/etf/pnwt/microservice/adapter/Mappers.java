package ba.unsa.etf.pnwt.microservice.adapter;

import static org.mapstruct.factory.Mappers.getMapper;

public final class Mappers {

    public static final AdminMapper ADMIN = getMapper(AdminMapper.class);

    private Mappers() {
    }
}
