drop table if exists chat.user, chat.chatroom, chat.message;
drop schema if exists chat;

create schema chat;

CREATE TABLE chat.user (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE chat.chatroom (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id INTEGER NOT NULL REFERENCES chat.user(id)
);

CREATE TABLE chat.message (
    id SERIAL PRIMARY KEY,
    author INTEGER NOT NULL REFERENCES chat.user(id),
    chatroom INTEGER NOT NULL REFERENCES chat.chatroom(id),
    text TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL
);