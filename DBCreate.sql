SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `JHUAppdb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`User` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `lastLogin` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`ContactInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`ContactInfo` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`ContactInfo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zipCode` VARCHAR(45) NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ContactInfo_User_idx` (`userId` ASC),
  CONSTRAINT `fk_ContactInfo_User`
    FOREIGN KEY (`userId`)
    REFERENCES `JHUAppdb`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Motorcycle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Motorcycle` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Motorcycle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL NOT NULL,
  `condition` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `quantity` INT NOT NULL,
  `productNumber` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Helmet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Helmet` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Helmet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL NOT NULL,
  `size` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `productNumber` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Gloves`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Gloves` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Gloves` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL NOT NULL,
  `size` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `productNumber` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Jacket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Jacket` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Jacket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL NOT NULL,
  `size` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `productNumber` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Order` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dateCreated` TIMESTAMP NOT NULL,
  `userId` INT NOT NULL,
  `dateOrdered` DATE NULL,
  `confirmationNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Order_User_idx` (`userId` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`userId`)
    REFERENCES `JHUAppdb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`CreditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`CreditCard` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`CreditCard` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `creditCardNumber` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CreditCard_User_idx` (`userId` ASC),
  CONSTRAINT `fk_CreditCard_User`
    FOREIGN KEY (`userId`)
    REFERENCES `JHUAppdb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Order_has_Jacket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Order_has_Jacket` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Order_has_Jacket` (
  `Order_id` INT NOT NULL,
  `Jacket_id` INT NOT NULL,
  PRIMARY KEY (`Order_id`, `Jacket_id`),
  INDEX `fk_Order_has_Jacket_Jacket1_idx` (`Jacket_id` ASC),
  INDEX `fk_Order_has_Jacket_Order1_idx` (`Order_id` ASC),
  CONSTRAINT `fk_Order_has_Jacket_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `JHUAppdb`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_has_Jacket_Jacket1`
    FOREIGN KEY (`Jacket_id`)
    REFERENCES `JHUAppdb`.`Jacket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Order_has_Helmet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Order_has_Helmet` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Order_has_Helmet` (
  `Order_id` INT NOT NULL,
  `Helmet_id` INT NOT NULL,
  PRIMARY KEY (`Order_id`, `Helmet_id`),
  INDEX `fk_Order_has_Helmet_Helmet1_idx` (`Helmet_id` ASC),
  INDEX `fk_Order_has_Helmet_Order1_idx` (`Order_id` ASC),
  CONSTRAINT `fk_Order_has_Helmet_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `JHUAppdb`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_has_Helmet_Helmet1`
    FOREIGN KEY (`Helmet_id`)
    REFERENCES `JHUAppdb`.`Helmet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Order_has_Gloves`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Order_has_Gloves` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Order_has_Gloves` (
  `Order_id` INT NOT NULL,
  `Gloves_id` INT NOT NULL,
  PRIMARY KEY (`Order_id`, `Gloves_id`),
  INDEX `fk_Order_has_Gloves_Gloves1_idx` (`Gloves_id` ASC),
  INDEX `fk_Order_has_Gloves_Order1_idx` (`Order_id` ASC),
  CONSTRAINT `fk_Order_has_Gloves_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `JHUAppdb`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_has_Gloves_Gloves1`
    FOREIGN KEY (`Gloves_id`)
    REFERENCES `JHUAppdb`.`Gloves` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `JHUAppdb`.`Order_has_Motorcycle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `JHUAppdb`.`Order_has_Motorcycle` ;

CREATE TABLE IF NOT EXISTS `JHUAppdb`.`Order_has_Motorcycle` (
  `Order_id` INT NOT NULL,
  `Motorcycle_id` INT NOT NULL,
  PRIMARY KEY (`Order_id`, `Motorcycle_id`),
  INDEX `fk_Order_has_Motorcycle_Motorcycle1_idx` (`Motorcycle_id` ASC),
  INDEX `fk_Order_has_Motorcycle_Order1_idx` (`Order_id` ASC),
  CONSTRAINT `fk_Order_has_Motorcycle_Order1`
    FOREIGN KEY (`Order_id`)
    REFERENCES `JHUAppdb`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_has_Motorcycle_Motorcycle1`
    FOREIGN KEY (`Motorcycle_id`)
    REFERENCES `JHUAppdb`.`Motorcycle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO tomcat;
 DROP USER tomcat;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'tomcat' IDENTIFIED BY 'password';

GRANT ALL ON `JHUAppdb`.* TO 'tomcat';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`User` (`id`, `userName`, `password`, `firstName`, `lastName`, `email`, `lastLogin`) VALUES (1, 'bsmith', 'password', 'Bob', 'Smith', 'bsmith@blah.com', '2014-10-30 12:34:54');
INSERT INTO `JHUAppdb`.`User` (`id`, `userName`, `password`, `firstName`, `lastName`, `email`, `lastLogin`) VALUES (2, 'gtrent', 'password', 'Greg', 'Trent', 'gtrent@nowhere.com', '2014-09-28 08:14:26');
INSERT INTO `JHUAppdb`.`User` (`id`, `userName`, `password`, `firstName`, `lastName`, `email`, `lastLogin`) VALUES (3, 'swood', 'password', 'Sarah', 'Wood', 'swood@fake.com', '2014-11-02 07:34:48');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`ContactInfo`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`ContactInfo` (`id`, `street`, `city`, `state`, `zipCode`, `userId`) VALUES (1, '205 Oak Ln', 'Sunnyvale', 'CA', '14465', 1);
INSERT INTO `JHUAppdb`.`ContactInfo` (`id`, `street`, `city`, `state`, `zipCode`, `userId`) VALUES (2, '47 Tyner Dr', 'Brookville', 'PA', '48826', 2);
INSERT INTO `JHUAppdb`.`ContactInfo` (`id`, `street`, `city`, `state`, `zipCode`, `userId`) VALUES (3, '1314 Limestone Ct', 'Boulder', 'CO', '75426', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Motorcycle`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (1, 'Fat Boy', 'The original fat custom icon with a burly style that\'s often imitated but never duplicated', 17699, 'New', 'Harley Davidson', 'Cruiser', 5, 'HA1357');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (2, 'Electra Glide', 'When you set out to improve the most respected touring machine on earth, you don\'t take shortcuts and you sure as hell don\'t accept any limits', 24399, 'New', 'Harley Davidson', 'Cruiser', 3, 'HA1358');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (3, 'SwitchBack', 'Easily convertible from cruising to touring, it\'s like two bikes in one, with no compromises.', 14399, 'Used', 'Harley Davidson', 'Cruiser', 1, 'HA1359');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (4, 'Highball', 'Among custom cruiser motorcycles, nothing touches the Victory High-ball.  This cruiser with custom wheels, bobbed fenders and ape hangers brings out an attitude that can\'t be matched.', 13499, 'New', 'Victory', 'Cruiser', 4, 'VI2367');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (5, 'Gunner', 'You\'ve wanted to step up to a true heavyweight cruiser.  Do it in style with the Gunner, a powerful cruiser with throwback bobber style and street-smart attitude.', 10399, 'Used', 'Victory', 'Cruiser', 1, 'VI2368');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (6, 'Interceptor', 'The Interceptor\'s 782cc V-4 VTEC engine has new fuel-injection mapping for better low-end torque feel.  It includes adjustable seat height to better fit a range of riders, new front-mount radiator for a slimmer profile, radial-mount front brakes, and all-new side-muffler, wheels and Pro-Arm swingarm.  There\'s even a Deluxe model for 2014 with features like traction control, Anti-Lock Brakes, self-cancelling turn signals, a centerstand and heated grips.', 9899, 'Used', 'Honda', 'Sport', 2, 'HO7988');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (7, 'CBR 1000RR', 'Known for being the best overall liter bike package, the CBR1000RR cements its stellar reputation with several improvements for 2014, including a new ride position, a new windscreen and engine and exhaust modifications for more power and torque.  There\'s also a new Pearl White finish on our standard and ABS models.', 10599, 'New', 'Honda', 'Sport', 6, 'HO7989');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (8, 'GSX-R1000', 'For more than a decade, the GSX-R1000 was the most successful name in 1,000cc production-based road racing around the globe, earning it the title of the Top Performer.  The 2015 Suzuki GSX-R1000 is ready to prove itself to be a legendary motorcycle with amazing throttle response, power, and acceleration at mid-range engine speeds - all with great fuel economy.  Engineering your dream bike to go fast is not the only priority at Suzuki.  Bred from the same DNA as our MotoGP machines, the GSX-R1000 draws heavily on Suzuki\'s racing expertise.', 13899, 'New', 'Suzuki', 'Sport', 5, 'SU4021');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (9, 'Hayabusa', 'More than a decade ago, Suzuki developed a machine so unique that it instantly attracted a devoted following around the world.  Like its namesake, the Japanese peregrine falcon, the Hayabusa is famed for cutting through the air with incredible agility and performance.', 14599, 'New', 'Suzuki', 'Sport', 7, 'SU4022');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (10, '1199 Panigale R', 'Unbelievable performance and technological excellence proclaim this the new queen of the Superbike family.  Born of the world of competitive racing, the 1199 Panigale R is an exclusive and treasured interpretation of man\'s passion for the racetrack: titanium piston rods, carbon fibre bodywork, Ducati Performance racing exhaust and livery inspired by the Ducati Corse.  Perfection was never so close.', 24499, 'New', 'Ducati', 'Sport', 3, 'DU0321');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (11, 'Freewheeler', 'Hot rod-inspired style and a smaller, easy-handling size make the new Freewheeler model unlike any other trike on the road.  Confidence comes instantly in this saddle.', 24999, 'New', 'Harley Davidson', 'Trike', 2, 'HA1360');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (12, 'Spyder F3-S', 'The cruising riding position of the Spyder F3-S - customized just for you with the new UFit System - enables you to sit back and confidently take in the scenery...or the stares from onlookers.  They\'ll look extra long at the machined deep black high gloss front wheels, red accents and added fender lighting.  And with its Rotax 1330 ACE engine, Y-frame design and seven automotive technologies, you can count on a thrilling and confident ride.', 12899, 'Used', 'Can-AM', 'Trike', 1, 'CA8909');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (13, 'CRF250X', 'Your perfect trail bike has arrived.  Skilled off-roaders seek a bike that offers an ideal balance of power and size, performance and handling.  That\'s why we built the CRF250X', 7410, 'New', 'Honda', 'Off Road', 8, 'HO7990');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (14, 'YZ450F', 'Completely re-engineered just last year with new EFI engine, an entirely new frame, minimized body work and new suspension components providing for excellent mass centralization and class leading 450 power with 250-style handling.  The critics agree: the YZ450F is the one to beat', 8590, 'New', 'Yamaha', 'Off Road', 6, 'YA8123');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (15, 'CRF150R', 'Honda’s CRF150R is, hands down, the best MX machine in the Mini Class. Designed around a four-stroke powerplant that showcases plenty of Honda technology, it’s also graced with the performance that will keep you up at the front of the pack and on the podium. And the CRF150R is available in two versions to fit a wide variety of racers: there’s also the CRF150R Expert, featuring bigger wheels, a higher seat and a longer swingarm.', 3750, 'Used', 'Honda', 'Off Road', 4, 'HO7991');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (16, 'WRF250F', 'The all-new WR250F offers high-performance components of the MX Champion YZ250F in a light, easy-to-handle machine perfect for enduro riding through tight woods and beyond: liquid-cooled, 4-stroke fuel-injected engine, 6-speed transmission, new electric start and aluminum frame.  Class-leading performance right off the showroom floor.', 6575, 'Used', 'Yamaha', 'Off Road', 3, 'YA8124');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (17, 'Forza', 'Honda’s Forza™ is a scooter that’s way more than what you think a scooter can be. With the power to carry you and a passenger on the highway thanks to a smooth, powerful engine, integral storage (enough for two helmets) and even a power outlet for your accessories, the Forza bridges the gaps between fun, practical, and luxurious. Whether you use it for commuting, around-town, errand running, or just a way to get out and have fun, the Forza is a great transportation option that just about anyone can enjoy.', 5599, 'New', 'Honda', 'Scooter', 5, 'HO7992');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (18, 'Ruckus', 'The Ruckus looks like it could survive a nuclear blast and come out swinging. But the Ruckus is about way more than just a unique look: its thrifty 49cc engine gets awesome mileage. Its Honda V-matic® automatic transmission makes for no-shifting, seamless power in and out of traffic. Its electric starter means you just turn a key, push a button, and you’re outta there. Add in Honda’s legendary reliability, and the Ruckus is not only a blast—it’s nearly unstoppable.', 2649, 'New', 'Honda', 'Scooter', 8, 'HO7993');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (19, 'Smax', 'Introducing the newest way to start your sport scooter adventure.  The all-new SMAX features a potent, highly-efficient 155cc fuel-injected engine, fully-automatic transmission, lightweight frame for sporty handling, spacious design perfect for 1 r 2 up riding, under-seat storage large enough for a full-face helmet or groceries, and more.', 3690, 'New', 'Yamaha', 'Scooter', 6, 'YA8125');
INSERT INTO `JHUAppdb`.`Motorcycle` (`id`, `name`, `description`, `price`, `condition`, `brand`, `type`, `quantity`, `productNumber`) VALUES (20, 'Vino Classic', 'Classic retro style-straight out of the movies - meets modern Yamaha engineering.  Vino classic comes with fuel injection for smooth throttle response and easy starting in nearly all conditions.', 1200, 'Used', 'Yamaha', 'Scooter', 1, 'YA8126');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Helmet`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (1, 'CL-17', 'HJC Helmet Gray Full Face', 450, 'Large', 11, 'H123H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (2, 'CL-Max', 'HJC Helmet Red and Black Full Face', 399, 'Medium', 15, 'H124H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (3, 'CS-R2', 'HJC Helmet Graphics Full Face', 575, 'Large', 8, 'H125H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (4, 'CL-33', 'HJC Helmet Black 3/4 Face', 489, 'Medium', 9, 'H126H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (5, 'IS-2', 'HJC Helmet Red 1/2 Helmet', 259, 'Large', 17, 'H127H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (6, 'RF-1200', 'Shoei Helmet Green and Black Graphics Full Face', 689, 'Large', 12, 'S578H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (7, 'X-Twelve', 'Shoei Helmet White Graphics Full Face', 720, 'Large', 9, 'S579H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (8, 'RJ Platinum-R', 'Shoei Helmet Black 3/4 Face', 650, 'Medium', 5, 'S580H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (9, 'J-Cruise', 'Shoei Helmet White 3/4 Face', 599, 'Large', 4, 'S581H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (10, 'Qwest', 'Shoei Helmet Black and Red Full Face', 489, 'Large', 2, 'S582H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (11, 'Vector 2', 'Arai Helmet Grey Full Face', 550, 'Large', 9, 'A723H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (12, 'CT-Z', 'Arai Helmet Black 3/4 Face', 425, 'Medium', 10, 'A724H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (13, 'Defiant', 'Arai Helmet Orange and Black Full Face', 899, 'Large', 6, 'A725H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (14, 'Corsair V', 'Arai Helmet Red and White Full Face', 769, 'Medium', 8, 'A726H');
INSERT INTO `JHUAppdb`.`Helmet` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (15, 'Signet Q', 'Arai Helmet Red and Blue Full Face', 699, 'Large', 3, 'A727H');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Gloves`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (1, 'Pursuit', 'Icon gloves Blue', 10, 'Large', 22, 'I678G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (2, 'Pursuit', 'Icon gloves Red', 10, 'Medium', 14, 'I679G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (3, 'Hooligan', 'Icon gloves Green and Black', 15, 'Large', 10, 'I680G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (4, 'Hooligan', 'Icon gloves Blue and White', 15, 'Large', 15, 'I681G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (5, 'Anthem', 'Icon gloves Black', 15, 'Small', 8, 'I682G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (6, 'S-1', 'Alpinestars Leather gloves Red and White', 20, 'Large', 9, 'A498G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (7, 'S-1', 'Alpinestars Leather gloves Blue and White', 20, 'Large', 12, 'A499G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (8, 'Archer', 'Alpinestars gloves Black', 25, 'Medium', 18, 'A500G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (9, 'Archer', 'Alpinestars gloves Orange', 25, 'Large', 19, 'A501G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (10, 'Accelerator', 'Cortech gloves White and Black', 15, 'Large', 5, 'C334G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (11, 'Adrenaline II', 'Cortech gloves Red and Black', 20, 'Medium', 4, 'C335G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (12, 'HighSide 2.0', 'Joe Rocket gloves Gunmetal and Black', 20, 'Large', 13, 'J212G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (13, 'Nitrogen', 'Joe Rocket gloves Black', 20, 'Medium', 12, 'J213G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (14, 'SG3', 'Scorpion Leather gloves White and Black', 30, 'Medium', 10, 'S867G');
INSERT INTO `JHUAppdb`.`Gloves` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (15, 'HDX 2', 'Cortech gloves Yellow and Black', 15, 'Medium', 19, 'C336G');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Jacket`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (1, 'Radar', 'Joe Rocket Leather Jacket Blue and Black ', 399, 'Large', 12, 'J167J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (2, 'Radar', 'Joe Rocket Leather Jacket Red and Black', 150, 'Medium', 18, 'J168J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (3, 'Sanctuary', 'Icon Leather Jacket Green and Black', 259, 'Large', 8, 'I456J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (4, 'Overlord', 'Icon Textile Jacket Yellow and Gray', 200, 'Large', 6, 'I457J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (5, 'GP Pro', 'Alpinestars Textile Jacket Gray, Blue, and White', 459, 'Medium', 3, 'A980J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (6, 'GP Pro', 'Alpinestars Textile Jacket Gray and Yellow', 400, 'Large', 17, 'A981J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (7, 'Atomic', 'Joe Rocket Leather Jacket Red and Black ', 375, 'Large', 15, 'J169J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (8, 'Phoenix', 'Joe Rocket Leather Jacket Black', 150, 'Small', 10, 'J170J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (9, 'Phoenix', 'Joe Rocket Leather Jacket Blue and Black', 255, 'Medium', 9, 'J171J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (10, 'Vapor', 'Rev\'It Leather Jacket Black', 549, 'Medium', 5, 'R657J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (11, 'Vapor', 'Rev\'It Leather Jacket Black', 500, 'Large', 7, 'R658J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (12, 'Assailant', 'Scorpion Leather Jacket White', 279, 'Medium', 18, 'S712J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (13, 'Assailant', 'Scorpion Leather Jacket White', 325, 'Small', 14, 'S713J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (14, 'Transition', 'Tourmaster Textile Jacket Red', 450, 'Medium', 12, 'T361J');
INSERT INTO `JHUAppdb`.`Jacket` (`id`, `name`, `description`, `price`, `size`, `quantity`, `productNumber`) VALUES (15, 'Transition', 'Tourmaster Textile Jacket Grey', 399, 'Large', 3, 'T362J');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Order`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Order` (`id`, `dateCreated`, `userId`, `dateOrdered`, `confirmationNumber`) VALUES (1, '2013-05-01', 1, '2013-05-03', '154678');
INSERT INTO `JHUAppdb`.`Order` (`id`, `dateCreated`, `userId`, `dateOrdered`, `confirmationNumber`) VALUES (2, '2012-03-14', 2, '2012-04-14', '257846');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`CreditCard`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`CreditCard` (`id`, `userId`, `creditCardNumber`) VALUES (1, 1, '4578631259872148');
INSERT INTO `JHUAppdb`.`CreditCard` (`id`, `userId`, `creditCardNumber`) VALUES (2, 2, '6572348912576152');
INSERT INTO `JHUAppdb`.`CreditCard` (`id`, `userId`, `creditCardNumber`) VALUES (3, 3, '7864231580142573');

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Order_has_Jacket`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Order_has_Jacket` (`Order_id`, `Jacket_id`) VALUES (1, 1);
INSERT INTO `JHUAppdb`.`Order_has_Jacket` (`Order_id`, `Jacket_id`) VALUES (2, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Order_has_Helmet`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Order_has_Helmet` (`Order_id`, `Helmet_id`) VALUES (1, 5);
INSERT INTO `JHUAppdb`.`Order_has_Helmet` (`Order_id`, `Helmet_id`) VALUES (1, 3);
INSERT INTO `JHUAppdb`.`Order_has_Helmet` (`Order_id`, `Helmet_id`) VALUES (2, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Order_has_Gloves`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Order_has_Gloves` (`Order_id`, `Gloves_id`) VALUES (1, 5);
INSERT INTO `JHUAppdb`.`Order_has_Gloves` (`Order_id`, `Gloves_id`) VALUES (1, 8);
INSERT INTO `JHUAppdb`.`Order_has_Gloves` (`Order_id`, `Gloves_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `JHUAppdb`.`Order_has_Motorcycle`
-- -----------------------------------------------------
START TRANSACTION;
USE `JHUAppdb`;
INSERT INTO `JHUAppdb`.`Order_has_Motorcycle` (`Order_id`, `Motorcycle_id`) VALUES (1, 8);
INSERT INTO `JHUAppdb`.`Order_has_Motorcycle` (`Order_id`, `Motorcycle_id`) VALUES (2, 12);

COMMIT;

