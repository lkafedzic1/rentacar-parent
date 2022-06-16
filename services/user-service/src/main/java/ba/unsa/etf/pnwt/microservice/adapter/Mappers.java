package ba.unsa.etf.pnwt.microservice.adapter;

import static org.mapstruct.factory.Mappers.getMapper;

public final class Mappers {

    public static final AddressMapper ADDRESS = getMapper(AddressMapper.class);
    public static final UserMapper USER = getMapper(UserMapper.class);
    public static final RoleMapper ROLE = getMapper(RoleMapper.class);

    private Mappers() {
    }
}
