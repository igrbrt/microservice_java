package br.com.microservice.payment.controller;

import br.com.microservice.payment.dto.PaymentDto;
import br.com.microservice.payment.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public Page<PaymentDto> getAll(@PageableDefault(size = 10) Pageable pagination) {
        return service.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto paymentDto, UriComponentsBuilder uriBuilder) {
        PaymentDto payment = service.create(paymentDto);
        URI address = uriBuilder
                .path("/payments/{id}")
                .buildAndExpand(payment.getId())
                .toUri();
        return ResponseEntity.created(address).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto paymentDto) {
        return ResponseEntity.ok(service.update(id, paymentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDto> delete(@PathVariable @NotNull Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/port")
    public String getPort(@Value("${local.server.port}") String port) {
        return String.format("Payment service running on port %s", port);
    }

}
