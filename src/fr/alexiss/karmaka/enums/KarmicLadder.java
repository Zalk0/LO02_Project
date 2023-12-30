package fr.alexiss.karmaka.enums;

public enum KarmicLadder {
    DUNG_BEETLE(4),
    SNAKE(5),
    WOLF(6),
    APE(7),
    TRANSCENDENCE(0);

    private final int value;

    KarmicLadder(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return switch (super.toString()) {
            case "DUNG_BEETLE" -> "Bousier";
            case "SNAKE" -> "Serpent";
            case "WOLF" -> "Loup";
            case "APE" -> "Singe";
            case "TRANSCENDENCE" -> "Transcendance";
            default -> super.toString();
        };
    }

    public int getValue() {
        return value;
    }
}
