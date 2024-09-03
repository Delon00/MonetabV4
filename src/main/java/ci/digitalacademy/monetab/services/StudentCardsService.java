package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.StudentCardsDTO;

import java.util.List;
import java.util.Optional;

public interface StudentCardsService {
    StudentCardsDTO save(StudentCardsDTO studentCardsDTO);
    Optional<StudentCardsDTO> findOne(Long id);
    StudentCardsDTO update(StudentCardsDTO studentCardsDTO);
    List<StudentCardsDTO> findAll();
    void delete(Long id);
    long countStudentCards();
}