package boot.service.impl;

import boot.bean.TeamStudent;
import boot.dao.TeamStudentDao;
import boot.service.TeamStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeamStudentServiceImpl implements TeamStudentService {

    @Resource
    private TeamStudentDao teamStudentDao;

    @Override
    public List<TeamStudent> findAllTeamStudent(int teamId) {
        return teamStudentDao.findAllTeamStudent(teamId);
    }

    @Override
    public boolean deleteByTeamIdStudentNumber(Integer teamId, String studentNumber) {
        int rows = teamStudentDao.deleteByTeamIdStudentNumber(teamId, studentNumber);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTeamStudent(TeamStudent teamStudent) {
        int rows = teamStudentDao.addTeamStudent(teamStudent);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeamStudent(TeamStudent teamStudent) {
        int rows = teamStudentDao.updateTeamStudent(teamStudent);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCaptain(Integer teamId) {
        int i = teamStudentDao.removeCaptain(teamId);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean studentCaptain(Integer teamId, String studentNumber) {
        int i = teamStudentDao.studentCaptain(teamId, studentNumber);
        if (i>0) {
            return true;
        }
        return false;
    }
}
