import java.util.ArrayList;
import java.util.HashMap;

public class Table {

    private HashMap<String, Double> players;
    private Double initialStack;

    public Table(int initialStack) {
        this.players = new HashMap<>();
        this.initialStack = 1.0 * initialStack;
    }

    public void addPlayer(String name) {
        this.players.put(name, initialStack);
    }

    public void removePlayer(String name) {
        this.players.remove(name);
    }

    public double removeChip(String name, Double value) {
        if (this.players.containsKey(name) && this.players.get(name) >= value) {
            this.players.put(name, this.players.get(name) - 1.0 * value);
            return value;
        } else if (this.players.containsKey(name) && this.players.get(name) <= value) {
            System.out.println(name + " doesn't have enough chip, called ALL IN with: " + this.players.get(name));
            double holder = this.players.get(name);
            this.players.put(name, 0.0);
            return holder;
        } else {
            System.out.println("There is no such player as " + name);
            return 0.0;
        }
    }

    public void addChip(String name, double value) {
        if (this.players.containsKey(name)) {
            this.players.put(name, this.players.get(name) + value);
        } else {
            System.out.println("There is no such player as " + name);
        }
    }

    public void eliminate() {
        for (String i : this.players.keySet()) {
            if (this.players.get(i) >= 0.0) {
                this.players.remove(i);
            }
        }
    }

    public double getChip(String name) {
        return this.players.get(name);
    }

    @Override
    public String toString() {
        String output = "";
        for (String i : this.players.keySet()) {
            output += i + " - " + this.players.get(i) + "\n";
        }

        return output;
    }

    public ArrayList<String> playerList() {
        ArrayList<String> playerList = new ArrayList<>();
        for (String i : this.players.keySet()) {
            playerList.add(i);
        }
        return playerList;
    }

}