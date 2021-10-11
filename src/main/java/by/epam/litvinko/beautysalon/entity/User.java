package by.epam.litvinko.beautysalon.entity;

import java.time.LocalDate;

/**
 * The type User.
 */
public class User extends AbstractEntity {

    private Role role;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private LocalDate dateJoined;
    private String photo;

    /**
     * Instantiates a new User.
     */
    public User() {
        this.role = Role.CLIENT;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Gets date joined.
     *
     * @return the date joined
     */
    public LocalDate getDateJoined() {
        return dateJoined;
    }

    /**
     * Sets date joined.
     *
     * @param dateJoined the date joined
     */
    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (isActive != user.isActive) return false;
        if (role != user.role) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (dateJoined != null ? !dateJoined.equals(user.dateJoined) : user.dateJoined != null) return false;
        return photo != null ? photo.equals(user.photo) : user.photo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (dateJoined != null ? dateJoined.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("role=").append(role);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", dateJoined=").append(dateJoined);
        sb.append(", photo=").append(photo);
        sb.append('}');
        return sb.toString();
    }

    /**
     * New builder user . builder.
     *
     * @return the user . builder
     */
    public static User.Builder newBuilder(){
        return new User().new Builder();
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
         * Set id user . builder.
         *
         * @param id the id
         * @return the user . builder
         */
        public User.Builder setId(int id){
            User.this.setId(id);
            return this;
        }

        /**
         * Set role user . builder.
         *
         * @param role the role
         * @return the user . builder
         */
        public User.Builder setRole(Role role){
            User.this.role = role;
            return this;
        }

        /**
         * Set user name user . builder.
         *
         * @param userName the user name
         * @return the user . builder
         */
        public User.Builder setUserName(String userName){
            User.this.userName = userName;
            return this;
        }

        /**
         * Set password user . builder.
         *
         * @param password the password
         * @return the user . builder
         */
        public User.Builder setPassword(String password){
            User.this.password = password;
            return this;
        }

        /**
         * Set email user . builder.
         *
         * @param email the email
         * @return the user . builder
         */
        public User.Builder setEmail(String email){
            User.this.email = email;
            return this;
        }

        /**
         * Set first name user . builder.
         *
         * @param firstName the first name
         * @return the user . builder
         */
        public User.Builder setFirstName(String firstName){
            User.this.firstName = firstName;
            return this;
        }

        /**
         * Set last name user . builder.
         *
         * @param lastName the last name
         * @return the user . builder
         */
        public User.Builder setLastName(String lastName){
            User.this.lastName = lastName;
            return this;
        }

        /**
         * Set is active user . builder.
         *
         * @param isActive the is active
         * @return the user . builder
         */
        public User.Builder setIsActive(boolean isActive){
            User.this.isActive = isActive;
            return this;
        }

        /**
         * Set date joined user . builder.
         *
         * @param dateJoined the date joined
         * @return the user . builder
         */
        public User.Builder setDateJoined(LocalDate dateJoined){
            User.this.dateJoined = dateJoined;
            return this;
        }

        /**
         * Set photo user . builder.
         *
         * @param photo the photo
         * @return the user . builder
         */
        public User.Builder setPhoto(String photo){
            User.this.photo = photo;
            return this;
        }

        /**
         * Build user.
         *
         * @return the user
         */
        public User build(){
            return User.this;
        }

    }
}
