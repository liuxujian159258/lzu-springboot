package boot.service.impl;

import boot.bean.ScoreTeam;
import boot.dao.ScoreTeamDao;
import boot.service.ScoreTeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreTeamServiceImpl implements ScoreTeamService {

    @Resource
    private ScoreTeamDao scoreTeamDao;

    @Override
    public List<ScoreTeam> findAllScoreTeam(Integer teamId, Integer competitionId, Integer teamTime) {
        return scoreTeamDao.findAllScoreTeam(teamId, competitionId, teamTime);
    }

    @Override
    public boolean deleteByTeamIdCompetitionIdTeamTime(Integer teamId, Integer competitionId, Integer teamTime) {
        int rows = scoreTeamDao.deleteByTeamIdCompetitionIdTeamTime(teamId, competitionId, teamTime);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTeamScore(ScoreTeam scoreTeam) {
        int rows = scoreTeamDao.addTeamScore(scoreTeam);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeamScore(ScoreTeam scoreTeam) {
        int rows = scoreTeamDao.updateTeamScore(scoreTeam);
        if (rows > 0) {
            return true;
        }
        return false;
    }
}
