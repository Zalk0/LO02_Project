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
        String[] words = super.toString().split("_");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return result.toString().trim();
    }

    public int getValue() {
        return value;
    }
}
