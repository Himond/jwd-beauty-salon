package by.epam.litvinko.beautysalon.model.service.dto;


import by.epam.litvinko.beautysalon.entity.ProvideService;

import java.math.BigDecimal;

public record ProvideServicesDto(int id, int categoryId, String name, String description, BigDecimal price, int serviceTime,  String image) {

    public static ProvideServicesDto create(ProvideService service){
        ProvideServicesDto provideServicesDto = new ProvideServicesDto(service.getId(), service.getCategoryId(), service.getName(), service.getDescription(),
                 service.getPrice(), service.getServiceTime(), service.getImage());
        return provideServicesDto;
    }
}
