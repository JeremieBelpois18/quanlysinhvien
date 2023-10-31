import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ManagementStudent {

    private final Constant constant = new Constant();
    private final ArrayList<Student> studentList = new ArrayList<>();

    ManagementStudent() {
        studentL();
    }

    void studentL() {
        studentList.add(new Student(1, "a", "01-01-1900", "hn", 1.8, 69.1, "0", "viu", 1900, 4));
        studentList.add(new Student(2, "b", "02-02-1901", "sg", 1.7, 65.5, "1", "hcmu", 1901, 3));
        studentList.add(new Student(3, "c", "03-03-1902", "dn", 1.9, 70.2, "2", "hanoi", 1902, 2));
        studentList.add(new Student(4, "d", "04-04-1903", "hue", 1.75, 68.0, "3", "hueuni", 1903, 1));
        studentList.add(new Student(5, "e", "05-05-1904", "cm", 1.85, 75.5, "4", "hcmut", 1904, 5));
    }


    public void createStudent() {
        try {
            Scanner scanner = new Scanner(System.in);
            Student student = new Student();
            while (true) {
                System.out.println("Enter student code:");
                String codeStudent = scanner.nextLine().trim();

                if (codeStudent.isEmpty()) {
                    System.out.println("Student ID cannot be left blank.");
                } else if (codeStudent.length() == constant.Max_StudentCode) {
                    boolean studentExists = studentList.stream().anyMatch(a -> a.getStudentCode().equals(codeStudent));

                    if (studentExists) {
                        System.out.println("Student already exists.");
                    } else {
                        student.setStudentCode(codeStudent);
                        break;
                    }
                } else {
                    System.out.println("Enter exactly 10 characters.");
                }
            }

            long countNonNullElements = studentList.stream().map(People::getId).count();
            long id = (countNonNullElements > 0) ? (countNonNullElements + 1) : 1;
            student.setId(id);
            System.out.println("Your ID is " + student.getId());

            String name;
            while (true) {
                System.out.println("Enter student name:");
                name = scanner.nextLine().trim();

                if (!name.isEmpty()) {
                    student.setName(name);
                    break;
                }

                System.out.println("Name cannot be empty.");
            }

            String birthDate;
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            while (true) {
                try {
                    System.out.println("Enter birthdate in dd-MM-yyyy format:");
                    birthDate = scanner.nextLine().trim();

                    if (isValidDateFormat(birthDate, "dd-MM-yyyy")) {
                        Date date = dateFormat.parse(birthDate);
                        int year = Integer.parseInt(yearFormat.format(date));

                        if (year < constant.Min_BirthDate) {
                            System.out.println("Date of birth should be from 1900 onwards.");
                        }

                        student.setBirthDate(birthDate);
                        break;
                    } else {
                        System.out.println("Invalid date format.");
                    }
                } catch (ParseException | NumberFormatException e) {
                    System.out.println("Invalid date format. Please enter a valid date.");
                }
            }

            System.out.println("Enter address:");
            String address = scanner.nextLine();

            if (address.length() > constant.MAX_ADDRESS) {
                System.out.println("Maximum 300 characters.");
            }
            student.setAddress(address);

            while (true) {
                System.out.println("Enter height and weight:");
                System.out.println("Enter height (in cm): ");
                double height = scanner.nextDouble();
                System.out.println("Enter weight (in kg): ");
                double weight = scanner.nextDouble();
                if (height > constant.Max_Height || weight > constant.Max_Weight) {
                    System.out.println("Height should not exceed 300cm and weight should not exceed 1000kg.");
                } else if (height < constant.Min_Height || weight < constant.Min_Weight) {
                    System.out.println("Height should be at least 50cm and weight should be at least 5kg.");
                } else {
                    student.setHeight(height);
                    student.setWeight(weight);
                    break;
                }
            }

            String nameSchool;
            while (true) {
                System.out.println("Enter nameSchool:");
                scanner.nextLine(); // Consume the newline character left by nextDouble()
                nameSchool = scanner.nextLine().trim();

                if (nameSchool.isEmpty()) {
                    System.out.println("Name of school cannot be empty.");
                } else if (nameSchool.length() > constant.Max_NameSchool) {
                    System.out.println("Maximum 200 characters.");
                } else {
                    student.setNameSchool(nameSchool);
                    break;
                }
            }

            int startYear;
            while (true) {
                System.out.println("Enter a year starting from 1900 (4 digits):");
                try {
                    startYear = Integer.parseInt(scanner.nextLine());

                    if (startYear >= constant.Min_year && startYear <= 9999) {
                        student.setStartYear(startYear);
                        break;
                    } else {
                        System.out.println("Year must be from 1900 and have 4 digits.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid year (4 digits). Please enter again.");
                }
            }

            float gpa;
            while (true) {
                System.out.println("Enter an average score between 0.0 and 10.0:");
                try {
                    gpa = Float.parseFloat(scanner.nextLine());

                    if (gpa >= 0.0 && gpa <= 10.0) {
                        student.setGpa(gpa);
                        break;
                    } else {
                        System.out.println("Average score must be between 0.0 and 10.0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid average score. Please enter again.");
                }
            }

            studentList.add(student);
            System.out.println("Student created successfully.");
            System.out.println();
            System.out.println(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Student findByStudent() {
        Scanner scanner = new Scanner(System.in);
        boolean validIdEntered = false;
        ArrayList<Student> listStudent ;
        Student studentSave = new Student();
        try{
            do {
                System.out.println("Enter the ID to search:");
                long idStudent = scanner.nextLong();
                // Sử dụng Stream API để lọc sinh viên có ID trùng khớp
                listStudent = studentList.stream()
                        .filter(student -> student.getId() == idStudent)
                        .collect(Collectors.toCollection(ArrayList::new));

                if (listStudent.isEmpty()) {
                    System.out.println("No data found for the provided ID.");
                    System.out.println("Do you want to search again? (yes/no)");
                    scanner.nextLine(); // Đọc dòng trống sau nextLong()
                    String response = scanner.nextLine().trim();
                    if (response.equalsIgnoreCase("no")) {
                        validIdEntered = true;
                    }
                } else {
                    System.out.println("Found student(s) with the provided ID:\n");
                    studentSave = listStudent.get(0);
                    System.out.println(studentSave);
                    validIdEntered = true;
                }
            } while (!validIdEntered);
            return studentSave;
        }catch (Exception e){
            System.out.println("not numbers");
            return studentSave;
        }
    }

    public void UpdateStudent() {
        try {
            //tìm kiếm theo id
            Student student1 = findByStudent();
            if (student1.getName() != null) {
                selectStudent(student1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteStudent() {
        try {
            Student student1 = findByStudent();
            if(student1.getName() == null){
                System.out.println("Deleted student information:");
                studentList.remove((int) student1.getId()); // Xóa sinh viên bằng phương thức remote
                bubbleSort(studentList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void findByTo5Studnet() {//nhập số lượng in số lượng sinh viên
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students to retrieve: ");
        int numberOfStudents = scanner.nextInt();

        if (numberOfStudents > studentList.size()) {
            System.out.println("The requested number of students exceeds the available number.");
            System.out.println("list only " + studentList.toArray().length);
        } else {
            List<Student> studentArrayList = studentList.subList(0, numberOfStudents);
            for (Student student1 : studentArrayList) {
                System.out.println(student1);
            }
        }
    }

    //in % theo danh hiệu
    public void sortByRankDescending() {
        Map<Rank, Integer> rankCount = new HashMap<>();
        long totalCount = studentList.size();

        // set rank ở 0
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }

        // đêm số lương rank
        for (Student student : studentList) {
            Rank studentRank = student.getRank();
            rankCount.put(studentRank, rankCount.get(studentRank) + 1);
        }

        TreeMap<Rank, Integer> sortedRankCount = new TreeMap<>(Collections.reverseOrder());
        sortedRankCount.putAll(rankCount);
        // in rank

        for (Map.Entry<Rank, Integer> entry : sortedRankCount.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            double percentage = (count / (double) totalCount) * 100.0;
            System.out.println("Rank " + rank + ": " + percentage + "%");
        }
    }


    //in % theo gpa
    public void Displays_GPA_percentage() {
        Map<Integer, Integer> gpaCountMap = new HashMap<>();
        for (Student student1 : studentList) {
            if (student1 != null) {
                float gpa = student1.getGpa();
                gpaCountMap.put((int) gpa, gpaCountMap.getOrDefault((int) gpa, 0) + 1);//đêm số lần xuất hiện gpa trong mảng
            }
        }
//         Tính phần trăm và hiển thị kết quả
        for (Map.Entry<Integer, Integer> entry : gpaCountMap.entrySet()) {
            int gpa = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / studentList.size() * 100.0;
            System.out.println(gpa + ": " + percentage + "%");
        }
    }

    public void findByRank() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the rank (POOR, WEAK, AVERAGE, GOOD, VERYGOOD, or EXCELLENT):");
        String rankInput = scanner.nextLine().toUpperCase(); // Convert input to uppercase

        ArrayList<Student> listRankStudent = studentList.stream()
                .filter(student -> student.getRank().toString().equals(rankInput))
                .collect(Collectors.toCollection(ArrayList::new));

        if (listRankStudent.isEmpty()) {
            System.out.println("No students found with the specified rank.");
        } else {
            System.out.println("Students with rank " + rankInput + ":");
            for (Student student : listRankStudent) {
                System.out.println(student);
            }
        }
    }

    public void SaveFile() {
        try {
            FileWriter fileWriter = new FileWriter("./FileSave.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Student student1 : studentList) {
                if (student1 != null) {
                    printWriter.println(student1);//luu dữ liệu vào file
                }
            }
            printWriter.close();

            System.out.println("Data has been saved to the file.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void selectStudent(Student student) {
        boolean exit = false;
        MenuStudent();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String choose;
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    System.out.println("Enter name");
                    student.setName(scanner.nextLine());
                    break;
                case "2":
                    System.out.println("Enter birthdate in dd-MM-yyyy format");
                    student.setBirthDate(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Enter address");
                    student.setAddress(scanner.nextLine());
                    break;
                case "4":
                    System.out.println("Enter height");
                    student.setHeight(scanner.nextDouble());
                    break;
                case "5":
                    System.out.println("Enter weight");
                    student.setWeight(scanner.nextDouble());
                    break;
                case "6":
                    System.out.println("Enter studentcode");
                    student.setStudentCode(testStudentCode(scanner.nextLine()));
                    break;
                case "7":
                    System.out.println("Enter nameschool");
                    student.setNameSchool(scanner.nextLine());
                    break;
                case "8":
                    System.out.println("Enter startyear");
                    student.setStartYear(scanner.nextInt());
                    break;
                case "9":
                    System.out.println("Enter GPA");
                    student.setGpa(scanner.nextFloat());
                    break;
                case "0":
                    System.out.println(student + "\n");
                    System.out.println("exited!");
                    exit = true;
                    break;
                default:
                    System.out.println("invalid! please choose action in below menu:");
                    break;
            }
            if (exit) {
                break;
            }
            MenuStudent();
        }
    }

    void MenuStudent() {
        System.out.println("1: you want to fix name");
        System.out.println("2: you want to fix birthDate");
        System.out.println("3: you want to fix address");
        System.out.println("4: you want to fix height");
        System.out.println("5: you want to fix weight");
        System.out.println("6: you want to fix studentCode");
        System.out.println("7: you want to fix nameSchool");
        System.out.println("8: you want to fix startYear");
        System.out.println("9: you want to fix gpa");
        System.out.println("0: exit");
    }

    private String testStudentCode(String codeStudent) {
        String str = codeStudent.trim();// nhập mã sinh viên
        while (true) {
            System.out.println("Enter student code");
            if (str.isEmpty()) {//cảnh báo người dùng để trống
                System.out.println("Student ID cannot be left blank");
            } else if (str.length() > constant.Max_StudentCode) {
                System.out.println("exceed 10 characters");//kiểm tra người dùng có nhập quá 10 ký tự không
            } else {
                boolean studentExists = studentList.stream().anyMatch(a -> a.getStudentCode().equals(str));//tìm kiến mã sinh viên có trong list hay không

                if (studentExists) {
                    System.out.println("students already exist");//kiểm tra sinh viên có tồn tại hya không
                } else {
                    break;
                }
            }

        }
        return codeStudent;
    }

    private void bubbleSort(List<Student> arr) {
        int n = arr.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr.get(i - 1) == null && arr.get(i) != null) {
                    // Nếu phần tử trước đó là null và phần tử hiện tại không phải null, hoán đổi chúng
                    Student temp = arr.get(i - 1);
                    arr.set(i - 1, arr.get(i));
                    arr.set(i, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static boolean isValidDateFormat(String input, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); // Không cho phép chuyển đổi sai định dạng ngày
        try {
            sdf.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
