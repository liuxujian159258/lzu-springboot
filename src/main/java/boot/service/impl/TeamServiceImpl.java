package boot.service.impl;

import boot.bean.Team;
import boot.dao.TeamDao;
import boot.service.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Resource
    private TeamDao teamDao;

    @Override
    public List<Team> findAllTeam() {
        return teamDao.findAllTeam();
    }

    @Override
    public List<Team> findTeamByTeamIdOrName(Integer teamId, String teamName) {
        return teamDao.findTeamByTeamIdOrName(teamId, teamName);
    }

    @Override
    public boolean deleteTeamByTeamId(int teamId) {
        int rows = teamDao.deleteTeamByTeamId(teamId);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTeam(Team team) {
        int rows = teamDao.addTeam(team);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeam(Team team) {
        int rows = teamDao.updateTeam(team);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    // 判断团队是否存在
    @Override
    public boolean existTeam(String teamName) {
        Team team = teamDao.existTeam(teamName);
        if (team != null) {
            return true;
        }
        return false;
    }
}
