package ba.unsa.etf.pnwt.microservice.core.method;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ba.unsa.etf.pnwt.microservice.core.proxy.reservation.ReservationServiceClient;
import ba.unsa.etf.pnwt.microservice.core.proxy.reservation.model.Reservation;
import ba.unsa.etf.pnwt.microservice.core.proxy.user.UserServiceClient;
import ba.unsa.etf.pnwt.microservice.core.proxy.user.model.User;
import ba.unsa.etf.pnwt.microservice.domain.model.Email;
import ba.unsa.etf.pnwt.microservice.domain.model.Invoice;

@Component
@AllArgsConstructor
@Slf4j
public class GenerateInvoice {

    private final static String MESSAGE_TEMPLATE = "Dear %s<br/><br/>Please find attached your invoice of amount $%s " +
            ".<br/><br/>Thank you for doing business with us!<br/><br/>Kind regards <br/>Rent a Car";
    private final ReservationServiceClient reservationClient;
    private final UserServiceClient userClient;


    public Email execute(final Invoice invoice) {
        final Reservation reservation = reservationClient.getReservationById(invoice.getReservationId());
        final User user = userClient.getUserById(invoice.getUserId());
        return Email.builder()
                .from("rentacarsite@gmail.com")
                .subject("Rent A Car Invoice #" + invoice.getId())
                .to(user.getEmail())
                .message(String.format(MESSAGE_TEMPLATE, user.getFirstName() + " " + user.getLastName(), reservation.getPrice())).build();

    }
}
