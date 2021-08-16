package by.epam.litvinko.beautysalon.entity;

import java.time.LocalDate;
import java.util.Arrays;

public class User extends Entity{

    private Role role;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private LocalDate dateJoined;
    private byte[] photo;

    public User() {
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
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
        return Arrays.equals(photo, user.photo);
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
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("role=").append(role);
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", dateJoined=").append(dateJoined);
        sb.append(", photo=").append(Arrays.toString(photo));
        sb.append('}');
        return sb.toString();
    }

    public static User.Builder newBuilder(){
        return new User().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public User.Builder setID(int id){
            User.this.setId(id);
            return this;
        }

        public User.Builder setRole(Role role){
            User.this.role = role;
            return this;
        }

        public User.Builder setUserName(String userName){
            User.this.userName = userName;
            return this;
        }

        public User.Builder setPassword(String password){
            User.this.password = password;
            return this;
        }

        public User.Builder setEmail(String email){
            User.this.email = email;
            return this;
        }

        public User.Builder setFirstName(String firstName){
            User.this.firstName = firstName;
            return this;
        }

        public User.Builder setLastName(String lastName){
            User.this.lastName = lastName;
            return this;
        }

        public User.Builder setIsActive(boolean isActive){
            User.this.isActive = isActive;
            return this;
        }

        public User.Builder setDateJoined(LocalDate dateJoined){
            User.this.dateJoined = dateJoined;
            return this;
        }

        public User.Builder setPhoto(byte[] photo){
            User.this.photo = photo;
            return this;
        }

        public User build(){
            return User.this;
        }

    }
}
