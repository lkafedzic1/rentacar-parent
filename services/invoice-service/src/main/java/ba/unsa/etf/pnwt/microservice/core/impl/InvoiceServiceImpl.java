package ba.unsa.etf.pnwt.microservice.core.impl;

import ba.unsa.etf.pnwt.microservice.core.InvoiceService;
import ba.unsa.etf.pnwt.microservice.core.method.GenerateInvoice;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.core.EmailService;
import ba.unsa.etf.pnwt.microservice.domain.model.Invoice;
import ba.unsa.etf.pnwt.microservice.domain.repositories.InvoiceRepository;
import ba.unsa.etf.pnwt.microservice.exception.CustomParameterizedException;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final EmailService emailService;
    private final InvoiceRepository invoiceRepository;
    private final GenerateInvoice generateInvoice;

    @Override
    public Invoice generateInvoice(final Invoice invoice) {
        final Invoice savedInvoice = Mappers.INVOICE.entityToDomain(invoiceRepository.save(Mappers.INVOICE.domainToEntity(invoice)));
        emailService.sendEmail(generateInvoice.execute(invoice));
        return savedInvoice;
    }

    @Override
    public void deleteInvoice(final long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public Invoice getInvoiceById(final long invoiceId) {
        return Mappers.INVOICE.entityToDomain(invoiceRepository.findById(invoiceId).orElseThrow(() -> CustomParameterizedException.singleParameterProblem(
                "invoice not found",
                HttpStatus.NOT_FOUND.value(),
                "INVOICE_ID", invoiceId)));
    }

    @Override
    public Collection<Invoice> getAllInvoiceByuserId(final long id) {
        return CollectionUtils.emptyIfNull(invoiceRepository.getAllByUserId(id))
                .stream()
                .map(Mappers.INVOICE::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Invoice> getAllInvoiceByReservationId(final long id) {
        return CollectionUtils.emptyIfNull(invoiceRepository.getAllByReservationId(id))
                .stream()
                .map(Mappers.INVOICE::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Invoice> getAllInvoices() {
        return CollectionUtils.emptyIfNull(invoiceRepository.findAll())
                .stream()
                .map(Mappers.INVOICE::entityToDomain)
                .collect(Collectors.toList());
    }
}
