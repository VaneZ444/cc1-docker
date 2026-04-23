package xyz.vanez.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vanez.tracker.model.TrainModel;

public interface TrainModelRepository extends JpaRepository<TrainModel, Integer> {
}
