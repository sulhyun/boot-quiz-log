DROP DATABASE IF EXISTS quizlog;

CREATE DATABASE IF NOT EXISTS quizlog;

USE quizlog;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `quiz_interest`;

CREATE TABLE `quiz_interest` (
	`qi_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`qt_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `quiz_choice`;

CREATE TABLE `quiz_choice` (
	`qu_num`	int primary key auto_increment	NOT NULL,
	`qu_content`	varchar(255)	NULL,
	`qu_answer1`	varchar(255)	NULL,
	`qu_answer2`	varchar(255)	NULL,
	`qu_answer3`	varchar(255)	NULL,
	`qu_answer4`	varchar(255)	NULL,
	`qu_correct_answer`	int(11)	NULL,
	`qt_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`mb_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`mb_pw`	varchar(255)	NULL,
	`mb_name`	varchar(50)	NULL,
	`mb_nick`	varchar(50)	NULL,
	`mb_hp`	varchar(50)	NULL,
	`mb_email`	varchar(50)	NULL,
	`mb_zip`	int(11)	NULL,
	`mb_addr`	varchar(255)	NULL,
	`mb_addr2`	varchar(255)	NULL,
	`mb_birth`	varchar(10)	NULL,
	`mb_level`	int(11)	NULL,
	`mb_datetime`	datetime	NULL,
	`mb_edit_date`	datetime	NULL,
	`mb_out_date`	datetime	NULL,
	`mb_cookie`	varchar(255)	NULL,
	`mb_cookie_limit`	varchar(255)	NULL,
	`mb_point`	int(11)	NULL,
	`mb_login_method`	varchar(255)	NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
	`cm_num`	int primary key auto_increment	NOT NULL,
	`cm_content`	varchar(255)	NULL,
	`cm_ori_num`	int	NULL,
	`cm_date`	datetime	NULL,
	`mb_id`	varchar(255)	NULL,
	`po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
	`ra_num`	int primary key auto_increment	NOT NULL,
	`ra_no`	int(11)	NULL,
	`mb_id`	varchar(255)	NULL,
	`qt_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `community`;

CREATE TABLE `community` (
	`co_num`	int primary key auto_increment	NOT NULL,
	`co_name`	varchar(255)	NULL
);

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
	`fr_num`	int primary key auto_increment	NOT NULL,
	`mb_id`	varchar(255)	NULL,
	`fr_id`	varchar(255)	NULL,
	`fr_status`	char(1)	NULL
);

DROP TABLE IF EXISTS `inquiry`;

CREATE TABLE `inquiry` (
	`iq_num`	int primary key auto_increment	NOT NULL,
	`iq_content`	longtext	NULL,
	`iq_datetime1`	datetime	NULL,
	`iq_comment`	longtext	NULL,
	`iq_datetime2`	datetime	NULL,
	`iq_view`	int	NULL,
	`mb_id`	varchar(255)	NULL,
	`iq_title`	varchar(255)	NULL
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
	`po_num`	int primary key auto_increment	NOT NULL,
	`po_title`	varchar(255)	NULL,
	`po_content`	longtext	NULL,
	`po_date`	datetime	NULL,
	`po_view`	int	NULL,
	`po_secret`	char(1)	NULL,
	`mb_id`	varchar(255)	NULL,
	`co_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
	`fi_num`	int primary key auto_increment	NOT NULL,
	`fi_ori_name`	varchar(255)	NULL,
	`fi_path`	text	NULL,
	`fi_no`	int	NULL,
	`fi_reg_num`	int	NULL,
	`fi_type`	varchar(255)	NULL
);

DROP TABLE IF EXISTS `email_verification`;

CREATE TABLE `email_verification` (
	`evc_num`	int primary key auto_increment	NOT NULL,
	`mb_email`	varchar(255)	NULL,
	`evc_code`	char(6)	NULL
);

DROP TABLE IF EXISTS `recommend`;

CREATE TABLE `recommend` (
	`re_num`	int primary key auto_increment	NOT NULL,
	`re_state`	char(1)	NULL,
	`mb_id`	varchar(255)	NULL,
	`po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `quiz_type`;

CREATE TABLE `quiz_type` (
	`qt_num`	int primary key auto_increment	NOT NULL,
	`qt_name`	varchar(255)	NULL
);

DROP TABLE IF EXISTS `quiz_subjective`;

CREATE TABLE `quiz_subjective` (
	`qs_num`	int primary key auto_increment	NOT NULL,
	`qs_content`	varchar(255)	NULL,
	`qs_answer`	varchar(255)	NULL,
	`qs_correct_answer`	varchar(255)	NULL,
	`qt_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `point`;

CREATE TABLE `point` (
	`pi_num`	int primary key auto_increment	NOT NULL,
	`pi_no`	int	NULL,
	`pi_content`	varchar(255)	NULL,
	`pi_datetime`	datetime	NULL,
	`mb_id`	varchar(255)	NULL
);

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `quiz_interest` ADD CONSTRAINT `FK_quiz_type_TO_quiz_interest_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `quiz_choice` ADD CONSTRAINT `FK_quiz_type_TO_quiz_choice_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment_1` FOREIGN KEY (
	`po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `rating` ADD CONSTRAINT `FK_quiz_type_TO_rating_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_community_TO_post_1` FOREIGN KEY (
	`co_num`
)
REFERENCES `community` (
	`co_num`
);

ALTER TABLE `recommend` ADD CONSTRAINT `FK_post_TO_recommend_1` FOREIGN KEY (
	`po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `quiz_subjective` ADD CONSTRAINT `FK_quiz_type_TO_quiz_subjective_1` FOREIGN KEY (
	`qt_num`
)
REFERENCES `quiz_type` (
	`qt_num`
);

