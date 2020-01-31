
CREATE TABLE `family`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `user_id`     varchar(32)  NULL,
    `name`        varchar(255) NULL,
    `members`     int(4)       NULL COMMENT '1 父母  2 夫妻 3 儿女 4 恋人 5 室友 6 朋友  7 其他',
    `create_time` timestamp    NULL ON UPDATE CURRENT_TIMESTAMP,
    `mobile`      varchar(20)  NULL,
    `status`      int(4) DEFAULT 1 COMMENT '0 无效 1 有效',
    PRIMARY KEY (`id`)
);


CREATE TABLE `temperature`
(
    `id`            int(11)       NOT NULL AUTO_INCREMENT,
    `user_id`       varchar(32)   NOT NULL,
    `degrees`       numeric(4, 1) NOT NULL,
    `create_by`     varchar(255)  NOT NULL,
    `family_id`     int(11)       NULL,
    `enter_type`    int(4)        NOT NULL COMMENT '1 员工录入  2 管理员录入 ',
    `department_id` varchar(100)  NOT NULL,
    `name`          varchar(255)  NOT NULL,
    `title`         varchar(100)  NULL,
    `mobile`        varchar(20)   NOT NULL,
    `create_time`   timestamp     NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (`id`)
);

CREATE TABLE `whitelist`
(
    `id`     int(11)     NOT NULL AUTO_INCREMENT,
    `mobile` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE user_promised
(
    id          int AUTO_INCREMENT,
    user_id     varchar(32)                         NULL COMMENT '钉钉用户id',
    is_promised tinyint   DEFAULT 0                 NOT NULL COMMENT '是否签了保证书',
    create_time timestamp DEFAULT current_timestamp NULL,
    CONSTRAINT user_promised_pk
        PRIMARY KEY (id)
);