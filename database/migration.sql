-- allow tables to store romanian characters
ALTER DATABASE rffz DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_romanian_ci;
-- executed on stage 04.02.2012
-- executed on live 01.04.2012


-- tables renames
rename table FeedSource to feed_source;
-- executed on stage 30.04.2012

rename table Candidate to candidate;
-- executed on stage 30.04.2012

rename table Article to article;
-- executed on stage 30.04.2012

-- columns renames
alter table article change creationDate creation_date datetime;
-- executed on stage 30.04.2012

alter table candidate change imagePath image_path longtext;
-- executed on stage 30.04.2012

alter table candidate change publishDate publish_date datetime;
-- executed on stage 30.04.2012


-- configuring scheduled tasks
insert into scheduled_task (id, name, active, last_run) values (1, 'CandidatesRetrievalJob', 1, NOW());
-- executed on stage 30.04.2012

insert into scheduled_task (id, name, active, last_run) values (2, 'CandidatesAnalyserJob', 1, NOW());
-- executed on stage 30.04.2012


-- configuring feed sources
insert into feed_source (id, name, active, url) values (1, 'www.gsp.ro', 1, 'http://feeds.feedburner.com/GsproFotbal');
insert into feed_source (id, name, active, url) values (2, 'www.prosport.ro', 1, 'http://feeds.feedburner.com/prosport/ifUQ');
insert into feed_source (id, name, active, url) values (3, 'www.onlinesport.ro', 1, 'http://feeds.feedburner.com/onlinesport/eoZs');
insert into feed_source (id, name, active, url) values (4, 'www.sport.ro', 0, 'http://feeds.feedburner.com/sport/qKoJ');
insert into feed_source (id, name, url, active) values (8, 'www.ofsaid.ro', 'http://feeds.feedburner.com/Ofsaid', 1);
insert into feed_source (id, name, url, active) values (9, 'www.prosport.ro', 'http://feeds.feedburner.com/prosport/LXCu', 1);


-- create tables on live
CREATE TABLE `scheduled_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `last_run` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_romanian_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;
-- executed on live 01.04.2012

CREATE TABLE `feed_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_romanian_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_romanian_ci NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;
-- executed on live 01.04.2012

CREATE TABLE `candidate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext COLLATE utf8_romanian_ci NOT NULL,
  `image_path` longtext COLLATE utf8_romanian_ci,
  `processed` bit(1) NOT NULL,
  `publish_date` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_romanian_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_romanian_ci NOT NULL,
  `feedSource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK95C3B563ED34B2FA` (`feedSource_id`),
  CONSTRAINT `FK95C3B563ED34B2FA` FOREIGN KEY (`feedSource_id`) REFERENCES `feed_source` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7332 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;
-- executed on live 01.04.2012

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D6B1739B1A` (`candidate_id`),
  CONSTRAINT `FK379164D6B1739B1A` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1361 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;
-- executed on live 01.04.2012