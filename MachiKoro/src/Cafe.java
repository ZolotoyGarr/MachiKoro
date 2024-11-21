import java.util.ArrayList;

public class Cafe extends RedBuilding {
    private final ArrayList<Integer> dropNumbers = new ArrayList<>();
    public Cafe() {
        this.setName("Cafe");
        this.setDescription("Take 1 coin from the player who rolled the dice");
        this.setPrice(2);
        dropNumbers.add(3);
        this.setAmount(1);
        this.setSymbol(Symbol.CAFE);
    }

    public ArrayList<Integer> getDropNumbers() {
        return dropNumbers;
    }
}
