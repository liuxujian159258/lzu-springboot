package boot.service.impl;

import boot.bean.*;
import boot.dao.AdminDao;
import boot.dao.StudentDao;
import boot.dao.TeamStudentDao;
import boot.exception.AffectedRowsException;
import boot.service.StudentService;
import boot.vo.admin.CompetitionsHtmlVO;
import boot.vo.student.StudentsMyJionTeamsVO;
import boot.vo.student.StudentsMyTeamsVO;
import boot.vo.student.StudentsScoreVO;
import boot.vo.student.TeamsScoreVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;
    @Resource
    private AdminDao adminDao;
    @Resource
    private TeamStudentDao teamStudentDao;

    @Override
    public List<Student> findAllStudent(Integer studentSex, String studentCollege, String studentClass, Integer studentGrade) {
        return studentDao.findAllStudent(studentSex, studentCollege, studentClass, studentGrade);
    }

    @Override
    public Student findStudentByStudentNumberOrName(String studentNumber, String studentName) {
        return studentDao.findStudentByStudentNumberOrName(studentNumber, studentName);
    }

    @Override
    public boolean deleteStudentByStudentNumber(String studentNumber) {
        int rows = studentDao.deleteStudentByStudentNumber(studentNumber);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        int rows = studentDao.updateStudent(student);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        int rows = studentDao.addStudent(student);
        if (rows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String existStudent(String studentNumber, String studentPassword) {
        return studentDao.existStudent(studentNumber, studentPassword);

    }

    @Override
    public boolean updateStudentPassword(String studentNumber, String studentPassword) {
        if (studentDao.updateStudentPassword(studentNumber, studentPassword)>0) {
            return true;
        }
        return false;
    }

    @Override
    public Student studentInformationGet(String studentNumber) {
        return studentDao.studentInformationGet(studentNumber);
    }

    @Override
    public boolean studentInformationsPut(Student student) {
        if (studentDao.studentInformationsPut(student)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean teamExistOrDismiss(Integer teamId) {
        if (studentDao.teamExistOrDismiss(teamId)>0) {
            return true;
        }
        return false;
    }

    @Override
    public String studentEmailGet(String studentNumber) {
        return studentDao.studentEmailGet(studentNumber);
    }

    @Override
    public CompetitionEmail competitionEmailGet(Integer competitionId) {
        return studentDao.competitionEmailGet(competitionId);
    }

    @Override
    public List<HashMap<String, Object>> studentCompetitionsGet(Integer competitionId, Integer competitionType) {
        return studentDao.studentCompetitionsGet(competitionId, competitionType);
    }

    @Override
    public List<CompetitionsHtmlVO> studentCompetitionsGetAll() {
        return studentDao.studentCompetitionsGetAll();
    }

    @Override
    public boolean isTeamCaptain(Integer teamId, String studentNumber) {
        String teamCaptain = studentDao.isTeamCaptain(teamId, studentNumber);
        if (teamCaptain != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean teamPeopleAndCompetitionPeople(Integer teamId, Integer competitionId) {
        if (studentDao.teamPeopleAndCompetitionPeople(teamId, competitionId)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean studentSignUpPerson(ScoreStudent scoreStudent) {
        if (studentDao.studentSignUpPerson(scoreStudent)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean studentSignUpTeam(ScoreTeam scoreTeam) {
        if (studentDao.studentSignUpTeam(scoreTeam)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean studentCancelPerson(String studentNumber, Integer competitionId, Integer studentTime) {
        if (studentDao.studentCancelPerson(studentNumber, competitionId, studentTime) >0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean studentCancelTeam(Integer teamId, Integer competitionId, Integer teamTime) {
        if (studentDao.studentCancelTeam(teamId, competitionId, teamTime) >0 ){
            return true;
        }
        return false;
    }

    @Override
    public List<StudentsScoreVO> studentScoresStudentNumberGet(String studentNumber) {
        return studentDao.studentScoresStudentNumberGet(studentNumber);
    }

    @Override
    public List<TeamsScoreVO> studentTeamsScoresStudentNumberGet(String studentNumber) {
        return studentDao.studentTeamsScoresStudentNumberGet(studentNumber);
    }

    @Override
    public String studentNameByStudentNumber(String studentNumber) {
        return studentDao.studentNameByStudentNumber(studentNumber);
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean studentTeamsStudentsPost(List<TeamStudent> teamStudents) {
        for (TeamStudent teamStudent : teamStudents) {
            int j = teamStudentDao.addTeamStudent(teamStudent);
            int i = adminDao.adminTeamsUpdatePeople(teamStudent.getTeamId());
            if ( j==0 || i==0){
                throw new AffectedRowsException("此团队成员已经存在");
            }
        }
        return true;
    }

    @Override
    public List<StudentsMyTeamsVO> studentTeamsMyTeams(String studentNumber) {
        return studentDao.studentTeamsMyTeams(studentNumber);
    }

    @Override
    public List<StudentsMyJionTeamsVO> studentTeamsMyJionTeams(String studentNumber) {
        return studentDao.studentTeamsMyJionTeams(studentNumber);
    }

    @Override
    public boolean removeTeamCaptain(Integer teamId, String studentNumber) {
        if ( studentDao.removeTeamCaptain(teamId, studentNumber)>0 ){
            return true;
        }
        return false;
    }

    @Override
    public boolean addTeamCaptain(Integer teamId, String captainStudentNumber) {
        if ( studentDao.addTeamCaptain(teamId, captainStudentNumber)>0 ) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean studentTeamsCaptainPut(Integer teamId, String studentNumber, String captainStudentNumber) {
        if (studentDao.removeTeamCaptain(teamId, studentNumber)>0&& studentDao.addTeamCaptain(teamId, captainStudentNumber)>0){
            return true;
        }else {
            throw new AffectedRowsException();
        }
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean studentTeamsStudentsDelete(Integer teamId, String studentNumber) {
        int j = adminDao.adminTeamsUpdatePeopleSub(teamId);
        int i = studentDao.studentTeamsStudentsDelete(teamId, studentNumber);
        System.out.println(i+": "+j);
        if (j == 0 || i==0) {
            throw new AffectedRowsException();
        }
        return true;
    }

    @Override
    public boolean studentTeamsDelete(Integer teamId) {
        if (studentDao.studentTeamsDelete(teamId)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean studentTeamsPut(Integer teamId, String teamName, String teamCollege) {
        if (studentDao.studentTeamsPut(teamId, teamName, teamCollege)>0) {
            return true;
        }
        return false;
    }
}
