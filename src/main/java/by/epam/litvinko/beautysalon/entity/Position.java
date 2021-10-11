package by.epam.litvinko.beautysalon.entity;

/**
 * The enum Position.
 */
public enum Position {
    /**
     * Hairdresser position.
     */
    HAIRDRESSER(1,"hairdresser"),
    /**
     * Visagiste position.
     */
    VISAGISTE(2,"visagiste"),
    /**
     * Browist position.
     */
    BROWIST(3,"browist"),
    /**
     * Manicurist position.
     */
    MANICURIST(4,"manicurist"),
    /**
     * Masseur position.
     */
    MASSEUR(5,"masseur");

    private String position;
    private int id;

    Position(int id, String position){
        this.position = position;
        this.id = id;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public String getPosition() {
        return position;
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
