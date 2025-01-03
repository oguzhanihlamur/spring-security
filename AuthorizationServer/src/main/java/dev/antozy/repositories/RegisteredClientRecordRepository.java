package dev.antozy.repositories;

import dev.antozy.entities.BaseEntity;
import dev.antozy.entities.RegisteredClientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegisteredClientRecordRepository extends BaseRepository<RegisteredClientRecord, String> {

    default Optional<RegisteredClientRecord> findActiveByClientId(String clientId) {
        return findByClientId(clientId).filter(BaseEntity::isActive);
    }

    @Query(nativeQuery = true, value = """
                SELECT rc.* FROM registered_client rc
                INNER JOIN users u ON rc.user_id = u.id
                WHERE rc.client_id = :clientId
                AND rc.active = true
                AND u.active = true
                AND (
                    SELECT COUNT(*) FROM user_roles ur WHERE ur.user_id = u.id
                ) = (
                    SELECT COUNT(*) FROM client_roles cr WHERE cr.client_id = rc.id
                )
                AND NOT EXISTS (
                    SELECT 1 FROM user_roles ur 
                    WHERE ur.user_id = u.id 
                    AND NOT EXISTS (
                        SELECT 1 FROM client_roles cr 
                        WHERE cr.client_id = rc.id 
                        AND cr.role_id = ur.role_id
                    )
                )
                AND NOT EXISTS (
                    SELECT 1 FROM client_roles cr 
                    WHERE cr.client_id = rc.id 
                    AND NOT EXISTS (
                        SELECT 1 FROM user_roles ur 
                        WHERE ur.user_id = u.id 
                        AND ur.role_id = cr.role_id
                    )
                )
            """)
    Optional<RegisteredClientRecord> findByClientId(@Param("clientId") String clientId);
}

