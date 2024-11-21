public class BlueBuilding extends CommerceBuilding {

    public void playEffect(Player player, int amount) {
        System.out.println("The " + this.getName() + " plays:");
        player.setBank(player.getBank() + amount);
        System.out.println("Player " + player.getNumber() + " receives " + amount + " coins");
    }
}
