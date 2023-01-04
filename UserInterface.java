import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Game game;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    // Pregame settings, player number, initial values
    public void preGame() {
        // Define table
        System.out.println("How much is the initial stack per player?");
        Table table = new Table(Integer.valueOf(this.scanner.nextLine()));
        System.out.println("Please enter player names, after you done type: end");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            table.addPlayer(input);
        }

        // Define game
        System.out.println("Please enter initial BigBlind:");
        int bigBlind = Integer.valueOf(scanner.nextLine());
        System.out.println("Please enter how many rounds a level takes:");
        int levelUp = Integer.valueOf(scanner.nextLine());
        System.out.println("Please enter initial ante value, if you don't want ante, enter 0");
        int ante = Integer.valueOf(scanner.nextLine());
        int anteRound = 0;
        if (ante > 0) {
            System.out.println("Please enter from which level do you want ante");
            anteRound = Integer.valueOf(scanner.nextLine());
        }

        this.game = new Game(table, bigBlind, ante, levelUp, anteRound);
    }

    // Game

    public void start() {
        while (true) {

            // Round start
            this.game.roundStart();
            // Betting
            System.out.println("If anybody wants to bet type: name value");
            System.out.println("When betting ends type: end");
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("end")) {
                    break;
                }
                String parts[] = input.split(" ");
                if (this.game.playerChecker(parts[0])) {
                    this.game.deposit(parts[0], Integer.parseInt(parts[1]));
                } else {
                    System.out.println("Invalid input");
                }
            }

            // Round end
            System.out.println("Please enter the winner(s), after all winner entered type: end");
            ArrayList<String> winners = new ArrayList<>();
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("end")) {
                    break;
                }
                winners.add(input);
            }
            this.game.roundEnd(winners);

            // Game end
            if (this.game.tableSize() == 1){
            this.game.lastPlayer();
            break;
            }
            
        }

    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.preGame();
        ui.start();

    }

}
