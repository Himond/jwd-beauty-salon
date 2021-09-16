package by.epam.litvinko.beautysalon.entity;

import java.time.LocalDate;
import java.util.List;


public class Order extends AbstractEntity {

    private int clientId;
    private int couponId;
    private LocalDate created;
    private boolean isPaid;
    private boolean isActive;
    private List<ProvideService> serviceList;

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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
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

    public List<ProvideService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ProvideService> serviceList) {
        this.serviceList = serviceList;
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
        return serviceList != null ? serviceList.equals(order.serviceList) : order.serviceList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + couponId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (isPaid ? 1 : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (serviceList != null ? serviceList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("clientId=").append(clientId);
        sb.append(", couponId=").append(couponId);
        sb.append(", created=").append(created);
        sb.append(", isPaid=").append(isPaid);
        sb.append(", isActive=").append(isActive);
        sb.append(", serviceList=").append(serviceList);
        sb.append('}');
        return sb.toString();
    }

    public static Order.Builder newBuilder(){
        return new Order().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public Order.Builder setId(int id){
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

        public Order.Builder setCreated(LocalDate created){
            Order.this.created = created;
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
