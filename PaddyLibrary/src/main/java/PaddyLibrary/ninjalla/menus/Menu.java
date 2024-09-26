package PaddyLibrary.ninjalla.menus;

import java.util.Scanner;

public class Menu {
    // Definieren der Menüoptionen als Konstanten
    public static final int SINGLEPLAYER = 1;
    public static final int MULTIPLAYER = 2;
    public static final int SKINSELECTOR = 3;
    public static final int EXIT = 4;

    private int selectedOption;

    // Methode zur Anzeige des Menüs
    public void displayMenu() {
        System.out.println("Willkommen zu Ninjalla!");
        System.out.println("Bitte wähle eine Option:");
        System.out.println("1. Singleplayer");
        System.out.println("2. Multiplayer");
        System.out.println("3. Skin Selector");
        System.out.println("4. Beenden");

        getUserInput();
    }

    // Methode, um die Eingabe des Benutzers zu erfassen
    private void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Deine Auswahl: ");
            selectedOption = scanner.nextInt();

            // Ungültige Eingabe abfangen
            if (selectedOption < 1 || selectedOption > 4) {
                System.out.println("Ungültige Auswahl. Bitte versuche es erneut.");
                displayMenu();
            }
        } catch (Exception e) {
            System.out.println("Ungültige Eingabe. Bitte gib eine Zahl ein.");
            scanner.next();  // Scanner zurücksetzen
            displayMenu();   // Menü erneut anzeigen
        }
    }

    // Methode zum Abrufen der ausgewählten Option
    public int getSelectedOption() {
        return selectedOption;
    }
}
