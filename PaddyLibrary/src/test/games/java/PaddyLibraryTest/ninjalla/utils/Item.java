package PaddyLibraryTest.ninjalla.utils;

import PaddyLibraryTest.ninjalla.entities.Player;

import java.util.Random;

public class Item {
    private String name;
    private int value;  // Wert oder Kosten des Items
    private String type;  // z.B. "coin", "equipment", "ability"

    // Konstruktor
    public Item(String name, int value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    // Getter für den Namen des Items
    public String getName() {
        return name;
    }

    // Getter für den Wert des Items
    public int getValue() {
        return value;
    }

    // Getter für den Typ des Items
    public String getType() {
        return type;
    }

    // Methode zur Anwendung des Items (z.B. Ausrüstung anlegen)
    public void use(Player player) {
        if (type.equals("equipment")) {
            // Logik, um das Equipment zu nutzen
            //System.out.println(player.getName() + " hat " + name + " ausgerüstet!");
        } else if (type.equals("ability")) {
            // Logik, um eine Fähigkeit zu aktivieren
            //System.out.println(player.getName() + " hat die Fähigkeit " + name + " aktiviert!");
        }
    }

    // Methode, um eine Münze zu erstellen
    public static Item createCoin() {
        return new Item("Coin", 1, "coin");
    }

    // Methode, um ein zufälliges Ausrüstungsstück zu erstellen
    public static Item createRandomEquipment() {
        String[] equipmentNames = {"Sword", "Shield", "Bow"};
        Random rand = new Random();
        String name = equipmentNames[rand.nextInt(equipmentNames.length)];
        int value = rand.nextInt(10) + 1;  // Wert zwischen 1 und 10
        return new Item(name, value, "equipment");
    }

    // Methode, um eine zufällige Fähigkeit zu erstellen
    public static Item createRandomAbility() {
        String[] abilityNames = {"Double Jump", "Dash", "Invisibility"};
        Random rand = new Random();
        String name = abilityNames[rand.nextInt(abilityNames.length)];
        return new Item(name, 0, "ability");  // Keine Kosten für Fähigkeiten
    }
}
