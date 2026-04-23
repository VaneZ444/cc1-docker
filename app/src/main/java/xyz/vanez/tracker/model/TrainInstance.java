package xyz.vanez.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "train_instance")
public class TrainInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serialNumber;
    private LocalDate manufactureDate;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private TrainModel model;
}
