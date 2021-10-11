package by.epam.litvinko.beautysalon.entity;

import java.time.LocalDate;

/**
 * The type Coupon.
 */
public class Coupon extends AbstractEntity {

    private String code;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int discount;
    private boolean isActive;

    /**
     * Instantiates a new Coupon.
     */
    public Coupon() {
    }

    public int getId(){
        return super.getId();
    }

    public void setId(int id){
        super.setId(id);
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets valid from.
     *
     * @return the valid from
     */
    public LocalDate getValidFrom() {
        return validFrom;
    }

    /**
     * Sets valid from.
     *
     * @param validFrom the valid from
     */
    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Gets valid to.
     *
     * @return the valid to
     */
    public LocalDate getValidTo() {
        return validTo;
    }

    /**
     * Sets valid to.
     *
     * @param validTo the valid to
     */
    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(int discount) {
        this.discount = discount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Coupon coupon = (Coupon) o;

        if (discount != coupon.discount) return false;
        if (isActive != coupon.isActive) return false;
        if (code != null ? !code.equals(coupon.code) : coupon.code != null) return false;
        if (validFrom != null ? !validFrom.equals(coupon.validFrom) : coupon.validFrom != null) return false;
        return validTo != null ? validTo.equals(coupon.validTo) : coupon.validTo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validTo != null ? validTo.hashCode() : 0);
        result = 31 * result + discount;
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coupon{");
        sb.append("code='").append(code).append('\'');
        sb.append(", validFrom=").append(validFrom);
        sb.append(", validTo=").append(validTo);
        sb.append(", discount=").append(discount);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }

    /**
     * New builder coupon . builder.
     *
     * @return the coupon . builder
     */
    public static Coupon.Builder newBuilder(){
        return new Coupon().new Builder();
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
         * Set id coupon . builder.
         *
         * @param id the id
         * @return the coupon . builder
         */
        public Coupon.Builder setId(int id){
            Coupon.this.setId(id);
            return this;
        }

        /**
         * Set code coupon . builder.
         *
         * @param code the code
         * @return the coupon . builder
         */
        public Coupon.Builder setCode(String code){
            Coupon.this.code = code;
            return this;
        }

        /**
         * Set valid from coupon . builder.
         *
         * @param validFrom the valid from
         * @return the coupon . builder
         */
        public Coupon.Builder setValidFrom(LocalDate validFrom){
            Coupon.this.validFrom = validFrom;
            return this;
        }

        /**
         * Set valid to coupon . builder.
         *
         * @param validTo the valid to
         * @return the coupon . builder
         */
        public Coupon.Builder setValidTo(LocalDate validTo){
            Coupon.this.validTo = validTo;
            return this;
        }

        /**
         * Set discount coupon . builder.
         *
         * @param discount the discount
         * @return the coupon . builder
         */
        public Coupon.Builder setDiscount(int discount){
            Coupon.this.discount = discount;
            return this;
        }

        /**
         * Set is active coupon . builder.
         *
         * @param isActive the is active
         * @return the coupon . builder
         */
        public Coupon.Builder setIsActive(boolean isActive){
            Coupon.this.isActive = isActive;
            return this;
        }


        /**
         * Build coupon.
         *
         * @return the coupon
         */
        public Coupon build(){
            return Coupon.this;
        }

    }

}
