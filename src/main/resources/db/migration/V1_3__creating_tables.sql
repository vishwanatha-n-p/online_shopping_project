CREATE TABLE `seller` (
   `id` INT AUTO_INCREMENT,
   `seller_name` VARCHAR(45) NOT NULL,
   `service` VARCHAR(45),
   `contact_number` VARCHAR(13) NOT NULL,
   `email` VARCHAR(45) UNIQUE,
   `city` VARCHAR(45) NOT NULL,
   `state` VARCHAR(45) NOT NULL,
   `postal_code` CHAR(8) NOT NULL,
   `country` VARCHAR(45) NOT NULL,
   `last_update` DATETIME,
   PRIMARY KEY (`id`)
 );

 CREATE TABLE `seller_product` (
   `seller_id` INT,
   `product_id` INT,
   CONSTRAINT FK_product_seller FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
   CONSTRAINT FK_seller_product FOREIGN KEY (`seller_id`) REFERENCES `seller`(`id`)
 );

 CREATE TABLE `cart_list` (
   `id` INT AUTO_INCREMENT,
   `user_id` INT,
   PRIMARY KEY (`id`),
   CONSTRAINT FK_user_cart_list FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
 );

 CREATE TABLE `cart_list_product` (
    `cartList_id` INT,
    `product_id` INT,
    CONSTRAINT FK_product_cart_list FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
    CONSTRAINT FK_cartList_id FOREIGN KEY (`cartList_id`) REFERENCES `cart_list`(`id`)
 );

 CREATE TABLE `wishlist` (
   `id` INT AUTO_INCREMENT,
   `user_id` INT,
   PRIMARY KEY (`id`),
   CONSTRAINT FK_user_wishlist FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
 );

 CREATE TABLE `wishlist_product` (
    `wishlist_id` INT,
    `product_id` INT,
    CONSTRAINT FK_product_wishlist FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
    CONSTRAINT FK_wishlist_id FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist`(`id`)
  );
