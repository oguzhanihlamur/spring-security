package dev.antozy.repositories;

import dev.antozy.entities.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    default Optional<T> findByIdIfActive(ID id) {
        return findById(id).filter(BaseEntity::isActive);
    }

    default List<T> findAllActive() {
        return findAll().stream()
                .filter(BaseEntity::isActive)
                .collect(Collectors.toList());
    }

    default Page<T> findAllActive(Pageable pageable) {
        return (Page<T>) findAll(pageable)
                .map(entity -> entity.isActive() ? entity : null)
                .filter(Objects::nonNull);
    }

    default T softDelete(ID id) {
        return findById(id).map(entity -> {
            entity.setActive(false);
            return save(entity);
        }).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}