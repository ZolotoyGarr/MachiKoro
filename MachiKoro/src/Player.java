import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private int number = 0;
    private int bank = 0;
    private int firstDiceResult = 0;
    private int secondDiceResult = 0;
    private int rollResult = 0;
    private int sequenceRoll = 0;
    private boolean built = false;

    private final ArrayList<Site> sites = new ArrayList<>();
    ArrayList<RedBuilding> redBuildings = new ArrayList<>();
    ArrayList<BlueBuilding> blueBuildings = new ArrayList<>();
    ArrayList<GreenBuilding> greenBuildings = new ArrayList<>();
    ArrayList<PurpleBuilding> purpleBuildings = new ArrayList<>();

    {
        this.sites.add(new Port());
        this.sites.add(new TrainStation());
        this.sites.add(new Hypermarket());
        this.sites.add(new Disneyland());
        this.sites.add(new RadioStation());
        this.sites.add(new Airport());
        this.setBank(3);
        this.blueBuildings.add(new Wheat());
        this.greenBuildings.add(new Bakery());
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public ArrayList<RedBuilding> getRedBuildings() {
        return redBuildings;
    }

    public ArrayList<BlueBuilding> getBlueBuildings() {
        return blueBuildings;
    }

    public ArrayList<GreenBuilding> getGreenBuildings() {
        return greenBuildings;
    }

    public ArrayList<PurpleBuilding> getPurpleBuildings() {
        return purpleBuildings;
    }

    public void setRedBuildings(ArrayList<RedBuilding> redBuildings) {
        this.redBuildings = redBuildings;
    }

    public void setBlueBuildings(ArrayList<BlueBuilding> blueBuildings) {
        this.blueBuildings = blueBuildings;
    }

    public void setGreenBuildings(ArrayList<GreenBuilding> greenBuildings) {
        this.greenBuildings = greenBuildings;
    }

    public void setPurpleBuildings(ArrayList<PurpleBuilding> purpleBuildings) {
        this.purpleBuildings = purpleBuildings;
    }


    public Player(int number) {
        this.setNumber(number);
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }

    public boolean isBuilt() {
        return built;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBank() {
        return this.bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getFirstDiceResult() {
        return this.firstDiceResult;
    }

    public void setFirstDiceResult(int result) {
        this.firstDiceResult = result;
    }

    public int getSecondDiceResult() {
        return this.secondDiceResult;
    }

    public void setSecondDiceResult(int result) {
        this.secondDiceResult = result;
    }

    public int getSequenceRoll() {
        return this.sequenceRoll;
    }

    public void setSequenceRoll(int sequenceRoll) {
        this.sequenceRoll = sequenceRoll;
    }

    public int getRollResult() {
        return this.rollResult;
    }

    public void setRollResult(int rollResult) {
        this.rollResult = rollResult;
    }

    public void rollDice() {
        Site playerTrainStation = this.sites.get(1);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        if (playerTrainStation.isReady()) {
            System.out.println("Do you want to roll 2 dice?");
            String choice = scanner.nextLine();
            if (choice.equals("+")) {
                setFirstDiceResult(random.nextInt(6) + 1);
                setSecondDiceResult(random.nextInt(6) + 1);
                this.setRollResult(this.getFirstDiceResult() + this.getSecondDiceResult());
                System.out.println("Player " + this.getNumber() + " roll " + this.getFirstDiceResult() + " on first cube and " + this.getSecondDiceResult() + " on second cube.");
                System.out.println("Player " + this.getNumber() + " roll result: " + this.getRollResult());
            } else {
                setFirstDiceResult(random.nextInt(6) + 1);
                setSecondDiceResult(0);
                this.setRollResult(this.getFirstDiceResult() + this.getSecondDiceResult());
                System.out.println("Player " + this.getNumber() + " roll " + this.getFirstDiceResult() + " on first cube.");
            }
        } else {
            setFirstDiceResult(random.nextInt(6) + 1);
            setSecondDiceResult(0);
            this.setRollResult(this.getFirstDiceResult() + this.getSecondDiceResult());
            System.out.println("Player " + this.getNumber() + " roll " + this.getFirstDiceResult() + " on first cube.");
        }
    }

    //если хватает денег
    public void buyCommerce(int slotNumber) {
        ShopWindow shopWindow = new ShopWindow();
        ArrayList<CommerceBuilding> currentCommerceSlot = shopWindow.getCommerceSlots().get(slotNumber).getBuildings();
        Building buyingBuilding = currentCommerceSlot.get(currentCommerceSlot.size() - 1);
        if (this.getBank() >= buyingBuilding.getPrice()) {
            if (buyingBuilding instanceof RedBuilding) {
                this.redBuildings.add((RedBuilding) buyingBuilding);
                System.out.println("Player " + this.getNumber() + " bought " + buyingBuilding.getName());
            } else if (buyingBuilding instanceof BlueBuilding) {
                this.blueBuildings.add((BlueBuilding) buyingBuilding);
                System.out.println("Player " + this.getNumber() + " bought " + buyingBuilding.getName());
            } else if (buyingBuilding instanceof GreenBuilding) {
                this.greenBuildings.add((GreenBuilding) buyingBuilding);
                System.out.println("Player " + this.getNumber() + " bought " + buyingBuilding.getName());
            } else {
                System.out.println("Choose another slot");
            }
            currentCommerceSlot.remove(currentCommerceSlot.size() - 1);
        } else {
            System.out.println("Player " + this.getNumber() + " have not enough money to buy " + buyingBuilding.getName() + ". Try to buy another building or skip turn.");
            Game game = new Game();
            game.buyOffer(this);
        }
    }

    public void buyPurple(int slotNumber) {
        ShopWindow shopWindow = new ShopWindow();
        ArrayList<PurpleBuilding> currentPurpleSlots = shopWindow.getPurpleSlots().get(slotNumber).getBuildings();
        PurpleBuilding buyingBuilding = currentPurpleSlots.get(currentPurpleSlots.size() - 1);
        if (this.getBank() >= buyingBuilding.getPrice()) {
            this.purpleBuildings.add(buyingBuilding);
            currentPurpleSlots.remove(currentPurpleSlots.size() - 1);
            System.out.println("Player " + this.getNumber() + " bought " + buyingBuilding.getName());
        } else {
            System.out.println("Player " + this.getNumber() + " have not enough money to buy " + buyingBuilding.getName() + ". Try to buy another building or skip turn.");
            Game game = new Game();
            game.buyOffer(this);
        }
    }

    public void buySite(int siteNumber) {
        Site currentSite = this.sites.get(siteNumber - 1);
        if (currentSite.isReady()) {
            System.out.println("This site is already ready, choose another one or skip turn");
        }
        if (this.getBank() >= currentSite.getPrice()) {
            currentSite.setReady(true);
            System.out.println("Player " + this.getNumber() + " bought " + currentSite.getName());
        } else {
            System.out.println("Player " + this.getNumber() + " have not enough money to buy " + currentSite.getName() + ". Try to buy another building or skip turn.");
            Game game = new Game();
            game.buyOffer(this);
        }
    }

    public void showBuildings() {
        Map<String, Integer> buildingCount = new HashMap<>();
        countBuildings(redBuildings, buildingCount);
        countBuildings(blueBuildings, buildingCount);
        countBuildings(greenBuildings, buildingCount);
        countBuildings(purpleBuildings, buildingCount);
        System.out.println("Buildings of player " + this.getNumber() + ":");
        for (Map.Entry<String, Integer> entry : buildingCount.entrySet()) {
            String buildingName = entry.getKey();
            int count = entry.getValue();
            System.out.println(buildingName + " x" + count);
        }
    }

    private <T extends CommerceBuilding> void countBuildings(ArrayList<T> buildings, Map<String, Integer> buildingCount) {
        for (T building : buildings) {
            String buildingName = building.getName();
            buildingCount.put(buildingName, buildingCount.getOrDefault(buildingName, 0) + 1);
        }
    }

    public void showSites() {
        System.out.println("Sites of player " + this.getNumber() + ":");
        boolean allSitesNoActive = true;
        for (Site site : this.getSites()) {
            if (site.isReady()) {
                System.out.println(site.getName() + " is active");
                allSitesNoActive = false;
            }
        }
        if (allSitesNoActive) {
            System.out.println("No active sites");
        }
    }
}