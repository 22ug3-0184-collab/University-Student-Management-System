/**
 * StudentBST - Binary Search Tree to organize/search student records by Student ID.
 * Member 2 - BST Component
 */
public class StudentBST {

    // BST Node class
    private class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public StudentBST() {
        root = null;
    }

    // Insert කිරීම
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node root, Student student) {
        if (root == null) {
            return new Node(student);
        }

        // Student ID අනුව compare කිරීම
        int cmp = student.getStudentId().compareTo(root.student.getStudentId());
        if (cmp < 0) {
            root.left = insertRec(root.left, student);
        } else if (cmp > 0) {
            root.right = insertRec(root.right, student);
        } else {
            // Duplicate ID
            System.out.println("Duplicate Student ID: " + student.getStudentId());
        }

        return root;
    }

    // Search කිරීම
    public Student search(String studentId) {
        return searchRec(root, studentId);
    }

    private Student searchRec(Node root, String studentId) {
        if (root == null) {
            return null;
        }

        int cmp = studentId.compareTo(root.student.getStudentId());
        if (cmp == 0) {
            return root.student;
        } else if (cmp < 0) {
            return searchRec(root.left, studentId);
        } else {
            return searchRec(root.right, studentId);
        }
    }

    // Delete කිරීම
    public boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }
        root = deleteRec(root, studentId);
        return true;
    }

    private Node deleteRec(Node root, String studentId) {
        if (root == null) {
            return null;
        }

        int cmp = studentId.compareTo(root.student.getStudentId());
        if (cmp < 0) {
            root.left = deleteRec(root.left, studentId);
        } else if (cmp > 0) {
            root.right = deleteRec(root.right, studentId);
        } else {
            // Node එක හමු වුණා

            // Case 1: Leaf node
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: එක child එකක් පමණයි
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            // Case 3: Children දෙකක්
            Node successor = findMin(root.right);
            root.student = successor.student;
            root.right = deleteRec(root.right, successor.student.getStudentId());
        }

        return root;
    }

    private Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Inorder traversal - sorted order එකෙන් display
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No students in BST.");
            return;
        }
        System.out.println("=== Students (Sorted by ID) ===");
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.student);
            inorderRec(root.right);
        }
    }

    // Tree එකේ තියෙන students ගණන
    public int count() {
        return countRec(root);
    }

    private int countRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countRec(root.left) + countRec(root.right);
    }
}