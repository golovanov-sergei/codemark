CREATE DATABASE `codemark`;

USE `codemark`;

CREATE TABLE `role` (
                        `id` int NOT NULL,
                        `role_name` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                        `user_login` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `user_name` varchar(255) NOT NULL,
                        PRIMARY KEY (`user_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
                             `user_login` varchar(255) NOT NULL,
                             `role_id` int NOT NULL,
                             PRIMARY KEY (`user_login`,`role_id`),
                             KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
                             CONSTRAINT `FK1sdhwgmdo2kwbq01514pt1t0k` FOREIGN KEY (`user_login`) REFERENCES `user` (`user_login`),
                             CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
