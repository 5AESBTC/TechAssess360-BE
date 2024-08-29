CREATE DATABASE IF NOT EXISTS tech_rate;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    dob DATE,
    gender VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    type_user VARCHAR(255),
    is_active BOOLEAN,
    is_deleted BOOLEAN
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

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS items
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)          NULL,
    point INT                   NOT NULL,
    CONSTRAINT pk_items PRIMARY KEY (id)
);
