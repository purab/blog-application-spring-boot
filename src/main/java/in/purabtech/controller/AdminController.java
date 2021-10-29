package in.purabtech.controller;

import in.purabtech.model.Role;
import in.purabtech.model.User;
import in.purabtech.repository.PostRepository;
import in.purabtech.repository.RoleRepository;
import in.purabtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {

        model.addAttribute("user", new User());
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user,
                                BindingResult bindingResult,
                                Model model,
                                HttpServletRequest request) throws ServletException {

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }

        if (!bindingResult.hasErrors()) {
            // Registration successful, save user
            // Set user role to USER and set it as active
            String password = user.getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setEnabled(true);
            List<Role> roles= new ArrayList<>();
            roles.add(roleRepository.findById(2L).get()); //SET USER ROLE
            user.setRoles(roles);
            userService.save(user);

            //auto login
            request.login(user.getEmail(),password);
            return "redirect:/";

           // model.addAttribute("successMessage", "User has been registered successfully");
            //model.addAttribute("user", new User());
        }

        return "/registration";
    }
}
