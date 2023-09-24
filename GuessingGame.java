package BagPackage;
import java.util.Scanner;


public class GuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Guessing Game!");
        System.out.print("Enter the number of integers to guess (m): ");
        int m = input.nextInt();
        
        

        System.out.print("Enter the range of integers (1 to N): ");
        int N = input.nextInt();

        // Create a bag to store the randomly chosen integers (of type Integer)
        BagInterface<Integer> numberBag = new ArrayBag<>(m);

        // Randomly choose m integers in the range from 1 to N and add them to the bag
        for (int i = 0; i < m; i++) {
            int randomInt = (int) (Math.random() * N) + 1;
            numberBag.add(randomInt);
        }

        int correctGuesses = 0;
        boolean gameWon = false;

        System.out.println("Enter " + m + " integers in the range from 1 to " + N + ". Entries may be duplicate.");

        while (!gameWon) {
            int[] userGuesses = new int[m];

            for (int i = 0; i < m; i++) {
                userGuesses[i] = input.nextInt();
            }

            correctGuesses = countCorrectGuesses(numberBag, userGuesses);

            System.out.println(correctGuesses + " of your guesses are correct.");

            if (correctGuesses == m) {
                gameWon = true;
            } else {
                System.out.print("Guess again: ");
            }
        }

        System.out.println("Congratulations! You are correct!");
        System.out.print("Play again? (Yes/No): ");
        String playAgain = input.next();

        if (playAgain.equalsIgnoreCase("No")) {
            System.out.println("Goodbye!");
        } else {
            main(args); // Restart the game if the user wants to play again
        }

        input.close();
    }

    private static int countCorrectGuesses(BagInterface<Integer> numberBag, int[] userGuesses) {
        int correctGuesses = 0;
        Object[] bagContents = numberBag.toArray();

        for (int i = 0; i < userGuesses.length; i++) {
            for (int j = 0; j < bagContents.length; j++) {
                if (bagContents[j] instanceof Integer && (Integer) bagContents[j] == userGuesses[i]) {
                    correctGuesses++;
                    // Remove the correct guess from the bagContents
                    bagContents[j] = null;
                    break; // Break the inner loop to avoid counting duplicates
                }
            }
        }

        return correctGuesses;
    }

}
