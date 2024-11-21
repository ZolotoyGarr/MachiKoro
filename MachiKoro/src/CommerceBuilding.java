import java.util.ArrayList;

public abstract class CommerceBuilding extends Building {
    private Symbol symbol;

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    private final ArrayList<Integer> dropNumbers = new ArrayList<>();

    public ArrayList<Integer> getDropNumbers() {
        return dropNumbers;
    }

    private int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
