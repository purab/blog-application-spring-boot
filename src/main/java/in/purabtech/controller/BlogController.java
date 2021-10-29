package in.purabtech.controller;

import in.purabtech.model.Post;
import in.purabtech.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    PostService postService;

    @GetMapping("/blog/{username}")
    public String blogPage(@PathVariable String username, Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return findPaginated(1, username, "createDate", "asc", model);
    }

    @GetMapping("/blog/{username}/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @PathVariable(value = "username") String username,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Post> page = postService.findPostbyUserPaginated(username, pageNo, pageSize, sortField, sortDir);
        List<Post> listPosts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listPosts", listPosts);

        return "posts";
    }
}
