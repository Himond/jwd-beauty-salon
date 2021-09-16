package by.epam.litvinko.beautysalon.entity;

import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cart extends AbstractEntity {

    private int orderId;
    private Set<ProvideServicesDto> services = new HashSet<>();
    private Coupon coupon;

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Set<ProvideServicesDto> getServices() {
        return services;
    }

    public void setServices(Set<ProvideServicesDto> services) {
        this.services = services;
    }

    public void addService(ProvideServicesDto service){
        this.services.add(service);
    }

    public void removeService(ProvideServicesDto service){
        this.services.remove(service);
    }

    public BigDecimal getTotalPrice(){
        Optional<BigDecimal> totalPrice;
        totalPrice = services.stream().map(ProvideServicesDto::price).reduce(BigDecimal::add);
        return totalPrice.orElseGet(() -> new BigDecimal(0));
    }

    public BigDecimal getTotalPriceAfterDiscount(){
        Optional<BigDecimal> totalPrice;
        totalPrice = services.stream().map(ProvideServicesDto::price).reduce(BigDecimal::add);
        return totalPrice.map(bigDecimal -> getTotalPrice().subtract(bigDecimal.multiply(BigDecimal.valueOf(coupon.getDiscount()).divide(BigDecimal.valueOf(100))))).orElseGet(() -> new BigDecimal(0));
    }

    public void clearCart(){
        services.clear();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cart{");
        sb.append("orderId=").append(orderId);
        sb.append(", services=").append(services);
        sb.append(", coupon=").append(coupon);
        sb.append('}');
        return sb.toString();
    }
}
