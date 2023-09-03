/*
 * By default I am taking the bank balance of the account holder to be Rs. 2000. You can set it to
 * 0 too. Also, by default, the ATM pin is set to 1234.
 */
package Project_3;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ATM {
    public static void main(String[] args) {
        BankAccount m1 = new BankAccount(2000);   //instantiating ATM class
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("<<<----WELCOME TO ATM MACHINE---->>>");
        System.out.println();
        m1.verifyPin(0, sc);
        System.out.println();
        
        // after verifying ATM pin we will provide different options to account holder.  
        int choice = 0;
        while(choice != 6) {
            System.out.println("ENTER ANY NUMBER FROM BELOW TO SELECT AN OPTION");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction Histories");
            System.out.println("5. Change ATM pin");
            System.out.println("6. Exit");
            try {
                choice = sc.nextInt();
            } catch(InputMismatchException e) {
                sc.nextLine();
                System.out.println("<<< INVALID CHOICE! PLEASE CHOOSE THE CORRECT OPTION FROM (1-6). >>>");
                System.out.println();
                continue;
            }

            switch(choice) {
                case 1 : 
                m1.checkBalance();
                break;

                case 2 : 
                m1.depositMoney(sc);
                break;
                
                case 3 : 
                m1.withdrawMoney(sc);
                break;

                case 4 :
                m1.displayTranHistory();
                break;

                case 5 :
                m1.verifyAndChange(sc);
                break;
                
                case 6 : 
                System.out.println("<< THANKS FOR COMING. HAVE A GOOD DAY! >>");
                break;

                default : 
                System.out.println("<<< INVALID CHOICE! PLEASE CHOOSE THE CORRECT OPTION FROM (1-6). >>>");
                System.out.println();           
            }  
        } 
        sc.close();
    }
}

class BankAccount {
    double balance;
    int atmPin = 1234;
    ArrayList<String> list = new ArrayList<>();

    public BankAccount(int balance) {    // constructor
        this.balance = balance;
    }

    // verifies the ATM pin entered by the user.
    void verifyPin(int pin, Scanner sc) {
        System.out.print("Enter your ATM pin : ");
        do {
            try {
                pin = sc.nextInt();
                if(this.atmPin != pin) {
                    System.out.print("Invalid Pin! Please enter correct ATM pin : ");
                } else {
                    System.out.println("<< PIN VERIFIED SUCCESSFULLY >>");
                }
            } catch(InputMismatchException e) {
                System.out.print("Invalid Pin! Enter valid numeric pin : ");
                sc.nextLine();  // Consume the invalid input from scanner's input buffer.
            } 
        } while(pin != this.atmPin);
    }

    // checks the account balance
    void checkBalance() {
        System.out.println("<<< YOUR ACCOUNT BALANCE IS : " + this.balance + " Rs. >>>");
        transactionHistory(list, "Balance checked.");
        System.out.println();
    }

    // deposits money
    void depositMoney(Scanner sc) {
        // first check the entered amount is valid or not
        double amount = 0;
        boolean validAmount = false;
        System.out.print("Enter amount to be deposited : ");
        while(!validAmount) {
            try {
                amount = sc.nextDouble();
                validAmount = isValid(amount, "deposit");
            } catch(InputMismatchException e) {
                sc.nextLine();
                System.out.print("Invalid amount! Enter amount in numberic value : ");
                continue;
            }
        }
        //now add the money
        this.balance = this.balance + amount;
        System.out.println("<<< " + amount + " Rs. SUCCESSFULLY DEPOSITTED. >>>");
        transactionHistory(list, amount + " Rs. successfully deposited.");
        System.out.println();
    }
    private boolean isValid(double amount, String m) {
        if(amount < 0) {
            System.out.print("Invalid amount! Please enter valid balance to " + m +" : ");
            return false;
        } else {
            return true;
        }
    }

    // withdraws money
    void withdrawMoney(Scanner sc) {
        // first check the enterd amount is valid or not
        double amount = 0;
        boolean validAmount = false;
        System.out.print("Enter amount to be withdrawn : ");
        while(!validAmount) {
            try {
                amount = sc.nextDouble();
                validAmount = isValid(amount, "withdraw");
            } catch(InputMismatchException e) {
                sc.nextLine();
                System.out.print("Invalid amount! Enter amount in numberic value : ");
                continue;
            }
        }
        // now deduct the money
        if(amount > this.balance) {
            System.out.println("<<< INSUFFICIENT BALANCE. CAN'T WITHDRAW. >>>");
        } else {
            this.balance = this.balance - amount;
            System.out.println("<<< " + amount + " Rs. SUCCESSFULLY WITHDRAWN. >>>");
            transactionHistory(list, amount + " Rs. successfully withdrawn.");
        }
        System.out.println();
    }

    // prints the history of transactions
    void displayTranHistory() {
        if(list.size() == 0) {
                System.out.println("<< NO TRANSACTION HISTORY YET!! >>");
        } else {
            System.out.println("<< TRANSACTION HISTORY >>");
            int i = 1;
            for (String m : list) {
                System.out.println(i+". " + m);
                i++;
            }
        }
        System.out.println();
    }

    // verify the current pin and then changes it with the new one
    void verifyAndChange(Scanner sc) {
        int pin = 0, chancesLeft = 3;
        System.out.print("Enter you current ATM pin to change it : ");
        while(chancesLeft > 0) {
            try {
                pin = sc.nextInt();
                if(this.atmPin == pin) {
                    System.out.println("CURRENT PIN VERIFIED SUCCESSFULLY");
                    change(sc, list);
                    break;
                } else {
                    chancesLeft--;
                    if (chancesLeft > 0) {
                        System.out.print("Incorrect Pin. Enter valid ATM pin (" + chancesLeft + " chance(s) left) : ");
                    }
                }
            } catch(InputMismatchException e) {
                sc.nextLine();
                chancesLeft--;
                if (chancesLeft > 0) {
                    System.out.print("Incorrect pin. Enter correct numeric pin (" + chancesLeft + " chance(s) left) : ");
                }
            }
        }
        if(chancesLeft == 0) {
            System.out.println("<< SORRY YOUR PIN CAN'T BE CHANGED AS YOU ENTERED YOUR CURRENT PIN WRONG 3 TIMES >>");
            System.out.println("COME BACK LATER.");
            System.out.println();
        }
    }

    private void change(Scanner sc, ArrayList<String> list) {
        System.out.print("Now enter the new 4 digit ATM pin : ");
        while(true) {
            try {
                int pin = sc.nextInt();
                if(pin < 1000 && pin > 9999) {
                    System.out.print("Enter the pin in valid range please : ");
                    pin = sc.nextInt();
                } else {
                    this.atmPin = pin;
                    System.out.println("<<< YOUR ATM PIN HAS BEEN SUCCESSFULLY CHANGED. >>>");
                    transactionHistory(list, "ATM Pin changed.");
                    break;
                }
            } catch(InputMismatchException e) {
                sc.nextLine();
                System.out.print("Enter pin in numbers and are of 4 digits : ");
            }    
        }
        System.out.println();
    }

    // this method takes the transaction history message and a list and stores that message in that list 
    void transactionHistory(ArrayList<String> list, String message) {
        list.add(message);
    }
}
