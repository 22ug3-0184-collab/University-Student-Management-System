public class Student {
    private String studentId;
    private String name;
    private String programme;
    private double marks;

    public Student(String studentId, String name, String programme, double marks) {
        setStudentId(studentId);
        setName(name);
        setProgramme(programme);
        setMarks(marks);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        if (programme == null || programme.trim().isEmpty()) {
            throw new IllegalArgumentException("Programme cannot be empty.");
        }
        this.programme = programme.trim();
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %-12s | Name: %-22s | Programme: %-25s | Marks: %.2f",
                studentId, name, programme, marks);
    }
}
