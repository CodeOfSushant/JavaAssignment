import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Menu {

    protected static String name;
    protected static int age;
    protected static String address;
    protected static long rollNo;

    protected static char[] course = new char[4];

    //  This list will contain data in add-user-runtime.
    static List<StudentDetails> newStudentDetailList = new ArrayList<>();

    // readList and currentList will use for getting and passing data
    static List<StudentDetails> currentList = new ArrayList<>();
    static List<StudentDetails> readList = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);


    // user addition function
    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        boolean adduser = true;
        while (adduser) {
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
                else {
                    System.out.println(" Enter valid Name ");
                }

            }
            // Ask For User Age
            while (true) {
                System.out.println(" Enter Student's Age");
                try {
                    age = Integer.parseInt(scanner.nextLine());
                    if (age <= 0) {
                        System.out.println(" Enter valid Age");
                    }
                    if (age > 0) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Enter valid Age");
                }
            }
            // Add For User Address
            while (true) {
                System.out.println(" Enter the Student's Address");
                address = scanner.nextLine();
                if (address.isBlank()) {
                    System.out.println(" Enter valid Address");
                }
                else {
                    break;
                }
            }
            // Ask For User rollno
            int rollNoFound = 0;
            while (true) {
                System.out.println(" Enter Student's Roll no");
                try {
                    rollNo = Long.parseLong(scanner.nextLine());
                    if (rollNo > 0) {
                        currentList = displayAllUser();
                        Iterator<StudentDetails> iterator = currentList.iterator();
                        while (iterator.hasNext()) {
                            StudentDetails data = iterator.next();
                            if (data.rollNo == rollNo) {
                                System.out.println(+rollNo + " is already assigned to others. please Enter Unique Roll No");
                                rollNoFound = 1;
                            }
                        }
                        if (rollNoFound == 1) {
                            currentList.clear();
                            rollNoFound = 0;
                            continue;
                        }
                        break;
                    }
                    if (rollNo <= 0) {
                        System.out.println(" Enter valid Roll No");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Enter Valid Roll No");
                }
            }
            // Ask for set of courses and check for validation;

            System.out.println(" Choose any four courses out of following .\n A\t B\t C\t D\t E\t F");
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
                                System.out.println(" Don't Enter Duplicate Course. choose from the Unselected:");
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
                adduser = false;
            }
        }
    }

    // Function will Return Details of All the Users.
    public static List<StudentDetails> displayAllUser() {
        currentList = Serialize.readDataFromDisc();
        return currentList;
    }
    // Function to read Data for special Roll No from Synchronised method and display to command line
    public static void displayByRollNo() {
        readList = displayAllUser();
        if (readList.isEmpty()) {
            System.out.println(" Student Record is empty: please Add some Record:  ");
            return;
        }
        boolean notgetRoll = true;
        long roll = 0;
        System.out.println(" Enter The Roll No: ");
        while (notgetRoll) {
            try {
                roll = Long.parseLong(scanner.nextLine());
                if (roll <= 0) {
                    System.out.println(" Roll No Cant Be a Zero or Below : Enter Valid Roll No");
                } else {
                    notgetRoll = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(" This is Not a Roll No : please Enter Correct Roll no .");
            }
        }
        Iterator<StudentDetails> iterator = readList.iterator();
        boolean getRoll = false;
        while (iterator.hasNext()) {
            StudentDetails data = iterator.next();
            if (data.rollNo == roll) {
                getRoll = true;
                if (getRoll) {
                    System.out.println(" The Student Details for Roll No " + roll + "is :\n " );
                    System.out.printf("%-30s %-5s %-30s %15s%n", "NAME", "AGE", "ADDRESS", "ROLL NO", "COURSE");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.printf("%-30s %-5d %-30s %15d%n", data.name, data.age, data.address, data.rollNo);
                }
                break;
            }
        }
        readList.clear();
        currentList.clear();
        if (!getRoll) {
            System.out.println(" No Record For Roll No has been Found : " + roll);
        }
    }

    // Function to display user details.
    public static void displayUser() {
        boolean play = true;
        int input = 0;
        while (play) {
            try {
                System.out.println(" choose Option ");
                System.out.println("\t 1. Display All users\n\t 2. Search By Roll No");
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        readList = displayAllUser();
                        play = false;
                        break;
                    case 2:
                        displayByRollNo();
                        play = false;
                        break;
                    default:
                        System.out.println(" Enter correct Options");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Enter correct options");
            }
        }
        if (input == 1) {
            if (readList.isEmpty()) {
                System.out.println(" Student Record is Empty : please Add some Record ");
            } else {
                while (true) {
                    System.out.println(" Enter The Order For Displaying Data:\n\t 1: Ascending order.\n\t 2. Descending Order");
                    try {
                        int inp = Integer.parseInt(scanner.nextLine());
                        if (!(inp > 0 && inp < 3)) {
                            System.out.println(" Enter Valid Options");
                            continue;
                        }
                        switch (inp) {
                            case 1:
                                while (true) {
                                    System.out.println("Ascending order By:\n\t 1: Name\n\t 2: Age\n\t 3: Roll No\n\t 4: Address");
                                    try {
                                        int inp1 = Integer.parseInt(scanner.nextLine());
                                        if (!(inp1 > 0 && inp1 < 5)) {
                                            System.out.println(" Enter valid choice: ");
                                            continue;
                                        }
                                        System.out.println(" User Data From :" + Serialize.fileName);
                                        System.out.printf("%-30s %-5s %-30s %15s%n", "NAME", "AGE", "ADDRESS", "ROLL NO", "COURSE");
                                        System.out.println("--------------------------------------------------------------------------------------------");
                                        for (StudentDetails user : readList) {
                                            System.out.printf("%-30s %-5d %-30s %15d%n", user.name, user.age, user.address, user.rollNo);
                                        }
                                        switch (inp1) {
                                            case 1:
                                                readList.sort(Comparator.comparing(StudentDetails::getName, String.CASE_INSENSITIVE_ORDER));
                                                break;
                                            case 2:
                                                readList.sort(Comparator.comparing(StudentDetails::getAge));
                                                break;
                                            case 3:
                                                readList.sort(Comparator.comparing(StudentDetails::getRollNo));
                                                break;
                                            case 4:
                                                readList.sort(Comparator.comparing(StudentDetails::getAddress));
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println(" Enter Valid Options: ");
                                        continue;
                                    }
                                    break;
                                }
                                break;
                            case 2:
                                while (true) {
                                    System.out.println("Descending order By:\n\t 1: Name\n\t 2: Age\n\t 3: Roll No\n\t 4: Address");
                                    try {
                                        int inp2 = Integer.parseInt(scanner.nextLine());
                                        if (!(inp2 >= 1 && inp2 <= 4)) {
                                            System.out.println(" Enter Valid Choice");
                                            continue;
                                        }
                                        System.out.println(" User Data From :" + Serialize.fileName);
                                        System.out.printf("%-30s %-5s %-30s %15s%n", "NAME", "AGE", "ADDRESS", "ROLL NO", "COURSE");
                                        System.out.println("--------------------------------------------------------------------------------------------");
                                        for (StudentDetails user : readList) {
                                            System.out.printf("%-30s %-5d %-30s %15d%n", user.name, user.age, user.address, user.rollNo);
                                        }
                                        switch (inp2) {
                                            case 1:
                                                readList.sort(Comparator.comparing(StudentDetails::getName, String.CASE_INSENSITIVE_ORDER).reversed());
                                                break;
                                            case 2:
                                                readList.sort(Comparator.comparing(StudentDetails::getAge).reversed());
                                                break;
                                            case 3:
                                                readList.sort(Comparator.comparing(StudentDetails::getRollNo).reversed());
                                                break;
                                            case 4:
                                                readList.sort(Comparator.comparing(StudentDetails::getAddress).reversed());
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println(" Enter Valid Options: ");
                                        continue;
                                    }
                                    break;
                                }
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(" Enter valid option: ");
                    }
                }
                System.out.println(" After sorting");
                System.out.printf("%-30s %-5s %-30s %15s%n", "NAME", "AGE", "ADDRESS", "ROLL NO", "COURSE");
                for (StudentDetails user : readList) {
                    System.out.printf("%-30s %-5d %-30s %15d%n", user.name, user.age, user.address, user.rollNo);
                }
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
        boolean notGetRoll = true;
        long in = 0;
        System.out.println(" Enter roll no to delete its Record ");
        while (notGetRoll) {
            try {
                in = Long.parseLong(scanner.nextLine());
                if (in <= 0) {
                    System.out.println(" Roll No cant be Below 1. Enter Correct Roll No: ");
                } else {
                    notGetRoll = false;
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
            System.out.println(" Successfully saved data to :" + Serialize.fileName);
        }
    }

    // function to exit
    public static void exit() {
        if(!(newStudentDetailList.isEmpty())){
            System.out.println(" Do you want to save before exit: Enter y for yes and Any key for no ");
            String in = scanner.nextLine();
            if(in.equalsIgnoreCase("y")){
                saveUser();
            }
        }
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

                } else {
                    System.out.println(" Enter valid choice");
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(" Enter valid choice  ");
            }
        }
    }
}
