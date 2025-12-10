public class StudentDetails {

    protected String name;
    protected int age;
    protected String address;
    protected long rollNo;
    protected char[] course;

    StudentDetails(String name, int age, String address, long rollNo, char[] course) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.rollNo = rollNo;
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentDetails{\nStudent-Name=" + name + ",\nAge=" +age+",\nAddress="+address+",\nRoll no="+rollNo+",\nnCourse="+String.valueOf(course);}
}


