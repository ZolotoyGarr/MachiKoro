import java.util.ArrayList;
import java.util.Scanner;

public class TVcenter extends PurpleBuilding {
    public TVcenter() {
        this.setName("TVcenter");
        this.setDescription("Take 5 coins from other player");
        this.setPrice(7);
        this.setSymbol(Symbol.UNIQUE);
        this.getDropNumbers().add(6);
    }

    @Override
    public void playEffect(Player recipient, ArrayList<Player> players) {
        System.out.println("The " + this.getName() + " plays:");
        System.out.println("Choose a player to steal 5 coins from him.");
        for (Player player : players) {
            if (player.getNumber() == recipient.getNumber()) {
                continue;
            }
            System.out.println("Player " + player.getNumber() + ": " + player.getBank() + " coins");
        }
        Scanner scanner = new Scanner(System.in);
        int playerNumberChoice = scanner.nextInt();
        Player payer = null;
        for (Player player : players) {
            if (player.getNumber() == playerNumberChoice) {
                payer = player;
                break;
            }
        }

        if (payer == null) {
            System.out.println("Invalid player number chosen.");
            return;
        }

        if (payer.getBank() == 0) {
            System.out.println("Player " + payer.getNumber() + " is out of money.");
            return;
        }
        if (payer.getBank() < 5 && payer.getBank() > 0) {
            recipient.setBank(payer.getBank());
            System.out.println("Player " + recipient.getNumber() + " receives " + 5 + " coins from player " + payer.getNumber());
            payer.setBank(0);
        } else {
            int recipientAmount = recipient.getBank() + 5;
            recipient.setBank(recipientAmount);
            payer.setBank(payer.getBank() - 5);
            System.out.println("Player " + recipient.getNumber() + " receives " + 5 + " coins from player " + payer.getNumber());
        }
    }
}

