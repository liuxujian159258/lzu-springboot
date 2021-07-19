package boot.service.impl;

import boot.bean.Team;
import boot.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TeamServiceImplTest {

    @Resource
    private TeamService teamService;

    @Test
    void findAllTeam() {
        List<Team> allTeam = teamService.findAllTeam();
        System.out.println(allTeam);
    }

    @Test
    void findTeamByTeamIdOrName() {
        System.out.println(teamService.findTeamByTeamIdOrName(null, "刘绪俭"));
    }

    @Test
    void deleteTeamByTeamId() {
        System.out.println(teamService.deleteTeamByTeamId(4));
    }

    @Test
    void addTeam() {
        Team team = new Team();
        team.setTeamName("小临界");
        System.out.println(teamService.addTeam(team));
    }

    @Test
    void updateTeam() {
        Team team = new Team(5, "小临界214124");
        System.out.println(teamService.updateTeam(team));
    }

    @Test
    void existTeam () {
        System.out.println(teamService.existTeam("刘绪俭测试队"));
    }
}
