package org.synyx.urlaubsverwaltung.workingtime.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

class WorkingTimeSettingsMapper {

    private WorkingTimeSettingsMapper() {
    }

    static WorkingTimeSettingsDto mapToWorkingTimeSettingsDto(WorkingTimeSettingsEntity workingTimeSettingsEntity) {
        return new ObjectMapper().convertValue(workingTimeSettingsEntity, WorkingTimeSettingsDto.class);
    }

    static WorkingTimeSettingsEntity mapToWorkingTimeSettingsEntity(WorkingTimeSettingsDto workingTimeSettingsDto) {
        return new ObjectMapper().convertValue(workingTimeSettingsDto, WorkingTimeSettingsEntity.class);
    }

    public static WorkingTimeSettings mapToWorkingTimeSettingsModel(WorkingTimeSettingsEntity workingTimeSettingsEntity) {
        return new ObjectMapper().convertValue(workingTimeSettingsEntity, WorkingTimeSettings.class);
    }
}