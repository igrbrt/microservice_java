package br.com.microservice.payment.repository;

import br.com.microservice.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
