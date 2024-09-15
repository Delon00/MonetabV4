package ci.digitalacademy.monetab.services.Impl;

import ci.digitalacademy.monetab.models.User;
import ci.digitalacademy.monetab.repositories.UserRepository;
import ci.digitalacademy.monetab.services.UserService;
import ci.digitalacademy.monetab.services.dto.UserDTO;
import ci.digitalacademy.monetab.services.mapper.UserMapper;
import ci.digitalacademy.monetab.utils.SlugifyUtils;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save User : {}", userDTO);

        final String slug = SlugifyUtils.genereate(String.valueOf(userDTO.getPseudo()));
        userDTO.setSlug(slug);
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }


    @Override
    public void registerUser(UserDTO userDTO) {

    }


    @Override
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> findByPseudo(String pseudo) {
        Optional<User> existingUser = userRepository.findByPseudo(pseudo);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with pseudo already exists");
        }
        return Optional.empty();
    }




    @Override
    public UserDTO update(UserDTO userDTO) {
        return findOne(userDTO.getId())
                .map(existingUser -> {
                    existingUser.setPseudo(userDTO.getPseudo());
                    existingUser.setPassword(userDTO.getPassword());
                    existingUser.setCreationDate(userDTO.getCreationDate());
                    return save(existingUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userDTO.getId()));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public List<UserDTO> initUser(List<UserDTO> users) {
        List<UserDTO > usersDto = findAll();
        if (usersDto.isEmpty()){
            users.forEach(user->{
                save(user);
            });
        }
        return findAll();
    }

    @Override
    public Optional<UserDTO> findOneUserBySlug(String slug) {
        log.debug("Request to get User by slug: {}", slug);
        return userRepository.findBySlug(slug).map(userMapper::toDto)
                .map(userDTO -> {
                    log.info("User found: {}", userDTO);
                    return userDTO;
                })
                .or(() -> {
                    log.warn("User not found for slug: {}", slug);
                    return Optional.empty();
                });
    }
}