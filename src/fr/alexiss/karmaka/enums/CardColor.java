package fr.alexiss.karmaka.enums;

public enum CardColor {
    BLUE, GREEN, RED, MOSAIC;

    @Override
    public String toString() {
        return switch (super.toString()) {
            case "BLUE" -> "Bleu";
            case "GREEN" -> "Vert";
            case "RED" -> "Rouge";
            case "MOSAIC" -> "Mosaïque";
            default -> super.toString();
        };
    }
}
