package in.purabtech.service;

import in.purabtech.model.Post;
import in.purabtech.model.User;
import in.purabtech.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.postRepository.findAll(pageable);

    }

}
