create table `attribute`(
	`id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    `nullable` bool NOT NULL default true,
    `type` int NOT NULL,
    `category_id` bigint NOT NULL,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`category_id`)
		REFERENCES `category`(`id`)
        ON DELETE CASCADE);
 