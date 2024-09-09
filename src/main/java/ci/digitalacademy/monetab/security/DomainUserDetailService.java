package ci.digitalacademy.monetab.security;

import ci.digitalacademy.monetab.models.User;
import ci.digitalacademy.monetab.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class DomainUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByPseudo(username);

        if(user.isEmpty()){
            throw new IllegalArgumentException("Utilisateur non trouvé");
        }
        /*final List<GrantedAuthority> grantedAuthorities = List.of(
                new SimpleGrantedAuthority(user.get().getRole().getRole())
        );*/

        return user.map(userRecover->org.springframework.security.core.userdetails.User.builder()
                .username(userRecover.getPseudo())
                .password(userRecover.getPassword())
                //.authorities(grantedAuthorities)
                //.roles()
                .build()).orElseThrow(()-> new IllegalArgumentException("Utilisateur non trouvé"));
    }
}
