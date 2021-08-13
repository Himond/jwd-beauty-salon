package epam.by.litvinko.beautysalon.entity;

public enum Position {
    HAIRDRESSER("hairdresser"),
    VISAGISTE("visagiste"),
    BROWIST("browist"),
    MANICURIST("manicurist"),
    MASSEUR("masseur");

    private String position;

    Position(String position){
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
