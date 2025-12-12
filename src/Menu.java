import java.io.*;
import java.util.*;

public class Menu {

    protected static String name;
    protected static int age;
    protected static String address;
    protected static long rollNo;

    protected static char[] course = new char[4];

    static List<StudentDetails> newStudentDetailList = new ArrayList<>();
    static List<StudentDetails> currentList = new ArrayList<>();
    static List<StudentDetails> readList = new ArrayList<>();

    static String fileName = "studentdetails.txt";
    static Scanner scanner = new Scanner(System.in);


    // user addition function
    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        boolean play = true;
        while (play) {

            // Ask For UserName and check for validation
            while (true) {
                System.out.println(" Enter Student's Full Name");
                name = scanner.nextLine();
                if(name.isBlank()){
                    System.out.println(" Name cant be left with blank : Please Enter Valid Name ");
                    continue;
                }
                if (name.matches("^[a-zA-Z ]+$")) {
                    break;
                }
                else
                    System.out.println(" Enter valid Name ");

            }
            // Ask For User Age
            while (true) {
                System.out.println(" Enter Student's Age");
                try {
                    age = Integer.parseInt(scanner.nextLine());
                    if (age <= 0)
                        System.out.println(" Enter valid Age");
                    if (age > 0)
                        break;
                } catch (NumberFormatException e) {
                    System.out.println(" Enter valid Age");
                }
            }
            // Add For User Address
            while (true) {
                System.out.println(" Enter the Student's Address");
                address = scanner.nextLine();
                if (address.isBlank())
                    System.out.println(" Enter valid Address");
                else
                    break;
            }
            // Ask For User rollno
            int g = 0;
            while (true) {
                System.out.println(" Enter Student's Roll no");
                try {
                    rollNo = Long.parseLong(scanner.nextLine());
                    if (rollNo > 0) {
                        List<StudentDetails> currentList = displayAllUser();
                        Iterator<StudentDetails> iterator = currentList.iterator();
                        while (iterator.hasNext()) {
                            StudentDetails data = iterator.next();
                            if (data.rollNo == rollNo) {
                                System.out.println(+rollNo + " is already assigned to others. please Enter Unique Roll No");
                                g = 1;
                            }
                        }
                        if (g == 1) {
                            currentList.clear();
                            g = 0;
                            continue;
                        }
                        break;
                    }
                    if (rollNo <= 0)
                        System.out.println(" Enter valid Roll No");
                } catch (NumberFormatException e) {
                    System.out.println(" Enter Valid Roll No");
                }
            }
            // Ask for set of courses and check for validation;

            System.out.println(" Choose any four courses out of following .\n A\t B\t C\t D\t E\t F");
            System.out.println(course.length);
            for (int i = 0; i < course.length; i++) {
                while (true) {

                    try {
                        String c = scanner.nextLine();
                        // check for input character .
                        if (c.matches("^[a-fA-F]")) {
                            char chr = c.charAt(0);
                            char ch = Character.toUpperCase(chr);
                            boolean found = String.valueOf(course).chars().anyMatch(cf -> cf == ch);
                            if (!found) {
                                course[i] = Character.toUpperCase(ch);
                                break;
                            } else {
                                System.out.println(" Dont't Enter Duplicate Course. choose from the Unselected:");
                            }

                        } else {
                            System.out.println(" Enter valid Course");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(" Enter valid course");
                    }

                }

            }

            // create object to initiate values to the field and add to the list.
            StudentDetails newStudentDetails = new StudentDetails(name, age, address, rollNo, course);
            newStudentDetailList.add(newStudentDetails);
            System.out.println(newStudentDetails.name+"'s Data has been added ");
            course = new char[course.length];

            System.out.println(" You Want To add More User? Enter n for No and any Key for Yes:");
            String choice = scanner.next().trim().toLowerCase();
            scanner.nextLine();
            if (choice.equals("n")) {
                play = false;
            }

        }
    }

    // Function will Return Details of All the Users.
    public static List<StudentDetails> displayAllUser() {
        currentList = Serialize.readDataFromDisc();

        return currentList;
    }

    public static void displayByRollNo() {
        readList = displayAllUser();
        if (readList.isEmpty()) {
            System.out.println(" Student Record is empty: please Add some Record:  ");
            return;
        }
        boolean b = true;
        long roll = 0;
        System.out.println(" Enter The Roll No: ");
        while (b) {
            try {
                roll = Long.parseLong(scanner.nextLine());
                if (roll <= 0) {
                    System.out.println(" Roll No Cant Be a Zero or Below : Enter Valid Roll No");
                } else {
                    b = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(" This is Not a Roll No : please Enter Correct Roll no .");
            }
        }
        Iterator<StudentDetails> iterator = readList.iterator();
        boolean show = false;
        while (iterator.hasNext()) {
            StudentDetails data = iterator.next();
            if (data.rollNo == roll) {
                show = true;
                if (show) {
                    System.out.println(" The Student Details for Roll No " + roll + "is :\n " + data);
                }
                break;
            }
        }
        readList.clear();
        currentList.clear();
        if (!show) {
            System.out.println(" No Record For Roll No has been Found : " + roll);
        }

    }

    // Function to display user details.
    public static void displayUser() {
        boolean b = true;
        int in = 0;
        while (b) {
            try {
                System.out.println(" choose Option ");
                System.out.println("\t 1. Display All users\n\t 2. Search By Roll No");
                in = Integer.parseInt(scanner.nextLine());
                switch (in) {
                    case 1:
                        readList = displayAllUser();
                        b = false;
                        break;
                    case 2:
                        displayByRollNo();
                        b = false;
                        break;
                    default:
                        System.out.println(" Enter correct Options");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Enter correct options");
            }
        }
            if (in == 1) {
                if (readList.isEmpty()) {
                    System.out.println(" Student Record is Empty : please Add some Record ");
                } else {
                    System.out.println(" User Data From :" + fileName);
                    System.out.printf("%-15s %-5s %-15s %15s%n", "NAME", "AGE", "ADDRESS", "ROLL NO", "COURSE");
                    System.out.println("---------------------------------------------------------------------");
                    for (StudentDetails user : readList) {
                        System.out.printf("%-15s %-5d %-15s %20d%n", user.name, user.age, user.address, user.rollNo);
                    }
                   // System.out.println(readList);
                    readList.clear();
                    currentList.clear();

                }

            }

    }


    // Function to Delete user details
    public static void deleteUser() {
        readList = displayAllUser();
        if (readList.isEmpty()) {
            System.out.println(" There Are No record to available to Delete ");
            return;
        }
        boolean b = true;
        long in = 0;
        System.out.println(" Enter roll no to delete its Record ");
        while (b) {
            try {
                in = Long.parseLong(scanner.nextLine());
                if (in <= 0) {
                    System.out.println(" Roll No cant be Below 1. Enter Correct Roll No: ");
                } else {
                    b = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(" This is Not a Roll no. Enter Correct Roll no: ");
            }
        }
        Iterator<StudentDetails> iterator = readList.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            StudentDetails data = iterator.next();
            if (data.rollNo == in) {
                iterator.remove();
                removed = true;
                System.out.println(" Found An Deleted Student Record with Roll No: " + in);
                break;
            }
        }
        if (removed) {
            saveUserDetailToFile(readList);
        } else {
            System.out.println("Roll No " + in + " has been not found");
        }


    }

    // Used Serialization to save data to disk;
    public static void saveUserDetailToFile(List<StudentDetails> savedList) {
              Serialize.saveDataToDisk(savedList);
              readList.clear();
              newStudentDetailList.clear();
              currentList.clear();;
    }

    // Function to save user details
    public static void saveUser() {
        if (newStudentDetailList.isEmpty()) {
            System.out.println(" There Is no Record Available to Save ");
            return;
        } else {
            readList = displayAllUser();
            if (readList.isEmpty()) {
                saveUserDetailToFile(newStudentDetailList);
            } else {
                readList.addAll(newStudentDetailList);
                saveUserDetailToFile(readList);
            }
            System.out.println(" Successfully saved data to :" + fileName);
        }

    }

    // function to exit
    public static void exit() {
        System.out.println();
        System.exit(1);

    }

    public static void main(String[] args) {

        int input;

        System.out.println("********STUDENT MANAGEMENT SYSTEM*********");
        // Ask for user input and validate the values.
        while (true) {
            System.out.println(" Choose The Action To Continue\n");
            System.out.println("1.  Add User details\n2.  Display User details\n3.  Delete User details\n4.  Save User details\n5.  Exit");
            try {
                input = Integer.parseInt(scanner.nextLine());

                if (input >= 1 && input <= 5) {

                    switch (input) {
                        case 1:
                            addUser();
                            break;
                        case 2:
                            displayUser();
                            break;
                        case 3:
                            deleteUser();
                            break;
                        case 4:
                            saveUser();
                            break;
                        case 5:
                            exit();
                            break;
                    }

                } else
                    System.out.println(" Enter valid choice");

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(" Enter valid choice ");
            }

        }


    }
}
