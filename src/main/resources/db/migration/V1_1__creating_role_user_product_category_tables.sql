 CREATE TABLE `role` (
     `id` INT AUTO_INCREMENT,
     `role_name` VARCHAR(45),
     PRIMARY KEY (`id`)
   );

 CREATE TABLE `user` (
   `id` INT AUTO_INCREMENT,
   `username` VARCHAR(45) NOT NULL,
   `password` VARCHAR(60) UNIQUE,
   `role_id` INT,
   PRIMARY KEY (`id`),
   CONSTRAINT FK_role_user FOREIGN KEY (`role_id`) REFERENCES `role`(`id`)
 );

CREATE TABLE `product_category` (
  `id` INT AUTO_INCREMENT,
  `category_name` VARCHAR(45),
  `last_update` DATETIME,
  `status` VARCHAR(45),
  PRIMARY KEY (`id`),
  KEY `UN` (`category_name`),
  KEY `NL` (`last_update`)
);

CREATE TABLE `product_subcategory` (
  `id` INT AUTO_INCREMENT,
  `subcategory_name` VARCHAR(45),
  `last_update` DATETIME,
  `product_category_id` INT,
  `status` VARCHAR(45),
  PRIMARY KEY (`id`),
  CONSTRAINT FK_product_category_id FOREIGN KEY (`product_category_id`) REFERENCES `product_category`(`id`),
  KEY `UN` (`subcategory_name`),
  KEY `NL` (`last_update`)
);

CREATE TABLE `product_types` (
  `id` INT AUTO_INCREMENT,
  `product_type` VARCHAR(45),
  `product_subcategory_id` INT,
  `status` VARCHAR(45),
  PRIMARY KEY (`id`),
  CONSTRAINT FK_product_subcategory_id FOREIGN KEY (`product_subcategory_id`) REFERENCES `product_subcategory`(`id`),
  KEY `NN` (`product_type`)
);
