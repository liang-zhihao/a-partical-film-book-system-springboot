
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS admin;
CREATE TABLE admin  (
                        admin_id int(0) NOT NULL AUTO_INCREMENT,
                        username varchar(255) ,
                        password varchar(255) ,
                        PRIMARY KEY (admin_id)
);

-- ----------------------------
-- Table structure for cinema
-- ----------------------------
DROP TABLE IF EXISTS cinema;
CREATE TABLE cinema  (
                         cinema_id int(0) NOT NULL AUTO_INCREMENT,
                         name varchar(255) ,
                         location varchar(255) ,
                         tel varchar(255) ,
                         PRIMARY KEY (cinema_id)
);

-- ----------------------------
-- Table structure for comment
-- ----------------------------


-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS favorite;
CREATE TABLE favorite  (
                           favorite_id int(0) NOT NULL,
                           uesr_id int(0) NOT NULL,
                           film_id int(0) NOT NULL,
                           PRIMARY KEY (favorite_id) ,
                           INDEX uesr_id(uesr_id) ,
                           INDEX film_id(film_id) ,
                           CONSTRAINT favorite_ibfk_1 FOREIGN KEY (uesr_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                           CONSTRAINT favorite_ibfk_2 FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for film
-- ----------------------------
DROP TABLE IF EXISTS film;
CREATE TABLE film  (
                       film_id int(0) NOT NULL AUTO_INCREMENT,
                       name varchar(255) ,
                       duration int(0) NULL ,
                       director varchar(255) ,
                       actors varchar(255) ,
                       rating double NULL ,
                       type_id int(0) NULL ,
                       poster varchar(255) ,
                       introduction varchar(255) ,
                       status varchar(255) ,
                       PRIMARY KEY (film_id) ,
                       INDEX film_ibfk_1(type_id) ,
                       CONSTRAINT film_ibfk_1 FOREIGN KEY (type_id) REFERENCES type (type_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS comment;
CREATE TABLE comment  (
                          comment_id int(0) NOT NULL AUTO_INCREMENT,
                          content varchar(255) ,
                          film_id int(0) NULL ,
                          order_id int(0) NULL ,
                          date datetime(0) NULL ,
                          PRIMARY KEY (comment_id) ,
                          INDEX film_id(film_id) ,
                          CONSTRAINT film_id FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS role;
CREATE TABLE role  (
                       role_id int(0) NOT NULL,
                       role_name varchar(255) ,
                       PRIMARY KEY (role_id)
);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS room;
CREATE TABLE room  (
                       room_id int(0) NOT NULL AUTO_INCREMENT,
                       cinema_id int(0) NOT NULL,
                       seats int(0) NULL ,
                       room_name varchar(255) ,
                       PRIMARY KEY (room_id) ,
                       INDEX cinema_id(cinema_id) ,
                       CONSTRAINT room_ibfk_1 FOREIGN KEY (cinema_id) REFERENCES cinema (cinema_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS seat;
CREATE TABLE seat  (
                       seat_id int(0) NOT NULL AUTO_INCREMENT,
                       room_id int(0) NOT NULL,
                       x char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                       y char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                       PRIMARY KEY (seat_id) ,
                       INDEX room_id(room_id) ,
                       CONSTRAINT seat_ibfk_1 FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS session;
CREATE TABLE session  (
                          session_id int(0) NOT NULL AUTO_INCREMENT,
                          film_id int(0) NULL ,
                          time datetime(0) NULL ,
                          status varchar(255) ,
                          remaining_seats int(0) NULL ,
                          room_id int(0) NULL ,
                          cinema_id int(0) NULL ,
                          price decimal(10, 2) NULL ,
                          PRIMARY KEY (session_id) ,
                          INDEX film_id(film_id) ,
                          INDEX room_id(room_id) ,
                          INDEX cinema_id(cinema_id) ,
                          CONSTRAINT session_ibfk_1 FOREIGN KEY (film_id) REFERENCES film (film_id) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT session_ibfk_2 FOREIGN KEY (room_id) REFERENCES room (room_id) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT session_ibfk_3 FOREIGN KEY (cinema_id) REFERENCES cinema (cinema_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for sub_order
-- ----------------------------
DROP TABLE IF EXISTS sub_order;
CREATE TABLE sub_order  (
                            sub_order_id int(0) NOT NULL AUTO_INCREMENT,
                            order_id int(0) NULL ,
                            fee double NULL ,
                            is_student int(0) NULL ,
                            seat_id int(0) NULL ,
                            PRIMARY KEY (sub_order_id) ,
                            INDEX order_id(order_id) ,
                            INDEX sub_order_ibfk_2(seat_id) ,
                            CONSTRAINT sub_order_ibfk_1 FOREIGN KEY (order_id) REFERENCES ticket_order (order_id) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT sub_order_ibfk_2 FOREIGN KEY (seat_id) REFERENCES seat (seat_id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for ticket_order
-- ----------------------------
DROP TABLE IF EXISTS ticket_order;
CREATE TABLE ticket_order  (
                               order_id int(0) NOT NULL AUTO_INCREMENT,
                               user_id int(0) NULL ,
                               session_id int(0) NULL ,
                               order_time datetime(0) NULL ,
                               pay_time datetime(0) NULL ,
                               status varchar(255) ,
                               fee double NULL ,
                               PRIMARY KEY (order_id) ,
                               INDEX user_id(user_id) ,
                               INDEX session_id(session_id) ,
                               CONSTRAINT ticket_order_ibfk_1 FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT ticket_order_ibfk_2 FOREIGN KEY (session_id) REFERENCES session (session_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS type;
CREATE TABLE type  (
                       type_id int(0) NOT NULL AUTO_INCREMENT,
                       type varchar(255) ,
                       PRIMARY KEY (type_id)
);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user  (
                       user_id int(0) NOT NULL AUTO_INCREMENT,
                       username varchar(255) ,
                       password varchar(255) ,
                       email varchar(255) ,
                       tel varchar(255) ,
                       portrait varchar(255) ,
                       is_student int(0) NULL ,
                       access_token varchar(255) ,
                       PRIMARY KEY (user_id)
);

-- ----------------------------
-- Table structure for waitlist
-- ----------------------------
DROP TABLE IF EXISTS waitlist;
CREATE TABLE waitlist  (
                           waitlist_id int(0) NOT NULL AUTO_INCREMENT,
                           session_id int(0) NULL ,
                           name varchar(255) ,
                           email varchar(255) ,
                           PRIMARY KEY (waitlist_id) ,
                           INDEX session_id(session_id) ,
                           CONSTRAINT waitlist_ibfk_1 FOREIGN KEY (session_id) REFERENCES session (session_id) ON DELETE SET NULL ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 1;


