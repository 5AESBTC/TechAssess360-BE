CREATE DATABASE IF NOT EXISTS techassess360;

CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_by   VARCHAR(255)          NULL,
    create_at    datetime              NULL,
    modified_at  datetime              NULL,
    modified_by  VARCHAR(255)          NULL,
    name         VARCHAR(255)          NULL,
    email        VARCHAR(255)          NULL,
    phone_number VARCHAR(255)          NULL,
    dob          date                  NULL,
    gender       VARCHAR(255)          NULL,
    username     VARCHAR(255)          NULL,
    password     VARCHAR(255)          NULL,
    rank_id      BIGINT                NULL,
    is_active    BIT(1)                NOT NULL,
    is_deleted   BIT(1)                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT                NULL,
    role_id BIGINT                NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (id)
);

ALTER TABLE  user_roles
    ADD CONSTRAINT FK_USER_ROLES_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_USER_ROLES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

