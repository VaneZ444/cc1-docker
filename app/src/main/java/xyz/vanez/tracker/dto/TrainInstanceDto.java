package xyz.vanez.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainInstanceDto {
    private Integer id;

    @NotBlank(message = "Серийный номер обязателен")
    @Size(max = 50, message = "Серийный номер не более 50 символов")
    private String serialNumber;

    @PastOrPresent(message = "Дата выпуска не может быть в будущем")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate manufactureDate;

    @Size(max = 500, message = "Ссылка на изображение слишком длинная")
    private String imageUrl;

    @NotNull(message = "ID модели обязателен")
    private Integer modelId;
}