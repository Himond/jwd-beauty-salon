package by.epam.litvinko.beautysalon.entity;

public enum Position {
    HAIRDRESSER(1,"hairdresser"),
    VISAGISTE(2,"visagiste"),
    BROWIST(3,"browist"),
    MANICURIST(4,"manicurist"),
    MASSEUR(5,"masseur");

    private String position;
    private int id;

    Position(int id, String position){
        this.position = position;
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public int getId(){
        return id;
    }
}
