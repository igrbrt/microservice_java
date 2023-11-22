package br.com.microservice.payment.service;

import br.com.microservice.payment.dto.PaymentDto;
import br.com.microservice.payment.model.Payment;
import br.com.microservice.payment.model.Status;
import br.com.microservice.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentDto> getAll(Pageable pagination) {
        return repository
                .findAll(pagination)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto getById(Long id) {
        Payment payment = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setStatus(Status.CREATED);
        repository.save(payment);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto update(Long id, PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
