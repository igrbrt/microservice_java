package br.com.microservice.order.controller;

import br.com.microservice.order.dto.OrderDto;
import br.com.microservice.order.dto.StatusDto;
import br.com.microservice.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

        @Autowired
        private OrderService service;

        @GetMapping()
        public List<OrderDto> getAll() {
            return service.getAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> getById(@PathVariable @NotNull Long id) {
            OrderDto dto = service.getById(id);

            return  ResponseEntity.ok(dto);
        }

        @GetMapping("/port")
        public String getPort(@Value("${local.server.port}") String port){
            return String.format("Order service running on port %s", port);
        }

        @PostMapping()
        public ResponseEntity<OrderDto> requestOrder(@RequestBody @Valid OrderDto dto, UriComponentsBuilder uriBuilder) {
            OrderDto orderRequested = service.create(dto);

            URI address = uriBuilder.path("/orders/{id}").buildAndExpand(orderRequested.getId()).toUri();

            return ResponseEntity.created(address).body(orderRequested);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDto> updateStatus(@PathVariable Long id, @RequestBody StatusDto status){
           OrderDto dto = service.updateStatus(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
            service.approvePaymentOrder(id);

            return ResponseEntity.ok().build();

        }
}
