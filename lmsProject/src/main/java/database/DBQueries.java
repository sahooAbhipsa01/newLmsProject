package database;

public class DBQueries {
	//users table queries
	public static final String insertUser = "INSERT INTO users (name, email, gender, password, role) VALUES (?, ?, ?, ?, ?)";
    public static final String getUserEmailAndPassword = "SELECT * FROM users WHERE email = ? AND password = ?";
    public static final String getUserByUserId = "SELECT * FROM users WHERE user_id = ?";
    public static final String getUserByUserEmail = "SELECT * FROM users WHERE email = ?";
    public static final String getAllUsers = "SELECT * FROM users";
    public static final String deleteUser = "DELETE FROM users WHERE id = ?";
    
    
    
    //courses table queries
    public static final String insertCourse = "INSERT INTO courses (name, trainer_id) VALUES (?, ?)";
    public static final String getCourseByCourseId = "SELECT * FROM courses WHERE course_id = ?";
    public static final String getcourseByTrainerId = "SELECT * FROM courses WHERE trainer_id=?";
    public static final String getAllCourses = "select c.course_id, c.name as course_name ,u.name as trainer_name from courses c join users u on c.trainer_id =u.user_id";
    public static final String getCourseId="select c.course_id , c.name,u.name as trainer_name from courses c join users u on c.trainer_id = u.user_id";
    public static final String updateCoursesByCousrseId = "UPDATE courses SET name = ? WHERE course_id = ?";
    public static final String deleteCourseById = "DELETE FROM courses WHERE course_id = ?";
    public static final String insertCoursesWithDescription="INSERT INTO courses(name, trainer_id, course_description) VALUES (?, ?, ?)";
    
    //Enrollment Queries
    public static final String insertEnrollments = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
    public static final String deleteEnrollments = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";
    public static final String getEnrollmentByStudentId = "SELECT * FROM enrollments WHERE student_id = ?";
    public static final String getEnrollmentByCourseid = "SELECT * FROM enrollments WHERE course_id = ?";
    
    //grade queries
    public static final String insertGrade = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
    public static final String UPDATE_GRADE = "UPDATE student_courses SET grade = ? WHERE student_id = ?";
    public static final String getGradesByStudentId = "SELECT c.name AS course_name, g.grade FROM grades g JOIN courses c ON g.course_id = c.course_id WHERE g.student_id = ?";
    
    public static final String getGradesByCourseId="SELECT u.name AS student_name, g.grade FROM grades g JOIN users u ON g.student_id = u.user_id WHERE g.course_id = ?";
    
    //// Forum Queries
    public static final String insertForumMessgaes = "INSERT INTO forum_messages (user_id, message) VALUES (?, ?)";
    public static final String deleteForumMessages = "DELETE FROM forum_messages WHERE forumMessages_id = ?";
    public static final String getAllForumMessages = "SELECT * FROM forum_messages ORDER BY created_at DESC";

   

}
