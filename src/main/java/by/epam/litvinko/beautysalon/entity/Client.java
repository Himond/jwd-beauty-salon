package by.epam.litvinko.beautysalon.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Client.
 */
public class Client extends User {

    private int userId;
    private String phone;
    private BigDecimal currentAccount;
    private LocalDate dateOfBirthday;
    private boolean isRegular;

    /**
     * Instantiates a new Client.
     */
    public Client() {
        super.setRole(Role.CLIENT);
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets date of birthday.
     *
     * @return the date of birthday
     */
    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    /**
     * Sets date of birthday.
     *
     * @param dateOfBirthday the date of birthday
     */
    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    /**
     * Is regular boolean.
     *
     * @return the boolean
     */
    public boolean isRegular() {
        return isRegular;
    }

    /**
     * Sets regular.
     *
     * @param regular the regular
     */
    public void setRegular(boolean regular) {
        isRegular = regular;
    }

    /**
     * Gets current account.
     *
     * @return the current account
     */
    public BigDecimal getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Sets current account.
     *
     * @param currentAccount the current account
     */
    public void setCurrentAccount(BigDecimal currentAccount) {
        this.currentAccount = currentAccount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (userId != client.userId) return false;
        if (isRegular != client.isRegular) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (currentAccount != null ? !currentAccount.equals(client.currentAccount) : client.currentAccount != null)
            return false;
        return dateOfBirthday != null ? dateOfBirthday.equals(client.dateOfBirthday) : client.dateOfBirthday == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (currentAccount != null ? currentAccount.hashCode() : 0);
        result = 31 * result + (dateOfBirthday != null ? dateOfBirthday.hashCode() : 0);
        result = 31 * result + (isRegular ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id='").append(getId()).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userName='").append(getUserName()).append('\'');
        sb.append(", password='").append(getPassword()).append('\'');
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", firstName='").append(getFirstName()).append('\'');
        sb.append(", lastName='").append(getLastName()).append('\'');
        sb.append(", isActive=").append(isActive());
        sb.append(", dateJoined=").append(getDateJoined());
        sb.append(", photo=").append(getPhoto());
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", currentAccount='").append(currentAccount).append('\'');
        sb.append(", dateOfBirthday=").append(dateOfBirthday);
        sb.append(", isRegular=").append(isRegular);
        sb.append('}');
        return sb.toString();
    }

    /**
     * New builder client . builder.
     *
     * @return the client . builder
     */
    public static Client.Builder newBuilder() {
        return new Client().new Builder();
    }

    /**
     * The type Builder.
     */
    public class Builder extends User.Builder {

        private Builder() {
        }

        /**
         * Sets user id.
         *
         * @param userId the user id
         * @return the user id
         */
        public Client.Builder setUserId(int userId) {
            Client.this.userId = userId;
            return this;
        }

        /**
         * Sets phone.
         *
         * @param phone the phone
         * @return the phone
         */
        public Client.Builder setPhone(String phone) {
            Client.this.phone = phone;
            return this;
        }

        /**
         * Sets date of birthday.
         *
         * @param dateOfBirthday the date of birthday
         * @return the date of birthday
         */
        public Client.Builder setDateOfBirthday(LocalDate dateOfBirthday) {
            Client.this.dateOfBirthday = dateOfBirthday;
            return this;
        }

        /**
         * Sets current account.
         *
         * @param money the money
         * @return the current account
         */
        public Client.Builder setCurrentAccount(BigDecimal money) {
            Client.this.currentAccount = money;
            return this;
        }

        /**
         * Sets is regular.
         *
         * @param isRegular the is regular
         * @return the is regular
         */
        public Client.Builder setIsRegular(boolean isRegular) {
            Client.this.isRegular = isRegular;
            return this;
        }

        public Client build() {
            return Client.this;
        }
    }
}
