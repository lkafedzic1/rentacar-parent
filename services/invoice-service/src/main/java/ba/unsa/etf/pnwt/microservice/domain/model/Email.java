package ba.unsa.etf.pnwt.microservice.domain.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {
    private String from;
    private String bcc;
    private String to;
    private String subject;
    private String message;
    private List<MultipartFile> attachments;
}
