CREATE TABLE `bill_member`
(
    `id`         bigint(20)   NOT NULL AUTO_INCREMENT,
    `billID`     bigint(20)   NOT NULL,
    `memberID`   bigint(20)   NOT NULL,
    `ratio`      double(5, 4) DEFAULT '0.0000',
    `memberName` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `bills`
(
    `id`         bigint(20)   NOT NULL AUTO_INCREMENT,
    `teamID`     bigint(20)   NOT NULL,
    `billName`   varchar(100) NOT NULL,
    `totalMoney` double(11, 2) DEFAULT '0.00',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE `team_member`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `teamID`   bigint(20) NOT NULL,
    `memberID` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `teams`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `leaderID` bigint(20)   NOT NULL,
    `teamName` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `userName` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `name`     varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;




