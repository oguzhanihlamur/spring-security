CREATE TABLE USERS
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    active     BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE ROLE
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    active     BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE REGISTERED_CLIENT
(
    id                     VARCHAR(255) PRIMARY KEY,
    client_id              VARCHAR(255) NOT NULL UNIQUE,
    client_name            VARCHAR(255),
    secret                 VARCHAR(255),
    redirect_uris          CLOB,
    scopes                 CLOB,
    authentication_methods CLOB,
    grant_types            CLOB,
    user_id                BIGINT,
    active                 BOOLEAN DEFAULT TRUE,
    created_at             TIMESTAMP,
    updated_at             TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE TABLE USER_ROLES
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES USERS (id),
    FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

CREATE TABLE CLIENT_ROLES
(
    client_id VARCHAR(255) NOT NULL,
    role_id   BIGINT       NOT NULL,
    PRIMARY KEY (client_id, role_id),
    FOREIGN KEY (client_id) REFERENCES REGISTERED_CLIENT (id),
    FOREIGN KEY (role_id) REFERENCES ROLE (id)
);