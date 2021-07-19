package boot.service.impl;

import boot.bean.ScoreTeam;
import boot.service.ScoreTeamService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ScoreTeamServiceImplTest {

    @Resource
    private ScoreTeamService scoreTeamService;

    @Test
    void findAllScoreTeam() {
        List<ScoreTeam> allScoreTeam = scoreTeamService.findAllScoreTeam(0, 0, 2017);
        System.out.println(allScoreTeam);
    }

    @Test
    void deleteByTeamIdCompetitionIdTeamTime() {
        boolean b = scoreTeamService.deleteByTeamIdCompetitionIdTeamTime(1, 2, 2020);
        System.out.println(b);
    }

    @Test
    void addTeamScore() {
        ScoreTeam scoreTeam = new ScoreTeam(1,2,"群体时空数据的语义分析" ,99.17,2020);
        boolean b = scoreTeamService.addTeamScore(scoreTeam);
        System.out.println(b);
    }

    @Test
    void updateTeamScore() {
        ScoreTeam scoreTeam = new ScoreTeam(1,2, "群体时空数据的语义分析", 55.55,2020);
        System.out.println(scoreTeamService.updateTeamScore(scoreTeam));
    }
}
