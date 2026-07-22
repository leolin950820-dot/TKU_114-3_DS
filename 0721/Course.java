public class Course {
    private String code;
    private String name;
    private int enrolled;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public void enroll() {
        enrolled++;
    }

    @Override
    public String toString() {
        return code + " | " + name + " | enrolled=" + enrolled;
    }
}