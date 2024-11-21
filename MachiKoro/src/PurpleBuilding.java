import java.util.ArrayList;

public class PurpleBuilding extends CommerceBuilding {
    private final ArrayList<Integer> dropNumbers = new ArrayList<>();
    public ArrayList<Integer> getDropNumbers() {
        return dropNumbers;
    }
    public void playEffect(Player player, ArrayList<Player> players) {
        System.out.println("The " + this.getName() + " plays:");
    }
}
