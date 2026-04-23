package xyz.vanez.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train_model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String manufacturer;

    @Column(name = "year_of_production")
    private Integer yearOfProduction;

    @Column(name = "power_kw")
    private Integer powerKw;

    @Column(name = "max_speed_kmh")
    private Integer maxSpeedKmh;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainInstance> instances = new ArrayList<>();
}