package battleships.model;

public interface Field {
    String getSymbol();

    boolean isVisible();

    void hit();
}
