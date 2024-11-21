import java.util.*;

public class Game {
    private int numberOfPlayers;
    private int turn = 0;
    ArrayList<Player> players = new ArrayList<>();
    List<Player> playersCopy = new ArrayList<>();

    public void gameSetup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        numberOfPlayers = scanner.nextInt();
    }

    public void preparePlayers() {
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player(i));
        }
        System.out.println(numberOfPlayers + " players were registered.");
        playersCopy.addAll(players);
    }

    public Player getFirstTurn() {
        Random random = new Random();
        while (true) {
            int maxRoll = 0;
            List<Player> maxRollers = new ArrayList<>();

            for (Player player : playersCopy) {
                player.setSequenceRoll(random.nextInt(6) + 1);
                System.out.println("Player " + player.getNumber() + " rolled " + player.getSequenceRoll());

                if (player.getSequenceRoll() > maxRoll) {
                    maxRoll = player.getSequenceRoll();
                    maxRollers.clear();
                    maxRollers.add(player);
                } else if (player.getSequenceRoll() == maxRoll) {
                    maxRollers.add(player);
                }
            }
            if (maxRollers.size() == 1) {
                return maxRollers.get(0);
            }
            System.out.println("Tie between players with roll " + maxRoll + " Reroll...");
            playersCopy = new ArrayList<>(maxRollers);
        }
    }

    public void startGame() {
        final Player firstPlayer = getFirstTurn(); // Call once and store the result
        System.out.println("First player number : " + firstPlayer.getNumber());
        playersCopy.clear();
        int startIndex = players.indexOf(firstPlayer);
        for (int i = startIndex; i < players.size(); i++) {
            playersCopy.add(players.get(i));
        }
        for (int i = 0; i < startIndex; i++) {
            playersCopy.add(players.get(i));
        }
    }

    public void startTurns() {
        for (Player player : playersCopy) {
            System.out.println("Player's " + player.getNumber() + " turn start");
            this.nextTurn(player);
            if (this.checkWinner(player)) {
                break;
            }
        }
    }

    public void nextTurn(Player player) {
        this.setTurn(this.getTurn() + 1);
        player.rollDice();
        Site playerPort = player.getSites().get(0);
        Site playerDisneyLand = player.getSites().get(3);
        Site playerRadioStation = player.getSites().get(4);
        if (player.getRollResult() >= 10 && playerPort.isReady()) {
            playerPort.playEffect(player);
        }
        if (playerRadioStation.isReady() && !((RadioStation) playerRadioStation).isRerolled()) {
            playerRadioStation.playEffect(player);
        }
        playBuildings(player, players);
        ShopWindow shopWindow = new ShopWindow();
        shopWindow.showWindow();
        player.showBuildings();
        player.showSites();
        showBanks(players);
        buyOffer(player);
        if (player.getFirstDiceResult() == player.getSecondDiceResult()) {
            playerDisneyLand.playEffect(player);
        }
    }

    private boolean checkWinner(Player player) {
        Site playerPort = player.getSites().get(0);
        Site playerTrainStation = player.getSites().get(1);
        Site playerHypermarket = player.getSites().get(2);
        Site playerDisneyland = player.getSites().get(3);
        Site playerRadioStation = player.getSites().get(4);
        Site playerAirport = player.getSites().get(5);
        if (playerPort.isReady() && playerTrainStation.isReady() && playerHypermarket.isReady() && playerDisneyland.isReady() && playerRadioStation.isReady() && playerAirport.isReady()) {
            System.out.println("Player " + player.getNumber() + " is WINNER!!!!!!!!!!!");
            return true;
        }
        return false;
    }

    public void buyOffer(Player player) {
        System.out.println("Choose slot number to buy building/site or skip turn:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        ShopWindow shopWindow = new ShopWindow();

        if (choice.equals("skip")) {
            System.out.println("Player " + player.getNumber() + " skips turn.");
            return;
        }

        try {
            if (choice.matches("\\d+")) {
                int parsedChoice = Integer.parseInt(choice);
                if (shopWindow.commerceSlotNumbers.contains(parsedChoice)) {
                    player.buyCommerce(parsedChoice);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                    buyOffer(player);
                }
            } else if (shopWindow.purpleSlotNumbers.contains(choice)) {
                switch (choice) {
                    case "p1":
                        player.buyPurple(1);
                        return;
                    case "p2":
                        player.buyPurple(2);
                        return;
                    case "p3":
                        player.buyPurple(3);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        buyOffer(player);
                }
            } else {
                switch (choice) {
                    case "Port":
                        player.buySite(1);
                        return;
                    case "TrainStation":
                        player.buySite(2);
                        return;
                    case "Hypermarket":
                        player.buySite(3);
                        return;
                    case "Disneyland":
                        player.buySite(4);
                        return;
                    case "RadioStation":
                        player.buySite(5);
                        return;
                    case "Airport":
                        player.buySite(6);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        buyOffer(player);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid slot number or 'skip' to skip turn.");
            buyOffer(player);
        }
    }



    public void playBuildings(Player currentPlayer, ArrayList<Player> players) {
        playRed(currentPlayer, players);
        playBlue(currentPlayer, players);
        playGreen(currentPlayer);
        playPurple(currentPlayer, players);

    }

    public void playRed(Player payer, ArrayList<Player> players) {
        int rollResult = payer.getRollResult();
        boolean marker = false;
        for (Player player : players) {
            if (player.getNumber() == payer.getNumber()) {
                continue;
            }
            for (RedBuilding redBuilding : player.getRedBuildings()) {
                if (redBuilding.getDropNumbers().contains(rollResult)) {
                    int amount = redBuilding.getAmount();
                    redBuilding.playEffect(payer, player, amount);
                    marker = true;
                }
            }
        }
        if (marker) {
            showBanks(players);
        }
    }

    public void playBlue(Player currentPlayer, ArrayList<Player> players) {
        boolean marker = false;
        for (Player player : players) {
            for (BlueBuilding blueBuilding : player.getBlueBuildings()) {
                for (Integer dropNumber : blueBuilding.getDropNumbers()) {
                    if (currentPlayer.getRollResult() == dropNumber) {
                        blueBuilding.playEffect(player, blueBuilding.getAmount());
                        marker = true;
                    }
                }
            }
        }
        if (marker) {
            showBanks(players);
        }
    }

    public void playGreen(Player player) {
        for (GreenBuilding greenBuilding : player.getGreenBuildings()) {
            for (Integer dropNumber : greenBuilding.getDropNumbers()) {
                if (player.getRollResult() == dropNumber) {
                    greenBuilding.playEffect(player, greenBuilding.getAmount());
                    System.out.println("Player " + player.getNumber() + " bank: " + player.getBank());
                }
            }
        }
    }

    public void playPurple(Player player, ArrayList<Player> players) {
        boolean marker = false;
        for (PurpleBuilding purpleBuilding : player.getPurpleBuildings()) {
            for (Integer dropNumber : purpleBuilding.getDropNumbers()) {
                if (player.getRollResult() == dropNumber) {
                    purpleBuilding.playEffect(player, players);
                    marker = true;
                }
            }
        }
        if (marker) {
            showBanks(players);
        }
    }

    public void showBanks(ArrayList<Player> players) {
        for (Player player : players) {
            System.out.println("Player " + player.getNumber() + " bank: " + player.getBank());
        }
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameSetup();
        game.preparePlayers();
        game.startGame();
        game.startTurns();
    }
}
