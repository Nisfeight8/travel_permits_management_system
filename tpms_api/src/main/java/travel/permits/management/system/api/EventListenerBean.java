package travel.permits.management.system.api;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import travel.permits.management.system.api.model.Authority;
import travel.permits.management.system.api.model.User;
import travel.permits.management.system.api.model.enums.RoleName;
import travel.permits.management.system.api.repository.AuthorityRepository;
import travel.permits.management.system.api.repository.UserRepository;
import travel.permits.management.system.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;


@Component
public class EventListenerBean  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    AuthorityRepository authRepository;
    @Autowired
    PasswordEncoder passwordEncode;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(userService.findAll().isEmpty()){
            Authority auth = new Authority();
            auth.setAuthority(RoleName.ROLE_ADMIN);
            authRepository.save(auth);
            User admin = new User();
            admin.setAuthority(authRepository.findByAuthority(RoleName.ROLE_ADMIN));
            admin.setFullName("admin");
            admin.setPassword(passwordEncode.encode("admin"));
            admin.setUsername("admin");
            userRepository.save(admin);
        }
    }
}