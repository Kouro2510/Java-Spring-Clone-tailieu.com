-- drop database tailieu_db--
-- create database tailieu_db--
use  tailieu_db;
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO account_tbl (username, password, email, phone_number, create_date, update_date)
VALUES ('admin', '$2a$12$V5GuoGoigS3qXdR25N635eEZETgHxsx7rGiAXbxFufdZyrYGsPDB6', 'admin@example.com', '1234567890', NOW(), NOW());
-- tk:admin mk:admin123--
INSERT INTO `users_roles` VALUES (1,1);

