CREATE TABLE `payment_mode` (
   `id` INT AUTO_INCREMENT,
   `mode_name` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`id`)
 );

 CREATE TABLE `payment` (
   `id` INT AUTO_INCREMENT,
   `payment_mode_id` INT,
   `amount` INT NOT NULL,
   `payment_date` DATETIME,
   `payment_status` VARCHAR(45) NOT NULL,
   `customer_detail_id` INT,
   PRIMARY KEY (`id`),
   CONSTRAINT FK_payment_mode_id FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode`(`id`),
   CONSTRAINT FK_customer_detail_payment FOREIGN KEY (`customer_detail_id`) REFERENCES `customer_detail`(`id`)
 );

 CREATE TABLE `product_order` (
   `id` INT AUTO_INCREMENT,
   `product_id` INT,
   `quantity` SMALLINT NOT NULL,
   `cost` INT,
   `user_id` INT,
   PRIMARY KEY (`id`),
   CONSTRAINT FK_product_order_details FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
   CONSTRAINT FK_user_product_order FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
 );

CREATE TABLE `final_order` (
  `order_number` INT AUTO_INCREMENT,
  `product_id` INT,
  `quantity` SMALLINT NOT NULL,
  `cost` INT NOT NULL,
  `order_status` VARCHAR(45) NOT NULL,
  `order_date` DATETIME,
  `ship_date` DATE,
  `delivery_date` DATE,
  `payment_id` INT,
  PRIMARY KEY (`order_number`),
  CONSTRAINT FK_product_final_order FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  CONSTRAINT FK_payment_final_order FOREIGN KEY (`payment_id`) REFERENCES `payment`(`id`)
);
