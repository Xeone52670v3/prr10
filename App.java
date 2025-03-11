import java.util.Scanner;

public class App {
    String username;
    String password;

    App(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void main(String[] args) {
        final int MAX_USERS = 15;
        App[] users = new App[MAX_USERS];
        int userCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1 - Add user");
            System.out.println("2 - Remove user");
            System.out.println("3 - Authenticate user");
            System.out.println("4 - Exit");
            System.out.print("Your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input!");
                continue;
            }

            if (choice == 1) {
                if (userCount >= MAX_USERS) {
                    System.out.println("Error: Maximum number of users reached.");
                } else {
                    try {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        users[userCount++] = new App(username, password);
                        System.out.println("User successfully registered!");
                    } catch (Exception e) {
                        System.out.println("Error adding user: " + e.getMessage());
                    }
                }
            } else if (choice == 2) {
                try {
                    System.out.print("Enter username to remove: ");
                    String username = scanner.nextLine();
                    boolean found = false;
                    for (int i = 0; i < userCount; i++) {
                        if (users[i] != null && users[i].username.equals(username)) {
                            users[i] = users[userCount - 1];
                            users[userCount - 1] = null;
                            userCount--;
                            System.out.println("User removed.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Error: User not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Error removing user: " + e.getMessage());
                }
            } else if (choice == 3) {
                try {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    boolean authenticated = false;
                    for (App user : users) {
                        if (user != null && user.username.equals(username) && user.password.equals(password)) {
                            System.out.println("Authentication successful!");
                            authenticated = true;
                            break;
                        }
                    }
                    if (!authenticated) {
                        System.out.println("Error: Invalid username or password.");
                    }
                } catch (Exception e) {
                    System.out.println("Error during authentication: " + e.getMessage());
                }
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }
}
