package epam.by.litvinko.beautysalon.entity;

import java.util.Arrays;

public class Master extends User{

    private long userId;
    private Position position;
    private String description;

    public Master() {
        super.setRole(Role.MASTER);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Master master = (Master) o;

        if (userId != master.userId) return false;
        if (position != master.position) return false;
        return description != null ? description.equals(master.description) : master.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Master{");
        sb.append("id='").append(getId()).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userName='").append(getUserName()).append('\'');
        sb.append(", password='").append(getPassword()).append('\'');
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", firstName='").append(getFirstName()).append('\'');
        sb.append(", lastName='").append(getLastName()).append('\'');
        sb.append(", isActive=").append(isActive());
        sb.append(", dateJoined=").append(getDateJoined());
        sb.append(", photo=").append(Arrays.toString(getPhoto()));
        sb.append(", position=").append(position);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Master.Builder newBuilder() {
        return new Master().new Builder();
    }

    public class Builder extends User.Builder {

        private Builder() {
        }

        public Master.Builder setUserId(long userId) {
            Master.this.userId = userId;
            return this;
        }

        public Master.Builder setPosition(Position position) {
            Master.this.position = position;
            return this;
        }

        public Master.Builder setDescription(String description) {
            Master.this.description = description;
            return this;
        }

        public Master build() {
            return Master.this;
        }
    }

}
