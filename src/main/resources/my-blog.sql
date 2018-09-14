-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.12-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 lu_tale 的数据库结构
CREATE DATABASE IF NOT EXISTS `lu_tale` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lu_tale`;

-- 导出  表 lu_tale.mto_comments 结构
CREATE TABLE IF NOT EXISTS `mto_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `pid` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_config 结构
CREATE TABLE IF NOT EXISTS `mto_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_99vo6d7ci4wlxruo3gd0q2jq8` (`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_favors 结构
CREATE TABLE IF NOT EXISTS `mto_favors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `own_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_feeds 结构
CREATE TABLE IF NOT EXISTS `mto_feeds` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `own_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_follows 结构
CREATE TABLE IF NOT EXISTS `mto_follows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `follow_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t38um6ivtsbk34xph98levx4x` (`follow_id`),
  KEY `FK_3grh3868ek909r524m0kphmux` (`user_id`),
  CONSTRAINT `FK_3grh3868ek909r524m0kphmux` FOREIGN KEY (`user_id`) REFERENCES `mto_users` (`id`),
  CONSTRAINT `FK_t38um6ivtsbk34xph98levx4x` FOREIGN KEY (`follow_id`) REFERENCES `mto_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_friend_link 结构
CREATE TABLE IF NOT EXISTS `mto_friend_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logo` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `site_name` varchar(255) NOT NULL,
  `sort` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_group 结构
CREATE TABLE IF NOT EXISTS `mto_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2s863lts1h6m7c30152262cvj` (`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_logs 结构
CREATE TABLE IF NOT EXISTS `mto_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` date DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_menu 结构
CREATE TABLE IF NOT EXISTS `mto_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(18) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `target` varchar(18) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_notify 结构
CREATE TABLE IF NOT EXISTS `mto_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `event` int(11) NOT NULL,
  `from_id` bigint(20) DEFAULT NULL,
  `own_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_posts 结构
CREATE TABLE IF NOT EXISTS `mto_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `favors` int(11) NOT NULL,
  `featured` int(11) NOT NULL,
  `group_` int(11) DEFAULT NULL,
  `images` int(11) NOT NULL,
  `last_image_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `views` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_posts_attach 结构
CREATE TABLE IF NOT EXISTS `mto_posts_attach` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` int(11) NOT NULL,
  `original` varchar(255) DEFAULT NULL,
  `preview` varchar(255) DEFAULT NULL,
  `screenshot` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `store` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `width` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_posts_attribute 结构
CREATE TABLE IF NOT EXISTS `mto_posts_attribute` (
  `id` bigint(20) NOT NULL,
  `content` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_role 结构
CREATE TABLE IF NOT EXISTS `mto_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_role_menu 结构
CREATE TABLE IF NOT EXISTS `mto_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  KEY `FK_td8ih8aorlkpyop3gemfqxmbt` (`menu_id`),
  KEY `FK_5o5vaxfyg0d1qa0142dnkruiv` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_users 结构
CREATE TABLE IF NOT EXISTS `mto_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT '/assets/images/ava/default.png',
  `updated` datetime DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `source` int(11) NOT NULL,
  `active_email` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `fans` int(11) NOT NULL,
  `favors` int(11) NOT NULL,
  `follows` int(11) NOT NULL,
  `posts` int(11) NOT NULL,
  `signature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_users_open_oauth 结构
CREATE TABLE IF NOT EXISTS `mto_users_open_oauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `expire_in` varchar(255) DEFAULT NULL,
  `oauth_code` varchar(255) DEFAULT NULL,
  `oauth_type` int(11) DEFAULT NULL,
  `oauth_user_id` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_user_role 结构
CREATE TABLE IF NOT EXISTS `mto_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK_fhtla2vc199mv0ru2r2kvakha` (`role_id`),
  KEY `FK_b4m7ef0uvkr4efrscf8r1ehy2` (`user_id`),
  CONSTRAINT `FK_b4m7ef0uvkr4efrscf8r1ehy2` FOREIGN KEY (`user_id`) REFERENCES `mto_users` (`id`),
  CONSTRAINT `FK_fhtla2vc199mv0ru2r2kvakha` FOREIGN KEY (`role_id`) REFERENCES `mto_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.mto_verify 结构
CREATE TABLE IF NOT EXISTS `mto_verify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL,
  `created` datetime NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `target` varchar(96) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m7p0b526c4xlgjn787t22om2g` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_attach 结构
CREATE TABLE IF NOT EXISTS `t_attach` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL DEFAULT '',
  `ftype` varchar(50) DEFAULT '',
  `fkey` text NOT NULL,
  `authorId` int(10) DEFAULT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_chats 结构
CREATE TABLE IF NOT EXISTS `t_chats` (
  `chid` varchar(32) NOT NULL,
  `content` varchar(200) DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `commentNum` int(10) DEFAULT '0' COMMENT '评论数',
  `clickNum` int(10) DEFAULT '0' COMMENT '点击数',
  `created` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`chid`),
  UNIQUE KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_comments 结构
CREATE TABLE IF NOT EXISTS `t_comments` (
  `coid` varchar(32) NOT NULL,
  `cid` varchar(32) DEFAULT '0' COMMENT '文章ID',
  `created` int(10) unsigned DEFAULT '0',
  `author` varchar(200) DEFAULT NULL,
  `authorId` int(10) unsigned DEFAULT '0',
  `ownerId` int(10) unsigned DEFAULT '0',
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text,
  `type` varchar(16) DEFAULT 'comment',
  `status` varchar(16) DEFAULT 'approved',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`coid`),
  KEY `cid` (`cid`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_contents 结构
CREATE TABLE IF NOT EXISTS `t_contents` (
  `cid` varchar(32) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `titlePic` varchar(55) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `modified` int(10) unsigned DEFAULT '0',
  `content` text COMMENT '内容文字',
  `authorId` int(10) unsigned DEFAULT '0',
  `type` varchar(16) DEFAULT 'post',
  `status` varchar(16) DEFAULT 'publish',
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) unsigned DEFAULT '0',
  `commentsNum` int(10) unsigned DEFAULT '0',
  `allowComment` tinyint(1) DEFAULT '1',
  `allowPing` tinyint(1) DEFAULT '1',
  `allowFeed` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_feedbacks 结构
CREATE TABLE IF NOT EXISTS `t_feedbacks` (
  `fid` varchar(32) NOT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `author` varchar(200) DEFAULT NULL,
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `content` text,
  `parent` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_logs 结构
CREATE TABLE IF NOT EXISTS `t_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `action` varchar(100) DEFAULT NULL COMMENT '事件',
  `data` varchar(2000) DEFAULT NULL COMMENT '数据',
  `authorId` int(10) DEFAULT NULL COMMENT '作者编号',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_metas 结构
CREATE TABLE IF NOT EXISTS `t_metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `type` varchar(32) NOT NULL DEFAULT '',
  `contentType` varchar(32) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `sort` int(10) unsigned DEFAULT '0',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_options 结构
CREATE TABLE IF NOT EXISTS `t_options` (
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(1000) DEFAULT '',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_relationships 结构
CREATE TABLE IF NOT EXISTS `t_relationships` (
  `cid` varchar(32) NOT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
-- 导出  表 lu_tale.t_users 结构
CREATE TABLE IF NOT EXISTS `t_users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `homeUrl` varchar(200) DEFAULT NULL,
  `screenName` varchar(32) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `activated` int(10) unsigned DEFAULT '0',
  `logged` int(10) unsigned DEFAULT '0',
  `groupName` varchar(16) DEFAULT 'visitor',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`username`),
  UNIQUE KEY `mail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
