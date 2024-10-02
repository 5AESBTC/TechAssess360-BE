ALTER TABLE users
    ADD COLUMN rank_id BIGIN null;

CREATE TABLE criterias
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255)          NULL,
    point INT                   NOT NULL,
    CONSTRAINT pk_criteria PRIMARY KEY (id)
);

CREATE TABLE questions
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(255)          NULL,
    point       INT                   NOT NULL,
    criteria_id BIGINT                NULL,
    CONSTRAINT pk_questions PRIMARY KEY (id)
);

ALTER TABLE questions
    ADD CONSTRAINT FK_QUESTIONS_ON_CRITERIA FOREIGN KEY (criteria_id) REFERENCES criteria (id);

CREATE TABLE projects
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)          NULL,
    start_day date                  NULL,
    end_day   date                  NULL,
    CONSTRAINT pk_projects PRIMARY KEY (id)
);

CREATE TABLE projects_user
(
    user_roles_id BIGINT NOT NULL,
    user_id       BIGINT NOT NULL
);

ALTER TABLE projects_user
    ADD CONSTRAINT fk_prouse_on_project FOREIGN KEY (user_roles_id) REFERENCES projects (id);

ALTER TABLE projects_user
    ADD CONSTRAINT fk_prouse_on_user FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE positions
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_positions PRIMARY KEY (id)
);

CREATE TABLE levels
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    position_id BIGINT                NULL,
    level       VARCHAR(255)          NULL,
    CONSTRAINT pk_levels PRIMARY KEY (id)
);

ALTER TABLE levels
    ADD CONSTRAINT FK_LEVELS_ON_POSITION FOREIGN KEY (position_id) REFERENCES positions (id);