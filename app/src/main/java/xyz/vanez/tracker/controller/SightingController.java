package xyz.vanez.tracker.controller;

import xyz.vanez.tracker.dto.SightingDto;
import xyz.vanez.tracker.service.SightingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sightings")
@RequiredArgsConstructor
public class SightingController {
    private final SightingService service;

    @GetMapping
    public List<SightingDto> getAll(@RequestParam(required = false) Integer instanceId) {
        return service.getAllByInstance(instanceId);
    }

    @GetMapping("/{id}")
    public SightingDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SightingDto create(@Valid @RequestBody SightingDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SightingDto update(@PathVariable Integer id, @Valid @RequestBody SightingDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}