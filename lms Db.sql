
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


-- forum message --
CREATE TABLE forum_messages (
    forumMessages_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

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
-- forum messages--
CREATE TABLE forum_messages (
    forumMessages_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each message
    user_id INT NOT NULL, -- Foreign key for the user who posted the message
    message TEXT NOT NULL, -- The content of the message
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the message was created
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE -- Relationship with the users table
);

