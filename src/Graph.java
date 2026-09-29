import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private final Map<String, Set<String>> adjacencyList = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        String key = normalize(location);

        if (key == null || adjacencyList.containsKey(key)) {
            return false;
        }

        adjacencyList.put(key, new LinkedHashSet<>());
        return true;
    }

    public boolean removeLocation(String location) {
        String key = normalize(location);

        if (key == null || !adjacencyList.containsKey(key)) {
            return false;
        }

        adjacencyList.remove(key);

        for (Set<String> neighbors : adjacencyList.values()) {
            neighbors.remove(key);
        }

        return true;
    }

    public boolean addConnection(String first, String second) {
        String a = normalize(first);
        String b = normalize(second);

        if (a == null || b == null || a.equalsIgnoreCase(b)
                || !adjacencyList.containsKey(a)
                || !adjacencyList.containsKey(b)) {
            return false;
        }

        adjacencyList.get(a).add(b);
        adjacencyList.get(b).add(a);
        return true;
    }

    public boolean removeConnection(String first, String second) {
        String a = normalize(first);
        String b = normalize(second);

        if (a == null || b == null
                || !adjacencyList.containsKey(a)
                || !adjacencyList.containsKey(b)) {
            return false;
        }

        boolean removed = adjacencyList.get(a).remove(b);
        adjacencyList.get(b).remove(a);
        return removed;
    }

    public boolean containsLocation(String location) {
        String key = normalize(location);
        return key != null && adjacencyList.containsKey(key);
    }

    public void displayLocations() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations found.");
            return;
        }

        System.out.println("Campus Locations:");
        for (String location : adjacencyList.keySet()) {
            System.out.println("- " + location);
        }
    }

    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }

        System.out.println("Campus Connections:");
        for (Map.Entry<String, Set<String>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");

            if (entry.getValue().isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println(String.join(", ", entry.getValue()));
            }
        }
    }

    public List<String> bfs(String start) {
        String source = normalize(start);
        List<String> order = new ArrayList<>();

        if (source == null || !adjacencyList.containsKey(source)) {
            return order;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(source);
        queue.offer(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        return order;
    }

    public List<String> dfs(String start) {
        String source = normalize(start);
        List<String> order = new ArrayList<>();

        if (source == null || !adjacencyList.containsKey(source)) {
            return order;
        }

        Set<String> visited = new HashSet<>();
        dfsRecursive(source, visited, order);
        return order;
    }

    private void dfsRecursive(String current, Set<String> visited, List<String> order) {
        visited.add(current);
        order.add(current);

        for (String neighbor : adjacencyList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, order);
            }
        }
    }

    private String normalize(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }
}
