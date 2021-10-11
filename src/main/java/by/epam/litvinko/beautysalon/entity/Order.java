package by.epam.litvinko.beautysalon.entity;

import java.time.LocalDate;
import java.util.List;


/**
 * The type Order.
 */
public class Order extends AbstractEntity {

    private int clientId;
    private int couponId;
    private LocalDate created;
    private boolean isPaid;
    private boolean isActive;
    private List<ProvideService> serviceList;

    /**
     * Instantiates a new Order.
     */
    public Order(){
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets coupon id.
     *
     * @return the coupon id
     */
    public int getCouponId() {
        return couponId;
    }

    /**
     * Sets coupon id.
     *
     * @param couponId the coupon id
     */
    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public LocalDate getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(LocalDate created) {
        this.created = created;
    }

    /**
     * Is paid boolean.
     *
     * @return the boolean
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Sets paid.
     *
     * @param paid the paid
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        isActive = active;
    }


    /**
     * Gets service list.
     *
     * @return the service list
     */
    public List<ProvideService> getServiceList() {
        return serviceList;
    }

    /**
     * Sets service list.
     *
     * @param serviceList the service list
     */
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

    /**
     * New builder order . builder.
     *
     * @return the order . builder
     */
    public static Order.Builder newBuilder(){
        return new Order().new Builder();
    }

    /**
     * The type Builder.
     */
    public class Builder{

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Set id order . builder.
         *
         * @param id the id
         * @return the order . builder
         */
        public Order.Builder setId(int id){
            Order.this.setId(id);
            return this;
        }

        /**
         * Set client id order . builder.
         *
         * @param clientId the client id
         * @return the order . builder
         */
        public Order.Builder setClientId(int clientId){
            Order.this.clientId = clientId;
            return this;
        }

        /**
         * Set coupon id order . builder.
         *
         * @param couponId the coupon id
         * @return the order . builder
         */
        public Order.Builder setCouponId(int couponId){
            Order.this.couponId = couponId;
            return this;
        }

        /**
         * Set created order . builder.
         *
         * @param created the created
         * @return the order . builder
         */
        public Order.Builder setCreated(LocalDate created){
            Order.this.created = created;
            return this;
        }

        /**
         * Set is paid order . builder.
         *
         * @param isPaid the is paid
         * @return the order . builder
         */
        public Order.Builder setIsPaid(boolean isPaid){
            Order.this.isPaid = isPaid;
            return this;
        }

        /**
         * Set is active order . builder.
         *
         * @param isActive the is active
         * @return the order . builder
         */
        public Order.Builder setIsActive(boolean isActive){
            Order.this.isActive = isActive;
            return this;
        }

        /**
         * Build order.
         *
         * @return the order
         */
        public Order build(){
            return Order.this;
        }

    }
}