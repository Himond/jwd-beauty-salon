package by.epam.litvinko.beautysalon.service.dto;

import by.epam.litvinko.beautysalon.entity.Role;


public class UserDto extends AbstractDto{

    private int Id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private byte[] photo;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
