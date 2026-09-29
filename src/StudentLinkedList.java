/**
 * A singly linked list that stores and manages student records.
 */
public class StudentLinkedList {

    private StudentNode head;
    private int count;

    /**
     * Adds a valid student unless its ID is already present.
     *
     * @return {@code true} when the student is added
     */
    public boolean addStudent(Student student) {
        if (!isValidStudent(student)) {
            return false;
        }
        if (searchStudent(student.getStudentId()) != null) {
            System.out.println("Error: A student with ID '" + student.getStudentId()
                    + "' already exists.");
            return false;
        }

        StudentNode newNode = new StudentNode(student);
        if (head == null) {
            head = newNode;
        } else {
            StudentNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // Link the final node to the new node.
            current.setNext(newNode);
        }
        count++;
        return true;
    }

    /**
     * Updates all editable fields of the student with the supplied ID.
     *
     * @return {@code true} when the record is updated
     */
    public boolean updateStudent(String id, String newName, String newProgramme,
            double newMarks) {
        if (!isValidId(id)) {
            return false;
        }
        if (!isValidName(newName)) {
            System.out.println("Error: Name must contain only letters and spaces.");
            return false;
        }
        if (!isValidProgramme(newProgramme)) {
            System.out.println("Error: Programme cannot be empty.");
            return false;
        }
        if (!isValidMarks(newMarks)) {
            System.out.println("Error: Marks must be a number from 0 to 100.");
            return false;
        }

        Student student = searchStudent(id);
        if (student == null) {
            System.out.println("Error: No student found with ID '" + id + "'.");
            return false;
        }
        student.setName(newName.trim());
        student.setProgramme(newProgramme.trim());
        student.setMarks(newMarks);
        return true;
    }

    /**
     * Deletes the student with the supplied ID.
     *
     * @return {@code true} when a record is deleted
     */
    public boolean deleteStudent(String id) {
        if (!isValidId(id)) {
            return false;
        }

        StudentNode current = head;
        StudentNode previous = null;
        while (current != null) {
            if (id.equals(current.getStudent().getStudentId())) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    // Bypass the removed node so the list remains connected.
                    previous.setNext(current.getNext());
                }
                count--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        System.out.println("Error: No student found with ID '" + id + "'.");
        return false;
    }

    /** Returns the student with the supplied ID, or {@code null} when absent. */
    public Student searchStudent(String id) {
        if (!isValidId(id)) {
            return null;
        }
        StudentNode current = head;
        while (current != null) {
            if (id.equals(current.getStudent().getStudentId())) {
                return current.getStudent();
            }
            current = current.getNext();
        }
        return null;
    }

    /** Prints every record, or a friendly message when the list is empty. */
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No student records found. The list is empty.");
            return;
        }
        StudentNode current = head;
        while (current != null) {
            System.out.println(current.getStudent());
            current = current.getNext();
        }
    }

    /** Returns whether this list contains no records. */
    public boolean isEmpty() {
        return head == null;
    }

    /** Returns the number of records in this list. */
    public int size() {
        return count;
    }

    private boolean isValidStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Student record cannot be null.");
            return false;
        }
        return isValidId(student.getStudentId())
                && isValidName(student.getName())
                && isValidProgramme(student.getProgramme())
                && isValidMarks(student.getMarks());
    }

    private boolean isValidId(String id) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error: Student ID cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return false;
        }
        if (!name.trim().matches("[\\p{L}]+(?:[ ]+[\\p{L}]+)*")) {
            System.out.println("Error: Name must contain only letters and spaces.");
            return false;
        }
        return true;
    }

    private boolean isValidProgramme(String programme) {
        if (programme == null || programme.trim().isEmpty()) {
            System.out.println("Error: Programme cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean isValidMarks(double marks) {
        if (Double.isNaN(marks) || Double.isInfinite(marks) || marks < 0 || marks > 100) {
            System.out.println("Error: Marks must be a number from 0 to 100.");
            return false;
        }
        return true;
    }
}
