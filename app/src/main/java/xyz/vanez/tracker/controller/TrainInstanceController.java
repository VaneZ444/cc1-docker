package xyz.vanez.tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vanez.tracker.model.TrainInstance;
import xyz.vanez.tracker.service.TrainInstanceService;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class TrainInstanceController {
    private final TrainInstanceService service;

    public TrainInstanceController(TrainInstanceService service) { this.service = service; }

    @GetMapping
    public List<TrainInstance> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public TrainInstance getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainInstance create(@RequestBody TrainInstance model) { return service.create(model); }

    @PutMapping("/{id}")
    public TrainInstance update(@PathVariable Integer id, @RequestBody TrainInstance model) { return service.update(id, model); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) { service.delete(id); }
}