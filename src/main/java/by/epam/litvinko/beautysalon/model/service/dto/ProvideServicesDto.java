package by.epam.litvinko.beautysalon.model.service.dto;


import by.epam.litvinko.beautysalon.entity.ProvideService;

import java.math.BigDecimal;

/**
 * The type Provide services dto.
 */
public record ProvideServicesDto(int id, int categoryId, String name, String description, BigDecimal price, int serviceTime,  String image) {

    /**
     * Create provide services dto.
     *
     * @param service the service
     * @return the provide services dto
     */
    public static ProvideServicesDto create(ProvideService service){
        ProvideServicesDto provideServicesDto = new ProvideServicesDto(service.getId(), service.getCategoryId(), service.getName(), service.getDescription(),
                 service.getPrice(), service.getServiceTime(), service.getImage());
        return provideServicesDto;
    }
}
