package ba.unsa.etf.pnwt.microservice.controller;

import ba.unsa.etf.pnwt.microservice.core.EmailService;
import ba.unsa.etf.pnwt.microservice.core.InvoiceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.controller.model.Email;
import ba.unsa.etf.pnwt.microservice.controller.model.Invoice;

@RestController
@AllArgsConstructor
public class InvoiceApiController implements EmailApi, InvoicesApi {

    private final EmailService emailService;
    private final InvoiceService invoiceService;

    @Override
    public ResponseEntity<Void> sendEmail(final @Valid Email email) {
        emailService.sendEmail(Mappers.EMAIL.dtoToProxy(email));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> sendEmailWithAttachment(final @Valid List<MultipartFile> attachments, final Email email) {
        emailService.sendEmail(Mappers.EMAIL.dtoToProxy(email, attachments));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invoice> deleteInvoice(Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Invoice> generateInvoice(@Valid Invoice invoice) {
        return ResponseEntity.ok(Mappers.INVOICE.domainToDto(invoiceService.generateInvoice(Mappers.INVOICE.dtoToDomain(invoice))));
    }

    @Override
    public ResponseEntity<List<Invoice>> getAllInvoiceByuserId(Long userId) {
        return ResponseEntity.ok(CollectionUtils.emptyIfNull(invoiceService.getAllInvoiceByuserId(userId))
                .stream().map(Mappers.INVOICE::domainToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(CollectionUtils.emptyIfNull(invoiceService.getAllInvoices())
                .stream().map(Mappers.INVOICE::domainToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Invoice> getInvoiceById(Long invoiceId) {
        return ResponseEntity.ok(Mappers.INVOICE.domainToDto(invoiceService.getInvoiceById(invoiceId)));
    }
}
