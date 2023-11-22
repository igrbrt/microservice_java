package br.com.microservice.order.service;

import br.com.microservice.order.dto.OrderDto;
import br.com.microservice.order.dto.StatusDto;
import br.com.microservice.order.model.Order;
import br.com.microservice.order.model.Status;
import br.com.microservice.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderDto> getAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto getById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto create(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setDateTime(LocalDateTime.now());
        order.setStatus(Status.ACCOMPLISHED);
        order.getItems().forEach(item -> item.setOrder(order));
        repository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto updateStatus(Long id, StatusDto dto) {

        Order order = repository.getOrderWithItemsById(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto.getStatus());
        repository.updateStatus(dto.getStatus(), order);
        return modelMapper.map(order, OrderDto.class);
    }

    public void approvePaymentOrder(Long id) {

        Order order = repository.getOrderWithItemsById(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAID);
        repository.updateStatus(Status.PAID, order);
    }
}
