package se.lexicon.g36todoit.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.dao.AppRoleDAO;
import se.lexicon.g36todoit.dao.AppUserDAO;
import se.lexicon.g36todoit.model.dto.AppUserDTO;
import se.lexicon.g36todoit.model.entity.AppUser;
import se.lexicon.g36todoit.model.entity.AppUserRole;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService{

    private final AppUserDAO appUserDAO;
    private final AppRoleDAO appRoleDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserServiceImpl(AppUserDAO appUserDAO, AppRoleDAO appRoleDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserDAO = appUserDAO;
        this.appRoleDAO = appRoleDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser create(AppUserDTO appUserDTO, AppUserRole appUserRole){
        AppUser appUser = new AppUser(
                appUserDTO.getUsername().trim(),
                bCryptPasswordEncoder.encode(appUserDTO.getPassword().trim())
        );

        appUser.setRole(appRoleDAO.findByAppUserRole(appUserRole).orElseThrow());

        return appUserDAO.save(appUser);
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findByUsername(String username){
        return appUserDAO.findByUsername(username).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(Integer id){
        return appUserDAO.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> findAll(){
        return appUserDAO.findAll();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser update(Integer id, AppUserDTO appUserDTO){
        AppUser appUser = findById(id);

        appUser.setUsername(appUserDTO.getUsername().trim());
        appUser.setPassword(bCryptPasswordEncoder.encode(appUserDTO.getPassword().trim()));

        return appUserDAO.save(appUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(String username){
        AppUser appUser = findByUsername(username);
        appUserDAO.delete(appUser);
        return !appUserDAO.existsById(appUser.getId());
    }

}