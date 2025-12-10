import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    protected static String name;
    protected static int age;
    protected static String address;
    protected static long rollNo;

    protected static char[] course= new char[4];

   static  List<StudentDetails> newStudentDetailList = new ArrayList<>();


    // user addition function
    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        // Ask For UserName and check for validation
        while (true) {
            System.out.println(" Enter Student's Full Name");
            name = scanner.nextLine();
            if (name.matches("^[a-zA-Z ]+$"))
                break;
            else if (name.isBlank())
                System.out.println(" Enter valid Name");
            else
                System.out.println(" Enter valid Name ");

        }
        // Ask For User Age
        while(true){
            System.out.println(" Enter Student's Age");
            try{
                age = Integer.parseInt(scanner.nextLine());
                if(age<=0)
                    System.out.println(" Enter valid Age");
                if(age>0)
                    break;
            }catch(NumberFormatException e){
                System.out.println(" Enter valid Age");
            }
        }
        // Add For User Address
        while(true){
            System.out.println(" Enter the Student's Address");
            address = scanner.nextLine();
            if(address.isBlank())
                System.out.println(" Enter valid Address");
            else
                break;
        }
        // Ask For User rollno
        while(true){
            System.out.println(" Enter Student's Roll no");
            try{
                rollNo = Long.parseLong(scanner.nextLine());
                if(rollNo>0)
                    break;
                if(rollNo<=0)
                    System.out.println(" Enter valid Roll No");
            }catch(NumberFormatException e){
                System.out.println(" Enter Valid Roll No");
            }
        }
        // Ask for set of courses and check for validation;

        System.out.println(" Choose any four courses out of following .\n A\t B\t C\t D\t E\t F");
        System.out.println(course.length);
        for (int i = 0; i < course.length; i++) {
             while(true) {

                try {
                    String c = scanner.nextLine();
                    // check for input character .
                    if (c.matches("^[a-fA-F]")) {
                        char ch = c.charAt(0);
                        boolean found = String.valueOf(course).chars().anyMatch(cf -> cf == ch);
                        if(!found) {
                            course[i] = Character.toUpperCase(ch);
                            break;
                        }else {
                            System.out.println(" Dont't Enter Duplicate Course. choose from the Unselected:");
                        }

                    } else
                        System.out.println(" Enter valid Course");
                }catch (InputMismatchException e) {
                           System.out.println(" Enter valid course");
                     }

            }

        }

        // create object to initiate values to the field and add to the list.
        StudentDetails newStudentDetails = new StudentDetails(name,age,address,rollNo,course);
        newStudentDetailList.add(newStudentDetails);
        System.out.println(newStudentDetailList);

    }

    // Function to display user details.
    public static void displayUser(){

    }

    // Function to Delete user details
    public static void deleteUser(){

    }

    // Function to save user details
    public static void saveUser(){

    }

    // function to exit

    public static void exit(){

    }

    public static void main(String[] args) {

        int input ;

        Scanner scanner = new Scanner(System.in);
        System.out.println("********STUDENT MANAGEMENT SYSTEM*********");
        // Ask for user input and validate the values.
        while(true){
            System.out.println(" Choose The Action To Continue\n");
            System.out.println("1.  Add User details\n2.  Display User details\n3.  Delete User details\n4.  Save User details\n5.  Exit");
           try{
               input = Integer.parseInt(scanner.nextLine());

               if(input>=1 && input <=5)
                   break;

               if(input<1 && input >5)
                   System.out.println(" Enter valid choice");

           }catch(InputMismatchException  | NumberFormatException e){
               System.out.println(" Enter valid choice ");
           }

        }

         // Provide functions to user menu input

        switch (input){
            case 1: addUser();
            case 2: displayUser();
            case 3: deleteUser();
            case 4: saveUser();
            case 5: exit();
        }
    }
}
