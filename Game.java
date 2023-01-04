import java.util.ArrayList;

public class Game {
    private int bigBlind;
    private int ante;
    private Table table;
    private int levelUp;
    private int roundcount;
    private int dealer;
    private double mainPot;
    private int anteRound;

    public Game(Table table, int bigBlind, int ante, int levelUp, int anteRound) {
        this.table = table;
        this.bigBlind = bigBlind;
        this.ante = ante;
        this.levelUp = levelUp;
        this.roundcount = 0;
        this.dealer = 0;
        this.mainPot = 0.0;
        this.anteRound = anteRound;
    }

    public void roundStart() {
        this.mainPot = 0.0;
        if (this.roundcount % this.levelUp == 0 && this.roundcount != 0) {
            this.bigBlind = this.bigBlind * 2;
        }
        System.out.println("---------------BLINDS AND ANTE--------------");
        if (this.dealer == this.table.playerList().size() - 1) {
            System.out.println(this.bigBlind + " big blind deposited by " + this.table.playerList().get(dealer));
            this.mainPot += this.table.removeChip(this.table.playerList().get(dealer), 1.0 * bigBlind);
            this.dealer = 0;
            System.out.println(this.bigBlind /2 + " small blind deposited by " + this.table.playerList().get(dealer) + "\n");
            this.mainPot += this.table.removeChip(this.table.playerList().get(dealer), 1.0 * bigBlind / 2);
        } else {
            System.out.println(this.bigBlind + " big blind deposited by " + this.table.playerList().get(dealer));
            this.mainPot += this.table.removeChip(this.table.playerList().get(dealer), 1.0 * bigBlind);
            System.out.println(this.bigBlind /2 + " small blind deposited by " + this.table.playerList().get(dealer+1) + "\n");
            this.mainPot += this.table.removeChip(this.table.playerList().get(dealer + 1), 1.0 * bigBlind / 2);
            this.dealer++;
        }

        if (this.anteRound <= 0) {
        } else if (this.roundcount / this.levelUp >= this.anteRound) {

            if (this.roundcount / this.levelUp > this.anteRound && this.roundcount % this.levelUp == 0) {
                this.ante = this.ante * 2;
            }
            System.out.println(this.ante + " ante deposited by everyone" + "\n");
            this.table.playerList().stream().forEach(i -> this.mainPot = +this.table.removeChip(i, 1.0 * this.ante));
        }

    }

    public void roundEnd(ArrayList<String> names) {
        for (String i : names) {
            this.table.addChip(i, this.mainPot / names.size());
        }
        this.mainPot = 0;
        this.roundcount++;

        // eliminating
        for (String y : this.table.playerList()) {
            if (this.table.getChip(y) <= 0) {
                this.table.removePlayer(y);
                System.out.println(y + " has been eliminated");
            }
        }

        System.out.println("------------Standings after round--------------");
        printTable();
    }

    public void deposit(String name, double value) {
        this.mainPot += this.table.removeChip(name, value);
    }

    public double getmainPot() {
        return this.mainPot;
    }

    public void printTable() {
        System.out.println(this.table);
    }

    public boolean playerChecker(String name) {
        return this.table.playerList().contains(name);
    }

    public void lastPlayer() {
        if (this.table.playerList().size() == 1) {
            System.out.println(this.table.playerList().get(0) + " won the game!");
        }
    }

    public int tableSize(){
        return this.table.playerList().size();
    }

}
