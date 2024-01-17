package fr.alexiss.karmaka.enums;

/**
 * Represents the color of a card.
 */
public enum CardColor {
    BLUE, GREEN, RED, MOSAIC;

    /**
     * Returns the color of the card in French.
     *
     * @return the color of the card in French.
     */
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
