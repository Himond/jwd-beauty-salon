package by.epam.litvinko.beautysalon.entity;


/**
 * The type Provide service review.
 */
public class ProvideServiceReview extends AbstractEntity {

    private int clientId;
    private int serviceId;
    private String review;
    private String clientFirstName;
    private String clientLastName;
    private boolean isActive;

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
     * Gets service id.
     *
     * @return the service id
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets service id.
     *
     * @param serviceId the service id
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Gets review.
     *
     * @return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * Sets review.
     *
     * @param review the review
     */
    public void setReview(String review) {
        this.review = review;
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
     * Gets client first name.
     *
     * @return the client first name
     */
    public String getClientFirstName() {
        return clientFirstName;
    }

    /**
     * Sets client first name.
     *
     * @param clientFirstName the client first name
     */
    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    /**
     * Gets client last name.
     *
     * @return the client last name
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * Sets client last name.
     *
     * @param clientLastName the client last name
     */
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
