package xyz.vanez.tracker.repository;

import xyz.vanez.tracker.model.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    List<Sighting> findByInstanceId(Integer instanceId);
}
