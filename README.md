# Assignment #2 – Banking System

**Student:** Serikov Dias 
**Group:** SE-2513

---

## Overview

This project is a console-based banking system written in Java. It demonstrates the use of physical and logical data structures including `LinkedList`, `Stack`, `Queue`, and `Array`. The program simulates basic banking operations such as account management, deposits, withdrawals, bill payments, and an admin panel.

---

## How to Run

1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download)
2. Clone or download this repository
3. Open the project in IntelliJ IDEA
4. Run `BankAccount.java`

---

## Project Structure

```
assignment2/
└── src/
    ├── BankAccount.java   # BankAccount class
        └── Main.java      # Main program with menu logic
```
## Part 1 – Logical Data Structures

### Task 1 – Bank Account Storage Using LinkedList

A `BankAccount` class was created with fields: `accountNumber`, `username`, and `balance`.  
Accounts are stored in a `LinkedList<BankAccount>`.

Three predefined accounts are loaded at startup:

```
Ali   [101] => Balance: 150000.0
Aslan  [102] => Balance: 220000.0
Nura  [103] => Balance: 300000.0
```
### Task 2 – Deposit & Withdraw Operations

Users can deposit or withdraw money by entering their username and amount.  
The balance inside the `LinkedList` is updated directly.  

**Example output:**
```
Enter username: Ali
Amount: 50000
Deposit successful.
```

**Screenshot:**  
<img width="697" height="254" alt="image" src="https://github.com/user-attachments/assets/a9b0dc3b-2eb1-47be-8205-797e271f2653" />

---

### Task 3 – Transaction History (Stack – LIFO)

A `Stack<String>` called `history` stores every deposit and withdrawal as a string.  
The admin can view the full history or undo the last transaction using `pop()`.

**Example output:**
```
Transaction history:
 - Deposited 50000 to Ali
 - Withdrew 20000 from Ali

Undone: Withdrew 20000 from Ali
```

**Screenshot:**  
<img width="869" height="239" alt="image" src="https://github.com/user-attachments/assets/1f867d1c-798f-4ab8-b9ce-06a1715f8d04" />


---

### Task 4 – Bill Payment Queue (Queue – FIFO)

A `Queue<String>` called `billQueue` stores bill payment requests in order.  
Bills are processed one at a time from the front of the queue (FIFO).

**Example output:**
```
Bill added to queue.
Processing: Electricity Bill - Aslan
```

**Screenshot:**  
<img width="605" height="205" alt="image" src="https://github.com/user-attachments/assets/6ca39e00-016d-41b9-a5b3-5c78e0bfce0f" />


---

### Task 5 – Account Opening Queue (Admin Simulation)

Users submit account opening requests through the bank menu.  
Requests are stored in a `Queue<BankAccount>` called `accountRequests`.  
The admin can approve requests one by one — each approved account is moved to the main `LinkedList`.

**Example output:**
```
Your request has been submitted.
Account approved: Nura [104] => Balance: 50000.0
```

**Screenshot:**  
<img width="702" height="258" alt="image" src="https://github.com/user-attachments/assets/bccbec74-1d9d-461d-9042-661e6fd6b52c" />


---

## Part 2 – Physical Data Structures

### Task 6 – Array of BankAccounts

A `BankAccount[3]` array stores 3 predefined accounts.  
These accounts are then loaded into the `LinkedList` at program start using `Collections.addAll()`.

**Screenshot:**  
<img width="720" height="145" alt="image" src="https://github.com/user-attachments/assets/82e91875-a55b-44dd-a397-961818623e5a" />


---

## Part 3 – Mini Banking Menu

The main menu integrates all tasks into one console interface:

```
=== MAIN MENU ===
1. Enter Bank
2. Enter ATM
3. Admin Panel
4. Exit
```

### Bank Menu
- Submit account opening request → added to `accountRequests` queue
- Deposit money → updates balance in `LinkedList`, logs to `Stack`
- Withdraw money → updates balance in `LinkedList`, logs to `Stack`

### ATM Menu
- Check balance
- Withdraw money
- Pay a bill → added to `billQueue`

### Admin Panel
- Process account requests (from queue to LinkedList)
- Process bills (from bill queue)
- View full transaction history (Stack)
- Undo last transaction (pop from Stack)

**Screenshot:**  
<img width="583" height="242" alt="image" src="https://github.com/user-attachments/assets/21170dc9-9fc4-42ae-9c88-5a5bcb2e8717" />


---

## Data Structures Summary

| Structure | Usage |
|-----------|-------|
| `LinkedList<BankAccount>` | Stores all active bank accounts |
| `Stack<String>` | Tracks transaction history (supports undo) |
| `Queue<String>` | Manages bill payment requests (FIFO) |
| `Queue<BankAccount>` | Manages new account opening requests |
| `BankAccount[]` | Physical array for initial account setup |

---

## Work Process

The project was built step by step following the assignment tasks. First the `BankAccount` class and `LinkedList` were set up, then deposit/withdraw logic was added. The `Stack` was connected to every transaction to enable undo functionality. The `Queue` was used for both bill payments and account requests to simulate real banking workflows. Finally, all parts were integrated into the main menu system.
