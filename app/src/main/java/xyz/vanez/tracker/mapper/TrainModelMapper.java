package xyz.vanez.tracker.mapper;

import xyz.vanez.tracker.dto.TrainModelDto;
import xyz.vanez.tracker.model.TrainModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrainModelMapper {
    TrainModelMapper INSTANCE = Mappers.getMapper(TrainModelMapper.class);

    TrainModelDto toDto(TrainModel model);
    TrainModel toEntity(TrainModelDto dto);
    void updateEntityFromDto(TrainModelDto dto, @MappingTarget TrainModel entity);
}
