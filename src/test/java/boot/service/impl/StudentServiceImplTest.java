package boot.service.impl;

import boot.bean.Student;
import boot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class StudentServiceImplTest {

    @Resource
    private StudentService studentService;

    @Test
    void findAllStudent() {
        List<Student> allStudent = studentService.findAllStudent(0, "信息科学与工程学院", null, 2017);
        System.out.println(allStudent);
    }

    @Test
    void findStudentByStudentNumberOrName() {
        System.out.println(studentService.findStudentByStudentNumberOrName("320170940841", null));
    }

    @Test
    void deleteStudentByStudentNumber() {
        System.out.println(studentService.deleteStudentByStudentNumber("320170940845"));
    }

    @Test
    void addStudent() {
        Student student = new Student("320170940846","1998096","来参加5",0,"土木学院","2017土木",2017,"234324@qq.com","17325013945");
        boolean b = studentService.addStudent(student);
        System.out.println(b);
    }

    @Test
    void updateStudent() {
        Student student = new Student("320170940845","1998095","来参加1323",0,"土木学院","2017土木",2017,"234324@qq.com","17325013945");
        System.out.println(studentService.updateStudent(student));
    }

    @Test
    void isTeamCaptain() {
        System.out.println(studentService.isTeamCaptain(0, "320170940841"));
    }

    @Test
    void studentScoresStudentNumberGet () {
        System.out.println(studentService.studentScoresStudentNumberGet("320170940841"));
        System.out.println(studentService.studentTeamsScoresStudentNumberGet("320170940844"));
    }

    @Test
    void studentTeamsMyTeams () {
        System.out.println(studentService.studentTeamsMyTeams("320170940844"));
        System.out.println(studentService.studentTeamsMyJionTeams("320170940845"));
    }
}
