package by.epam.litvinko.beautysalon.entity;

/**
 * The enum Role.
 */
public enum Role {
    /**
     * Administrator role.
     */
    ADMINISTRATOR(1, "administrator"),
    /**
     * Master role.
     */
    MASTER(2, "master"),
    /**
     * Client role.
     */
    CLIENT(3, "client"),
    /**
     * Guest role.
     */
    GUEST(4, "guest");

    private String role;
    private int id;

    Role(int id, String role){
        this.role = role;
        this.id = id;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getId(){
        return id;
    }
}
