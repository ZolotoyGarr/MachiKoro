import java.util.ArrayList;

public class Bakery extends GreenBuilding {
    private final ArrayList<Integer> dropNumbers = new ArrayList<>();
    public Bakery() {
        this.setName("Bakery");
        this.setDescription("Take 1 coin from bank on 2-3 roll");
        this.setPrice(1);
        this.setAmount(1);
        dropNumbers.add(2);
        dropNumbers.add(3);
    }

    public ArrayList<Integer> getDropNumbers() {
        return dropNumbers;
    }
}
