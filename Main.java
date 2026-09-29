import java.util.Scanner;

public class Main {

    // Global components
    static StudentLinkedList linkedList = new StudentLinkedList();
    static ActionStack actionStack = new ActionStack();
    static ServiceQueue serviceQueue = new ServiceQueue();
    static StudentBST bst = new StudentBST();
    static StudentHashTable hashTable = new StudentHashTable(100);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  UNIVERSITY STUDENT MANAGEMENT SYSTEM");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:  addStudent(); break;
                case 2:  updateStudent(); break;
                case 3:  deleteStudent(); break;
                case 4:  linkedList.displayAll(); break;
                case 5:  addServiceRequest(); break;
                case 6:  processServiceRequest(); break;
                case 7:  actionStack.displayAll(); break;
                case 8:  bst.displayInOrder(); break;
                case 9:  searchUsingHash(); break;
                case 10: System.out.println("(Graph component - Member 3 handles this)"); break;
                case 11: System.out.println("(Graph component - Member 3 handles this)"); break;
                case 12: System.out.println("(Graph component - Member 3 handles this)"); break;
                case 13: System.out.println("(Graph component - Member 3 handles this)"); break;
                case 14: System.out.println("(Graph component - Member 3 handles this)"); break;
                case 15: System.out.println("(Graph component - Member 3 handles this)"); break;
                case 16:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1-16.");
            }
        }
        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n===== UNIVERSITY STUDENT MANAGEMENT =====");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS/DFS");
        System.out.println("16. Exit");
    }

    // ========== STUDENT OPERATIONS ==========

    static void addStudent() {
        System.out.println("\n--- Add Student ---");
        String id = readString("Enter Student ID: ");
        String name = readString("Enter Name: ");
        String programme = readString("Enter Programme: ");
        double marks = readDouble("Enter Marks (0-100): ");

        Student student = new Student(id, name, programme, marks);

        if (linkedList.addStudent(student)) {
            bst.insert(student);
            hashTable.insert(student);
            actionStack.push("Added student: " + id);
            System.out.println("Student added successfully.");
        }
    }

    static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        String id = readString("Enter Student ID to update: ");

        Student existing = linkedList.searchStudent(id);
        if (existing == null) {
            System.out.println("Error: No student found with ID '" + id + "'.");
            return;
        }

        String name = readString("Enter new Name: ");
        String programme = readString("Enter new Programme: ");
        double marks = readDouble("Enter new Marks (0-100): ");

        if (linkedList.updateStudent(id, name, programme, marks)) {
            actionStack.push("Updated student: " + id);
            System.out.println("Student updated successfully.");
        }
    }

    static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        String id = readString("Enter Student ID to delete: ");

        if (linkedList.deleteStudent(id)) {
            bst.delete(id);
            hashTable.delete(id);
            actionStack.push("Deleted student: " + id);
            System.out.println("Student deleted successfully.");
        }
    }

    static void searchUsingHash() {
        System.out.println("\n--- Search Student using Hashing ---");
        String id = readString("Enter Student ID to search: ");

        Student found = hashTable.search(id);
        if (found != null) {
            System.out.println("Student found:");
            System.out.println(found);
        } else {
            System.out.println("Student not found.");
        }
    }

    // ========== SERVICE QUEUE OPERATIONS ==========

    static void addServiceRequest() {
        System.out.println("\n--- Add Service Request ---");
        String request = readString("Enter service request: ");
        if (request.trim().isEmpty()) {
            System.out.println("Error: Request cannot be empty.");
            return;
        }
        serviceQueue.enqueue(request);
        actionStack.push("Added service request: " + request);
        System.out.println("Request added to queue.");
    }

    static void processServiceRequest() {
        System.out.println("\n--- Process Next Service Request ---");
        String processed = serviceQueue.dequeue();
        if (processed != null) {
            System.out.println("Processed: " + processed);
            actionStack.push("Processed request: " + processed);
        } else {
            System.out.println("No pending service requests.");
        }
    }

    // ========== INPUT HELPERS ==========

    static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0 || value > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}