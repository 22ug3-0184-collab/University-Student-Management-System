public class StudentHashTable {
    private static class Entry {
        private Student student;
        private Entry next;

        Entry(Student student) {
            this.student = student;
        }
    }

    private final Entry[] table;
    private int size;

    public StudentHashTable() {
        this(17);
    }

    public StudentHashTable(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Hash table capacity must be positive.");
        }
        table = new Entry[capacity];
    }

    private int hash(String studentId) {
        return (studentId.toLowerCase().hashCode() & 0x7fffffff) % table.length;
    }

    public boolean insert(Student student) {
        if (student == null || search(student.getStudentId()) != null) {
            return false;
        }

        int index = hash(student.getStudentId());
        Entry newEntry = new Entry(student);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
        return true;
    }

    public Student search(String studentId) {
        if (studentId == null) {
            return null;
        }

        int index = hash(studentId);
        Entry current = table[index];

        while (current != null) {
            if (current.student.getStudentId().equalsIgnoreCase(studentId)) {
                return current.student;
            }
            current = current.next;
        }

        return null;
    }

    public boolean delete(String studentId) {
        if (studentId == null) {
            return false;
        }

        int index = hash(studentId);
        Entry current = table[index];
        Entry previous = null;

        while (current != null) {
            if (current.student.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public void updateStudentReference(Student student) {
        if (student == null) {
            return;
        }

        int index = hash(student.getStudentId());
        Entry current = table[index];

        while (current != null) {
            if (current.student.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                current.student = student;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Hash table is empty.");
            return;
        }

        for (int i = 0; i < table.length; i++) {
            System.out.print("Bucket " + i + ": ");

            Entry current = table[i];

            if (current == null) {
                System.out.println("empty");
                continue;
            }

            while (current != null) {
                System.out.print(current.student.getStudentId());
                if (current.next != null) {
                    System.out.print(" -> ");
                }
                current = current.next;
            }

            System.out.println();
        }
    }
}
