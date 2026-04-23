package xyz.vanez.tracker.repository;

import xyz.vanez.tracker.model.TrainInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainInstanceRepository extends JpaRepository<TrainInstance, Integer> {
    List<TrainInstance> findByModelId(Integer modelId);
}