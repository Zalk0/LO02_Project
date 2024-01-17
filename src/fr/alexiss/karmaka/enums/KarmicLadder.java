package fr.alexiss.karmaka.enums;

/**
 * Represents the different levels of the karmic ladder.
 */
public enum KarmicLadder {
    DUNG_BEETLE(4),
    SNAKE(5),
    WOLF(6),
    APE(7),
    TRANSCENDENCE(0);

    private final int value;

    /**
     * Constructor.
     *
     * @param value initial value of the level.
     */
    KarmicLadder(int value) {
        this.value = value;
    }

    /**
     * Returns the karmic ladder level in French.
     *
     * @return the karmic ladder level in French.
     */
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

    /**
     * Returns the value of the level.
     *
     * @return the value of the level.
     */
    public int getValue() {
        return value;
    }
}
