DROP DATABASE IF EXISTS jdbc_article_manager;
CREATE DATABASE jdbc_article_manager;
USE jdbc_article_manager;

DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS article;

# article 테이블 생성
CREATE TABLE article(
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title VARCHAR(200) NOT NULL,
	`body` TEXT NOT NULL
);

# 구조확인
DESC article;

# member 테이블 생성
CREATE TABLE `member` (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId VARCHAR(20) NOT NULL,
	loginPw VARCHAR(50) NOT NULL,
	loginPwC VARCHAR(50) NOT NULL,
	`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `member` (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId VARCHAR(20) NOT NULL,
	loginPw VARCHAR(50) NOT NULL,
	`name` VARCHAR(50) NOT NULL
);

#게시물 작성시 작성자의 회원번호를 저장하도록
ALTER TABLE article ADD COLUMN memberId INT UNSIGNED NOT NULL AFTER updateDate;

#역순 체크
SELECT * FROM article ORDER BY id DESC;

# article 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목', RAND()),
`body` = CONCAT('내용', RAND());

# member 데이터 추가
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = CONCAT('TestId', RAND()),
loginPw = CONCAT('TestPw', RAND()),
`name` = CONCAT('Testname', RAND());

# 아이디 중복 체크
SELECT COUNT(loginId) > 0
FROM `member`
WHERE loginId = loginId;

SELECT COUNT(loginId) > 0
FROM `member`
WHERE loginId = 'test3';

SELECT *
FROM `member`
WHERE loginId = loginId;

# article 테이블 조회
SELECT * FROM article;

# member 테이블 조회
SELECT * FROM `member`;

# article 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = 'test1',
`body` = 'test1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = 'test1',
`body` = 'test1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
title = 'test2',
`body` = 'test2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
title = 'test2',
`body` = 'test2';

# member 데이터 추가
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '스테이크';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '파스타';

SELECT A.*, M.name AS writerName 
FROM article AS A
INNER JOIN `member` AS M
ON A.memberId = M.id
ORDER BY id DESC;

UPDATE article 
SET hit = hit + 1

#
SELECT A.*, M.name AS writerName 
FROM article AS A
INNER JOIN `member` AS M
ON A.memberId = M.id
WHERE A.id = 2;

#게시물 작성시 작성자의 회원번호를 저장하도록
ALTER TABLE article ADD COLUMN memberId INT UNSIGNED NOT NULL AFTER updateDate;

#조회수 기능 추가
ALTER TABLE article ADD COLUMN hit INT UNSIGNED NOT NULL;

# 아이디 중복 체크