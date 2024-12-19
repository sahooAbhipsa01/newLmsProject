
 create database lms;
 use lms;
 drop database lms;
 
 -- users table--
 CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    gender ENUM('M', 'F', 'O') NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'teacher') NOT NULL
);
select * from users;



-- courses table--
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    trainer_id INT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES users(user_id) ON DELETE CASCADE
);
ALTER TABLE courses ADD COLUMN course_description TEXT;
INSERT INTO courses (name, trainer_id)
VALUES
('Biology',14);
delete from courses;
select * from courses;

-- grades table --
CREATE TABLE grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    grade VARCHAR(2) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);
INSERT INTO grades (student_id, course_id, grade)
VALUES
(10,21,'A');
SELECT c.name AS course_name, g.grade  -- student dashboard --
                       FROM grades g
                       JOIN courses c ON g.course_id = c.course_id 
                       WHERE g.student_id = 10;
SELECT * FROM grades WHERE student_id =13 AND course_id =8 ;
-- trainer dashboard --
SELECT   
    u.name AS student_name, 
    c.name AS course_name, 
    g.grade, 
    g.student_id, 
    g.course_id
FROM grades g
JOIN users u ON g.student_id = u.user_id
JOIN courses c ON g.course_id = c.course_id
WHERE c.trainer_id = 9;


select * from grades;


-- forum message --
CREATE TABLE forum_messages (
    forumMessages_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
select * from forum_messages;


-- assignment table --
-- CREATE TABLE assignments (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     student_id INT NOT NULL,
--     course_id INT NOT NULL,
--     file_name VARCHAR(255) NOT NULL,
--     submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
--     FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
-- );--
 -- enrollments table --
 CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

SELECT * FROM enrollments;
INSERT INTO enrollments (student_id, course_id)
VALUES
(10,21);

CREATE TABLE forum_history (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each message
    forum_topic_id INT NOT NULL,         -- Foreign Key referencing the forum_topics table
    user_id INT NOT NULL,                -- The ID of the user who posted the message
    message TEXT NOT NULL,               -- The content of the message
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp when the message was posted
    FOREIGN KEY (forum_topic_id) REFERENCES forum_messages(forumMessages_id) ON DELETE CASCADE,  -- Links messages to specific topics
    FOREIGN KEY (user_id) REFERENCES users(user_id)  -- Assuming there is a users table with user IDs
);
select * from forum_history;
alter table enrollments add column grade varchar(10) default 'Not Graded';




 create database lms;
 use lms;
 drop database lms;
 
 -- users table--
 CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    gender ENUM('M', 'F', 'O') NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'teacher') NOT NULL
);
select * from users;



-- courses table--
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    trainer_id INT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES users(user_id) ON DELETE CASCADE
);
ALTER TABLE courses ADD COLUMN course_description TEXT;
INSERT INTO courses (name, trainer_id)
VALUES
('Biology',14);
delete from courses;
select * from courses;

-- grades table --
CREATE TABLE grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    grade VARCHAR(2) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);
INSERT INTO grades (student_id, course_id, grade)
VALUES
(10,21,'A');
SELECT c.name AS course_name, g.grade  -- student dashboard --
                       FROM grades g
                       JOIN courses c ON g.course_id = c.course_id 
                       WHERE g.student_id = 10;
SELECT * FROM grades WHERE student_id =13 AND course_id =8 ;
-- trainer dashboard --
SELECT   
    u.name AS student_name, 
    c.name AS course_name, 
    g.grade, 
    g.student_id, 
    g.course_id
FROM grades g
JOIN users u ON g.student_id = u.user_id
JOIN courses c ON g.course_id = c.course_id
WHERE c.trainer_id = 9;


select * from grades;


-- forum message --
CREATE TABLE forum_messages (
    forumMessages_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
select * from forum_messages;


-- assignment table --
-- CREATE TABLE assignments (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     student_id INT NOT NULL,
--     course_id INT NOT NULL,
--     file_name VARCHAR(255) NOT NULL,
--     submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
--     FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
-- );--
 -- enrollments table --
 CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

SELECT * FROM enrollments;
INSERT INTO enrollments (student_id, course_id)
VALUES
(10,21);

CREATE TABLE forum_history (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each message
    forum_topic_id INT NOT NULL,         -- Foreign Key referencing the forum_topics table
    user_id INT NOT NULL,                -- The ID of the user who posted the message
    message TEXT NOT NULL,               -- The content of the message
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp when the message was posted
    FOREIGN KEY (forum_topic_id) REFERENCES forum_messages(forumMessages_id) ON DELETE CASCADE,  -- Links messages to specific topics
    FOREIGN KEY (user_id) REFERENCES users(user_id)  -- Assuming there is a users table with user IDs
);
select * from forum_history;
alter table enrollments add column grade varchar(10) default 'Not Graded';



