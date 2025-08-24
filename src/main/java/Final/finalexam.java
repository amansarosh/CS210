package Final;

// Final assignment is option A - note-taking app
// Created by Aman Sarosh

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class finalexam {

    static Scanner sc = new Scanner(System.in);
    static File baseDir = new File("courses");
    static String[] defaults = {"Computer Science", "Math", "History"};
    static ArrayList<SubjectClass> subjects = new ArrayList<>();
    static File inventoryFile = new File(baseDir, "inventory.txt");

    public static void main(String[] args) {
        if (!baseDir.exists()) baseDir.mkdirs();
        for (String name : defaults) subjects.add(new SubjectClass(name, new File(baseDir, safe(name))));
        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> listSubjects();
                case 2 -> writeNote();
                case 3 -> viewNotes();
                case 4 -> searchNotes();
                case 5 -> listReferences();
                case 6 -> addReferenceFile();
                case 7 -> showInventory();
                case 8 -> { writeInventory(); System.out.println("Program Ended"); return; }
                default -> System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }

    static void printMenu() {
        System.out.println("CS210 Note App");
        System.out.println("--------------");
        System.out.println("1) Display all subjects");
        System.out.println("2) Write or edit note");
        System.out.println("3) Display all notes");
        System.out.println("4) Search notes");
        System.out.println("5) List reference file");
        System.out.println("6) Add reference file");
        System.out.println("7) Show inventory");
        System.out.println("8) Exit");
    }

    static void listSubjects() {
        if (subjects.isEmpty()) { System.out.println("(no subjects yet)"); return; }
        for (int i = 0; i < subjects.size(); i++) System.out.println((i + 1) + ") " + subjects.get(i).getName());
    }

    static SubjectClass pickSubject() {
        if (subjects.isEmpty()) { System.out.println("No subjects."); return null; }
        listSubjects();
        int i = readInt("Select subject #: ") - 1;
        if (i < 0 || i >= subjects.size()) { System.out.println("Invalid selection."); return null; }
        return subjects.get(i);
    }

    static void writeNote() {
        SubjectClass s = pickSubject();
        if (s == null) return;
        System.out.print("Enter note (single line): ");
        String text = sc.nextLine().trim();
        if (text.isEmpty()) { System.out.println("Nothing to save."); return; }
        String stamp = zuluStamp(); // UTC timestamp like "Aug 21, 2025, 14:05:00Z"
        String line = "[" + stamp + ": " + text + "]";
        try {
            s.getNoteFile().appendLine(line);
            writeInventory();
            System.out.println("Saved to: " + s.getNoteFile().getPath());
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    static void viewNotes() {
        SubjectClass s = pickSubject();
        if (s == null) return;
        try {
            ArrayList<String> lines = s.getNoteFile().readAllLines();
            if (lines.isEmpty()) System.out.println("(Note is empty, add a note to get started)");
            else { System.out.println("---- " + s.getName() + " notes ----"); for (String L : lines) System.out.println(L); }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    static void searchNotes() {
        SubjectClass s = pickSubject();
        if (s == null) return;
        System.out.print("Keyword (case-insensitive): ");
        String kw = sc.nextLine().trim().toLowerCase();
        if (kw.isEmpty()) { System.out.println("Keyword cannot be empty."); return; }
        try {
            ArrayList<String> lines = s.getNoteFile().readAllLines();
            boolean found = false;
            for (int i = 0; i < lines.size(); i++) {
                String L = lines.get(i);
                if (L.toLowerCase().contains(kw)) { System.out.println("Line " + (i + 1) + ": " + L); found = true; }
            }
            if (!found) System.out.println("No matches.");
        } catch (IOException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    // copying from pc
    static void addReferenceFile() {
        SubjectClass s = pickSubject();
        if (s == null) return;
        System.out.print("Full path to file to copy: ");
        String path = sc.nextLine().trim();
        File src = new File(path);
        if (!src.exists() || src.isDirectory()) { System.out.println("That file does not exist (or is a folder)."); return; }
        System.out.print("Optional new file name (Enter = keep original): ");
        String newName = sc.nextLine().trim();
        if (newName.isEmpty()) newName = src.getName();
        ReferenceFile ref = new ReferenceFile(newName, s.getReferencesFolder());
        try {
            ref.copyFrom(src);
            s.refreshReferences();
            writeInventory();
            System.out.println("Copied to: " + ref.getPath());
        } catch (IOException e) {
            System.out.println("Copy failed: " + e.getMessage());
        }
    }

    static void listReferences() {
        SubjectClass s = pickSubject();
        if (s == null) return;
        ArrayList<ReferenceFile> refs = s.getReferences();
        if (refs.isEmpty()) { System.out.println("(no references yet)"); return; }
        System.out.println("References for " + s.getName() + ":");
        for (ReferenceFile rf : refs) System.out.println("- " + rf.getFileName());
    }

    static void showInventory() {
        writeInventory();
        try (BufferedReader br = new BufferedReader(new FileReader(inventoryFile))) {
            String line;
            while ((line = br.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            System.out.println("Could not read inventory: " + e.getMessage());
        }
    }

    static void writeInventory() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(inventoryFile, false))) {
            pw.println("Inventory:");
            for (SubjectClass s : subjects) {
                pw.println("Subject: " + s.getName());
                pw.println("  Folder: " + s.getFolder().getPath());
                pw.println("  Notes : " + s.getNoteFile().getFileName());
                ArrayList<ReferenceFile> refs = s.getReferences();
                if (refs.isEmpty()) pw.println("  Ref  : (empty)");
                else {
                    pw.println("  Refs  :");
                    for (ReferenceFile rf : refs) pw.println("    - " + rf.getFileName());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to write inventory: " + e.getMessage());
        }
    }

    static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String raw = sc.nextLine().trim();
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    // ensures folder names avoid unusable filesystem characters
    static String safe(String s) {
        return s.replaceAll("[^a-zA-Z0-9-_ ]", "_").trim();
    }

    // returns UTC time like "Aug dd, yyyy, HH:mm:ssZ"
    static String zuluStamp() {
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss'Z'");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return fmt.format(new Date());
    }
    // Displays inheritance, from here we work with the file system
    static abstract class CourseFile {
        protected String fileName;
        protected File folder;
        public CourseFile(String fileName, File folder) { this.fileName = fileName; this.folder = folder; if (!folder.exists()) folder.mkdirs(); }
        public File getPath() { return new File(folder, fileName); }
        public File getFolder() { return folder; }
        public String getFileName() { return fileName; }
        public boolean exists() { return getPath().exists(); }
    }
    // appending and reading notes file
    static class NoteFile extends CourseFile {
        public NoteFile(String fileName, File folder) { super(fileName, folder); }
        public void appendLine(String line) throws IOException { try (FileWriter fw = new FileWriter(getPath(), true)) { fw.write(line + System.lineSeparator()); } }
        public ArrayList<String> readAllLines() throws IOException {
            ArrayList<String> out = new ArrayList<>();
            File f = getPath();
            if (!f.exists()) return out;
            try (Scanner s = new Scanner(f)) { while (s.hasNextLine()) out.add(s.nextLine()); }
            return out;
        }
    }
    // Copying files
    static class ReferenceFile extends CourseFile {
        public ReferenceFile(String fileName, File folder) { super(fileName, folder); }
        public void copyFrom(File source) throws IOException {
            File target = getPath();
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
                 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target))) {
                byte[] buf = new byte[8192];
                int n;
                while ((n = in.read(buf)) != -1) out.write(buf, 0, n);
            }
        }
    }

    static class SubjectClass {
        private String name;
        private File folder;
        private NoteFile noteFile;
        private File referencesFolder;
        private ArrayList<ReferenceFile> references = new ArrayList<>();

        public SubjectClass(String name, File folder) {
            this.name = name;
            this.folder = folder;
            if (!folder.exists()) folder.mkdirs();
            this.noteFile = new NoteFile("notes.txt", folder);
            this.referencesFolder = new File(folder, "references");
            if (!referencesFolder.exists()) referencesFolder.mkdirs();
            refreshReferences();
        }

        public String getName() { return name; }
        public File getFolder() { return folder; }
        public NoteFile getNoteFile() { return noteFile; }
        public File getReferencesFolder() { return referencesFolder; }

        public void refreshReferences() {
            references.clear();
            File[] files = referencesFolder.listFiles();
            if (files == null) return;
            for (File f : files) if (f.isFile()) references.add(new ReferenceFile(f.getName(), referencesFolder));
        }

        public ArrayList<ReferenceFile> getReferences() { refreshReferences(); return references; } // getter to get updated refs
    }
}
