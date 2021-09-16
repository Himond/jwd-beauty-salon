package by.epam.litvinko.beautysalon.model.service.dto;

import by.epam.litvinko.beautysalon.entity.Order;

import java.util.List;

public record OrderDto(int id, List<ProvideServicesDto> products) {

    public static OrderDto create(Order order){
        List<ProvideServicesDto> provideServices = order.getServiceList().stream().map(s -> new ProvideServicesDto(s.getId(), s.getCategoryId(), s.getName(), s.getDescription(), s.getPrice(), s.getServiceTime(), s.getImage())).toList();
        OrderDto orderDto = new OrderDto(order.getId(), provideServices);
        return orderDto;
    }
}