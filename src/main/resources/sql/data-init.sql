DELETE FROM COURSE;

INSERT INTO COURSE(NAME, DURATION) VALUES('Java 1', 10);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 2', 20);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 3', 30);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 4', 40);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 5', 50);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 6', 60);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 7', 70);
INSERT INTO COURSE(NAME, DURATION) VALUES('Java 8', 80);

CREATE TABLE IF NOT EXISTS users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

CREATE TABLE IF NOT EXISTS authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
CREATE UNIQUE index IF NOT EXISTS ix_auth_username on authorities (username,authority);

DELETE FROM AUTHORITIES;
DELETE FROM USERS;
INSERT INTO USERS(username, password, enabled) VALUES ('admin', '$2a$10$/nQbhIIuxbFzhz2RGKQaVuXbrvs4LcbO0Owmdbun8.NJ65SUZwMcm', true);
INSERT INTO AUTHORITIES(username, authority) VALUES('admin', 'ROLE_ADMIN');