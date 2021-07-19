package boot.service.impl;

import boot.bean.ScoreStudent;
import boot.dao.ScoreStudentDao;
import boot.service.ScoreStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreStudentServiceImpl implements ScoreStudentService {

    @Resource
    private ScoreStudentDao scoreStudentDao;

    @Override
    public List<ScoreStudent> findAllScoreUser(String studentNumber, Integer competitionId, Integer studentTime) {
        return scoreStudentDao.findAllScoreUser(studentNumber, competitionId, studentTime);
    }

    @Override
    public boolean deleteByStudentNumberCompetitionIdUserTime(String studentNumber, Integer competitionId, Integer studentTime) {
        int rows = scoreStudentDao.deleteByStudentNumberCompetitionIdUserTime(studentNumber, competitionId, studentTime);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addScoreUser(ScoreStudent scoreStudent) {
        int rows = scoreStudentDao.addScoreUser(scoreStudent);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateScoreUser(ScoreStudent scoreStudent) {
        int rows = scoreStudentDao.updateScoreUser(scoreStudent);
        if (rows >0 ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByStudentNumber(String studentNumber) {
        int i = scoreStudentDao.deleteByStudentNumber(studentNumber);
        if (i>0) {
            return true;
        }
        return false;
    }
}
