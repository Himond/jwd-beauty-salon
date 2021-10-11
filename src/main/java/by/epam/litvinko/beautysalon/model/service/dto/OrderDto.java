package by.epam.litvinko.beautysalon.model.service.dto;

import by.epam.litvinko.beautysalon.entity.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Order dto.
 */
public record OrderDto(int id, boolean isActive, List<ProvideServicesDto> products, LocalDate date) {

    /**
     * Create order dto.
     *
     * @param order the order
     * @return the order dto
     */
    public static OrderDto create(Order order){
        List<ProvideServicesDto> provideServices = order.getServiceList().stream().map(s -> new ProvideServicesDto(s.getId(), s.getCategoryId(), s.getName(), s.getDescription(), s.getPrice(), s.getServiceTime(), s.getImage())).toList();
        OrderDto orderDto = new OrderDto(order.getId(), order.isActive(), provideServices, order.getCreated());
        return orderDto;
    }
}
