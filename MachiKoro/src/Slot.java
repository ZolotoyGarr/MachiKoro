import java.util.ArrayList;
import java.util.Collections;

class Slot<T extends CommerceBuilding> {
    private ArrayList<T> buildings;

    public Slot(ArrayList<T> buildings) {
        this.buildings = buildings;
        Collections.shuffle(this.buildings);
        // Перемешиваем здания случайным образом
    }

    public ArrayList<T> getBuildings() {
        return buildings;
    }

    public void addBuilding(T building) {
        if (buildings.size() < 10) {
            buildings.add(building);
        } else {
            System.out.println("Slot is full");
        }
    }
}
