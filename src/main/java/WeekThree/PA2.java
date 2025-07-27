package WeekThree;
// PA2 is a resume builder that collects user information to construct a resume output with a scanner method
// Created by Aman Sarosh

import java.util.*;

public class PA2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Init scanner

        // Define values
        String name = getName(sc); // First and Last Name
        String contact = getContactInfo(sc); // Phone, Email, and Address
        String education = getEducation(sc);
        String workExp = getWorkExperience(sc);
        String skills = getSkills(sc);

        buildResume(name, contact, education, workExp, skills);

        sc.close();
    }
        // Get user input for values above
    public static String getName(Scanner scanner) {
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        return firstName + " " + lastName;
    }
    public static String getContactInfo(Scanner scanner) {
        System.out.println("Enter your phone number: ");
        String contactNumber = scanner.nextLine();
        System.out.println("Enter your email: ");
        String contactEmail = scanner.nextLine();
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();
        return contactNumber + " " + contactEmail + " " + address;
    }

    public static String getEducation(Scanner scanner) {
        System.out.println("Enter your school name: ");
        String schoolName = scanner.nextLine();
        System.out.println("Enter your major ");
        String major = scanner.nextLine();
        System.out.println("Enter your graduation year ");
        String gradYear = scanner.nextLine();
        return schoolName + " " + major + " " + gradYear;
    }

    public static String getWorkExperience(Scanner scanner) {
        System.out.println("Enter your work experience (Company): ");
        String company = scanner.nextLine();
        System.out.println("Enter your work experience (Title): ");
        String role = scanner.nextLine();
        System.out.println("Enter your work experience (Dates): ");
        String dates = scanner.nextLine();
        System.out.println("Enter your work experience (Scope): ");
        String scope = scanner.nextLine();
        return company + " " + role + " " + dates + " " + scope;
    }

    public static String getSkills(Scanner scanner) {
        System.out.println("Enter your skills: ");
        return scanner.nextLine();
    }

    // Compile together information given
    public static void buildResume(String name, String contact, String education,
                                   String workExp, String skills) {
        System.out.println("Compiled Resume");
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Education: " + education);
        System.out.println("Work Experience: " + workExp);
        System.out.println("Skills: " + skills);
    }
}
