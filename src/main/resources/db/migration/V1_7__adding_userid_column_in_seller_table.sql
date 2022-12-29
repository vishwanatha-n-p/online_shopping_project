ALTER TABLE `seller` ADD `user_id` INT,
ADD CONSTRAINT user_seller FOREIGN KEY(`user_id`) REFERENCES `user`(`id`);