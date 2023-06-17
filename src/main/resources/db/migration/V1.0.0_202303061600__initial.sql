CREATE TABLE users (
                         id bigint NOT NULL PRIMARY KEY,
                         username  character varying(255),
                         password  character varying(255) NOT NULL,
                         email character varying(255) NOT NULL
);

CREATE TABLE roles (
                       id bigint NOT NULL PRIMARY KEY,
                       name  character varying(50)
);

insert into roles (id, name) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');
