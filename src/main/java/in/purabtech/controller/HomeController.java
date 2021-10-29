package in.purabtech.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.purabtech.model.Post;
import in.purabtech.model.User;
import in.purabtech.service.PostService;
import in.purabtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping({"/","/home"})
    public String homePage(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return findPaginated(1, "createDate", "asc", model);
    }



    @GetMapping("/posts")
    public String listPosts(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return findPaginated(1, "createDate", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Post> page = postService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Post> listPosts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listPosts", listPosts);
        /*// for-each loop
        for (int i = 0; i < listPosts.size(); i++) {
            Post aName = listPosts.get(i);
            System.out.println(aName);
            System.out.println(aName);
        }

        System.out.println(listPosts.toString());


        System.exit(1);*/
        return "posts";
    }
}
