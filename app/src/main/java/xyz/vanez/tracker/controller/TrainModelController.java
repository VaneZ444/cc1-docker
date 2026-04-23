package xyz.vanez.tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import xyz.vanez.tracker.dto.TrainModelDto;
import xyz.vanez.tracker.service.TrainModelService;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class TrainModelController {
    private final TrainModelService service;

    @GetMapping
    public List<TrainModelDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TrainModelDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainModelDto create(@Valid @RequestBody TrainModelDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public TrainModelDto update(@PathVariable Integer id, @Valid @RequestBody TrainModelDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}