package boot.service.impl;

import boot.bean.ScoreStudent;
import boot.service.ScoreStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ScoreStudentServiceImplTest {

    @Resource
    private ScoreStudentService scoreStudentService;

    @Test
    void findAllScoreUser() {
        List<ScoreStudent> allScoreStudent = scoreStudentService.findAllScoreUser(null, 0, null);
        System.out.println(allScoreStudent);
    }

    @Test
    void deleteByStudentNumberCompetitionIdUserTime() {
        System.out.println(scoreStudentService.deleteByStudentNumberCompetitionIdUserTime("320170940845", 9, 2017));
    }

    @Test
    void addScoreUser() {
        ScoreStudent scoreStudent = new ScoreStudent("320170940846",9,"IET全球英语演讲竞赛兰州大学选拔赛",99.8,2017);
        System.out.println(scoreStudentService.addScoreUser(scoreStudent));
    }

    @Test
    void updateScoreUser() {
        ScoreStudent scoreStudent = new ScoreStudent("320170940845",9,"IET全球英语演讲竞赛兰州大学选拔赛", 55.55,2017);
        System.out.println(scoreStudentService.updateScoreUser(scoreStudent));
    }

    @Test
    void studentNumber () {
        boolean b = scoreStudentService.deleteByStudentNumber("320170940846");
        System.out.println(b);
    }
}
