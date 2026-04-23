package xyz.vanez.tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import xyz.vanez.tracker.model.TrainModel;
import xyz.vanez.tracker.service.TrainModelService;

@RestController
@RequestMapping("/api/models")
public class TrainModelController {
    private final TrainModelService service;

    public TrainModelController(TrainModelService service) { this.service = service; }

    @GetMapping
    public List<TrainModel> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public TrainModel getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainModel create(@RequestBody TrainModel model) { return service.create(model); }

    @PutMapping("/{id}")
    public TrainModel update(@PathVariable Integer id, @RequestBody TrainModel model) { return service.update(id, model); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) { service.delete(id); }
}