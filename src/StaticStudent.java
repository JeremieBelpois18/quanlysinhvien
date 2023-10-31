import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StaticStudent {

    private Student[] studentArry = new Student[5];
    private final Constant constant = new Constant();


    StaticStudent() {
        setStudentArry();
    }

    void setStudentArry() {
        studentArry[0] = new Student(1, "a", "01-01-1900", "hn", 1.8, 69.1, "012345678a", "viu", 1900, 4);
        studentArry[1] = new Student(2, "b", "02-02-1901", "sg", 1.7, 65.5, "123456789b", "hcmu", 1901, 3);
        studentArry[2] = new Student(3, "c", "03-03-1902", "dn", 1.9, 70.2, "23456789cd", "hanoi", 1902, 2);
        studentArry[3] = new Student(4, "d", "04-04-1903", "hu", 1.75, 68.0, "3456789dcf", "hueuni", 1903, 1);
        studentArry[4] = new Student(5, "e", "05-05-1904", "cm", 1.85, 75.5, "456345343g", "hcmut", 1904, 5);
    }


    //thêm sinh viên
    public void createStudent() {
        Scanner scanner = new Scanner(System.in);
        String codeStudent;
        Student student = new Student();
        try {
            while (true) {
                System.out.println("Enter student code ");
                codeStudent = scanner.nextLine();// nhập mã sinh viên
                if (codeStudent.isEmpty()) {//cảnh báo người dùng để trống
                    System.out.println("Student ID cannot be left blank");
                } else if (codeStudent.length() == constant.Max_StudentCode) {
                    boolean studentExists = false;
                    for (Student student1 : studentArry) {
                        if (student1 != null && student1.getStudentCode().equals(codeStudent)) {//tìm kiến mã sinh viên có trong list hay không
                            studentExists = true;
                            break;
                        }
                    }

                    if (studentExists) {
                        System.out.println("students already exist");//kiểm tra sinh viên có tồn tại hay không
                    } else {
                        student.setStudentCode(codeStudent);
                        break;
                    }
                } else {
                    System.out.println("exceed 10 characters");//kiểm tra người dùng có nhập quá 10 ký tự không
                }

            }
            //set id
            int countNonNullElements = 0;
            for (Student student1 : studentArry) {
                if (student1 != null) {
                    countNonNullElements++;
                }
            }
            long id = (countNonNullElements > 0) ? (countNonNullElements + 1) : 1;// set ID tự động tăng khi thêm sinh viên
            student.setId(id);
            System.out.println("your ID is " + student.getId());


            String name;
            while (true) {
                System.out.println("Enter student name");
                name = scanner.nextLine().trim(); // Lấy và loại bỏ khoảng trắng ở đầu và cuối tên
                if (!name.isEmpty()) {
                    student.setName(name);
                    break; // Nếu tên không trống, thoát khỏi vòng lặp
                }
                System.out.println("don't leave any space");
                scanner.nextLine();
            }

            String birthDate;
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");//format về năm
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");//format về int

            while (true) {
                try {
                    System.out.println("Enter birthdate in dd-MM-yyyy format:");
                    birthDate = scanner.nextLine().trim();
                    Date date = dateFormat.parse(birthDate);//lấy số năm trong chuôi;
                    int year = Integer.parseInt(yearFormat.format(date));//chuyển từ dạng Date sang int

                    if (year >= constant.Min_BirthDate) {
                        student.setBirthDate(birthDate);
                        break;
                    }

                    System.out.println("Date of birth should be from 1900 onwards.");
                } catch (ParseException | NumberFormatException e) {
                    System.out.println("Invalid date format. Please enter a valid date.");
                }
            }

            System.out.println("Enter address");
            String address = scanner.nextLine();// nhập địa chỉ học sinh
            if (address.length() > constant.MAX_ADDRESS) {
                System.out.println("Maximum 300 characters");
            }
            student.setAddress(address);

            while (true) {
                System.out.println("Enter height and weight");
                System.out.println("Enter height (in cm): ");
                double height = scanner.nextDouble();//nhâ chiều cao
                System.out.println("Enter weight (in kg): ");
                double weight = scanner.nextDouble();//nhập cân nặng
                System.out.println();
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

            while (true) {
                System.out.println("Enter nameSchool ");
                String nameSchool;
                nameSchool = scanner.nextLine().trim(); // Nhập và loại bỏ khoảng trắng ở đầu và cuối tên trường
                if (nameSchool.isEmpty()) {
                    System.out.println("don't leave any space ");
                } else if (nameSchool.length() > constant.Max_NameSchool) {
                    System.out.println("Maximum 200 characters ");
                } else {
                    // Đã nhập đúng giá trị, thoát khỏi vòng lặp.
                    student.setNameSchool(nameSchool);
                    break;
                }
            }
            while (true) {
                System.out.println("Enter a year starting from 1900 (4 digits):");
                try {
                    int startYear;
                    startYear = Integer.parseInt(scanner.nextLine());//nhập năm bắt đầu học

                    if (startYear >= constant.Min_year && startYear <= 9999) {//nhập năm bắt đầu 1900 đến 9999
                        student.setStartYear(startYear);
                        break;
                    } else {
                        System.out.println("Year must be from 1900 and have 4 digits.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid year (4 digits). Please enter again.");
                }
            }

            while (true) {
                System.out.println("Enter an average score between 0.0 and 10.0:");
                try {
                    float gpa;
                    gpa = Float.parseFloat(scanner.nextLine());//nhập điểm trung bình

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
            int intValue = (int) id;
            Student[] newStudent = new Student[intValue];//tạo mảng có độ dài tương ứng
            System.arraycopy(studentArry, 0, newStudent, 0, studentArry.length);
            newStudent[intValue - 1] = student;
            studentArry = newStudent;// gán mảng mới vào mảng cũ
            System.out.println("create success ");
            System.out.println();
            System.out.println(studentArry[(int) id]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //tìm sinh viên
    public Student findByStudent() {
        Scanner sc = new Scanner(System.in);
        boolean validIdEntered = false;
        ArrayList<Student> listStudent = new ArrayList<>();
        Student studentsave = new Student();
        do {
            System.out.println("Enter the ID to search:");
            long idStudent = sc.nextLong();

            for (Student student : studentArry) {
                if (student != null && student.getId() == idStudent) {
                    listStudent.add(student);
                }
            }

            if (listStudent.isEmpty()) {
                System.out.println("No data found for the provided ID.");
                System.out.println("Do you want to search again? (yes/no)");
                sc.nextLine(); // Đọc dòng trống sau nextLong()
                String response = sc.nextLine().trim();
                if (response.equalsIgnoreCase("no")) {
                    validIdEntered = true;
                }
            } else {
                System.out.println("Found student(s) with the provided ID:");
                for (Student student : listStudent) {
                    studentsave = student;
                    System.out.println(student);
                    break;
                }
                validIdEntered = true;
            }
        } while (!validIdEntered);
        return studentsave;
    }

    //update sinh viên
    public void UpdateStudent() {
        try {
            Student student = findByStudent();
            if (student.getName() != null) {
                selectStudent(student);//menu chọn
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteStudent() {
        try {
            Student student1 = findByStudent();
            if (student1 != null) {
                System.out.println("Deleted student information:");
                studentArry[(int) student1.getId()] = null; // Xóa sinh viên bằng cách đặt giá trị null
                bubbleSort(studentArry);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void findByTo5Studnet() {
        for (int i = 0; i < 5; i++) {
            System.out.println(studentArry[i]);
        }
    }

    private void selectStudent(Student student) {
        Scanner scanner = new Scanner(System.in);
        String choose;
        boolean bl = true;
        MenuStudent();
        while (true) {
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
                    System.out.println("exited!");
                    System.out.println(student);
                    bl = false;
                    break;
                default:
                    System.out.println("invalid! please choose action in below menu:");
                    break;
            }
            if (!bl) {
                break;
            }
            MenuStudent();
        }
    }


    private String testStudentCode(String codeStudent) {
        boolean studentExists = false;
        if (codeStudent.isEmpty()) {//cảnh báo người dùng để trống
            System.out.println("Student ID cannot be left blank");
        } else if (codeStudent.length() > constant.Max_StudentCode) {
            System.out.println("exceed 10 characters");//kiểm tra người dùng có nhập quá 10 ký tự không
        }
        for (Student student1 : studentArry) {
            if (student1 != null && student1.getStudentCode().equals(codeStudent)) {//tìm kiến mã sinh viên có trong list hay không
                studentExists = true;
                break;
            }
        }

        if (studentExists) {
            System.out.println("students already exist");//kiểm tra sinh viên có tồn tại hay không
        }
        return codeStudent;
    }

    private void bubbleSort(Student[] arr) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] == null && arr[i] != null) {
                    // Nếu phần tử trước đó là null và phần tử hiện tại không phải null, hoán đổi chúng
                    Student temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
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
}

