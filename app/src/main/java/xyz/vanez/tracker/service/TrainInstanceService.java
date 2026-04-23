package xyz.vanez.tracker.service;


import org.springframework.stereotype.Service;
import xyz.vanez.tracker.model.TrainInstance;
import xyz.vanez.tracker.repository.TrainInstanceRepository;

import java.util.List;

@Service
public class TrainInstanceService {
    private final TrainInstanceRepository repository;

    public TrainInstanceService(TrainInstanceRepository repository) {
        this.repository = repository;
    }

    public List<TrainInstance> getAll() { return repository.findAll(); }
    public TrainInstance getById(Integer id) { return repository.findById(id).orElseThrow(); }
    public TrainInstance create(TrainInstance model) { return repository.save(model); }
    public TrainInstance update(Integer id, TrainInstance model) {
        model.setId(id);
        return repository.save(model);
    }
    public void delete(Integer id) { repository.deleteById(id); }
}