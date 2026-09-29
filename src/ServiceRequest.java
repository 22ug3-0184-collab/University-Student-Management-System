public class ServiceRequest {
    private final int requestId;
    private final String studentId;
    private final String description;

    public ServiceRequest(int requestId, String studentId, String description) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Request description cannot be empty.");
        }

        this.requestId = requestId;
        this.studentId = studentId.trim();
        this.description = description.trim();
    }

    public int getRequestId() {
        return requestId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Request #%d | Student ID: %s | %s",
                requestId, studentId, description);
    }
}
