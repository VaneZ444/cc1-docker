package xyz.vanez.tracker.mapper;

import xyz.vanez.tracker.dto.SightingDto;
import xyz.vanez.tracker.model.Sighting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SightingMapper {
    SightingMapper INSTANCE = Mappers.getMapper(SightingMapper.class);

    @Mapping(source = "instance.id", target = "instanceId")
    SightingDto toDto(Sighting sighting);

    @Mapping(source = "instanceId", target = "instance.id")
    Sighting toEntity(SightingDto dto);

    @Mapping(source = "instanceId", target = "instance.id")
    void updateEntityFromDto(SightingDto dto, @MappingTarget Sighting entity);
}