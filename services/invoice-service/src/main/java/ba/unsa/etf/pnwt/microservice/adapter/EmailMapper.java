package ba.unsa.etf.pnwt.microservice.adapter;

import ba.unsa.etf.pnwt.microservice.controller.model.Email;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface EmailMapper {

    ba.unsa.etf.pnwt.microservice.domain.model.Email dtoToProxy(Email email);

    ba.unsa.etf.pnwt.microservice.domain.model.Email dtoToProxy(Email email, List<MultipartFile> attachments);
}
