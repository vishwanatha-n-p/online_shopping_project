CREATE TABLE `my_orders` (
   `id` INT AUTO_INCREMENT,
   `product_name` VARCHAR(45) NOT NULL,
   `model_number` VARCHAR(45) UNIQUE,
   `color` VARCHAR(45) NOT NULL,
   `total_price` INT NOT NULL,
   `quantity` INT NOT NULL,
   `order_status` VARCHAR(45) NOT NULL,
   `order_date` DATETIME,
   `user_id` INT,
   `last_updated` DATETIME,
   PRIMARY KEY (`id`),
   CONSTRAINT FK_user_my_orders FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
 );