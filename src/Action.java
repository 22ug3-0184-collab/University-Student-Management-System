import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Action {
    private final String description;
    private final LocalDateTime timestamp;

    public Action(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " - " + description;
    }
}
