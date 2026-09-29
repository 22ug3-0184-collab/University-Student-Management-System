/**
 * Small unit-style harness for the Member 2 student and linked-list components.
 */
public class StudentLinkedListTester {

    /** Runs CRUD and validation checks for StudentLinkedList. */
    public static void main(String[] args) {
        StudentLinkedList students = new StudentLinkedList();
        check("new list is empty", students.isEmpty());
        check("add valid student", students.addStudent(
                new Student("S001", "Asha Perera", "Computer Science", 82.5)));
        check("add second student", students.addStudent(
                new Student("S002", "Nimal Silva", "Information Systems", 70)));
        check("size is two", students.size() == 2);
        check("duplicate ID rejected", !students.addStudent(
                new Student("S001", "Other Name", "Computing", 60)));
        check("invalid name rejected", !students.addStudent(
                new Student("S003", "Nimal123", "Computing", 60)));
        check("out-of-range marks rejected", !students.addStudent(
                new Student("S004", "Valid Name", "Computing", 101)));
        check("non-numeric marks rejected", !students.addStudent(
                new Student("S005", "Valid Name", "Computing", Double.NaN)));
        check("search finds record", students.searchStudent("S001") != null);
        check("update succeeds", students.updateStudent(
                "S001", "Asha Fernando", "Software Engineering", 91));
        check("missing update rejected",
                !students.updateStudent("S999", "Valid Name", "Computing", 50));
        check("delete succeeds", students.deleteStudent("S002"));
        check("missing delete rejected", !students.deleteStudent("S999"));

        System.out.println("\nAll records:");
        students.displayAll();
        check("size after delete", students.size() == 1);
        check("delete final record", students.deleteStudent("S001"));
        check("empty list after deletes", students.isEmpty());
        System.out.println("\nEmpty list:");
        students.displayAll();
        System.out.println("\nTest harness completed.");
    }

    private static void check(String description, boolean passed) {
        System.out.println((passed ? "PASS: " : "FAIL: ") + description);
        if (!passed) {
            throw new AssertionError(description);
        }
    }
}
