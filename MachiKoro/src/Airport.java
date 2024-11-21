import java.util.Scanner;

public class Airport extends Site {
    {
        this.setDescription("You get 10 coins if you didn't build any buildings this turn");
        this.setName("Airport");
        this.setPrice(30);
    }
    public void playEffect(Player player) {
        if (!player.isBuilt()) {
            player.setBank(player.getBank() + 10);
        }
    }
}
