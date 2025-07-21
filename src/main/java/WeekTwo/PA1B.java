package WeekTwo;
// PA1B is a program that prompts the user to input their team,
// then calls the method five times.
// Created by Aman Sarosh

import java.util.*;

public class PA1B {

    public static void main(String[] args) {

        //    Create name given to input, define data types for team and number
        String searchTeam;

        //    Get input from User
        Scanner sc = new Scanner(System.in); // Create Scanner Object
        System.out.println("Enter Team Name");
        searchTeam = sc.nextLine();

        //    Looping input five times
        for (int i = 0; i < 5; i++) {
            printMessage(searchTeam);
        }

        sc.close(); // Closing scanner to avoid memory leaks

    }

    public static void printMessage(String searchTeam) {
        System.out.println("You Entered: " + searchTeam);
    }
}
