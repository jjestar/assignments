import java.util.*;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    public String toString() {
        return username + " [" + accountNumber + "] => Balance: " + balance;
    }
}

class Main {
    public static void main(String[] args) {
        LinkedList<BankAccount> accounts = new LinkedList<>();
        Stack<String> history = new Stack<>();
        Queue<String> billQueue = new LinkedList<>();
        Queue<BankAccount> accountRequests = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        BankAccount[] initialAccounts = {
                new BankAccount("101", "Ali", 150000),
                new BankAccount("102", "Aslan", 220000),
                new BankAccount("103", "Nura", 300000)
        };
        Collections.addAll(accounts, initialAccounts);

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Enter Bank");
            System.out.println("2. Enter ATM");
            System.out.println("3. Admin Panel");
            System.out.println("4. Exit");

            if (!sc.hasNextInt()) { sc.next(); continue; }
            int mainChoice = sc.nextInt();
            sc.nextLine();

            if (mainChoice == 1) {
                System.out.println("\n1. Open account request\n2. Deposit\n3. Withdraw");
                if (!sc.hasNextInt()) { sc.next(); continue; }
                int bankChoice = sc.nextInt();
                sc.nextLine();

                if (bankChoice == 1) {
                    System.out.print("Enter your name: ");
                    String n = sc.nextLine();
                    System.out.print("Initial deposit amount: ");
                    double b = sc.nextDouble();
                    sc.nextLine();
                    accountRequests.add(new BankAccount("PENDING", n, b));
                    System.out.println("Your request has been submitted.");
                } else if (bankChoice == 2 || bankChoice == 3) {
                    System.out.print("Enter username: ");
                    String user = sc.nextLine();
                    boolean found = false;
                    for (BankAccount acc : accounts) {
                        if (acc.username.equalsIgnoreCase(user)) {
                            found = true;
                            System.out.print("Amount: ");
                            double amt = sc.nextDouble();
                            sc.nextLine();
                            if (bankChoice == 2) {
                                acc.balance += amt;
                                history.push("Deposited " + amt + " to " + user);
                                System.out.println("Deposit successful.");
                            } else {
                                if (amt <= acc.balance) {
                                    acc.balance -= amt;
                                    history.push("Withdrew " + amt + " from " + user);
                                    System.out.println("Withdrawal successful.");
                                } else {
                                    System.out.println("Not enough funds.");
                                }
                            }
                        }
                    }
                    if (!found) System.out.println("Account not found.");
                }
            } else if (mainChoice == 2) {
                System.out.println("\n1. Check balance\n2. Withdraw\n3. Pay bill");
                if (!sc.hasNextInt()) { sc.next(); continue; }
                int atmChoice = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter username: ");
                String user = sc.nextLine();
                boolean found = false;
                for (BankAccount acc : accounts) {
                    if (acc.username.equalsIgnoreCase(user)) {
                        found = true;
                        if (atmChoice == 1) {
                            System.out.println("Current balance: " + acc.balance);
                        } else if (atmChoice == 2) {
                            System.out.print("Amount to withdraw: ");
                            double amt = sc.nextDouble();
                            sc.nextLine();
                            if (amt <= acc.balance) {
                                acc.balance -= amt;
                                history.push("ATM withdrawal of " + amt + " by " + user);
                                System.out.println("Done.");
                            } else {
                                System.out.println("Insufficient balance.");
                            }
                        } else if (atmChoice == 3) {
                            System.out.print("Bill name: ");
                            String bill = sc.nextLine();
                            billQueue.add(bill + " - " + user);
                            System.out.println("Bill added to queue.");
                        }
                    }
                }
                if (!found) System.out.println("User not found.");
            } else if (mainChoice == 3) {
                System.out.println("\n1. Process account requests\n2. Process bills\n3. View history\n4. Undo last action");
                if (!sc.hasNextInt()) { sc.next(); continue; }
                int adminChoice = sc.nextInt();
                sc.nextLine();
                if (adminChoice == 1) {
                    if (!accountRequests.isEmpty()) {
                        BankAccount approved = accountRequests.poll();
                        approved.accountNumber = String.valueOf(100 + accounts.size() + 1);
                        accounts.add(approved);
                        System.out.println("Account approved: " + approved);
                    } else {
                        System.out.println("No pending requests.");
                    }
                } else if (adminChoice == 2) {
                    if (!billQueue.isEmpty()) {
                        System.out.println("Processing: " + billQueue.poll());
                    } else {
                        System.out.println("No bills in queue.");
                    }
                } else if (adminChoice == 3) {
                    if (history.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("Transaction history:");
                        for (String entry : history) System.out.println(" - " + entry);
                    }
                } else if (adminChoice == 4) {
                    if (!history.isEmpty()) {
                        System.out.println("Undone: " + history.pop());
                    } else {
                        System.out.println("Nothing to undo.");
                    }
                }
            } else if (mainChoice == 4) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}