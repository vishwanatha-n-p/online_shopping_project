CREATE TABLE `customer_detail` (
      `id` INT AUTO_INCREMENT,
      `first_name` VARCHAR(45) NOT NULL,
      `last_name` VARCHAR(45) NOT NULL,
      `email` VARCHAR(45) UNIQUE,
      `contact_number` VARCHAR(13) NOT NULL,
      `user_id` INT,
      PRIMARY KEY (`id`),
      CONSTRAINT FK_user_customer_detail FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
    );

  CREATE TABLE `address` (
    `id` INT AUTO_INCREMENT,
    `street` VARCHAR(45),
    `city` VARCHAR(45) NOT NULL,
    `state` VARCHAR(45) NOT NULL,
    `postal_code` CHAR(6) NOT NULL,
    `country` VARCHAR(45) NOT NULL,
    `last_updated` DATETIME,
    `customer_detail_id` INT,
    PRIMARY KEY (`id`),
    CONSTRAINT FK_customer_detail_address FOREIGN KEY (`customer_detail_id`) REFERENCES `customer_detail`(`id`)
  );
