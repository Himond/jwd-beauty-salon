package by.epam.litvinko.beautysalon.entity;

import java.time.LocalDateTime;

public class Order extends Entity{

    private int clientId;
    private int couponId;
    private LocalDateTime created;
    private LocalDateTime dateOfService;
    private boolean isPaid;
    private boolean isActive;

    public Order(){

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(LocalDateTime dateOfService) {
        this.dateOfService = dateOfService;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (clientId != order.clientId) return false;
        if (couponId != order.couponId) return false;
        if (isPaid != order.isPaid) return false;
        if (isActive != order.isActive) return false;
        if (created != null ? !created.equals(order.created) : order.created != null) return false;
        return dateOfService != null ? dateOfService.equals(order.dateOfService) : order.dateOfService == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + couponId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (dateOfService != null ? dateOfService.hashCode() : 0);
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("clientId=").append(clientId);
        sb.append(", couponId=").append(couponId);
        sb.append(", created=").append(created);
        sb.append(", dateOfService=").append(dateOfService);
        sb.append(", isPaid=").append(isPaid);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }

    public static Order.Builder newBuilder(){
        return new Order().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public Order.Builder setID(int id){
            Order.this.setId(id);
            return this;
        }

        public Order.Builder setClientId(int clientId){
            Order.this.clientId = clientId;
            return this;
        }

        public Order.Builder setCouponId(int couponId){
            Order.this.couponId = couponId;
            return this;
        }

        public Order.Builder setCreated(LocalDateTime created){
            Order.this.created = created;
            return this;
        }

        public Order.Builder setDateOfService(LocalDateTime dateOfService){
            Order.this.dateOfService = dateOfService;
            return this;
        }

        public Order.Builder setIsPaid(boolean isPaid){
            Order.this.isPaid = isPaid;
            return this;
        }

        public Order.Builder setIsActive(boolean isActive){
            Order.this.isActive = isActive;
            return this;
        }

        public Order build(){
            return Order.this;
        }

    }
}
