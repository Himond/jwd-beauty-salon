package epam.by.litvinko.beautysalon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Coupon extends Entity{

    private String code;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private int discount;
    private double isActive;

    public Coupon() {
    }

    public long getId(){
        return super.getId();
    }

    public void setId(long id){
        super.setId(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getIsActive() {
        return isActive;
    }

    public void setIsActive(double isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Coupon coupon = (Coupon) o;

        if (discount != coupon.discount) return false;
        if (Double.compare(coupon.isActive, isActive) != 0) return false;
        if (code != null ? !code.equals(coupon.code) : coupon.code != null) return false;
        if (validFrom != null ? !validFrom.equals(coupon.validFrom) : coupon.validFrom != null) return false;
        return validTo != null ? validTo.equals(coupon.validTo) : coupon.validTo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validTo != null ? validTo.hashCode() : 0);
        result = 31 * result + discount;
        temp = Double.doubleToLongBits(isActive);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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

    public static Coupon.Builder newBuilder(){
        return new Coupon().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public Coupon.Builder setID(long id){
            Coupon.this.setId(id);
            return this;
        }

        public Coupon.Builder setCode(String code){
            Coupon.this.code = code;
            return this;
        }

        public Coupon.Builder setValidFrom(LocalDateTime validFrom){
            Coupon.this.validFrom = validFrom;
            return this;
        }

        public Coupon.Builder setValidTo(LocalDateTime validTo){
            Coupon.this.validTo = validTo;
            return this;
        }

        public Coupon.Builder setDiscount(int discount){
            Coupon.this.discount = discount;
            return this;
        }

        public Coupon.Builder setIsActive(double isActive){
            Coupon.this.isActive = isActive;
            return this;
        }


        public Coupon build(){
            return Coupon.this;
        }

    }

}
