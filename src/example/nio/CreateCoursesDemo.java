package example.nio;

import example.nio.entities.Course;
import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try(factory;session){
            session.beginTransaction();

            // get the instructor
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            // create some courses
            Course course1 = new Course("Excel Masterclass");
            Course course2 = new Course("You and your Tableau!");

            // add courses to the instructor
            instructor.addCourse(course1);
            instructor.addCourse(course2);

            // save courses
            session.save(course1);
            session.save(course2);

            // commit
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
