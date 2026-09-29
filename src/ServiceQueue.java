public class ServiceQueue {
    private static class QueueNode {
        private ServiceRequest request;
        private QueueNode next;

        QueueNode(ServiceRequest request) {
            this.request = request;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(ServiceRequest request) {
        if (request == null) {
            return;
        }

        QueueNode node = new QueueNode(request);

        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }

        size++;
    }

    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }

        ServiceRequest request = front.request;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return request;
    }

    public ServiceRequest peek() {
        return isEmpty() ? null : front.request;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Service request queue is empty.");
            return;
        }

        QueueNode current = front;

        while (current != null) {
            System.out.println(current.request);
            current = current.next;
        }

        System.out.println("Requests waiting: " + size);
    }
}
