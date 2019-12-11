import java.sql.*;

public class BuildDatabase{
	public static void main(String[] args){
		String driveName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306?serverTimezone=GMT";
		String user = "root";
		String pwd = "#ASDF1234";
        Connection con = null;
		try {
			Class.forName(driveName);
			con = DriverManager.getConnection(url, user, pwd);
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DROP DATABASE IF EXISTS Management");
			stmt.executeUpdate("CREATE DATABASE Management");
			stmt.executeUpdate("USE Management");
			stmt.executeUpdate("DROP TABLE IF EXISTS students");
			stmt.executeUpdate("DROP TABLE IF EXISTS enrollments");
			stmt.executeUpdate("DROP TABLE IF EXISTS courses");
			stmt.executeUpdate("DROP TABLE IF EXISTS schedules");

			stmt.executeUpdate(
				"CREATE TABLE students ("
				+ "student_id INT NOT NULL AUTO_INCREMENT,"
				+ "student_first_name VARCHAR(50) NOT NULL,"
				+ "student_last_name VARCHAR(50) NOT NULL, PRIMARY KEY (student_id))");

			stmt.executeUpdate(
				"CREATE TABLE courses ("
				+ "course_id INT NOT NULL AUTO_INCREMENT,"
				+ "course_name VARCHAR(50) NOT NULL,"
				+ "course_day VARCHAR(50) NOT NULL,"
				+ "course_time VARCHAR(50) NOT NULL, PRIMARY KEY (course_id))");

			stmt.executeUpdate(
				"CREATE TABLE enrollments ("
				+ "enrollment_id INT AUTO_INCREMENT,"
				+ "student_id INT NOT NULL,"
				+ "course_id INT NOT NULL,"
				+ "CONSTRAINT enrollments_fk_students FOREIGN KEY (student_id) REFERENCES students (student_id),"
				+ "CONSTRAINT enrollments_fk_courses FOREIGN KEY (course_id) REFERENCES courses (course_id), PRIMARY KEY (enrollment_id))");

			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}