package in.purabtech.repository;

import in.purabtech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByUsername(String username);

    @Query("Select u from User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}
