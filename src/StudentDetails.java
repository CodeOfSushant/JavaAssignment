import java.io.Serializable;

public class StudentDetails implements Serializable {

    protected String name;
    protected int age;
    protected String address;
    protected long rollNo;
    protected char[] course;
    private static final long serialVersionUID = 1L;

    StudentDetails(String name, int age, String address, long rollNo, char[] course) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.rollNo = rollNo;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getRollNo() {
        return rollNo;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "StudentDetails{\nStudent-Name=" + name + ",\nAge=" +age+",\nAddress="+address+",\nRoll no="+rollNo+",\nCourse="+String.valueOf(course)+"\n";}
}


