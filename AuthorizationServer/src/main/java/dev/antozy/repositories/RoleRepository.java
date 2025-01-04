package dev.antozy.repositories;

import dev.antozy.entities.BaseEntity;
import dev.antozy.entities.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends BaseRepository<Role, Long> {

    default Optional<Role> findActiveByName(String name) {
        return findByName(name).filter(BaseEntity::isActive);
    }

    Optional<Role> findByName(String name);

    Set<Role> findByIdIn(Set<Long> roleIds);
}