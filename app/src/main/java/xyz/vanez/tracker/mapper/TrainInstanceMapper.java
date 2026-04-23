package xyz.vanez.tracker.mapper;

import xyz.vanez.tracker.dto.TrainInstanceDto;
import xyz.vanez.tracker.model.TrainInstance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrainInstanceMapper {
    TrainInstanceMapper INSTANCE = Mappers.getMapper(TrainInstanceMapper.class);

    @Mapping(source = "model.id", target = "modelId")
    TrainInstanceDto toDto(TrainInstance instance);

    @Mapping(source = "modelId", target = "model.id")
    TrainInstance toEntity(TrainInstanceDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "modelId", target = "model.id")
    void updateEntityFromDto(TrainInstanceDto dto, @MappingTarget TrainInstance entity);
}