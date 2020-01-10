package GitHub_projects;

// imports

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static final Scanner scan = new Scanner(System.in);
    private static final Random random = new Random();
    private static final String[] courses = {"Java developer", "Front-end developer", "Automechanica"};
    private static final String[][] courseLessons = {{"Basis programmeren", "Java fundamentals", "Logica", "Databases"}, {"Javascript", "Python"}, {"Introductie", "Onderhoud"}};
    private static final String separator = "-------------------------";
    private static int amountOfStudents;

    public static void main(String... args) {

        // Voorzien dat het een integer is dat wordt ingegeven en geen string
        boolean checkIfInteger = false;
        while (!checkIfInteger) {
            try {
                System.out.println("Typ het aantal studenten per opleiding in, aub: ");
                amountOfStudents = Integer.parseInt(scan.nextLine());
                checkIfInteger = true;
            } catch (NumberFormatException ex) {
                System.out.println("Geen geldige integer opgegeven!");
            }
        }
        printSeparator();
        printHeader();
        printSeparator();
        Utils.addItemsToList();

        // Begin loop met opleiding

        for (int i = 0; i < courses.length; i++) {

            String course = courses[i];

            String[] lessons = courseLessons[i];

            String[] students = Utils.randomUniqueNames(amountOfStudents); // aantal studenten

            boolean courseLine = true;


            for (String lesson : lessons) {

                String professor = Utils.randomFirstName();

                int maxPoints = 10 * (random.nextInt(3) + 1);

                boolean lessonLine = true;

                for (String student : students) {

                    int punt = Utils.randomScore(maxPoints);
                    printLine(

                            courseLine ? course : "",

                            lessonLine ? lesson : "",

                            lessonLine ? professor : "",

                            student,

                            punt + " / " + maxPoints, Utils.isStudentGeslaagd(punt, maxPoints) ? "Geslaagd" : "Niet geslaagd");

                    printSeparator();


                    lessonLine = false;

                    courseLine = false;


                }

            }

        }
    }

    private static void printHeader() {

        printLine("Opleiding", "Vak", "Professor", "Student", "Punten", "Geslaagd/Niet Geslaagd");

    }


    private static void printLine(

            String opleiding, String vak, String professor, String student, String punten, String geslaagd) {

        System.out.printf(

                "| %-20s | %-20s | %-20s | %-20s | %-8s | | %-25s |\n", opleiding, vak, professor, student, punten, geslaagd);

    }


    private static void printSeparator() {

        System.out.printf(

                "| %.20s | %.20s | %.20s | %.20s | %.8s | | %.25s |\n",

                separator, separator, separator, separator, separator, separator);

    }

}

class Utils {

    private static final Random random = new Random(64);

    private static final List<String> uniqueNames = new ArrayList<>();


    private static final String[] firstNames = {

            "Marie", "Bert", "Jan", "Evy", "Stefan", "Thalia", "Glenn", "Barry", "Harry"

    };


    private static final String[] lastNames = {

            "Janssens", "Jansen", "Verplancken", "Vranckx", "Merckx", "Boonen", "Opdebroeck", "Verstrepen",

    };


    public static void addItemsToList() {
        for (String firstName : firstNames) {

            for (String lastName : lastNames) {

                uniqueNames.add(firstName + " " + lastName);
            }
        }
    }


    public static String randomFirstName() {

        return firstNames[random.nextInt(firstNames.length)];

    }


    public static String randomUniqueNames() {

        if (uniqueNames.isEmpty()) {

            throw new IllegalStateException("Geen unieke naam combinaties meer over!");

        }

        int index = random.nextInt(uniqueNames.size());

        return uniqueNames.remove(index);

    }


    public static String[] randomUniqueNames(int amount) {

        String[] names = new String[amount];

        for (int i = 0; i < amount; i++) {

            names[i] = randomUniqueNames();
        }

        return names;

    }


    public static int randomScore(int upperBound) {

        return random.nextInt(upperBound + 1);

    }

    public static boolean isStudentGeslaagd(int i, int maxPoints) {
        if (i > 0.5 * maxPoints) {
            return true;
        }
        return false;
    }


}
