package boot.service.impl;

import boot.bean.Competition;
import boot.dao.CompetitionDao;
import boot.service.CompetitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService   {

    @Resource
    private CompetitionDao competitionDao;

    @Override
    public List<Competition> findAllCompetition(int competitionType) {
        return competitionDao.findAllCompetition(competitionType);
    }

    @Override
    public Competition findCompetitionByCompetitionIdOrCompetitionName(Integer competitionId, String competitionName) {
        return competitionDao.findCompetitionByCompetitionIdOrCompetitionName(competitionId, competitionName);
    }

    @Override
    public boolean deleteByCompetitionId(int CompetitionId) {
        int rows = competitionDao.deleteByCompetitionId(CompetitionId);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addCompetition(Competition competition) {
        int rows = competitionDao.addCompetition(competition);
        if (rows > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompetition(Competition competition) {
        int rows = competitionDao.updateCompetition(competition);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
