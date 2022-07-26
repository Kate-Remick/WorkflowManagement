-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cardinaldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cardinaldb` ;

-- -----------------------------------------------------
-- Schema cardinaldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cardinaldb` DEFAULT CHARACTER SET utf8 ;
USE `cardinaldb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` TEXT NOT NULL,
  `email` VARCHAR(300) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `img_url` TEXT NULL,
  `google_id` TEXT NULL,
  `active` TINYINT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workspace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workspace` ;

CREATE TABLE IF NOT EXISTS `workspace` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(300) NULL,
  `created_at` DATETIME NOT NULL,
  `goal_date` DATETIME NULL,
  `active` TINYINT NOT NULL,
  `manager_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_workspace_user1_idx` (`manager_id` ASC),
  CONSTRAINT `fk_workspace_user1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deck`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `deck` ;

CREATE TABLE IF NOT EXISTS `deck` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(300) NULL,
  `workspace_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_deck_workspace1_idx` (`workspace_id` ASC),
  CONSTRAINT `fk_deck_workspace1`
    FOREIGN KEY (`workspace_id`)
    REFERENCES `workspace` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `card` ;

CREATE TABLE IF NOT EXISTS `card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(300) NULL,
  `due_date` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `completed_at` DATETIME NULL,
  `completed` TINYINT NOT NULL,
  `deck_id` INT NOT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_card_deck1_idx` (`deck_id` ASC),
  INDEX `fk_card_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_card_deck1`
    FOREIGN KEY (`deck_id`)
    REFERENCES `deck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_card_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat` ;

CREATE TABLE IF NOT EXISTS `chat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `workspace_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_chat_workspace1_idx` (`workspace_id` ASC),
  CONSTRAINT `fk_chat_workspace1`
    FOREIGN KEY (`workspace_id`)
    REFERENCES `workspace` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `content` VARCHAR(300) NOT NULL,
  `chat_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_chat1_idx` (`chat_id` ASC),
  INDEX `fk_message_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_message_chat1`
    FOREIGN KEY (`chat_id`)
    REFERENCES `chat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_workspace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_workspace` ;

CREATE TABLE IF NOT EXISTS `user_workspace` (
  `user_id` INT NOT NULL,
  `workspace_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `workspace_id`),
  INDEX `fk_user_has_workspace_workspace1_idx` (`workspace_id` ASC),
  INDEX `fk_user_has_workspace_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_workspace_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_workspace_workspace1`
    FOREIGN KEY (`workspace_id`)
    REFERENCES `workspace` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS cardinal@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'cardinal'@'localhost' IDENTIFIED BY 'cardinal';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'cardinal'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `first_name`, `last_name`, `created_at`, `img_url`, `google_id`, `active`, `role`) VALUES (1, 'test', 'password', 'testtest@test.com', 'testUser', 'testUserLast', '2022-07-25 00:00:00', NULL, NULL, 1, 'ROLE_ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `workspace`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `workspace` (`id`, `name`, `description`, `created_at`, `goal_date`, `active`, `manager_id`) VALUES (1, 'testWorkspace', 'test description', '2022-07-25 00:00:00', NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `deck`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `deck` (`id`, `name`, `description`, `workspace_id`) VALUES (1, 'test deck 1', 'test desctiption', 1);
INSERT INTO `deck` (`id`, `name`, `description`, `workspace_id`) VALUES (2, 'test deck 2', 'test description 2', 1);
INSERT INTO `deck` (`id`, `name`, `description`, `workspace_id`) VALUES (3, 'test deck 3', 'test description 3', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `card`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `card` (`id`, `name`, `description`, `due_date`, `created_at`, `updated_at`, `completed_at`, `completed`, `deck_id`, `user_id`) VALUES (1, ' test deck card 1', 'testdeck card1 description', NULL, '2022-07-01 00:00:00', NULL, NULL, 0, 1, 1);
INSERT INTO `card` (`id`, `name`, `description`, `due_date`, `created_at`, `updated_at`, `completed_at`, `completed`, `deck_id`, `user_id`) VALUES (2, 'test deck card 2', 'testdeck card2 description', NULL, '2022-07-01 00:00:00', NULL, NULL, 0, 2, 1);
INSERT INTO `card` (`id`, `name`, `description`, `due_date`, `created_at`, `updated_at`, `completed_at`, `completed`, `deck_id`, `user_id`) VALUES (3, 'test deck card 3', 'testdeck card3 description', NULL, '2022-07-01 00:00:00', NULL, NULL, 0, 3, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `chat`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `chat` (`id`, `workspace_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `message` (`id`, `created_at`, `content`, `chat_id`, `user_id`) VALUES (1, '2022-07-25 00:01:01', 'Hey', 1, 1);
INSERT INTO `message` (`id`, `created_at`, `content`, `chat_id`, `user_id`) VALUES (2, '2022-07-25 00:01:02', 'Hi', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_workspace`
-- -----------------------------------------------------
START TRANSACTION;
USE `cardinaldb`;
INSERT INTO `user_workspace` (`user_id`, `workspace_id`) VALUES (1, 1);

COMMIT;

