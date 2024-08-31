package ci.digitalacademy.monetab.services.mapper;

import ci.digitalacademy.monetab.models.StudentCards;
import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentCardsMapper extends EntityMapper<StudentCardsDTO, StudentCards> {
}