create table `attribute`(
	`id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    `nullable` bool NOT NULL default true,
    `type` int NOT NULL,
    `category_id` int NOT NULL,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`category_id`)
		REFERENCES `category`(`id`)
        ON DELETE CASCADE);
