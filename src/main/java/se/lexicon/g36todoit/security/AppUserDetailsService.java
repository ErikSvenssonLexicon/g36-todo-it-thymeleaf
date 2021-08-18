package se.lexicon.g36todoit.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.dao.AppUserDAO;
import se.lexicon.g36todoit.model.entity.AppUser;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserDAO appUserDAO;

    public AppUserDetailsService(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    @Transactional
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with username " + username));

        return new MyUserDetails(user);
    }
}
