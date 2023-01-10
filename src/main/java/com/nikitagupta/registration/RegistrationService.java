package com.nikitagupta.registration;

import com.nikitagupta.appuser.AppUser;
import com.nikitagupta.appuser.AppUserRole;
import com.nikitagupta.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail =
                emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not Valid");
        }
        return appUserService.singUpUser(
                new AppUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
