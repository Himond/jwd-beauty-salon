package epam.by.litvinko.beautysalon.entity;

public enum Role {
    ADMINISTRATOR("administrator"),
    MASTER("master"),
    CLIENT("client");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
