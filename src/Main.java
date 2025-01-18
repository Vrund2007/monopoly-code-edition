import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Codeopoly!");

        Game game = new Game();
        game.startGame();
    }
}

class Game {
    Dice dice = new Dice();
    Board board = new Board();
    Player[] players;

    Game() {
        this.players = new Player[4]; // Initializing 4 players
    }

    void startGame() {
        Scanner sc = new Scanner(System.in);

        // Initialize Players and names
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter Player " + (i + 1) + " name: ");
            String name = sc.nextLine();
            players[i] = new Player(name, 1500);
        }

        int turn = 0;
        boolean gameover = false;

        // Game loop
        while (!gameover) {
            Player currentPlayer = players[turn % 4];
            if (currentPlayer.isBankrupt()) {
                // Skip bankrupt players
                System.out.println(currentPlayer.getName() + " is bankrupt and cannot take a turn.");
                turn++;
                continue;
            }

            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");

            // Check if player has properties to upgrade
            if (currentPlayer.hasProperties()) {
                // Ask for upgrade option before rolling the dice
                System.out.print("Do you want to upgrade a property? (yes/no): ");
                String upgradeChoice = sc.nextLine();
                if (upgradeChoice.equalsIgnoreCase("yes")) {
                    upgradeProperty(sc, currentPlayer);
                }
            } else {
                System.out.println("You have no properties to upgrade.");
            }

            System.out.print("Press ENTER to roll the dice...");
            sc.nextLine();
            System.out.println();

            int diceRoll = dice.roll();
            System.out.println(currentPlayer.getName() + " rolled a " + diceRoll);

            // Move the player
            currentPlayer.move(diceRoll, board.getBoardSize());

            // Determine the current space
            Property currentSpace = board.getSpace(currentPlayer.getPosition());
            System.out.println(currentPlayer.getName() + " landed on: " + currentSpace.getName());
            System.out.println();

            // Check if the space is a property
            if (currentSpace.getCost() > 0) {
                if (!currentSpace.isOwned()) {
                    System.out.println("The property is unowned and costs " + currentSpace.getCost() + ".");

                    if (currentPlayer.getBalance() >= currentSpace.getCost()) {
                        System.out.print("Do you want to buy it? (yes/no): ");
                        String choice = sc.nextLine();
                        System.out.println();

                        if (choice.equalsIgnoreCase("yes")) {
                            currentPlayer.updateBalance(-currentSpace.getCost());
                            currentSpace.setOwner(currentPlayer);
                            currentPlayer.addProperty(currentSpace); // Add property to player
                            System.out.println(currentPlayer.getName() + " bought " + currentSpace.getName() + "!");
                            System.out.println("Remaining balance: " + currentPlayer.getBalance());
                            System.out.println();
                        }
                    } else {
                        System.out.println("You don't have enough money to buy this property.");
                    }
                } else {
                    Player propertyOwner = currentSpace.getOwner();
                    System.out.println("The property is owned by " + propertyOwner.getName() + ".");

                    if (propertyOwner != currentPlayer) {
                        int rent = currentSpace.getRent();
                        System.out.println("You need to pay a rent of " + rent + ".");

                        if (currentPlayer.getBalance() >= rent) {
                            currentPlayer.updateBalance(-rent);
                            propertyOwner.updateBalance(rent);
                            System.out.println(currentPlayer.getName() + " paid " + rent + " to " + propertyOwner.getName() + ".");
                        } else {
                            System.out.println(currentPlayer.getName() + " cannot afford the rent of " + rent + ".");
                            handleBankruptcy(currentPlayer, propertyOwner);
                        }
                    } else {
                        System.out.println("This is your own property.");
                    }
                }
            } else {
                System.out.println("This space is a special tile (e.g., Start, Income Tax, Jail, etc.).");
            }

            // Check for game over condition
            int activePlayersCount = 0;
            for (Player player : players) {
                if (!player.isBankrupt()) {
                    activePlayersCount++;
                }
            }

            if (activePlayersCount <= 1) {
                gameover = true;
                System.out.println("\nThe game is over!");
                break;
            }

            // End turn
            turn++;
            System.out.println();
            System.out.println("End of " + currentPlayer.getName() + "'s turn.");
        }

        // Declare the winner
        for (Player player : players) {
            if (!player.isBankrupt()) {
                System.out.println("\nCongratulations! " + player.getName() + " is the winner!");
            }
        }

        sc.close();
    }

    void handleBankruptcy(Player bankruptPlayer, Player creditor) {
        // Transfer properties to the creditor
        for (int i = 0; i < board.getBoardSize(); i++) {
            Property property = board.getSpace(i);
            if (property.getOwner() == bankruptPlayer) {
                property.setOwner(creditor);
                System.out.println(creditor.getName() + " now owns " + property.getName() + ".");
            }
        }

        // Set the player as bankrupt
        bankruptPlayer.setBankrupt(true);
    }

    void upgradeProperty(Scanner sc, Player player) {
        // List owned properties
        System.out.println("You own the following properties. Please enter the property number to upgrade: ");
        for (int i = 0; i < player.propertyCount; i++) {
            System.out.println((i + 1) + ". " + player.ownedProperties[i].getName());
        }

        System.out.print("Your Choice: ");
        int propertyChoice = Integer.parseInt(sc.nextLine()) - 1;
        if (propertyChoice >= 0 && propertyChoice < player.propertyCount) {
            Property selectedProperty = player.ownedProperties[propertyChoice];

            if (selectedProperty.canUpgrade()) {
                System.out.println("Upgrading " + selectedProperty.getName());
                int upgradeCost = selectedProperty.getCost() / 2; // Cost of upgrading property

                if (player.getBalance() >= upgradeCost) {
                    player.updateBalance(-upgradeCost);
                    selectedProperty.upgradeProperty();
                } else {
                    System.out.println("You don't have enough money to upgrade the property.");
                }
            } else {
                System.out.println("This property cannot be upgraded further.");
            }
        } else {
            System.out.println("Invalid property selection.");
        }
    }
}

class Player {
    String name;
    int balance;
    int position;
    boolean bankrupt;
    Property[] ownedProperties; // Array of properties owned by the player
    int propertyCount; // Counter for the number of owned properties

    Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.position = 0;
        this.bankrupt = false;
        this.ownedProperties = new Property[10]; // Max 10 properties for now
        this.propertyCount = 0; // Initialize property count
    }

    // Method to set bankruptcy status
    void setBankrupt(boolean status) {
        this.bankrupt = status;
        if (status) {
            System.out.println(name + " is bankrupt!");
        }
    }

    boolean hasProperties() {
        return propertyCount > 0; // Check if player has any properties
    }

    void addProperty(Property property) {
        if (propertyCount < ownedProperties.length) {
            ownedProperties[propertyCount++] = property; // Add property to owned array
        }
    }

    void updateBalance(int amount) {
        this.balance += amount;
        if (this.balance < 0) {
            this.setBankrupt(true);
        }
        System.out.println(name + "'s balance is now: " + balance);
    }

    boolean isBankrupt() {
        return bankrupt;
    }

    String getName() {
        return name;
    }

    int getBalance() {
        return balance;
    }

    int getPosition() {
        return position;
    }

    void move(int diceRoll, int boardSize) {
        this.position = (this.position + diceRoll) % boardSize;
        System.out.println(name + " moved to position: " + this.position);
    }
}

class Dice {
    int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}

class Board {
    Property[] spaces;

    Board() {
        spaces = new Property[40]; // Standard board with 40 spaces
        initializeBoard();
    }

    void initializeBoard() {
        spaces[0] = new Property("Start", 0, 0);
        spaces[1] = new Property("Mumbai", 200, 16);
        spaces[2] = new Property("Delhi", 220, 18);
        spaces[3] = new Property("Income Tax", 0, 0);
        spaces[4] = new Property("Chennai", 300, 25);
        spaces[5] = new Property("Kolkata", 240, 20);
        spaces[6] = new Property("Chance", 0, 0);
        spaces[7] = new Property("Bangalore", 250, 22);
        spaces[8] = new Property("Hyderabad", 260, 24);
        spaces[9] = new Property("Jail", 0, 0);
        spaces[10] = new Property("Ahmedabad", 280, 26);
        spaces[11] = new Property("Pune", 300, 28);
        spaces[12] = new Property("Lucknow", 320, 30);
        spaces[13] = new Property("Jaipur", 340, 32);
        spaces[14] = new Property("Goa", 360, 35);
        spaces[15] = new Property("Chandigarh", 380, 38);
        spaces[16] = new Property("Bhopal", 400, 40);
        spaces[17] = new Property("Kochi", 420, 42);
        spaces[18] = new Property("Patna", 440, 44);
        spaces[19] = new Property("Free Parking", 0, 0);
        spaces[20] = new Property("Indore", 460, 46);
        spaces[21] = new Property("Nagpur", 480, 48);
        spaces[22] = new Property("Lucknow", 500, 50);
        spaces[23] = new Property("Kanpur", 520, 52);
        spaces[24] = new Property("Ranchi", 540, 54);
        spaces[25] = new Property("Varanasi", 560, 56);
        spaces[26] = new Property("Jabalpur", 580, 58);
        spaces[27] = new Property("Surat", 600, 60);
        spaces[28] = new Property("Vadodara", 620, 62);
        spaces[29] = new Property("Agra", 640, 64);
        spaces[30] = new Property("Go to Jail", 0, 0);
        spaces[31] = new Property("Srinagar", 660, 66);
        spaces[32] = new Property("Shimla", 680, 68);
        spaces[33] = new Property("Dehradun", 700, 70);
        spaces[34] = new Property("Mysore", 720, 72);
        spaces[35] = new Property("Raipur", 740, 74);
        spaces[36] = new Property("Amritsar", 760, 76);
        spaces[37] = new Property("Udaipur", 780, 78);
        spaces[38] = new Property("Dhanbad", 800, 80);
        spaces[39] = new Property("Kolkata", 820, 82);
    }

    Property getSpace(int position) {
        return spaces[position];
    }

    int getBoardSize() {
        return spaces.length;
    }
}

class Property {
    String name;
    int cost;
    int rent;
    Player owner;

    Property(String name, int cost, int rent) {
        this.name = name;
        this.cost = cost;
        this.rent = rent;
        this.owner = null;
    }

    String getName() {
        return name;
    }

    int getCost() {
        return cost;
    }

    int getRent() {
        return rent;
    }

    Player getOwner() {
        return owner;
    }

    void setOwner(Player owner) {
        this.owner = owner;
    }

    boolean isOwned() {
        return owner != null;
    }

    boolean canUpgrade() {
        return cost > 200 && rent < 100; // Simple condition for upgrade
    }

    void upgradeProperty() {
        this.rent *= 2; // Doubling rent as an example of upgrading
        System.out.println(name + " has been upgraded! New rent: " + rent);
    }
}
