package by.epam.litvinko.beautysalon.entity;

public class ProvideServiceReview extends Entity{

    private int clientId;
    private int serviceId;
    private String review;
    private boolean isActive;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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

        ProvideServiceReview review1 = (ProvideServiceReview) o;

        if (clientId != review1.clientId) return false;
        if (serviceId != review1.serviceId) return false;
        if (isActive != review1.isActive) return false;
        return review != null ? review.equals(review1.review) : review1.review == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + serviceId;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("clientId=").append(clientId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", review='").append(review).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }

}
