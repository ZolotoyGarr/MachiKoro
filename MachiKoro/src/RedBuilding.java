public class RedBuilding extends CommerceBuilding {
    public void playEffect(Player payer, Player recipient, int amount) {
        System.out.println("The " + this.getName() + " plays:");
        if (payer.getBank() == 0) {
            System.out.println("Player " + payer.getNumber() + " is out of money");
            return;
        }
        if (payer.getBank() < amount && payer.getBank() > 0) {
            recipient.setBank(recipient.getBank() + payer.getBank());
            System.out.println("Player " + recipient.getNumber() + " receives " + payer.getBank() + " coins from player " + payer.getNumber());
            payer.setBank(0);
        } else {
            int recipientAmount = recipient.getBank() + amount;
            recipient.setBank(recipientAmount);
            payer.setBank(payer.getBank() - amount);
            System.out.println("Player " + recipient.getNumber() + " receives " + amount + " coins from player " + payer.getNumber());
        }
    }
}
