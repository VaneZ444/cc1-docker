package xyz.vanez.tracker.service;

import xyz.vanez.tracker.dto.SightingDto;
import xyz.vanez.tracker.exception.ResourceNotFoundException;
import xyz.vanez.tracker.mapper.SightingMapper;
import xyz.vanez.tracker.model.Sighting;
import xyz.vanez.tracker.model.TrainInstance;
import xyz.vanez.tracker.repository.SightingRepository;
import xyz.vanez.tracker.repository.TrainInstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SightingService {
    private final SightingRepository sightingRepository;
    private final TrainInstanceRepository instanceRepository;
    private final SightingMapper mapper;

    public List<SightingDto> getAllByInstance(Integer instanceId) {
        List<Sighting> sightings;
        if (instanceId != null) {
            sightings = sightingRepository.findByInstanceId(instanceId);
        } else {
            sightings = sightingRepository.findAll();
        }
        return sightings.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public SightingDto getById(Integer id) {
        Sighting sighting = sightingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нотация с id=" + id + " не найдена"));
        return mapper.toDto(sighting);
    }

    @Transactional
    public SightingDto create(SightingDto dto) {
        TrainInstance instance = instanceRepository.findById(dto.getInstanceId())
                .orElseThrow(() -> new ResourceNotFoundException("Экземпляр поезда с id=" + dto.getInstanceId() + " не существует"));
        Sighting entity = mapper.toEntity(dto);
        entity.setInstance(instance);
        Sighting saved = sightingRepository.save(entity);
        log.info("Добавлена нотация для поезда {} в регионе {}", instance.getSerialNumber(), saved.getRegion());
        return mapper.toDto(saved);
    }

    @Transactional
    public SightingDto update(Integer id, SightingDto dto) {
        Sighting existing = sightingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Нотация с id=" + id + " не найдена"));
        if (dto.getInstanceId() != null && !dto.getInstanceId().equals(existing.getInstance().getId())) {
            TrainInstance newInstance = instanceRepository.findById(dto.getInstanceId())
                    .orElseThrow(() -> new ResourceNotFoundException("Экземпляр с id=" + dto.getInstanceId() + " не существует"));
            existing.setInstance(newInstance);
        }
        mapper.updateEntityFromDto(dto, existing);
        Sighting updated = sightingRepository.save(existing);
        log.info("Обновлена нотация id={}", id);
        return mapper.toDto(updated);
    }

    @Transactional
    public void delete(Integer id) {
        if (!sightingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Нотация с id=" + id + " не найдена");
        }
        sightingRepository.deleteById(id);
        log.info("Удалена нотация id={}", id);
    }
}