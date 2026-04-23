package xyz.vanez.tracker.repository;

import xyz.vanez.tracker.model.TrainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainModelRepository extends JpaRepository<TrainModel, Integer> {
}