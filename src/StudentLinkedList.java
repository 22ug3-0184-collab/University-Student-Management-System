public class StudentLinkedList {
    private StudentNode head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public boolean containsId(String studentId) {
        return search(studentId) != null;
    }

    public boolean add(Student student) {
        if (student == null || containsId(student.getStudentId())) {
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
            current.setNext(newNode);
        }

        size++;
        return true;
    }

    public Student search(String studentId) {
        StudentNode current = head;

        while (current != null) {
            if (current.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                return current.getStudent();
            }
            current = current.getNext();
        }

        return null;
    }

    public boolean update(String studentId, String name, String programme, double marks) {
        Student student = search(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);
        return true;
    }

    public boolean delete(String studentId) {
        if (head == null) {
            return false;
        }

        if (head.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
            head = head.getNext();
            size--;
            return true;
        }

        StudentNode current = head;

        while (current.getNext() != null) {
            if (current.getNext().getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        StudentNode current = head;
        int number = 1;

        while (current != null) {
            System.out.println(number + ". " + current.getStudent());
            current = current.getNext();
            number++;
        }

        System.out.println("Total students: " + size);
    }
}
