package ci.digitalacademy.monetab.services.mapping;

import ci.digitalacademy.monetab.models.Absence;
import ci.digitalacademy.monetab.services.dto.AbsenceDTO;


public final class AbsenceMapping {
    private AbsenceMapping(){}
    public static void partialUpdate(Absence absence, AbsenceDTO absenceDTO){
        if(absenceDTO.getAbsenceNumber()!=null){
            absence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
        }
        if (absenceDTO.getAbsenceNumber()!=null){
            absence.setAbsenceDate(absenceDTO.getAbsenceDate());
        }
        if (absenceDTO.getId()!=null){
            absence.setId(Math.toIntExact(absenceDTO.getId()));
        }
        if (absenceDTO.getSlug()!=null){
            absence.setSlug(absenceDTO.getSlug());
        }
    }
}
