import java.util.ArrayList;

public class Wheat extends BlueBuilding {
    private final ArrayList<Integer> dropNumbers = new ArrayList<>();
    public Wheat() {
        this.setName("Wheat");
        this.setDescription("Take 1 coin from bank on 1 roll");
        this.setPrice(1);
        this.setSymbol(Symbol.PLANT);
        this.setAmount(1);
        dropNumbers.add(1);
    }
    public ArrayList<Integer> getDropNumbers() {
        return dropNumbers;
    }
}
