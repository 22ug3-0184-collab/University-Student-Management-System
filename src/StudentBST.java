public class StudentBST {
    private BSTNode root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean insert(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new BSTNode(student);
            return true;
        }

        return insertRecursive(root, student);
    }

    private boolean insertRecursive(BSTNode current, Student student) {
        int comparison = student.getStudentId().compareToIgnoreCase(
                current.getStudent().getStudentId());

        if (comparison == 0) {
            return false;
        }

        if (comparison < 0) {
            if (current.getLeft() == null) {
                current.setLeft(new BSTNode(student));
                return true;
            }
            return insertRecursive(current.getLeft(), student);
        }

        if (current.getRight() == null) {
            current.setRight(new BSTNode(student));
            return true;
        }

        return insertRecursive(current.getRight(), student);
    }

    public Student search(String studentId) {
        BSTNode current = root;

        while (current != null) {
            int comparison = studentId.compareToIgnoreCase(
                    current.getStudent().getStudentId());

            if (comparison == 0) {
                return current.getStudent();
            }

            current = comparison < 0 ? current.getLeft() : current.getRight();
        }

        return null;
    }

    public boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }

        root = deleteRecursive(root, studentId);
        return true;
    }

    private BSTNode deleteRecursive(BSTNode node, String studentId) {
        if (node == null) {
            return null;
        }

        int comparison = studentId.compareToIgnoreCase(
                node.getStudent().getStudentId());

        if (comparison < 0) {
            node.setLeft(deleteRecursive(node.getLeft(), studentId));
        } else if (comparison > 0) {
            node.setRight(deleteRecursive(node.getRight(), studentId));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            BSTNode successor = findMinimum(node.getRight());
            node.setStudent(successor.getStudent());
            node.setRight(deleteRecursive(
                    node.getRight(), successor.getStudent().getStudentId()));
        }

        return node;
    }

    private BSTNode findMinimum(BSTNode node) {
        BSTNode current = node;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    public void updateStudentReference(Student student) {
        BSTNode node = findNode(root, student.getStudentId());

        if (node != null) {
            node.setStudent(student);
        }
    }

    private BSTNode findNode(BSTNode node, String studentId) {
        if (node == null) {
            return null;
        }

        int comparison = studentId.compareToIgnoreCase(
                node.getStudent().getStudentId());

        if (comparison == 0) {
            return node;
        }

        return comparison < 0
                ? findNode(node.getLeft(), studentId)
                : findNode(node.getRight(), studentId);
    }

    public void displayInOrder() {
        if (isEmpty()) {
            System.out.println("BST is empty.");
            return;
        }

        inOrder(root);
    }

    private void inOrder(BSTNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeft());
        System.out.println(node.getStudent());
        inOrder(node.getRight());
    }
}
