INSERT INTO ROLE (name, active, created_at, updated_at)
VALUES ('ROLE_ADMIN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ROLE_USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO USERS (username,
                   password,
                   first_name,
                   last_name,
                   active,
                   created_at,
                   updated_at)
VALUES ('admin',
        '$2a$12$UaPQri8e8uFLOIg0NrzpBujXMrnY4esXKZwQvkZvuOk0X0ET5QQMW',
        'Admin',
        'User',
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('user1',
        '$2a$12$UaPQri8e8uFLOIg0NrzpBujXMrnY4esXKZwQvkZvuOk0X0ET5QQMW',
        'Test',
        'User',
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

INSERT INTO USER_ROLES (user_id, role_id)
VALUES (1, 1), -- admin -> ROLE_ADMIN
       (1, 2), -- admin -> ROLE_USER
       (2, 2);
-- user1 -> ROLE_USER

-- 4. Client'ları ekleyelim
INSERT INTO REGISTERED_CLIENT (id,
                               client_id,
                               client_name,
                               secret,
                               redirect_uris,
                               scopes,
                               authentication_methods,
                               grant_types,
                               user_id,
                               active,
                               created_at,
                               updated_at)
VALUES ('client-001',
        'test-client',
        'Test Client Application',
        '$2a$12$UaPQri8e8uFLOIg0NrzpBujXMrnY4esXKZwQvkZvuOk0X0ET5QQMW',
        '["http://localhost:8080/callback","http://localhost:8080/login"]',
        '["read","write"]',
        '["client_secret_basic","client_secret_post"]',
        '["client_credentials","authorization_code","refresh_token"]',
        1,
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('client-002',
        'test-client-2',
        'Second Test Client',
        '$2a$12$UaPQri8e8uFLOIg0NrzpBujXMrnY4esXKZwQvkZvuOk0X0ET5QQMW',
        '["http://localhost:8080/callback"]',
        '["read"]',
        '["client_secret_basic"]',
        '["authorization_code"]',
        2,
        true,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

-- 5. Client rollerini ekleyelim
INSERT INTO CLIENT_ROLES (client_id, role_id)
VALUES ('client-001', 1), -- test-client -> ROLE_USER
       ('client-001', 2), -- test-client -> ROLE_CLIENT
       ('client-002', 2);
-- test-client-2 -> ROLE_USER

-- Test için ek veriler (deaktif kayıtlar)
INSERT INTO USERS (username,
                   password,
                   first_name,
                   last_name,
                   active,
                   created_at,
                   updated_at)
VALUES ('inactive_user',
        '$2a$12$UaPQri8e8uFLOIg0NrzpBujXMrnY4esXKZwQvkZvuOk0X0ET5QQMW',
        'Inactive',
        'User',
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

INSERT INTO REGISTERED_CLIENT (id,
                               client_id,
                               client_name,
                               secret,
                               redirect_uris,
                               scopes,
                               user_id,
                               active,
                               created_at,
                               updated_at)
VALUES ('client-003',
        'inactive-client',
        'Inactive Client',
        '$2a$12$UaPQri8e8uFLOIg0NrzpBujXMrnY4esXKZwQvkZvuOk0X0ET5QQMW',
        '["http://localhost:8080/callback"]',
        '["read"]',
        2,
        false,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);