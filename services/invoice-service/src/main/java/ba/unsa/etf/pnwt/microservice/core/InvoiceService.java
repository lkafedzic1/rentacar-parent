package ba.unsa.etf.pnwt.microservice.core;

import java.util.Collection;
import ba.unsa.etf.pnwt.microservice.domain.model.Invoice;

public interface InvoiceService {

    Invoice generateInvoice(Invoice invoice);

    void deleteInvoice(long invoiceId);

    Invoice getInvoiceById(long invoiceId);

    Collection<Invoice> getAllInvoiceByuserId(long id);

    Collection<Invoice> getAllInvoiceByReservationId(long id);

    Collection<Invoice> getAllInvoices();
}
