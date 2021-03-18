CREATE TABLE `item_value` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attribute_id` bigint DEFAULT NULL,
  `item_value_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKin1owya2tfds8i1jqyfec6fql` (`attribute_id`),
  KEY `FKnlhb9f2jb6mg57jqym2loobod` (`item_value_id`),
  CONSTRAINT `FKin1owya2tfds8i1jqyfec6fql` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`),
  CONSTRAINT `FKnlhb9f2jb6mg57jqym2loobod` FOREIGN KEY (`item_value_id`) REFERENCES `item_value` (`id`)
);

CREATE TABLE `boolean_item_value` (
  `value` bit(1) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKcwpb9wg6cs96mtd7t1kkwyeiy` FOREIGN KEY (`id`) REFERENCES `item_value` (`id`)
);

CREATE TABLE `integer_item_value` (
  `value` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKerrst1a7a3ck2fwx4pjfl9qef` FOREIGN KEY (`id`) REFERENCES `item_value` (`id`)
);

CREATE TABLE `double_item_value` (
  `value` double DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK3uiby6j6fg1ju5qjuv4nsp8h8` FOREIGN KEY (`id`) REFERENCES `item_value` (`id`)
);

CREATE TABLE `textual_item_value` (
  `value` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKgmw4lgv1fo4wmuns0nr41kmqq` FOREIGN KEY (`id`) REFERENCES `item_value` (`id`)
);

CREATE TABLE `date_time_item_value` (
  `value` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK6d7x7lygy1t93jj14sb0wcsg6` FOREIGN KEY (`id`) REFERENCES `item_value` (`id`)
);