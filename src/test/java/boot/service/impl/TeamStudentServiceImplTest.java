package boot.service.impl;

import boot.bean.TeamStudent;
import boot.service.TeamStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TeamStudentServiceImplTest {

    @Resource
    private TeamStudentService teamStudentService;

    @Test
    void findAllTeamStudent() {
        List<TeamStudent> allTeamStudent = teamStudentService.findAllTeamStudent(0);
        System.out.println(allTeamStudent);
    }

    @Test
    void deleteByTeamIdStudentNumber() {
        System.out.println(teamStudentService.deleteByTeamIdStudentNumber(0, "320170940899"));
    }

    @Test
    void addTeamStudent() {
        TeamStudent teamStudent = new TeamStudent(0,"320170940899","刘绪俭99",0);
        System.out.println(teamStudentService.addTeamStudent(teamStudent));
    }

    @Test
    void updateTeamStudent() {
        TeamStudent teamStudent = new TeamStudent(0,"320170940899", "刘绪俭99", 1);
        System.out.println(teamStudentService.updateTeamStudent(teamStudent));
    }

    @Test
    void adminTeamsTeamIdStudentNumberPut () {
        System.out.println(teamStudentService.removeCaptain(14));
        System.out.println(teamStudentService.studentCaptain(14, "3201709408407"));
    }
}
