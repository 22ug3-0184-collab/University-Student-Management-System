import java.util.LinkedList;

/**
 * ActionStack - Stack to maintain recent actions (Undo/History feature).
 * Uses LIFO (Last In First Out) principle.
 * Member 2 - Stack Component
 */
public class ActionStack {

    // LinkedList එකක් use කරනවා Stack එක විදියට (Java එකේ built-in)
    private LinkedList<String> actions;

    // Constructor - Stack එක හදනවා
    public ActionStack() {
        actions = new LinkedList<>();
    }

    // Action එකක් Stack එකට add කිරීම (push)
    public void push(String action) {
        actions.addFirst(action);   // අලුත් action එක උඩට දානවා
    }

    // අන්තිම action එක ගන්නවා (pop)
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        return actions.removeFirst();
    }

    // අන්තිම action එක බලනවා (pop නොකර)
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return actions.getFirst();
    }

    // Stack එක හිස්ද?
    public boolean isEmpty() {
        return actions.isEmpty();
    }

    // Stack එකේ තියෙන ගණන
    public int size() {
        return actions.size();
    }

    // හැම action එකක්ම display කිරීම
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }
        System.out.println("=== Recent Actions (Latest First) ===");
        int count = 1;
        for (String action : actions) {
            System.out.println(count + ". " + action);
            count++;
        }
    }
}