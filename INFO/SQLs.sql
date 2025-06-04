CREATE SCHEMA IF NOT EXISTS `servlet_api`
DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `servlet_api`.`products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `cost` DECIMAL(16,4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

CREATE TABLE `servlet_api`.`orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL,
  `cost` DECIMAL(16,4) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `servlet_api`.`order_product` (
  `order_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `product_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `servlet_api`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `servlet_api`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
