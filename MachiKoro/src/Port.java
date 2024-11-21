import java.util.Scanner;

public class Port extends Site {
    {
        this.setDescription("You can add 2 to the roll result if it is greater than or equal to 10");
        this.setName("Port");
        this.setPrice(2);
    }
    public void playEffect(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add 2 to roll result?");
        String choice = scanner.nextLine();
        if (choice.equals("+")) {
            player.setRollResult(player.getRollResult() + 2);
            System.out.println("Roll result was increased by 2, current roll result: " + player.getRollResult());
        }
    }
}
