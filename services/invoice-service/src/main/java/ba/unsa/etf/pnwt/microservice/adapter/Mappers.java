package ba.unsa.etf.pnwt.microservice.adapter;

import static org.mapstruct.factory.Mappers.getMapper;

public final class Mappers {
    public static final EmailMapper EMAIL = getMapper(EmailMapper.class);
    public static final InvoiceMapper INVOICE = getMapper(InvoiceMapper.class);
}
