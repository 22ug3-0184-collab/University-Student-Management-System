import java.util.LinkedList;

/**
 * ServiceQueue - Queue to manage student service requests in order of arrival.
 * Uses FIFO (First In First Out) principle.
 * Member 2 - Queue Component
 */
public class ServiceQueue {

    private LinkedList<String> requests;

    public ServiceQueue() {
        requests = new LinkedList<>();
    }

    public void enqueue(String request) {
        requests.addLast(request);
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }
        return requests.removeFirst();
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return requests.getFirst();
    }

    public boolean isEmpty() {
        return requests.isEmpty();
    }

    public int size() {
        return requests.size();
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("=== Pending Service Requests (In Order) ===");
        int count = 1;
        for (String request : requests) {
            System.out.println(count + ". " + request);
            count++;
        }
    }
}