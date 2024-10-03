-- Удаление базы данных, если она существует
DROP DATABASE IF EXISTS db_instClone;

-- Создание базы данных
CREATE DATABASE db_instClone;

-- Подключение к базе данных
\c db_instClone;

-- Создание таблицы Account
CREATE TABLE IF NOT EXISTS Account
(
    id                SERIAL PRIMARY KEY,
    username          VARCHAR(255) NOT NULL,
    email             VARCHAR(255) NOT NULL,
    password          VARCHAR(255) NOT NULL,
    avatar            BYTEA,
    bio               VARCHAR(255),
    registration_date DATE NOT NULL
);

-- Создание таблицы Post
CREATE TABLE IF NOT EXISTS Post
(
    id          SERIAL PRIMARY KEY,
    account_id  INT NOT NULL,
    content     BYTEA NOT NULL,
    description VARCHAR(255),
    post_date   DATE NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account (id)
);

-- Создание таблицы likes
CREATE TABLE IF NOT EXISTS likes
(
    id         SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    post_id    INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES Account (id),
    FOREIGN KEY (post_id) REFERENCES Post (id)
);

-- Создание таблицы Comment
CREATE TABLE IF NOT EXISTS Comment
(
    id           SERIAL PRIMARY KEY,
    account_id   INT NOT NULL,
    post_id      INT NOT NULL,
    text         VARCHAR(255),
    comment_date TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account (id),
    FOREIGN KEY (post_id) REFERENCES Post (id)
);

-- Создание таблицы Follower
CREATE TABLE IF NOT EXISTS Follower
(
    id           SERIAL PRIMARY KEY,
    follower_id  INT NOT NULL,
    following_id INT NOT NULL,
    FOREIGN KEY (follower_id) REFERENCES Account (id),
    FOREIGN KEY (following_id) REFERENCES Account (id)
);
