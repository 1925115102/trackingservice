package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.model.Item;
import edu.iu.c322.trackingservice.model.ItemStatus;
import edu.iu.c322.trackingservice.model.Order;
import edu.iu.c322.trackingservice.model.Update;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import edu.iu.c322.trackingservice.repository.TrackingRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackingRepository repository;

    public TrackingController(TrackingRepository repository) {
        this.repository = repository;
    }

    // Get localhost:8080/orders
    @GetMapping("/{id}/{itemId}")
    public ItemStatus findById(@PathVariable int id, @PathVariable int itemId){
        return repository.findById(id, itemId);
    }


    // PUT localhost:8080/order/2
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Update update, @PathVariable int id){
        repository.update(update, id);
    }

}
