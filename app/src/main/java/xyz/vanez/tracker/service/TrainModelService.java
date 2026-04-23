package xyz.vanez.tracker.service;


import org.springframework.stereotype.Service;
import xyz.vanez.tracker.dto.TrainModelDto;
import xyz.vanez.tracker.exception.ResourceNotFoundException;
import xyz.vanez.tracker.mapper.TrainModelMapper;
import xyz.vanez.tracker.model.TrainModel;
import xyz.vanez.tracker.repository.TrainModelRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainModelService {
    private final TrainModelRepository repository;
    private final TrainModelMapper mapper;

    public List<TrainModelDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public TrainModelDto getById(Integer id) {
        TrainModel model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Модель с id=" + id + " не найдена"));
        return mapper.toDto(model);
    }

    @Transactional
    public TrainModelDto create(TrainModelDto dto) {
        TrainModel entity = mapper.toEntity(dto);
        TrainModel saved = repository.save(entity);
        log.info("Создана модель поезда: {}", saved.getName());
        return mapper.toDto(saved);
    }

    @Transactional
    public TrainModelDto update(Integer id, TrainModelDto dto) {
        TrainModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Модель с id=" + id + " не найдена"));
        mapper.updateEntityFromDto(dto, existing);
        TrainModel updated = repository.save(existing);
        log.info("Обновлена модель поезда id={}", id);
        return mapper.toDto(updated);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Модель с id=" + id + " не найдена");
        }
        repository.deleteById(id);
        log.info("Удалена модель поезда id={}", id);
    }
}