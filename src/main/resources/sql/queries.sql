
INSERT INTO `CUSTOMER`(`phoneNo`,`email`,  `username`) VALUES ('9129945862','customer1@gmail.com','customer1');
INSERT INTO `CUSTOMER`(`phoneNo`,`email`,  `username`) VALUES ('9125555863','customer2@gmail.com','customer2');
INSERT INTO `CUSTOMER`(`phoneNo`,`email`,  `username`) VALUES ('9129943464','customer3@gmail.com','customer3');
INSERT INTO `CUSTOMER`(`phoneNo`,`email`,  `username`) VALUES ('9129945665','customer4@gmail.com','customer4');
INSERT INTO `CUSTOMER`(`phoneNo`,`email`,  `username`) VALUES ('9129222266', 'customer5@gmail.com','customer5');
INSERT INTO `CUSTOMER`(`phoneNo`,`email`,  `username`) VALUES ('9129946567','customer6@gmail.com','customer6');


INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('1','xyz-1','new york','10001','9129945862');
INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('2','xyz-2','new york','10001','9125555863');
INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('3','xyz-3','new york','10001','9129943464');
INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('4','xyz-4','new york','10001','9129945665');
INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('5','xyz-5','new york','10001','9129222266');
INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('6','xyz-6','new york','10001','9129946567');
INSERT INTO `ADDRESS_TBL`(`id`, `address`, `city`, `postal_code`, `customer_phoneNo`) VALUES ('7','xyz-6','new york','10002','9129946567');


INSERT INTO `DELIVERY_AGENT_TBL` (`phoneNo`, `availableTime`, `email`, `status`, `username`) VALUES
('7484586951', '2023-02-06 12:31:07', 'deness@gmail.com', b'0', 'Diness'),
('7484446952', '2023-02-06 12:31:07', 'mukesh@gmail.com', b'0', 'Mukesh');
