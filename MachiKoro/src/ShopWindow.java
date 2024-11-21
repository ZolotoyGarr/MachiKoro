import java.util.ArrayList;

public class ShopWindow {
    private int commerceSlotsNumber = 12;
    private int purpleSlotsNumber = 3;
    private int slotCapacity = 10;
    ArrayList<Integer> commerceSlotNumbers = new ArrayList<>();
    ArrayList<String> purpleSlotNumbers = new ArrayList<>();
    ArrayList<RedBuilding> redTypes = new ArrayList<>();
    ArrayList<BlueBuilding> blueTypes = new ArrayList<>();
    ArrayList<GreenBuilding> greenTypes = new ArrayList<>();
    ArrayList<PurpleBuilding> purpleTypes = new ArrayList<>();
    private final ArrayList<Slot<CommerceBuilding>> commerceSlots = new ArrayList<>();
    private final ArrayList<Slot<PurpleBuilding>> purpleSlots = new ArrayList<>();

    public ArrayList<Slot<CommerceBuilding>> getCommerceSlots() {
        return commerceSlots;
    }

    public ArrayList<Slot<PurpleBuilding>> getPurpleSlots() {
        return purpleSlots;
    }

    {
        //Добавить фабрики
        redTypes.add(new Cafe());
        redTypes.add(new Restaurant());
        blueTypes.add(new Wheat());
        blueTypes.add(new Forest());
        greenTypes.add(new Bakery());
        greenTypes.add(new Shop());
        purpleTypes.add(new TVcenter());
        purpleTypes.add(new Stadium());
        purpleTypes.add(new BusinessCenter());
        for (int i = 0; i < 12; i++) {
            commerceSlotNumbers.add(i + 1);
        }
        purpleSlotNumbers.add("p1");
        purpleSlotNumbers.add("p2");
        purpleSlotNumbers.add("p3");
        fillCommerce();
        fillPurple();
    }

    public void showWindow() {
        System.out.println("-------------------Shop window-------------------");
        for (int i = 0; i < commerceSlots.size(); i++) {
            ArrayList<CommerceBuilding> commerceBuildings = commerceSlots.get(i).getBuildings();
            System.out.println("Slot " + (i + 1) + " : " + commerceBuildings.get(commerceBuildings.size() - 1).getName());
        }
        System.out.println("-------------------Purple buildings-------------------");
        for (int i = 0; i < purpleSlots.size(); i++) {
            ArrayList<PurpleBuilding> purpleBuildings = purpleSlots.get(i).getBuildings();
            System.out.println("Slot " + (i + 1) + " : " + purpleBuildings.get(purpleBuildings.size() - 1).getName());
        }
        System.out.println("------------------------------------------------------");
    }

    private void fillCommerce() {
        for (int i = 0; i < commerceSlotsNumber; i++) {
            ArrayList<CommerceBuilding> buildings = new ArrayList<>();
            for (int j = 0; j < slotCapacity; j++) {
                int randomType = (int) (Math.random() * 3);
                switch (randomType) {
                    case 0:
                        buildings.add(redTypes.get((int) (Math.random() * redTypes.size())));
                        break;
                    case 1:
                        buildings.add(blueTypes.get((int) (Math.random() * blueTypes.size())));
                        break;
                    case 2:
                        buildings.add(greenTypes.get((int) (Math.random() * greenTypes.size())));
                        break;
                }
            }
            commerceSlots.add(new Slot<>(buildings));
        }
    }

    private void fillPurple() {
        for (int i = 0; i < purpleSlotsNumber; i++) {
            ArrayList<PurpleBuilding> buildings = new ArrayList<>();
            for (int j = 0; j < slotCapacity; j++) {
                buildings.add(purpleTypes.get((int) (Math.random() * purpleTypes.size())));
            }
            purpleSlots.add(new Slot<>(buildings));
        }
    }

    public int getSlotCapacity() {
        return slotCapacity;
    }

    public int getCommerceSlotsNumber() {
        return commerceSlotsNumber;
    }

    public int getPurpleSlotsNumber() {
        return purpleSlotsNumber;
    }

    public void setPurpleSlotsNumber(int purpleSlotsNumber) {
        this.purpleSlotsNumber = purpleSlotsNumber;
    }

    public void setCommerceSlotsNumber(int commerceSlotsNumber) {
        this.commerceSlotsNumber = commerceSlotsNumber;
    }

    public void setSlotCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    public ShopWindow() {

    }
}
