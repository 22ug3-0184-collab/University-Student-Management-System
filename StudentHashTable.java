/**
 * StudentHashTable - Hash Table for efficient Student ID searching.
 * Uses chaining (Linked List) for collision handling.
 * Member 2 - Hashing Component
 */
public class StudentHashTable {

    // Hash table slot (Linked List node)
    private class HashNode {
        String key;
        Student student;
        HashNode next;

        HashNode(String key, Student student) {
            this.key = key;
            this.student = student;
            this.next = null;
        }
    }

    private HashNode[] table;
    private int size;
    private int capacity;

    public StudentHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
        this.size = 0;
    }

    // Hash function - generate index from Student ID
    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (hashValue * 31 + key.charAt(i)) % capacity;
        }
        return Math.abs(hashValue);
    }

    // Insert a student
    public boolean insert(Student student) {
        String key = student.getStudentId();
        int index = hash(key);
        HashNode current = table[index];

        // Duplicate check
        while (current != null) {
            if (current.key.equals(key)) {
                return false;
            }
            current = current.next;
        }

        // Add new node at the head (chaining)
        HashNode newNode = new HashNode(key, student);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        return true;
    }

    // Search for a student
    public Student search(String key) {
        int index = hash(key);
        HashNode current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.student;
            }
            current = current.next;
        }
        return null;
    }

    // Delete a student
    public boolean delete(String key) {
        int index = hash(key);
        HashNode current = table[index];
        HashNode prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    // Get size
    public int size() {
        return size;
    }

    // Display all entries
    public void display() {
        if (size == 0) {
            System.out.println("Hash Table is empty.");
            return;
        }
        System.out.println("=== Hash Table Contents ===");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.print("Index " + i + ": ");
                HashNode current = table[i];
                while (current != null) {
                    System.out.print(current.key);
                    if (current.next != null) {
                        System.out.print(" -> ");
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}