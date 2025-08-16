package WeekSix;

// PA6 is a program that utilizes classes to add and remove people from a sports roster with search
// Created by Aman Sarosh

import java.util.Scanner;
import java.util.ArrayList;

// Person class
class Person {
    private String name;
    private String phoneNumber;
    private String birthDate;
    private int jerseyNumber;

    // Constructor for person class; this will help in the search
    public Person(String name, String phoneNumber, String birthDate, int jerseyNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.jerseyNumber = jerseyNumber;
    }

    // Display players
    public void displayInfo() {
        System.out.println("Name: " + name +
                ", Phone: " + phoneNumber +
                ", Birth Date: " + birthDate +
                ", Jersey Number: " + jerseyNumber);
    }

    // Getters
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getBirthDate() { return birthDate; }
    public int getJerseyNumber() { return jerseyNumber; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }
}

// Team class with main
public class PA6 {
    private String teamName;
    private String coachName;
    private String conferenceName;
    private ArrayList<Person> roster;

    // Constructor
    public PA6(String teamName, String coachName, String conferenceName) {
        this.teamName = teamName;
        this.coachName = coachName;
        this.conferenceName = conferenceName;
        this.roster = new ArrayList<>();
    }

    // Add player to roster
    public void addPlayer(Person p) {
        roster.add(p);
    }

    // Find player by jersey number
    public Person findPlayer(int jerseyNumber) {
        for (Person p : roster) {
            if (p.getJerseyNumber() == jerseyNumber) {
                return p;
            }
        }
        return null;
    }

    // Display team details
    public void displayRoster() {
        System.out.println("\nTeam: " + teamName +
                "\nCoach: " + coachName +
                "\nConfernce: " + conferenceName);
        System.out.println("----- Player Information -----");
        for (Person p : roster) {
            p.displayInfo();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Team Details
        PA6 team = new PA6("Example Team", "John", "Big Conference");

        // Example Players
        team.addPlayer(new Person("John Doe", "425-111-1111", "01/01/2001", 1));
        team.addPlayer(new Person("Doe John", "425-222-2222", "02/02/2002", 2));
        team.addPlayer(new Person("John D Doe", "425-333-3333", "03/03/2003", 3));

        // Add player via Scanner
        System.out.println("\nAdd a new player:");
        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Phone: ");
        String phone = input.nextLine();

        System.out.print("Birth Date (MM/DD/YYYY): ");
        String birth = input.nextLine();

        System.out.print("Jersey Number: ");
        int jersey = input.nextInt();

        team.addPlayer(new Person(name, phone, birth, jersey));
        System.out.println("Player added");

        // Display roster
        team.displayRoster();

        // Find player by jersey number
        System.out.print("\nEnter a jersey number to search: ");
        int searchJersey = input.nextInt();
        Person found = team.findPlayer(searchJersey);

        if (found != null) {
            System.out.println("Player found: Name: " + found.getName() +
                    ", Jersey Number: " + found.getJerseyNumber());
        } else {
            System.out.println("No player found with jersey number: " + searchJersey);
        }

        input.close();
    }
}
