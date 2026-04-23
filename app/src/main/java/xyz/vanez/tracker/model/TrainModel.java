package xyz.vanez.tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "train_model")
public class TrainModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String manufacturer;
    private Integer yearOfProduction;
    private Integer powerKw;
    private Integer maxSpeedKmh;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrainInstance> instances;
}