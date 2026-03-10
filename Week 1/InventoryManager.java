// PROBLEM 2
import java.util.*;
import java.util.concurrent.*;

public class InventoryManager {
    private final Map<String, Integer> stockMap;              // productId -> stockCount
    private final Map<String, Queue<Integer>> waitingList;    // productId -> waiting users

    public InventoryManager() {
        stockMap = new ConcurrentHashMap<>();
        waitingList = new ConcurrentHashMap<>();
    }

    // Add product with initial stock
    public void addProduct(String productId, int stock) {
        stockMap.put(productId, stock);
        waitingList.put(productId, new LinkedList<>());
    }

    // Check stock availability
    public String checkStock(String productId) {
        return productId + " → " + stockMap.getOrDefault(productId, 0) + " units available";
    }

    // Purchase item safely (synchronized to prevent overselling)
    public synchronized String purchaseItem(String productId, int userId) {
        int stock = stockMap.getOrDefault(productId, 0);
        if (stock > 0) {
            stockMap.put(productId, stock - 1);
            return "Success, " + (stock - 1) + " units remaining";
        } else {
            waitingList.get(productId).add(userId);
            return "Added to waiting list, position #" + waitingList.get(productId).size();
        }
    }

    // Get waiting list for a product
    public Queue<Integer> getWaitingList(String productId) {
        return waitingList.getOrDefault(productId, new LinkedList<>());
    }

    // Demo
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.addProduct("IPHONE15_256GB", 2);

        System.out.println(manager.checkStock("IPHONE15_256GB"));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
    }
}
