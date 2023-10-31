import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShowMenuAll();
    }

    static void ShowMenuAll() {
        Scanner scanner = new Scanner(System.in);
        String choose;
        showMenu();
        choose = scanner.nextLine();
        switch (choose) {
            case "1" -> Static_student();
            case "2" -> Management_student();
            case "0" -> {
                System.out.println("exited!");
            }
            default -> {
                System.out.println("invalid! please choose action in below menu:");
                ShowMenuAll();
            }
        }
    }

    public static void Static_student() {
        String choose;
        boolean exit = false;
        StaticStudent staticStudent = new StaticStudent();

        Menu();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextLine();
            switch (choose) {
                case "1" -> staticStudent.createStudent();
                case "2" -> staticStudent.findByStudent();
                case "3" -> staticStudent.UpdateStudent();
                case "4" -> staticStudent.DeleteStudent();
                case "5" -> staticStudent.findByTo5Studnet();
                case "6" -> System.out.println("not flie");
                case "7" -> System.out.println("not flie");
                case "8" -> System.out.println("not flie");
                case "9" -> ShowMenuAll();

                case "0" -> {
                    System.out.println("exited!");
                    exit = true;
                }
                default -> System.out.println("invalid! please choose action in below menu:");
            }
            if (exit) {
                break;
            }
            Menu();
        }
    }

    public static void Management_student() {
        String choose;
        boolean exit = false;
        ManagementStudent managementStudent = new ManagementStudent();

        Menu();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextLine();
            switch (choose) {
                case "1" -> managementStudent.createStudent();
                case "2" -> managementStudent.findByStudent();
                case "3" -> managementStudent.UpdateStudent();
                case "4" -> managementStudent.DeleteStudent();
                case "5" -> managementStudent.findByTo5Studnet();
                case "6" -> managementStudent.sortByRankDescending();
                case "7" -> managementStudent.Displays_GPA_percentage();
                case "8" -> managementStudent.findByRank();
                case "9" -> ShowMenuAll();
                case "0" -> {
                    managementStudent.SaveFile();
                    System.out.println("exited!");
                    exit = true;
                }
                default -> System.out.println("invalid! please choose action in below menu:");
            }
            if (exit) {
                break;
            }
            Menu();
        }

    }

    public static void showMenu() {
        System.out.println("-----------------------");
        System.out.println("1. Static student.");
        System.out.println("2. Management student");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }

    public static void Menu() {
        System.out.println("-----------menu------------");
        System.out.println("1. create student.");
        System.out.println("2. Read student by id");
        System.out.println("3. Update student by id");
        System.out.println("4. Delete student by id");
        System.out.println("5. find to 5 student");
        System.out.println("6. sortByRankDescending");
        System.out.println("7. Displays_GPA_percentage");
        System.out.println("8. findByRank");
        System.out.println("9. Back Menu");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}