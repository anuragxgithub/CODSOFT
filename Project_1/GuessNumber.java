// GUESS THE NUMBER GAME
package Project_1;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GuessNumber {
    public static void main(String[] args) {
        System.out.println(">>>> WELCOME TO 'GUESS THE NUMBER' GAME <<<<");
        Random random = new Random();  
        int random_num = random.nextInt(101); // generates a random integer b/w 1-100 
        game(random_num);
    }

    private static void game(int random_num) {
        Scanner sc = new Scanner(System.in);
        int attempt = 8;
        System.out.print("Attempts left -> " + attempt + " Enter your guess (1 to 100): ");

        while(true) {
            try {
                int guessed_num = sc.nextInt();

                if(guessed_num < 1 || guessed_num > 100) {
                    System.out.print("You guess is out of valid range (1-100). Enter valid integer: ");
                    continue;
                }
                attempt--;
                // checking for winning
                if (guessed_num == random_num) {
                    System.out.println(">> BRAVO! YOU GUESSED THE NUMBER IN " + (8-attempt) + " ATTEMPT(S)!!!! <<");
                    playAgain(sc);
                    break;
                               
                }
                // check for loosing if no attempts left
                if(attempt == 0) {   
                    System.out.println("NO ATTEMPTS LEFT YOU LOST!");
                    playAgain(sc);
                    break;
                }
                //check for guessed num. is little high or too high or little low or too low
                if (guessed_num > random_num) {
                    if(guessed_num - random_num < 10) {
                        System.out.println("Your guess is little high.");
                    } else {
                        System.out.println("Your guess is too high.");
                    }
                    System.out.print("Attempts left -> " + attempt + " Guess smaller number: ");
                }
                else {
                    if(guessed_num < random_num) {
                        if(random_num - guessed_num < 10) {
                            System.out.println("Your guess is little low");
                        } else {
                            System.out.println("Your guess is too low");
                        }
                        System.out.print("Attempts left -> " + attempt + " Guess larger number: ");
                    }
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input.");
                System.out.print("Attempts left -> " + attempt + " Enter valid number (1-100): ");
                sc.nextLine();
                // Without sc.nextLine();, the invalid input would still be in the scanner's input buffer. This means that the next time you call sc.nextInt(), it will attempt to read the same invalid input again, resulting in another InputMismatchException.
            } 
        }
        sc.close();
    }

    private static void playAgain(Scanner sc) {
        System.out.print("Do you want to play again? If yes then enter 1 else enter 0: ");
        try {
            int num = sc.nextInt();
            restart(num);
        } catch(InputMismatchException e) {
            System.out.println("Invalid input. Assuming that you don't want to play again.");
            System.out.println("THANKS FOR PLAYING!!");
        }
    }

    private static void restart(int num) {
        if(num == 1) {
            System.out.println("Let's start the game again.");
            Random r = new Random();
            int r_num = r.nextInt(101);
            game(r_num);
        } else {
            System.out.println("THANKS FOR PLAYING!!");
        }
    }
}
