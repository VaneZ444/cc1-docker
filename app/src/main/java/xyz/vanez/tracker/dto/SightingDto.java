package xyz.vanez.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SightingDto {
    private Integer id;

    @NotBlank(message = "Регион обязателен")
    @Size(max = 100, message = "Регион не более 100 символов")
    private String region;

    @Size(max = 100, message = "Железная дорога не более 100 символов")
    private String railway;

    @NotNull(message = "Дата наблюдения обязательна")
    @PastOrPresent(message = "Дата наблюдения не может быть в будущем")
    private LocalDate sightingDate;

    @Size(max = 500, message = "Ссылка на изображение слишком длинная")
    private String imageUrl;

    private String note;

    @NotNull(message = "ID экземпляра поезда обязателен")
    private Integer instanceId;
}