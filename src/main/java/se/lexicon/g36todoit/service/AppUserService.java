package se.lexicon.g36todoit.service;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.model.dto.AppUserDTO;
import se.lexicon.g36todoit.model.entity.AppUser;
import se.lexicon.g36todoit.model.entity.AppUserRole;

import java.util.List;

public interface AppUserService {
    @Transactional(rollbackFor = RuntimeException.class)
    AppUser create(AppUserDTO appUserDTO, AppUserRole appUserRole);

    @Transactional(readOnly = true)
    AppUser findByUsername(String username);

    @Transactional(readOnly = true)
    AppUser findById(Integer id);

    @Transactional(readOnly = true)
    List<AppUser> findAll();

    @Transactional(rollbackFor = RuntimeException.class)
    AppUser update(Integer id, AppUserDTO appUserDTO);

    @Transactional(rollbackFor = RuntimeException.class)
    boolean delete(String username);
}
