/**
 * Represents one student record shared by all data-structure components.
 */
public class Student {

    private String studentId;
    private String name;
    private String programme;
    private double marks;

    /**
     * Creates a student record. Collection-level validation is performed when
     * the record is added to a StudentLinkedList.
     */
    public Student(String studentId, String name, String programme, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.programme = programme;
        this.marks = marks;
    }

    /** Returns the student's unique identifier. */
    public String getStudentId() {
        return studentId;
    }

    /** Returns the student's name. */
    public String getName() {
        return name;
    }

    /** Returns the student's programme. */
    public String getProgramme() {
        return programme;
    }

    /** Returns the student's marks. */
    public double getMarks() {
        return marks;
    }

    /** Updates the student's identifier. */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /** Updates the student's name. */
    public void setName(String name) {
        this.name = name;
    }

    /** Updates the student's programme. */
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /** Updates the student's marks. */
    public void setMarks(double marks) {
        this.marks = marks;
    }

    /** Returns a readable single-line representation of this record. */
    @Override
    public String toString() {
        return "Student ID: " + studentId +
               ", Name: " + name +
               ", Programme: " + programme +
               ", Marks: " + marks;
    }
}
