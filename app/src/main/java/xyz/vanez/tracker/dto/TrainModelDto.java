package xyz.vanez.tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainModelDto {
    private Integer id;

    @NotBlank(message = "Название модели не может быть пустым")
    @Size(max = 100, message = "Название не более 100 символов")
    private String name;

    @Size(max = 100, message = "Производитель не более 100 символов")
    private String manufacturer;

    @Min(value = 1700, message = "Год выпуска должен быть не ранее 1700")
    private Integer yearOfProduction;

    @Min(value = 0, message = "Мощность должна быть положительной")
    private Integer powerKw;

    @Min(value = 0, message = "Скорость должна быть положительной")
    private Integer maxSpeedKmh;
}
