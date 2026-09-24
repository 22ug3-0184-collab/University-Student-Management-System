/**
 * A node in the manually implemented singly linked student list.
 */
public class StudentNode {

    private Student student;
    private StudentNode next;

    /** Creates a node containing the supplied student. */
    public StudentNode(Student student) {
        this.student = student;
    }

    /** Returns the student stored in this node. */
    public Student getStudent() {
        return student;
    }

    /** Returns the next node, or {@code null} at the end of the list. */
    public StudentNode getNext() {
        return next;
    }

    /** Sets the next node in the list. */
    public void setNext(StudentNode next) {
        this.next = next;
    }
}
