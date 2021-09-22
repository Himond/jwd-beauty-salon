package by.epam.litvinko.beautysalon.entity;


public class ProvideServiceReview extends AbstractEntity {

    private int clientId;
    private int serviceId;
    private String review;
    private String clientFirstName;
    private String clientLastName;
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

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
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
        if (review != null ? !review.equals(review1.review) : review1.review != null) return false;
        if (clientFirstName != null ? !clientFirstName.equals(review1.clientFirstName) : review1.clientFirstName != null)
            return false;
        return clientLastName != null ? clientLastName.equals(review1.clientLastName) : review1.clientLastName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + serviceId;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (clientFirstName != null ? clientFirstName.hashCode() : 0);
        result = 31 * result + (clientLastName != null ? clientLastName.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProvideServiceReview{");
        sb.append("clientId=").append(clientId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", review='").append(review).append('\'');
        sb.append(", clientFirstName='").append(clientFirstName).append('\'');
        sb.append(", clientLastName='").append(clientLastName).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
