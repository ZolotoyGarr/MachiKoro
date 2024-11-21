import java.util.Scanner;

public class RadioStation extends Site {
    {
        this.setDescription("You can re roll dices");
        this.setName("RadioStation");
        this.setPrice(22);
    }
    public boolean rerolled = false;

    public void setRerolled(boolean rerolled) {
        this.rerolled = rerolled;
    }

    public boolean isRerolled() {
        return rerolled;
    }

    public void playEffect(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to re roll the dice?");
        String choice = scanner.nextLine();
        if (choice.equals("+")) {
            this.setRerolled(true);
            player.rollDice();
        }
    }
}
