create database if not exists content_posting;

use content_posting;

CREATE TABLE users (
    id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    username VARCHAR(100),
    email_id VARCHAR(100),
    password VARCHAR(100),
    num_followers int,
    num_following int
);

CREATE TABLE communities(
	id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(100),
    tags JSON
);

CREATE TABLE posts(
	id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    community_id CHAR(36) DEFAULT (UUID()),
    user_id CHAR(36) DEFAULT (UUID()),
    title VARCHAR(100),
    content VARCHAR(1000),
    num_likes int,
    num_dislikes int,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(community_id) REFERENCES communities(id)
);