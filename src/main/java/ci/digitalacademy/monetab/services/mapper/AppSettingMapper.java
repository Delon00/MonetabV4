package ci.digitalacademy.monetab.services.mapper;

import ci.digitalacademy.monetab.models.AppSetting;
import ci.digitalacademy.monetab.services.dto.AppSettingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO, AppSetting> {
}