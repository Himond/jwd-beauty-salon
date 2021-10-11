package by.epam.litvinko.beautysalon.entity;

import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The type Cart.
 */
public class Cart extends AbstractEntity {

    private int orderId;
    private Set<ProvideServicesDto> services = new HashSet<>();
    private Coupon coupon;

    /**
     * Gets coupon.
     *
     * @return the coupon
     */
    public Coupon getCoupon() {
        return coupon;
    }

    /**
     * Sets coupon.
     *
     * @param coupon the coupon
     */
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets services.
     *
     * @return the services
     */
    public Set<ProvideServicesDto> getServices() {
        return services;
    }

    /**
     * Sets services.
     *
     * @param services the services
     */
    public void setServices(Set<ProvideServicesDto> services) {
        this.services = services;
    }

    /**
     * Add service.
     *
     * @param service the service
     */
    public void addService(ProvideServicesDto service){
        this.services.add(service);
    }

    /**
     * Remove service.
     *
     * @param service the service
     */
    public void removeService(ProvideServicesDto service){
        this.services.remove(service);
    }

    /**
     * Get total price big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal getTotalPrice(){
        Optional<BigDecimal> totalPrice;
        totalPrice = services.stream().map(ProvideServicesDto::price).reduce(BigDecimal::add);
        return totalPrice.orElseGet(() -> new BigDecimal(0));
    }

    /**
     * Get total price after discount big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal getTotalPriceAfterDiscount(){
        Optional<BigDecimal> totalPrice;
        totalPrice = services.stream().map(ProvideServicesDto::price).reduce(BigDecimal::add);
        return totalPrice.map(bigDecimal -> getTotalPrice().subtract(bigDecimal.multiply(BigDecimal.valueOf(coupon.getDiscount()).divide(BigDecimal.valueOf(100))))).orElseGet(() -> new BigDecimal(0));
    }

    /**
     * Clear cart.
     */
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
