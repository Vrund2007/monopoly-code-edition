import java.util.*;
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Codeopoly - Code empire, fix the errors, and win👑");

        Game game = new Game();
        game.startGame();
    }

}

class Game{
	Dice dice = new Dice();
	Board board = new Board();
    Player [] players;

    Game() {
		this.players = new Player[4]; //Initializing 4 players
    }

    void startGame(){
        Scanner sc = new Scanner(System.in);

        //Initialize Players and names
        for (int i = 0; i<4; i++){
            System.out.println("Enter Player "+(i+1)+" name : ");
            String name = sc.nextLine();
            players[i] = new Player(name, 1500);
        }
		
		int turn = 0;
		boolean gameover = true;
		while(gameover) {
			Player currentPlayer = players[turn % 4];
			System.out.println("It's " + currentPlayer.getName() + "'s turn!");
			
			System.out.println("Press any key to roll a Dice");
			sc.nextLine();
			
			int diceroll = dice.roll();
			System.out.println(currentPlayer.getName() + " rolled a " + diceroll);
			
			
			// Move the player
            currentPlayer.move(diceroll, board.getSize());
			
			
			
			
			
			gameover = false;
		}
    }
	
	
}

class Player{
    String name;
    int balance;
    int position;
    boolean bankrupt;
    int propertyCount;
	Dice dice = new Dice();

    Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.position = 0;
        this.bankrupt = false;
        this.get();
    }
    void get(){
        System.out.println("name = "+ this.name);
        System.out.println("balance "+ this.balance);
    }
	
	//Move Player
	void move(int diceRoll, int boardSize) {
		this.position = (this.position + diceRoll) % boardSize;
        if (this.position < 0) {
            this.position += boardSize;
        }
        System.out.println(name + " moved to position: " + this.position);
    }
	//Getter for player's name
	String getName(){
		return name;
	}
    // Getter for player's position
    int getPosition() {
        return this.position;
    }

    // Getter for player's balance
    int getBalance() {
        return this.balance;
    }

    // Update the balance of the player
    void updateBalance(int amount) {
        this.balance += amount;
        System.out.println(name + "'s balance is now: " + this.balance);
    }

}

class Dice{
	int roll(){
		return(int)(Math.random()*6) + 1;
	}
}

class Board{
	final int boardSize = 40;
	int getSize(){
		return boardSize;
	}
	// Space [] spaces;
}


class Property {
    int cost;     // Cost to purchase the property
    int rent;     // Rent charged when another player lands on this property
    String name;  // Name of the property (e.g., "Baltic Avenue")
    Player owner; // The player who owns this property, null if unowned

    // Constructor
    public Property(String name, int cost, int rent) {
        this.name = name;
        this.cost = cost;
        this.rent = rent;
        this.owner = null; // No owner initially
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for cost
    public int getCost() {
        return cost;
    }

    // Getter for rent
    public int getRent() {
        return rent;
    }

    // Getter for owner
    public Player getOwner() {
        return owner;
    }

    // Set the owner of the property
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // Check if the property is owned
    public boolean isOwned() {
        return owner != null;
    }
}
