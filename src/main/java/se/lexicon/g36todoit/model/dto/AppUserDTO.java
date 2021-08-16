package se.lexicon.g36todoit.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AppUserDTO implements Serializable {

    private Integer id;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 4, max = 255, message = "Username need to have at least 2 letters")
    private String username;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 8, max = 32, message = "Password need to have at least 8 letters")
    private String password;
    @NotBlank(message = "This field is mandatory")
    private String passwordConfirm;

    public AppUserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
