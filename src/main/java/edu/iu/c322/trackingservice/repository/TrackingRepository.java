package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.model.Item;
import edu.iu.c322.trackingservice.model.ItemStatus;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackingRepository {
    private List<Order> orders = new ArrayList<>();

    public ItemStatus findById(int id, int itemId){
        for(Order order: orders){
            if (order.getOrderId()==id){
                ItemStatus itemStatus = new ItemStatus();
                itemStatus.setStatus(order.getItems().get(itemId).getStatus());
                return itemStatus;
            }
        }
        throw new IllegalStateException("order with this id does not exist in the system.");
    }

    public void update(Update update, int id){
        Order x = getOrderById(id);
        if (x != null){
            for (Item item : x.getItems()) {
                if (item.getId() == update.getItemId()) {
                    item.setStatus(update.getStatus());
                    return;
                }
            }
        } else {
            throw new IllegalStateException("order with this id does not exist in the system");
        }
    }

    private Order getOrderById(int id) {
        return orders.stream().filter(x -> x.getCustomerId() == id).findAny().orElse(null);
    }
}
