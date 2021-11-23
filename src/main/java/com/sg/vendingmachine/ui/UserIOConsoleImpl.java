/*
      These methods take in a message to prompt a user for information.  They reprompt until
      the correct type is given.
 */
package com.sg.vendingmachine.ui;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class UserIOConsoleImpl implements UserIO {

    final private Scanner scanner = new Scanner(System.in);

    /**
     * Takes in a string to display on the console.
     *
     * @param message A string to display to the user.
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /**
     *
     * Prompts the user for an answer.
     * It then returns the user's answer.
     *
     * @param prompt - String prompting for information from the user.
     * @return the answer to the message as string
     */
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    /**
     *
     * Takes in a message to display and prompt user for information.  It will continue to prompt
     * until an integer is given.
     *
     * @param message - String explaining what information you want from the user.
     * @return the answer to the message as integer
     */
    @Override
    public int readInt(String message) {
        boolean invalidInput = true;
        int validNumber = 0;

        while (invalidInput) {
            try {
                String inputStr = this.readString(message);
                validNumber = Integer.valueOf(inputStr);
                break;
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return validNumber;
    }


    /**
     * Takes in a message to display on the console and continues to prompt
     * until a long is given from user.
     *
     * @param message - String to prompt information from user.
     * @return A long
     */
    @Override
    public long readLong(String message) {
        while (true) {
            try {
                return Long.valueOf(this.readString(message));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * Takes in a message to display on the console,
     * and continually reprompts the user with that message until they enter a float
     * to be returned as the answer to that message.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as float
     */
    @Override
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.valueOf(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     *
     * Takes in a message to display on the console, exits if user input is 'exit', otherwise it
     * ccontinues to prompt the user with that message until they enter a double.
     *
     * @param message - String explaining what information to receive from the user.
     * @return the answer to the message as a double
     */
    @Override
    public double readDouble(String message) {
        String userInput = checkIfExit(message);

        while (true) {
            try {
                return Double.valueOf(userInput);
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
                userInput = this.readString(message);
            }
        }
    }


    /**
     * Checks if the user's answer is 'exit' or not.  If so, the program exits.
     *
     * @param message
     * @return
     */
    public String checkIfExit(String message) {
        String userInput = this.readString(message);

        if (userInput.toLowerCase().equals("exit")) {
            this.print("Goodbye!");
            System.exit(0);
            return null;
        } else {
            return userInput;
        }
    }

}
