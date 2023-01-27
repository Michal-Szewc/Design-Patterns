package DP;

import java.util.Scanner;

// Client has main function that starts him and joins him to chat.
// Run multiple instances of Client to communicate.

public class Client {
    public Client() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Type your name (max length: " + ChatFacade.getMaxNameLen() + " characters)");
        ChatFacade chat = new ChatFacade(input.next());


        showCommands();
        String message;

        // check commands and chat using chat facade
        // without worrying about base chat below it
        while (true) {
            String command = input.next();

            // simple switch on commands from facade
            switch (command) {
                case "HELP" -> showCommands();
                case "SEND" -> {
                    String receiver = input.next();
                    message = input.nextLine();
                    chat.sendMessage(receiver, message);
                }
                case "BROADCAST" -> {
                    message = input.nextLine();
                    chat.broadcast(message);
                }
                case "USERS" -> chat.getActiveUsers();
                case "EXIT" -> {
                    chat.close();
                    return;
                }
                default -> System.out.println("Unrecognized command");
            }

        }
    }

    private void showCommands() {
        System.out.println("""
                Available commands:
                HELP - show commands list
                SEND <receiver username> <message> - send message to one user
                BROADCAST <message> - send message to all active users
                USERS - show list of active users' usernames
                EXIT - close chat
                """);
    }

    public static void main(String[] args) throws Exception {
        new Client();
    }
}
