import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class FlashSaleInventory {

    private Map<String, AtomicInteger> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, new AtomicInteger(quantity));
        waitingList.put(productId, new LinkedList<>());
    }

    public int checkStock(String productId) {
        return stock.get(productId).get();
    }

    public synchronized String purchaseItem(String productId, int userId) {

        AtomicInteger count = stock.get(productId);

        if (count.get() > 0) {
            int remaining = count.decrementAndGet();
            return "Success, remaining stock: " + remaining;
        } else {

            Queue<Integer> queue = waitingList.get(productId);
            queue.offer(userId);

            return "Added to waiting list. Position: " + queue.size();
        }
    }
}