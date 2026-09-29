public class ActionStack {
    private static class StackNode {
        private Action action;
        private StackNode next;

        StackNode(Action action) {
            this.action = action;
        }
    }

    private StackNode top;
    private int size;

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void push(Action action) {
        if (action == null) {
            return;
        }

        StackNode node = new StackNode(action);
        node.next = top;
        top = node;
        size++;
    }

    public Action pop() {
        if (isEmpty()) {
            return null;
        }

        Action action = top.action;
        top = top.next;
        size--;
        return action;
    }

    public Action peek() {
        return isEmpty() ? null : top.action;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Action stack is empty.");
            return;
        }

        StackNode current = top;
        int number = 1;

        while (current != null) {
            System.out.println(number + ". " + current.action);
            current = current.next;
            number++;
        }
    }
}
