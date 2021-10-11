package by.epam.litvinko.beautysalon.entity;

/**
 * The type Master.
 */
public class Master extends User{

    private int userId;
    private Position position;
    private String description;

    /**
     * Instantiates a new Master.
     */
    public Master() {
        super.setRole(Role.MASTER);
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
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
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
        result = 31 * result + userId;
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
        sb.append(", photo=").append(getPhoto());
        sb.append(", position=").append(position);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * New builder master . builder.
     *
     * @return the master . builder
     */
    public static Master.Builder newBuilder() {
        return new Master().new Builder();
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
        public Master.Builder setUserId(int userId) {
            Master.this.userId = userId;
            return this;
        }

        /**
         * Sets position.
         *
         * @param position the position
         * @return the position
         */
        public Master.Builder setPosition(Position position) {
            Master.this.position = position;
            return this;
        }

        /**
         * Sets description.
         *
         * @param description the description
         * @return the description
         */
        public Master.Builder setDescription(String description) {
            Master.this.description = description;
            return this;
        }

        public Master build() {
            return Master.this;
        }
    }

}
