
-- Users
INSERT INTO chat.user (login, password) VALUES ('user1', 'password1');
INSERT INTO chat.user (login, password) VALUES ('user2', 'password2');
INSERT INTO chat.user (login, password) VALUES ('user3', 'password3');
INSERT INTO chat.user (login, password) VALUES ('user4', 'password4');
INSERT INTO chat.user (login, password) VALUES ('user5', 'password5');

-- Chatrooms
INSERT INTO chat.chatroom (name, owner_id) VALUES ('Chatroom 1', 1);
INSERT INTO chat.chatroom (name, owner_id) VALUES ('Chatroom 2', 2);
INSERT INTO chat.chatroom (name, owner_id) VALUES ('Chatroom 3', 3);
INSERT INTO chat.chatroom (name, owner_id) VALUES ('Chatroom 4', 4);
INSERT INTO chat.chatroom (name, owner_id) VALUES ('Chatroom 5', 5);

-- Messages
INSERT INTO chat.message (author, chatroom, text, timestamp) VALUES (1, 1, 'Hello, world!', '2021-09-01 13:00:00');
INSERT INTO chat.message (author, chatroom, text, timestamp) VALUES (2, 1, 'How are you?', '2021-09-01 13:01:00');
INSERT INTO chat.message (author, chatroom, text, timestamp) VALUES (1, 2, 'This is a test message.', '2021-09-01 14:00:00');
INSERT INTO chat.message (author, chatroom, text, timestamp) VALUES (3, 3, 'Good morning, everyone!', '2021-09-02 09:00:00');
INSERT INTO chat.message (author, chatroom, text, timestamp) VALUES (4, 4, 'Anyone up for a game of chess?', '2021-09-03 19:00:00');
INSERT INTO chat.message (author, chatroom, text, timestamp) VALUES (5, 5, 'I have a question about the project.', '2021-09-04 10:00:00');