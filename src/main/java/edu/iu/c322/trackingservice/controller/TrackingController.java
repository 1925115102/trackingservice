package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Item;
import edu.iu.c322.trackingservice.model.ItemStatus;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.Update;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private final WebClient orderService;


    public TrackingController(WebClient.Builder webClientBuilder) {
        orderService = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    // Get localhost:8080/orders
    @GetMapping("/{orderId}/{itemId}")
    public ItemStatus findById(@PathVariable int orderId, @PathVariable int itemId){
        Order order = orderService.get().uri("/orders/order/{orderId}", orderId)
                .retrieve()
                .bodyToMono(Order.class).block();
        Item item = null;
        for (Item theItem : order.items()){
            if (theItem.id() == itemId){
                item = theItem;
            }
        }

        ItemStatus itemStatus = new ItemStatus();
        itemStatus.setStatus(item.status());
        return itemStatus;
    }


    // PUT localhost:8080/order/2
    @PutMapping("/{orderId}")
    public void update(@RequestBody Update update, @PathVariable int orderId){
        Order order = orderService.get().uri("/orders/order/{orderId}", orderId)
                .retrieve()
                .bodyToMono(Order.class).block();

        Item item = null;
        for (Item theItem : order.items()){
            if (theItem.id() == update.getItemId()){
                item = theItem;
            }
        }

        Item newItem = new Item(item.id(), item.name(), item.quantity(), item.price(), update.getStatus());

        List<Item> newItems = new ArrayList<>();
        for (int i = 0; i < order.items().size(); i++){
            if (order.items().get(i).id() != newItem.id()){
                newItems.add(order.items().get(i));
            }
            else {
                newItems.add(newItem);
            }
        }

        // create a new order with the updated list of items
        Order updatedOrder = new Order(order.id(), order.customerId(), order.total(),
                order.shippingAddress(), newItems, order.payment());

        // update the order in the order service
        orderService.put().uri("/orders/{orderId}", orderId)
                .bodyValue(updatedOrder)
                .retrieve()
                .toBodilessEntity().block();
    }

}
