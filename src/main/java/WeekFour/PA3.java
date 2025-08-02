package WeekFour;
// PA3 is an array word search program
// Created by Aman Sarosh

import java.util.Arrays;
import java.util.Scanner;

public class PA3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Read the entire list in one line
        System.out.println("Enter list of words (separated by spaces):");
        String input = sc.nextLine().trim();

        // 2) Parse into array (always split, even if input is blank)
        String[] words = input.split("\\s+");

        // 3) Prompt for the search word
        System.out.print("Type in word you'd like to search for: ");
        String search = sc.nextLine();

        // 4) Search logic using for(i++)
        int count = 0;
        for (String word : words) {
            if (word.equals(search)) {
                count++;
            }
        }

        // 5) Print results
        System.out.println("\n--- Search Results ---");
        System.out.println("Words you entered: " + Arrays.toString(words));
        System.out.println("Search word: " + search);
        System.out.println("Exists in list? " + (count > 0 ? "Yes" : "No"));
        if (count > 0) {
            System.out.println("Number of occurrences: " + count);
        }

        sc.close();
    }
}

// Sample Output
//--- Search Results ---
//Words you entered: [hello, hello]
//Search word: hello
//Exists in list? Yes
//Number of occurrences: 2