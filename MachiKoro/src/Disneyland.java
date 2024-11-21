public class Disneyland extends Site {
    {
        this.setDescription("You get extra turn after equal dice roll");
        this.setName("Disneyland");
        this.setPrice(16);
    }
    public void playEffect(Player player) {
        Game game = new Game();
        System.out.println("Double!!! One more turn");
        game.nextTurn(player);
    }
}
