package xyz.vanez.tracker.service;


import org.springframework.stereotype.Service;
import xyz.vanez.tracker.model.TrainModel;
import xyz.vanez.tracker.repository.TrainModelRepository;

import java.util.List;

@Service
public class TrainModelService {
    private final TrainModelRepository repository;

    public TrainModelService(TrainModelRepository repository) {
        this.repository = repository;
    }

    public List<TrainModel> getAll() { return repository.findAll(); }
    public TrainModel getById(Integer id) { return repository.findById(id).orElseThrow(); }
    public TrainModel create(TrainModel model) { return repository.save(model); }
    public TrainModel update(Integer id, TrainModel model) {
        model.setId(id);
        return repository.save(model);
    }
    public void delete(Integer id) { repository.deleteById(id); }
}