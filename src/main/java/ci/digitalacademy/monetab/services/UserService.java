package ci.digitalacademy.monetab.services;

import ci.digitalacademy.monetab.services.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO userDTO);
    void registerUser(UserDTO userDTO);
    Optional<UserDTO> findOne(Long id);
    Optional<UserDTO> findByPseudo(String pseudo);
    UserDTO update(UserDTO userDTO);
    List<UserDTO> findAll();
    void delete(Long id);
    long countUsers();
    List<UserDTO> initUser(List<UserDTO> users);
    Optional<UserDTO> findOneUserBySlug(String slug);
}