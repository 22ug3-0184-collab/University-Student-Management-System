import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentLinkedList studentList = new StudentLinkedList();
    private static final ActionStack actionStack = new ActionStack();
    private static final ServiceQueue serviceQueue = new ServiceQueue();
    private static final StudentBST studentBST = new StudentBST();
    private static final StudentHashTable hashTable = new StudentHashTable();
    private static final Graph campusGraph = new Graph();

    private static int nextRequestId = 1;

    public static void main(String[] args) {
        seedCampusLocations();

        boolean running = true;

        System.out.println("==============================================");
        System.out.println(" UNIVERSITY STUDENT MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        while (running) {
            displayMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> studentStructureMenu();
                case 7 -> stackMenu();
                case 8 -> queueMenu();
                case 9 -> graphMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n--------------- MAIN MENU ----------------");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Student Data Structures (BST / Hashing)");
        System.out.println("7. Recent Actions (Stack)");
        System.out.println("8. Service Requests (Queue)");
        System.out.println("9. Campus Connections (Graph)");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");

        String id = readNonEmpty("Student ID: ");

        if (studentList.containsId(id)) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String programme = readNonEmpty("Programme: ");
        double marks = readMarks("Marks (0-100): ");

        try {
            Student student = new Student(id, name, programme, marks);

            boolean added = studentList.add(student);

            if (!added) {
                System.out.println("Student could not be added.");
                return;
            }

            studentBST.insert(student);
            hashTable.insert(student);

            recordAction("Added student " + student.getStudentId());
            System.out.println("Student added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayStudents() {
        System.out.println("\n--- Student Records (Linked List) ---");
        studentList.display();
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        String id = readNonEmpty("Enter Student ID: ");

        Student listResult = studentList.search(id);

        if (listResult == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student found:");
        System.out.println(listResult);

        Student bstResult = studentBST.search(id);
        Student hashResult = hashTable.search(id);

        System.out.println("BST search: " + (bstResult != null ? "Found" : "Not found"));
        System.out.println("Hash table search: " + (hashResult != null ? "Found" : "Not found"));

        recordAction("Searched for student " + id);
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        String id = readNonEmpty("Enter Student ID: ");

        Student existing = studentList.search(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readNonEmpty("New name: ");
        String programme = readNonEmpty("New programme: ");
        double marks = readMarks("New marks (0-100): ");

        try {
            existing.setName(name);
            existing.setProgramme(programme);
            existing.setMarks(marks);

            // All structures hold the same Student object reference.
            studentBST.updateStudentReference(existing);
            hashTable.updateStudentReference(existing);

            recordAction("Updated student " + id);
            System.out.println("Student updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        String id = readNonEmpty("Enter Student ID: ");

        if (!studentList.containsId(id)) {
            System.out.println("Student not found.");
            return;
        }

        studentList.delete(id);
        studentBST.delete(id);
        hashTable.delete(id);

        recordAction("Deleted student " + id);
        System.out.println("Student deleted from Linked List, BST and Hash Table.");
    }

    private static void studentStructureMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Student Data Structures ---");
            System.out.println("1. BST In-order Traversal");
            System.out.println("2. Search using BST");
            System.out.println("3. Hash Table Display");
            System.out.println("4. Search using Hash Table");
            System.out.println("5. Delete using BST/Hash Table");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- BST In-order Traversal ---");
                    studentBST.displayInOrder();
                }
                case 2 -> {
                    String id = readNonEmpty("Enter Student ID: ");
                    Student student = studentBST.search(id);
                    System.out.println(student == null ? "Student not found." : student);
                }
                case 3 -> {
                    System.out.println("\n--- Hash Table ---");
                    hashTable.display();
                }
                case 4 -> {
                    String id = readNonEmpty("Enter Student ID: ");
                    Student student = hashTable.search(id);
                    System.out.println(student == null ? "Student not found." : student);
                }
                case 5 -> deleteStudent();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void stackMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Recent Actions / Stack ---");
            System.out.println("1. Push custom action");
            System.out.println("2. Pop latest action");
            System.out.println("3. Peek latest action");
            System.out.println("4. Display stack");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    String description = readNonEmpty("Action description: ");
                    actionStack.push(new Action(description));
                    System.out.println("Action pushed.");
                }
                case 2 -> {
                    Action action = actionStack.pop();
                    System.out.println(action == null
                            ? "Stack is empty."
                            : "Popped: " + action);
                }
                case 3 -> {
                    Action action = actionStack.peek();
                    System.out.println(action == null
                            ? "Stack is empty."
                            : "Top: " + action);
                }
                case 4 -> actionStack.display();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void queueMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Service Request Queue ---");
            System.out.println("1. Add service request");
            System.out.println("2. Process next request");
            System.out.println("3. View next request");
            System.out.println("4. Display all requests");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addServiceRequest();
                case 2 -> {
                    ServiceRequest request = serviceQueue.dequeue();
                    System.out.println(request == null
                            ? "Queue is empty."
                            : "Processed: " + request);
                    if (request != null) {
                        recordAction("Processed service request #" + request.getRequestId());
                    }
                }
                case 3 -> {
                    ServiceRequest request = serviceQueue.peek();
                    System.out.println(request == null
                            ? "Queue is empty."
                            : "Next: " + request);
                }
                case 4 -> serviceQueue.display();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addServiceRequest() {
        String studentId = readNonEmpty("Student ID: ");

        if (!studentList.containsId(studentId)) {
            System.out.println("Student ID does not exist.");
            return;
        }

        String description = readNonEmpty("Request description: ");

        ServiceRequest request =
                new ServiceRequest(nextRequestId++, studentId, description);

        serviceQueue.enqueue(request);
        recordAction("Added service request #" + request.getRequestId());
        System.out.println("Request added to queue.");
    }

    private static void graphMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Campus Graph ---");
            System.out.println("1. Add Location");
            System.out.println("2. Remove Location");
            System.out.println("3. Display Locations");
            System.out.println("4. Add Connection");
            System.out.println("5. Remove Connection");
            System.out.println("6. Display Connections");
            System.out.println("7. BFS Traversal");
            System.out.println("8. DFS Traversal");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addLocation();
                case 2 -> removeLocation();
                case 3 -> campusGraph.displayLocations();
                case 4 -> addConnection();
                case 5 -> removeConnection();
                case 6 -> campusGraph.displayConnections();
                case 7 -> runBfs();
                case 8 -> runDfs();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addLocation() {
        String location = readNonEmpty("Location name: ");

        if (campusGraph.addLocation(location)) {
            recordAction("Added campus location " + location);
            System.out.println("Location added.");
        } else {
            System.out.println("Location already exists or is invalid.");
        }
    }

    private static void removeLocation() {
        String location = readNonEmpty("Location name: ");

        if (campusGraph.removeLocation(location)) {
            recordAction("Removed campus location " + location);
            System.out.println("Location removed.");
        } else {
            System.out.println("Location not found.");
        }
    }

    private static void addConnection() {
        String first = readNonEmpty("First location: ");
        String second = readNonEmpty("Second location: ");

        if (campusGraph.addConnection(first, second)) {
            recordAction("Connected " + first + " and " + second);
            System.out.println("Connection added.");
        } else {
            System.out.println("Could not add connection. Check that both locations exist and are different.");
        }
    }

    private static void removeConnection() {
        String first = readNonEmpty("First location: ");
        String second = readNonEmpty("Second location: ");

        if (campusGraph.removeConnection(first, second)) {
            recordAction("Removed connection between " + first + " and " + second);
            System.out.println("Connection removed.");
        } else {
            System.out.println("Connection not found.");
        }
    }

    private static void runBfs() {
        String start = readNonEmpty("Start location: ");
        List<String> order = campusGraph.bfs(start);

        if (order.isEmpty()) {
            System.out.println("Location not found.");
        } else {
            System.out.println("BFS: " + String.join(" -> ", order));
            recordAction("Performed BFS from " + start);
        }
    }

    private static void runDfs() {
        String start = readNonEmpty("Start location: ");
        List<String> order = campusGraph.dfs(start);

        if (order.isEmpty()) {
            System.out.println("Location not found.");
        } else {
            System.out.println("DFS: " + String.join(" -> ", order));
            recordAction("Performed DFS from " + start);
        }
    }

    private static void recordAction(String description) {
        actionStack.push(new Action(description));
    }

    private static void seedCampusLocations() {
        campusGraph.addLocation("Main Gate");
        campusGraph.addLocation("Library");
        campusGraph.addLocation("Engineering Block");
        campusGraph.addLocation("IT Lab");
        campusGraph.addLocation("Student Center");

        campusGraph.addConnection("Main Gate", "Library");
        campusGraph.addConnection("Main Gate", "Student Center");
        campusGraph.addConnection("Library", "Engineering Block");
        campusGraph.addConnection("Engineering Block", "IT Lab");
        campusGraph.addConnection("Student Center", "IT Lab");
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                double marks = Double.parseDouble(input);

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
}
