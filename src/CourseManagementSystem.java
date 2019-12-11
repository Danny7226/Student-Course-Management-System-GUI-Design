import java.sql.*;
import java.util.*;
import javafx.beans.binding.Bindings;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.layout.Background;
import javafx.util.converter.*;
import javafx.util.*;
import javafx.animation.*;
import javafx.scene.effect.*;
import javafx.collections.*; 
import java.io.*; 
import javafx.stage.FileChooser;
import javafx.scene.image.*;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.*;

public class CourseManagementSystem extends Application{
    TextArea textarea;
    Stage stage;
    @Override
    public void init() {

    }
    @Override
    public void start(Stage stage){
        this.stage = stage;
        String driveName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/Management?serverTimezone=GMT";
        String user = "root";
        String pwd = "#ASDF1234";   

        // GUI setup 
        Button newStudent = new Button();
        newStudent.setPrefWidth(300);
        newStudent.setText("Introduce a student");
        Button newCourse = new Button();
        newCourse.setText("Introduce a course");
        newCourse.setPrefWidth(300);
        Button enrollStudent = new Button();
        enrollStudent.setText("Enroll Student");
        enrollStudent.setPrefWidth(300);
        Button courseCohort = new Button();
        courseCohort.setText("Course Cohort");
        courseCohort.setPrefWidth(300);
        Button studentSchedule = new Button();
        studentSchedule.setText("Student Schedule"); 
        studentSchedule.setPrefWidth(300); 
        Button studentDaySchedule = new Button();
        studentDaySchedule.setPrefWidth(300);
        studentDaySchedule.setText("Student Schedule on Given Day");
        Button allStudents = new Button();
        allStudents.setText("All Students");
        allStudents.setPrefWidth(150);
        Button allCourses = new Button();
        allCourses.setText("All Courses");
        allCourses.setPrefWidth(150);
        Button exitProgram = new Button();
        exitProgram.setText("Exit Program");
        exitProgram.setPrefWidth(300); 

        VBox left = new VBox();

        HBox line1 = new HBox();
        TextField tf11 = new TextField();
        TextField tf12 = new TextField();
        tf11.setPromptText("first name");
        tf11.setPrefWidth(150);
        tf12.setPromptText("last name");
        tf12.setPrefWidth(150);        
        line1.getChildren().addAll(tf11,tf12);
        HBox line2 = new HBox();
        TextField tf21 = new TextField();
        TextField tf22 = new TextField();
        TextField tf23 = new TextField();
        tf21.setPromptText("name");
        tf21.setPrefWidth(100);
        tf22.setPromptText("day");
        tf22.setPrefWidth(100); 
        tf23.setPromptText("time");
        tf23.setPrefWidth(100);               
        line2.getChildren().addAll(tf21, tf22, tf23);        
        HBox line3 = new HBox();
        TextField tf31 = new TextField();
        TextField tf32 = new TextField();
        tf31.setPromptText("Student ID");
        tf31.setPrefWidth(150);
        tf32.setPromptText("Course ID");
        tf32.setPrefWidth(150);              
        line3.getChildren().addAll(tf31, tf32);     
        HBox line4 = new HBox();
        TextField tf4 = new TextField();
        tf4.setPromptText("Course ID");
        tf4.setPrefWidth(300);            
        line4.getChildren().addAll(tf4);       
        HBox line5 = new HBox();
        TextField tf5 = new TextField();
        tf5.setPromptText("Student ID");
        tf5.setPrefWidth(300);            
        line5.getChildren().addAll(tf5);                   
        HBox line6 = new HBox();
        TextField tf61 = new TextField();
        TextField tf62 = new TextField();
        tf61.setPromptText("Student ID");
        tf61.setPrefWidth(150);
        tf62.setPromptText("Day");
        tf62.setPrefWidth(150);              
        line6.getChildren().addAll(tf61, tf62);         
        HBox line7 = new HBox();
        line7.getChildren().addAll(allStudents, allCourses);
        HBox line8 = new HBox();
        line8.getChildren().addAll(exitProgram);

        left.getChildren().addAll(newStudent, line1, newCourse, line2, enrollStudent, line3, courseCohort, line4, studentSchedule, line5, studentDaySchedule, line6, line7, line8);
        HBox root = new HBox();
        textarea = new TextArea();
        textarea.setPrefWidth(300);
        textarea.setMaxHeight(550);
        root.getChildren().addAll(left, textarea);
        root.setMargin(left, new Insets(100,0,10,50));
        left.setMinWidth(150);
        root.setMargin(textarea, new Insets(50,30,0,50));
        Scene scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.setTitle("Student_Course Management Syste");             
        stage.show();        
        try {
            Class.forName(driveName);
            Connection con = DriverManager.getConnection(url, user, pwd);  
     
        // on new student click
        newStudent.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Statement stmt = con.createStatement();  
                    stmt.executeUpdate("USE Management");  
                    String first_name = tf11.getText();
                    String last_name = tf12.getText();
                    if(first_name.isEmpty() || last_name.isEmpty()){
                        System.out.println("Invalid input");
                        return;
                    }             
                    stmt.executeUpdate(
                    "INSERT INTO students "
                    + "values (DEFAULT, '" + first_name + "', '" + last_name + "')");
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("\nStudent introduced.\n");                
            }
        });  
        newCourse.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Statement stmt = con.createStatement();  
                    stmt.executeUpdate("USE Management");         
                    String course_name = tf21.getText();          
                    String day = tf22.getText().toLowerCase();
                    String time = tf23.getText();
                    if(course_name.isEmpty() || day.isEmpty() || time.isEmpty()){
                        System.out.println("Invalid input");
                        return;
                    }        
                    stmt.executeUpdate(
                    "INSERT INTO courses "
                    + "values (default, '" + course_name + "', '" + day +  "', '" + time + "')");
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("\nCourse introduced.\n");                
            }
        });  
        enrollStudent.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Statement stmt = con.createStatement();  
                    stmt.executeUpdate("USE Management");         
                    String student_id = tf31.getText();
                    String course_id = tf32.getText();
                    if(student_id.isEmpty() || course_id.isEmpty()){
                        System.out.println("Invalid input");
                        return;                        
                    }                               
                    stmt.executeUpdate(
                        "INSERT INTO enrollments "
                        + "values (DEFAULT, " + student_id + ", " + course_id + ")");
                    stmt.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
                System.out.println("\nStudent enrolled.\n");                
            }
        });  
        courseCohort.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("USE Management");
                    String course_id = tf4.getText();
                    if(course_id.isEmpty()){
                        System.out.println("Invalid input");
                        return;                        
                    }
                    ResultSet rs = stmt.executeQuery("SELECT s.student_id, s.student_first_name, s.student_last_name "
                        + "FROM students s JOIN enrollments e ON s.student_id = e.student_id "
                        + "JOIN courses c ON e.course_id = c.course_id "
                        + "WHERE c.course_id = " + course_id + " ORDER BY s.student_id");
                    StringBuilder sb = new StringBuilder();
                    while(rs.next()){
                        int student_id = rs.getInt("student_id");
                        String first_name = rs.getString("student_first_name");
                        String last_name = rs.getString("student_last_name");
                        sb.append("student_id: " + student_id + "\nfirst_name: " + first_name + "\nlast_name: " + last_name + "\n\n");
                    }
                    textarea.setText(sb.toString());
                    stmt.close(); 
                } catch(SQLException e) {e.printStackTrace();}                
            }
        });  
        studentSchedule.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("USE Management");
                    String student_id = tf5.getText();
                    if(student_id.isEmpty()){
                        System.out.println("Invalid input");
                        return;                        
                    }                    
                    ResultSet rs = stmt.executeQuery("SELECT c.course_id, c.course_name, c.course_day, c.course_time FROM students s JOIN enrollments e ON s.student_id = e.student_id JOIN courses c ON e.course_id = c.course_id "
                        + "WHERE s.student_id = " + student_id + " ORDER BY c.course_id");
                    StringBuilder sb = new StringBuilder();                    
                    while(rs.next()){
                        int course_id = rs.getInt("course_id");
                        String course_name = rs.getString("course_name");
                        String day = rs.getString("course_day");
                        String time = rs.getString("course_time");
                        sb.append("course_id: " + course_id + "\ncourse_name: " + course_name + "\ncourse_day: " + day + "\ncourse_time: " + time + "\n\n");
                    }
                    textarea.setText(sb.toString());
                    stmt.close();          
                } catch(SQLException e) {e.printStackTrace();}                
            }
        });  
        studentDaySchedule.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("USE Management");
                    String student_id = tf61.getText();
                    String day = tf62.getText().toLowerCase();
                    if(student_id.isEmpty() || day.isEmpty()){
                        System.out.println("Invalid input");
                        return;                        
                    }
                    StringBuilder sb = new StringBuilder();                            
                    ResultSet rs = stmt.executeQuery("SELECT c.course_id, c.course_name, c.course_time FROM students s JOIN enrollments e ON s.student_id = e.student_id JOIN courses c ON e.course_id = c.course_id "
                        + "WHERE s.student_id = " + student_id + " AND c.course_day LIKE '%" + day + "%' ORDER BY c.course_id");
                    while(rs.next()){
                        int course_id = rs.getInt("course_id");
                        String course_name = rs.getString("course_name");
                        String course_time = rs.getString("course_time");
                        sb.append("course_id: " + course_id + "\ncourse_name: " + course_name + "\ncourse_time: " + course_time + "\n\n");
                    }
                    textarea.setText(sb.toString());
                    stmt.close();
                } catch(SQLException e) {e.printStackTrace();}                
            }
        });  
        allStudents.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Statement stmt = con.createStatement();  
                    stmt.executeUpdate("USE Management");         
                    ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                    StringBuilder sb = new StringBuilder();
                    while(rs.next()){
                        int student_id = rs.getInt("student_id");
                        String student_first_name = rs.getString("student_first_name");
                        String student_last_name = rs.getString("student_last_name");
                        sb.append("student_id: " + student_id + "\nfirst_name: " + student_first_name + "\nlast_name: " + student_last_name + "\n\n");
                    }
                    textarea.setText(sb.toString());
                    stmt.close();            
                } catch(SQLException e) {e.printStackTrace();}                
            }
        });     
        allCourses.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Statement stmt = con.createStatement();  
                    stmt.executeUpdate("USE Management");         
                    ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
                    StringBuilder sb = new StringBuilder();
                    while(rs.next()){
                        int course_id = rs.getInt("course_id");
                        String course_name = rs.getString("course_name");
                        String day = rs.getString("course_day");
                        String time = rs.getString("course_time");
                        sb.append("course_id: " + course_id + "\ncourse_name: " + course_name + "\ncourse_day: " + day + "\ncourse_time: " + time + "\n\n");
                    }
                    textarea.setText(sb.toString());
                    stmt.close();    
                } catch(SQLException e) {e.printStackTrace();}
            }
        });                                                                 
        exitProgram.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                stop();
            }
        });   
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    @Override
    public void stop() {
        stage.close();
        System.out.println("Closing......");
    }

    public static void main(String[] args) {
        Application.launch(CourseManagementSystem.class, args);
    }       

    // private static void introduceStudent(Scanner sc, Connection con){     
    //     try {
    //         Statement stmt = con.createStatement();  
    //         stmt.executeUpdate("USE Management");  
    //         System.out.print("Type in first_name of the student: ");
    //         String first_name = sc.nextLine().toLowerCase();
    //         System.out.print("Type in last_name of the student: ");
    //         String last_name = sc.nextLine().toLowerCase();               
    //         stmt.executeUpdate(
    //         "INSERT INTO students "
    //         + "values (DEFAULT, '" + first_name + "', '" + last_name + "')");
    //         stmt.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println("\nStudent introduced.\n");
    // }

    // private static void introduceCourses(Scanner sc, Connection con){
    //     // System.out.println("INSERT INTO courses "
    //     //     + "values (default, " + course_id + ", '"+ course_name + "', '" + day +  "', '" + time + "')");
    //     try {
    //         Statement stmt = con.createStatement();  
    //         stmt.executeUpdate("USE Management");         
    //         System.out.print("Type in the name of the course: ");
    //         String course_name = sc.nextLine();          
    //         System.out.print("Schedule a day for the course: ");
    //         String day = sc.nextLine().toLowerCase();
    //         System.out.print("Schedule a time for the course: ");
    //         String time = sc.nextLine().toLowerCase();            
    //         stmt.executeUpdate(
    //         "INSERT INTO courses "
    //         + "values (default, '" + course_name + "', '" + day +  "', '" + time + "')");
    //         stmt.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println("\nCourse introduced.\n");
    // }

    // private static void enrollStudentToCourse(Scanner sc, Connection con){
    //     try{
    //         Statement stmt = con.createStatement();  
    //         stmt.executeUpdate("USE Management");         
    //         System.out.print("Type in the student_id of enrolling student: ");
    //         int student_id = Integer.valueOf(sc.nextLine().toLowerCase());
    //         System.out.print("Type in the course_id of enrolling course: ");
    //         int course_id = Integer.valueOf(sc.nextLine().toLowerCase());            
    //         stmt.executeUpdate(
    //             "INSERT INTO enrollments "
    //             + "values (DEFAULT, " + student_id + ", " + course_id + ")");
    //         stmt.close();
    //     } catch(SQLException e){
    //         e.printStackTrace();
    //     }
    //     System.out.println("\nStudent enrolled.\n");
    // }

    // private static void showStudents(Connection con){
    //     try{
    //         Statement stmt = con.createStatement();  
    //         stmt.executeUpdate("USE Management");         
    //         ResultSet rs = stmt.executeQuery("SELECT * FROM students");
    //         while(rs.next()){
    //             int student_id = rs.getInt("student_id");
    //             String student_first_name = rs.getString("student_first_name");
    //             String student_last_name = rs.getString("student_last_name");
    //             System.out.println("student_id: " + student_id + " first_name: " + student_first_name + " last_name: " + student_last_name);
    //         }
    //         stmt.close();            
    //     } catch(SQLException e) {e.printStackTrace();}
    // }

    // private static void showCourses(Connection con){
    //     try{
    //         Statement stmt = con.createStatement();  
    //         stmt.executeUpdate("USE Management");         
    //         ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
    //         while(rs.next()){
    //             int course_id = rs.getInt("course_id");
    //             String course_name = rs.getString("course_name");
    //             String day = rs.getString("course_day");
    //             String time = rs.getString("course_time");               
    //             System.out.println("course_id: " + course_id + " course_name: " + course_name + " course_day: " + day + " course_time: " + time);
    //         }
    //         stmt.close();    
    //     } catch(SQLException e) {e.printStackTrace();}
    // }

    // private static void queryStudentsInCourse(Scanner sc, Connection con){
    //     try{
    //         Statement stmt = con.createStatement();
    //         stmt.executeUpdate("USE Management");
    //         System.out.print("Type in course_id: ");
    //         int course_id = Integer.valueOf(sc.nextLine().toLowerCase());
    //         ResultSet rs = stmt.executeQuery("SELECT s.student_id, s.student_first_name, s.student_last_name "
    //             + "FROM students s JOIN enrollments e ON s.student_id = e.student_id "
    //             + "JOIN courses c ON e.course_id = c.course_id "
    //             + "WHERE c.course_id = " + course_id + " ORDER BY s.student_id");
    //         while(rs.next()){
    //             int student_id = rs.getInt("student_id");
    //             String first_name = rs.getString("student_first_name");
    //             String last_name = rs.getString("student_last_name");
    //             System.out.println("student_id: " + student_id + " first_name: " + first_name + " last_name: " + last_name);
    //         }
    //         stmt.close(); 
    //     } catch(SQLException e) {e.printStackTrace();}
    // }

    // private static void queryCoursesOfStudent(Scanner sc, Connection con){
    //     try{
    //         Statement stmt = con.createStatement();
    //         stmt.executeUpdate("USE Management");
    //         System.out.print("Type in student_id: ");
    //         int student_id = Integer.valueOf(sc.nextLine().toLowerCase());
    //         ResultSet rs = stmt.executeQuery("SELECT c.course_id, c.course_name, c.course_day, c.course_time FROM students s JOIN enrollments e ON s.student_id = e.student_id JOIN courses c ON e.course_id = c.course_id "
    //             + "WHERE s.student_id = " + student_id + " ORDER BY c.course_id");
    //         while(rs.next()){
    //             int course_id = rs.getInt("course_id");
    //             String course_name = rs.getString("course_name");
    //             String day = rs.getString("course_day");
    //             String time = rs.getString("course_time");
    //             System.out.println("course_id: " + course_id + " course_name: " + course_name + " course_day: " + day + " course_time: " + time);
    //         }
    //         stmt.close();          
    //     } catch(SQLException e) {e.printStackTrace();}
    // }

    // private static void querySchedule(Scanner sc, Connection con){
    //     try{
    //         Statement stmt = con.createStatement();
    //         stmt.executeUpdate("USE Management");
    //         System.out.print("Type in student_id: ");
    //         int student_id = Integer.valueOf(sc.nextLine().toLowerCase());
    //         System.out.println("Type in a specific day: ");
    //         String day = sc.nextLine().toLowerCase();
    //         ResultSet rs = stmt.executeQuery("SELECT c.course_id, c.course_name, c.course_time FROM students s JOIN enrollments e ON s.student_id = e.student_id JOIN courses c ON e.course_id = c.course_id "
    //             + "WHERE s.student_id = " + student_id + " AND c.course_day LIKE '%" + day + "%' ORDER BY c.course_id");
    //         while(rs.next()){
    //             int course_id = rs.getInt("course_id");
    //             String course_name = rs.getString("course_name");
    //             String course_time = rs.getString("course_time");
    //             System.out.println("course_id: " + course_id + " course_name: " + course_name + " course_time: " + course_time);
    //         }
    //         stmt.close();
    //     } catch(SQLException e) {e.printStackTrace();}
    // }
}