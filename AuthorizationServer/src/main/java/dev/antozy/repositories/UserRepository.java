package dev.antozy.repositories;

import dev.antozy.entities.BaseEntity;
import dev.antozy.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    default Optional<User> findActiveByUsername(String username) {
        return findByUsername(username).filter(BaseEntity::isActive);
    }

    @Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE u.active = true AND u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE u.active = true AND u.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("SELECT u FROM User u JOIN FETCH u.roles r WHERE u.active = true AND r.name = :roleName")
    List<User> findAllActiveByRole(@Param("roleName") String roleName);
}