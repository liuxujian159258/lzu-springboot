package boot.service.impl;

import boot.bean.Competition;
import boot.service.CompetitionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CompetitionServiceImplTest {

    @Resource
    private CompetitionService competitionService;

    @org.junit.jupiter.api.Test
    void findAllCompetition() {
        PageHelper.startPage(1,2);
        List<Competition> allCompetition = competitionService.findAllCompetition(0);
        PageInfo pageInfo = new PageInfo(allCompetition);
        System.out.println(pageInfo);
    }

    @org.junit.jupiter.api.Test
    void findCompetitionByCompetitionIdOrCompetitionName() {
        String competitionName = "MODMA";
        Competition competitionByCompetitionIdOrCompetitionName = competitionService.findCompetitionByCompetitionIdOrCompetitionName(null, competitionName);
        System.out.println(competitionByCompetitionIdOrCompetitionName);
    }

    @org.junit.jupiter.api.Test
    void deleteByCompetitionId() {
        boolean b = competitionService.deleteByCompetitionId(10);
        System.out.println(b);
    }

    @org.junit.jupiter.api.Test
    void addCompetition() {
        Competition competition = new Competition();
        competition.setCompetitionName("测试竞赛");
        competition.setCompetitionType(0);
        competition.setCompetitionPeople(1);
        competition.setCompetitionDepartment("信息科学与工程学院");
        competition.setIsDelete(0);
        boolean addCompetition = competitionService.addCompetition(competition);
        System.out.println(addCompetition);
    }

    @org.junit.jupiter.api.Test
    void updateCompetition() {
        Competition competition = new Competition();
        competition.setCompetitionId(11);
        competition.setCompetitionName("测试竞赛213");
        competition.setCompetitionType(0);
        competition.setCompetitionPeople(1);
        competition.setCompetitionDepartment("信息科学与工程学院");
        competition.setIsDelete(0);
        boolean b = competitionService.updateCompetition(competition);
        System.out.println(b);
    }
}
