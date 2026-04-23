package xyz.vanez.tracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vanez.tracker.model.TrainInstance;
import java.util.List;

public interface TrainInstanceRepository extends JpaRepository<TrainInstance, Integer> {
    List<TrainInstance> findByModelId(Integer modelId);
}