import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // 1. Add Location
    public boolean addLocation(String location) {
        if (location == null || location.trim().isEmpty()) return false;
        String loc = location.trim();
        if (adjacencyList.containsKey(loc)) return false;
        adjacencyList.put(loc, new ArrayList<>());
        return true;
    }

    // 2. Remove Location
    public boolean removeLocation(String location) {
        if (location == null || !adjacencyList.containsKey(location.trim())) return false;
        String loc = location.trim();
        adjacencyList.remove(loc);
        for (List<String> neighbors : adjacencyList.values()) {
            neighbors.remove(loc);
        }
        return true;
    }

    // 3. Add Connection
    public boolean addConnection(String loc1, String loc2) {
        if (loc1 == null || loc2 == null) return false;
        String l1 = loc1.trim();
        String l2 = loc2.trim();
        if (!adjacencyList.containsKey(l1) || !adjacencyList.containsKey(l2)) return false;
        if (adjacencyList.get(l1).contains(l2)) return false;
        
        adjacencyList.get(l1).add(l2);
        adjacencyList.get(l2).add(l1);
        return true;
    }

    // 4. Remove Connection
    public boolean removeConnection(String loc1, String loc2) {
        if (loc1 == null || loc2 == null) return false;
        String l1 = loc1.trim();
        String l2 = loc2.trim();
        if (!adjacencyList.containsKey(l1) || !adjacencyList.containsKey(l2)) return false;
        
        adjacencyList.get(l1).remove(l2);
        adjacencyList.get(l2).remove(l1);
        return true;
    }

    // 5. Display Connections
    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No locations available.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + String.join(", ", entry.getValue()));
        }
    }

    // 6. BFS Traversal
    public void bfsTraversal(String startLocation) {
        if (startLocation == null || !adjacencyList.containsKey(startLocation.trim())) return;
        String start = startLocation.trim();
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        visited.add(start);
        queue.add(start);
        
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("BFS Traversal: " + String.join(" -> ", result));
    }

    // 7. DFS Traversal
    public void dfsTraversal(String startLocation) {
        if (startLocation == null || !adjacencyList.containsKey(startLocation.trim())) return;
        String start = startLocation.trim();
        
        Set<String> visited = new HashSet<>();
        List<String> result = new ArrayList<>();
        
        dfsHelper(start, visited, result);
        System.out.println("DFS Traversal: " + String.join(" -> ", result));
    }

    private void dfsHelper(String current, Set<String> visited, List<String> result) {
        visited.add(current);
        result.add(current);
        for (String neighbor : adjacencyList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }
}