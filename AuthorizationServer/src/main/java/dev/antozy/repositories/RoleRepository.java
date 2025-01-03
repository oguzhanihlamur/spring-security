package dev.antozy.repositories;

import dev.antozy.entities.BaseEntity;
import dev.antozy.entities.Role;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role, Long> {

    default Optional<Role> findActiveByName(String name) {
        return findByName(name).filter(BaseEntity::isActive);
    }

    Optional<Role> findByName(String name);
}