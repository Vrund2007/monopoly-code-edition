# Monopoly: Code Edition – Code empire, fix the errors, and win👑

## 🎲 **Project Overview**

Welcome to **Monopoly: Code Edition**, a console-based board game designed using **core Java** principles, inspired by **Monopoly**. It brings the exciting and competitive world of **real-estate empire building** to your screen. This game is for players who want to get a taste of cutthroat capitalism—where only the wealthiest and most strategic players survive!

This game combines familiar gameplay elements of Monopoly with **advanced features** like **property auctions**, **chance cards**, and **bank loans**, all developed using core **Java OOP principles** (such as **inheritance**, **constructors**, **polymorphism**, and **encapsulation**).

---

## 🏆 **Key Features**

### 1. **Player Movement**
   - Each player rolls **two six-sided dice** to determine their movement on the board.
   - Players move around the board, landing on **properties**, **tax spaces**, **chance cards**, and **special event spaces**.

### 2. **Property Purchase**
   - When a player lands on an **unowned property**, they can choose to **buy** it. If they can’t afford the property, they must pass on the opportunity.
   - Each property has a **purchase price** and **rent** values, which can increase when the player upgrades the property.

### 3. **Rent Collection**
   - When a player lands on a **property owned by another player**, they must **pay rent** to the owner.
   - Rent increases with **property upgrades** like **houses** and **hotels**.

### 4. **Bankruptcy**
   - If a player cannot afford to pay rent, taxes, or loans, or if their balance drops to **zero**, they are declared **bankrupt**.
   - A bankrupt player is eliminated from the game.

### 5. **Winning the Game**
   - The game continues until **one player** remains with a **non-zero balance**.
   - The last remaining player is crowned the **ultimate capitalist** and wins the game.

---

## 🏠 **Advanced Features**

### 1. **Property Upgrades (Houses & Hotels)**
   - Players can **upgrade** their properties by purchasing **houses** and **hotels**.
     - **House**: Increases the rent by a factor of **2**.
     - **Hotel**: Increases the rent by a factor of **5**.
   - Properties need to be owned in a complete color group to start upgrading.

### 2. **Chance Cards**
   - Landing on a **Chance** space triggers a **random event**, which can be either **positive** or **negative**:
     - **Positive Event**: “You win a lottery! Collect $200.”
     - **Negative Event**: “You’re overspeeding. Pay $100.”
     - **Movement Event**: “Advance to Go and collect $200.”

### 3. **Income Tax & Luxury Tax**
   - **Income Tax**: Players landing on the Income Tax space must pay **10%** of their total balance.
   - **Luxury Tax**: Players landing on this space pay a **fixed fee** of $75.
   - Both taxes serve as a challenge to the player's cash flow, adding a financial burden.

### 4. **Bank Loan System**
   - Players can take out **loans** from the bank to get extra cash.
     - **Maximum Loan**: $500
     - **Interest Rate**: 10%
     - Players must repay the loan in **5 turns**, or they risk going bankrupt.
   - Loans are an **economic risk** players must manage wisely to avoid falling behind.

### 5. **Property Auctions**
   - If a player lands on a property but chooses **not to buy it**, the property goes to **auction**.
   - All players can **bid** for the property, and the highest bidder wins it.
   - Auctions can spark **bidding wars** for the most coveted properties.

### 6. **Trading System**
   - Players can engage in **negotiations** and **trades** at any point during the game.
   - Trades can involve **properties**, **money**, **loans**, or **property upgrades**.
   - Form alliances or manipulate deals to create **monopolies** and increase your power.

### 7. **Mortgage System**
   - Players can **mortgage** their properties to gain extra cash if they are in need of funds.
   - When mortgaging, players receive **50%** of the property’s value in cash, but the **rent** on the property is **halved** until it is unmortgaged.
   - Players can **unmortgage** a property by paying back the **original mortgage amount** plus **interest** (usually 10% of the mortgage amount).
   - The mortgage option allows players to strategically manage their **finances** during the game. However, mortgaging too many properties may result in reduced income from rent, which could make it harder to win the game.

---

## ⚙️ **How to Play - Step-by-Step**

### **1. Game Setup**
   - **Players**: 2-4 players
   - **Starting Balance**: Each player starts with **$1500** in cash.
   - **Game Board**: The board consists of various types of spaces, including **properties**, **tax spaces**, **chance cards**, and **special event spaces** like **Go to Jail**.

### **2. Player Turn**
   - Players take turns, performing the following actions:
     1. **Roll the Dice**: Players roll two six-sided dice to determine how far they move on the board.
     2. **Land on a Space**: Players land on a space and follow the rules associated with it:
        - **Buy Property**: If unowned, the player may purchase the property.
        - **Pay Rent**: If the property is owned, the player must pay rent to the owner.
        - **Chance Cards**: If they land on a Chance space, they draw a Chance card.
        - **Pay Taxes**: If they land on an Income Tax or Luxury Tax space, they must pay the required amount.

### **3. Property Upgrades**
   - Players can **buy houses** and **hotels** for their owned properties:
     - To upgrade, a player must own all properties of a specific color set (e.g., all properties in the **blue set**).
     - **Houses** and **hotels** increase the rent and make the properties more profitable.

### **4. Bank Loans**
   - Players can take a **loan** from the bank if they need more money to buy properties or upgrade them.
   - Loans must be paid off within **5 turns**, or the player risks **bankruptcy**.

### **5. Property Auctions**
   - If a player does not want to buy a property, it is placed for **auction**.
   - All players can **bid** for the property. The highest bidder wins and must pay the bank.

### **6. Trading**
   - Players can **trade properties**, **money**, or even **loans** with each other to create monopolies and strengthen their positions.
   - Strategic trading is a key component of the game.

### **7. Bankruptcy**
   - A player who can’t afford to pay rent, taxes, or debts is **eliminated** from the game.
   - The last player standing with a non-zero balance wins the game.

---

## 💻 **Technologies Used**
- **Java** (Core Java: OOP principles, constructors, inheritance, keywords, etc.)
- **No external libraries** or frameworks used.
- The game uses **Object-Oriented Programming** principles:
  - **Classes and Objects**: Representing players, properties, the board, spaces, and events.
  - **Inheritance**: Extending functionality for different property types, chance cards, taxes, etc.
  - **Encapsulation**: Ensuring data integrity and controlling access to sensitive game data.
  - **Polymorphism**: Allowing flexibility in how objects behave (e.g., Chance cards and special spaces).

---

## 🎮 **Enjoy the Game!**

Get ready to outwit your competitors in a high-stakes environment where every decision could make or break your empire. **Monopoly: Code Edition** is designed for those who love competition, strategic thinking, and of course, becoming the **wealthiest capitalist** in the world!

**Good luck, and may the richest player win!**

---
