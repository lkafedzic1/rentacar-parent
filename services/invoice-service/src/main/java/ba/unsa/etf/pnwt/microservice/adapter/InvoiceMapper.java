package ba.unsa.etf.pnwt.microservice.adapter;

import ba.unsa.etf.pnwt.microservice.controller.model.Invoice;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper {

    ba.unsa.etf.pnwt.microservice.domain.model.Invoice dtoToDomain(Invoice dto);
    Invoice domainToDto(ba.unsa.etf.pnwt.microservice.domain.model.Invoice domain);

    ba.unsa.etf.pnwt.microservice.domain.entities.Invoice domainToEntity(ba.unsa.etf.pnwt.microservice.domain.model.Invoice domain);
    ba.unsa.etf.pnwt.microservice.domain.model.Invoice entityToDomain(ba.unsa.etf.pnwt.microservice.domain.entities.Invoice entity);

}
