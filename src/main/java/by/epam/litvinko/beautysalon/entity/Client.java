package by.epam.litvinko.beautysalon.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Client extends User {

    private int userId;
    private String phone;
    private BigDecimal currentAccount;
    private LocalDate dateOfBirthday;
    private boolean isRegular;

    public Client() {
        super.setRole(Role.CLIENT);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public boolean isRegular() {
        return isRegular;
    }

    public void setRegular(boolean regular) {
        isRegular = regular;
    }

    public BigDecimal getCurrentAccount() {
        return currentAccount;
    }

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

    public static Client.Builder newBuilder() {
        return new Client().new Builder();
    }

    public class Builder extends User.Builder {

        private Builder() {
        }

        public Client.Builder setUserId(int userId) {
            Client.this.userId = userId;
            return this;
        }

        public Client.Builder setPhone(String phone) {
            Client.this.phone = phone;
            return this;
        }

        public Client.Builder setDateOfBirthday(LocalDate dateOfBirthday) {
            Client.this.dateOfBirthday = dateOfBirthday;
            return this;
        }

        public Client.Builder setCurrentAccount(BigDecimal money) {
            Client.this.currentAccount = money;
            return this;
        }

        public Client.Builder setIsRegular(boolean isRegular) {
            Client.this.isRegular = isRegular;
            return this;
        }

        public Client build() {
            return Client.this;
        }
    }
}
