package by.epam.litvinko.beautysalon.entity;

public class ServiceReview extends Review{

    private int clientId;
    private int serviceId;

    public ServiceReview() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ServiceReview that = (ServiceReview) o;

        if (clientId != that.clientId) return false;
        return serviceId == that.serviceId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + serviceId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceReview{");
        sb.append("Id=").append(getId());
        sb.append(", clientId=").append(clientId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", review=").append(getReview());
        sb.append(", isActive=").append(isActive());
        sb.append('}');
        return sb.toString();
    }

    public static ServiceReview.Builder newBuilder(){
        return new ServiceReview().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public ServiceReview.Builder setID(int id){
            ServiceReview.super.setId(id);
            return this;
        }

        public ServiceReview.Builder setReview(String review){
            ServiceReview.this.setReview(review);
            return this;
        }

        public ServiceReview.Builder setActive(boolean isActive){
            ServiceReview.this.setActive(isActive);
            return this;
        }

        public ServiceReview.Builder setClientId(int clientId){
            ServiceReview.this.clientId = clientId;
            return this;
        }

        public ServiceReview.Builder setServiceId(int serviceId){
            ServiceReview.this.serviceId = serviceId;
            return this;
        }

        public ServiceReview build(){
            return ServiceReview.this;
        }

    }

}
