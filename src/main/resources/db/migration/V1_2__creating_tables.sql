CREATE TABLE `highlights` (
   `id` INT AUTO_INCREMENT,
   `features` MEDIUMTEXT NOT NULL,
   `size` VARCHAR(200) NOT NULL,
   `last_update` DATETIME,
   `model_number` VARCHAR(100) NOT NULL,
   PRIMARY KEY (`id`)
 );

CREATE TABLE `product` (
   `id` INT AUTO_INCREMENT,
   `product_name` VARCHAR(60) UNIQUE,
   `color` VARCHAR(45) NOT NULL,
   `highlights_id` INT,
   `product_type_id` INT,
   `product_count` INT,
   `status` VARCHAR(45),
   PRIMARY KEY (`id`),
   CONSTRAINT FK_highlights_product FOREIGN KEY (`highlights_id`) REFERENCES `highlights`(`id`),
   CONSTRAINT FK_product_type_product FOREIGN KEY (`product_type_id`) REFERENCES `product_types`(`id`)
 );

 CREATE TABLE `currency` (
    `id` INT AUTO_INCREMENT,
    `currency_symbol` VARCHAR(45) UNIQUE,
    `country` VARCHAR(45) UNIQUE,
    `last_update` DATETIME,
    PRIMARY KEY (`id`)
  );

CREATE TABLE `price_detail` (
  `id` INT AUTO_INCREMENT,
  `price` BIGINT NOT NULL,
  `product_count` INT NOT NULL,
  `discount` VARCHAR(45),
  `special_offer_discount` INT,
  `delivery_charge` INT,
  `final_price` INT NOT NULL,
  `last_update` DATETIME,
  `product_id` INT,
  `seller_name` VARCHAR(45) NOT NULL,
  `currency_id` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT FK_product_price_detail FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  CONSTRAINT FK_currency_price_detail FOREIGN KEY (`currency_id`) REFERENCES `currency`(`id`)
);