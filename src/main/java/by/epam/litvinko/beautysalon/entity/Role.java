package by.epam.litvinko.beautysalon.entity;

public enum Role {
    ADMINISTRATOR(1, "administrator"),
    MASTER(2, "master"),
    CLIENT(3, "client");

    private String role;
    private int id;

    Role(int id, String role){
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public int getId(){
        return id;
    }
}
