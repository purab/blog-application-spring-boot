package in.purabtech.service;

import in.purabtech.model.CustomUserDetail;
import in.purabtech.model.Post;
import in.purabtech.model.User;
import in.purabtech.repository.RoleRepository;
import in.purabtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private static final String USER_ROLE = "ROLE_USER";

    public Optional<User> findForId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void save(User user) {
        userRepository.save(user);
    }

}
