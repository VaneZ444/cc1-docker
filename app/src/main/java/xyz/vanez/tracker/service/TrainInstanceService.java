package xyz.vanez.tracker.service;


import org.springframework.stereotype.Service;
import xyz.vanez.tracker.dto.TrainInstanceDto;
import xyz.vanez.tracker.exception.ResourceNotFoundException;
import xyz.vanez.tracker.mapper.TrainInstanceMapper;
import xyz.vanez.tracker.model.TrainInstance;
import xyz.vanez.tracker.model.TrainModel;
import xyz.vanez.tracker.repository.TrainInstanceRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import xyz.vanez.tracker.repository.TrainModelRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainInstanceService {
    private final TrainInstanceRepository instanceRepository;
    private final TrainModelRepository modelRepository;
    private final TrainInstanceMapper mapper;

    public List<TrainInstanceDto> getAll(Integer modelId) {
        List<TrainInstance> instances;
        if (modelId != null) {
            instances = instanceRepository.findByModelId(modelId);
        } else {
            instances = instanceRepository.findAll();
        }
        return instances.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public TrainInstanceDto getById(Integer id) {
        TrainInstance instance = instanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Экземпляр поезда с id=" + id + " не найден"));
        return mapper.toDto(instance);
    }

    @Transactional
    public TrainInstanceDto create(TrainInstanceDto dto) {
        TrainModel model = modelRepository.findById(dto.getModelId())
                .orElseThrow(() -> new ResourceNotFoundException("Модель с id=" + dto.getModelId() + " не существует"));
        TrainInstance entity = mapper.toEntity(dto);
        entity.setModel(model);
        TrainInstance saved = instanceRepository.save(entity);
        log.info("Создан экземпляр поезда: {} (модель {})", saved.getSerialNumber(), model.getName());
        return mapper.toDto(saved);
    }

    @Transactional
    public TrainInstanceDto update(Integer id, TrainInstanceDto dto) {
        TrainInstance existing = instanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Экземпляр с id=" + id + " не найден"));
        if (dto.getModelId() != null && !dto.getModelId().equals(existing.getModel().getId())) {
            TrainModel newModel = modelRepository.findById(dto.getModelId())
                    .orElseThrow(() -> new ResourceNotFoundException("Модель с id=" + dto.getModelId() + " не существует"));
            existing.setModel(newModel);
        }
        mapper.updateEntityFromDto(dto, existing);
        TrainInstance updated = instanceRepository.save(existing);
        log.info("Обновлён экземпляр поезда id={}", id);
        return mapper.toDto(updated);
    }

    @Transactional
    public void delete(Integer id) {
        if (!instanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Экземпляр с id=" + id + " не найден");
        }
        instanceRepository.deleteById(id);
        log.info("Удалён экземпляр поезда id={}", id);
    }
}