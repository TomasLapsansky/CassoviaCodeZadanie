package sk.cassoviacode.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sk.cassoviacode.app.entity.User;
import sk.cassoviacode.app.persistance.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByLogin(userName);
        if(user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true, null);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true, true,
                true, getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        /* Space for more authorities */
        String userRole = "user";
        return AuthorityUtils.createAuthorityList(userRole);
    }
}
