package boot.service.impl;

import boot.bean.*;
import boot.dao.AdminDao;
import boot.dao.ScoreStudentDao;
import boot.dao.TeamDao;
import boot.dao.TeamStudentDao;
import boot.exception.AffectedRowsException;
import boot.service.AdminService;
import boot.vo.admin.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;
    @Resource
    private ScoreStudentDao scoreStudentDao;
    @Resource
    private TeamStudentDao teamStudentDao;
    @Resource
    private TeamDao teamDao;

    @Override
    public boolean findByUserName(String username, String password) {
        Admin admin = adminDao.findByUserName(username, password);
        if ( !admin.equals(null) ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        int i = adminDao.updateAdmin(admin);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        int i = adminDao.addAdmin(admin);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(Admin admin) {
        int i = adminDao.deleteAdmin(admin);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Student> adminStudentsGet(String studentNumber, String studentName) {
        return adminDao.adminStudentsGet(studentNumber, studentName);
    }

    @Override
    public List<StudentsVO> adminStudentsCompetitionsGet(String studentNumber, String studentName) {
        return adminDao.adminStudentsCompetitionsGet(studentNumber, studentName);
    }

    @Override
    public boolean adminStudentsPut (String studentNumber, String studentEmail, String studentPhone)
    {
        int i = adminDao.adminStudentsPut(studentNumber, studentEmail, studentPhone);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminStudentsDelete(String studentNumber) {
        int i = adminDao.adminStudentsDelete(studentNumber);
        if (i==0) {
            throw new AffectedRowsException();
        }else {
            return true;
        }
    }

    @Override
    public List<StudentsDownloadVO> adminStudentsDownload(String competitionName, Integer studentTime) {
        return adminDao.adminStudentsDownload(competitionName, studentTime);
    }

    @Override
    public List<TeamsVO> adminTeamsCompetitionsGet(Integer teamId, String teamName) {
        return adminDao.adminTeamsCompetitionsGet(teamId, teamName);
    }

    @Override
    public boolean adminTeamsPut(Integer teamId, String teamName, String teamCollege) {
        int i = adminDao.adminTeamsPut(teamId, teamName, teamCollege);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminTeamsUpdatePeople(Integer teamId) {
        if (adminDao.adminTeamsUpdatePeople(teamId)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminTeamsUpdatePeopleSub(Integer teamId) {
        if (adminDao.adminTeamsUpdatePeopleSub(teamId)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminTeamsDelete(Integer teamId) {
        int i = adminDao.adminTeamsDelete(teamId);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<TeamsDownloadVO> adminTeamsDownload(String competitionName, Integer teamTime) {
        return adminDao.adminTeamsDownload(competitionName, teamTime);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean adminTeamsPost(Team team, List<TeamStudent> teamStudents) {
        Team team1 = teamDao.existTeam(team.getTeamName());
        int k;
        if (team1 == null) {
            if (adminDao.adminTeamsPost(team) > 0) {
                k=team.getTeamId();
                for (TeamStudent teamStudent : teamStudents) {
                    teamStudent.setTeamId(k);
                    System.out.println(teamStudent);
                    int j = teamStudentDao.addTeamStudent(teamStudent);
                    int i = adminDao.adminTeamsUpdatePeople(k);
                    if (j == 0 || i==0) {
                        throw new AffectedRowsException();
                    }
                }
                return true;
            } else {
                throw new AffectedRowsException("此团队名称已经被注册");
            }
        }else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean adminTeamsTeamIdStudentNumberPut(Integer teamId, String studentNumber) {
        int i = teamStudentDao.removeCaptain(teamId);
        int i1 = teamStudentDao.studentCaptain(teamId, studentNumber);
        if (i>0&&i1>0) {
            return true;
        }else {
            throw new AffectedRowsException();        }
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean adminTeamsStudentsDelete(Integer teamId, String studentNumber) {
        int i = adminDao.adminTeamsStudentsDelete(teamId, studentNumber);
        int j = adminDao.adminTeamsUpdatePeopleSub(teamId);
        System.out.println(i+": "+j);
        if (j == 0 || i==0) {
            throw new AffectedRowsException();
        }
        return true;
    }

    @Override
    public List<TeamsNotSignUpVO> adminTeamsNotSignUpGet(Integer teamId, String teamName) {
        return adminDao.adminTeamsNotSignUpGet(teamId, teamName);
    }

    @Override
    public CompetitionsHtmlVO adminCompetitionsCompetitionIdGet(Integer competitionId) {
        return adminDao.adminCompetitionsCompetitionIdGet(competitionId);
    }

    @Override
    public List<TeamsVO> adminTeamsDeleteGet(Integer teamId, String teamName) {
        return adminDao.adminTeamsDeleteGet(teamId, teamName);
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean adminTeamsDeleteForeverNotSignUpDelete(Integer teamId) {
        if (adminDao.adminTeamsDeleteForeverDelete(teamId)>0&&adminDao.adminTeamsStudentsDeleteForeverDelete(teamId)>0){
            return true;
        }else {
            throw new AffectedRowsException();
        }
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean adminTeamsDeleteForeverTeamDelete(Integer teamId) {
        if (adminDao.adminTeamsDeleteForeverDelete(teamId)>0&&adminDao.adminTeamsStudentsDeleteForeverDelete(teamId)>0&&adminDao.adminTeamsScoreTeamDeleteForeverDelete(teamId)>0) {
            return true;
        }else {
            throw new AffectedRowsException();
        }
    }



    @Override
    public boolean adminTeamsDeleteCancelPut(Integer teamId) {
        if (adminDao.adminTeamsDeleteCancelPut(teamId)>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<CompetitionsHtmlVO> adminCompetitionsGet() {
        return adminDao.adminCompetitionsGet();
    }

    @Override
    public boolean adminCompetitionsHtmlPut(CompetitionHtml competitionHtml) {
        if (adminDao.adminCompetitionsHtmlPut(competitionHtml)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminCompetitionsHtmlPost(CompetitionHtml competitionHtml) {
        if (adminDao.adminCompetitionsHtmlPost(competitionHtml)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminCompetitionsPut(Competition competition) {
        if (adminDao.adminCompetitionsPut(competition)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean studentScoreCompetitionName(Integer competitionId, String competitionName) {
        if (adminDao.studentScoreCompetitionName(competitionId, competitionName)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean teamScoreCompetitionName(Integer competitionId, String competitionName) {
        if (adminDao.teamScoreCompetitionName(competitionId, competitionName)>0) {
            return true;
        }
        return false;
    }

    @Override
    public int adminCompetitionsPost(Competition competition) {
        if (adminDao.adminCompetitionsPost(competition)>0) {
            return competition.getCompetitionId();
        }
        return -1;
    }

    @Override
    public boolean adminCompetitionsAllPut(CompetitionsHtmlVO competitionsHtmlVO) {
        Competition competition = new Competition(competitionsHtmlVO.getCompetitionId(), competitionsHtmlVO.getCompetitionName(), competitionsHtmlVO.getCompetitionType(), competitionsHtmlVO.getCompetitionPeople(), competitionsHtmlVO.getCompetitionDepartment(), competitionsHtmlVO.getIsDelete());
        CompetitionHtml competitionHtml = new CompetitionHtml(competitionsHtmlVO.getCompetitionId(), competitionsHtmlVO.getCompetitionHtml());
        if (adminDao.adminCompetitionsHtmlPut(competitionHtml)>0) {
            adminDao.adminCompetitionsPut(competition);
            return true;
        }else {
            throw new AffectedRowsException();
        }
    }

    @Override
    @Transactional(rollbackFor = AffectedRowsException.class)
    public boolean adminCompetitionsAllPost(CompetitionsHtmlVO competitionsHtmlVO) {
        Competition competition = new Competition(competitionsHtmlVO.getCompetitionId(), competitionsHtmlVO.getCompetitionName(), competitionsHtmlVO.getCompetitionType(), competitionsHtmlVO.getCompetitionPeople(), competitionsHtmlVO.getCompetitionDepartment(), competitionsHtmlVO.getIsDelete());
        CompetitionHtml competitionHtml = new CompetitionHtml(competitionsHtmlVO.getCompetitionId(), competitionsHtmlVO.getCompetitionHtml());
        if (adminDao.adminCompetitionsPost(competition)>0) {
            competitionHtml.setCompetitionId(competition.getCompetitionId());
            System.out.println(competition.getCompetitionId());
            if (adminDao.adminCompetitionsHtmlPost(competitionHtml)>0) {
                return true;
            }else {
                throw new AffectedRowsException();
            }
        }else {
            throw new AffectedRowsException();
        }
    }

    @Override
    public boolean adminCompetitionsDelete(Integer competitionId) {
        int i = adminDao.adminCompetitionsDelete(competitionId);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminCompetitionsRecover(Integer competitionId) {
        int i = adminDao.adminCompetitionsRecover(competitionId);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminEmailsPut(Admin admin) {
        int i = adminDao.adminEmailsPut(admin);
        if (i>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> adminEmailsCompetitions() {
        return adminDao.adminEmailsCompetitions();
    }

    @Override
    public int adminEmailCompetitionType(String competitionName) {
        return adminDao.adminEmailCompetitionType(competitionName);
    }

    @Override
    public Admin adminEmailsGet(String username) {
        return adminDao.adminEmailsGet(username);
    }

    @Override
    public String[] adminEmailsTeamsGet(Integer teamId) {
        return adminDao.adminEmailsTeamsGet(teamId);
    }

    @Override
    public String[] adminEmailsOne(String competitionName, Integer studentTime) {
        return adminDao.adminEmailsOne(competitionName, studentTime);
    }

    @Override
    public String[] adminEmailsTeam(String competitionName, Integer teamTime) {
        return adminDao.adminEmailsTeam(competitionName, teamTime);
    }

    @Override
    public List<CompetitionEmail> adminEmailsCompetitionEmailsGet() {
        return adminDao.adminEmailsCompetitionEmailsGet();
    }

    @Override
    public boolean adminEmailsCompetitionEmailsPost(CompetitionEmail competitionEmail) {
        if (adminDao.adminEmailsCompetitionEmailsPost(competitionEmail)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminEmailsCompetitionEmailsPut(CompetitionEmail competitionEmail) {
        if (adminDao.adminEmailsCompetitionEmailsPut(competitionEmail)>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<StudentsDownloadVO> adminStudentsScoreGet(String studentNumber, String studentName, Integer studentTime, Integer competitionId) {
        return adminDao.adminStudentsScoreGet(studentNumber, studentName, studentTime, competitionId);
    }

    @Override
    public List<Map<String, Object>> adminStudentsScoreCompetitionListGet() {
        return adminDao.adminStudentsScoreCompetitionListGet();
    }

    @Override
    public List<Map<String, Object>> adminStudentsScoreCompetitionTimeListGet() {
        return adminDao.adminStudentsScoreCompetitionTimeListGet();
    }

    @Override
    public boolean adminStudentsScorePut(String studentNumber, Double StudentScore, Integer competitionId, Integer studentTime) {
        if (adminDao.adminStudentsScorePut(studentNumber, StudentScore, competitionId, studentTime)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<TeamsScoresVO> adminTeamsScoresGet(Integer teamId, String teamName, Integer teamTime, Integer competitionId) {
        return adminDao.adminTeamsScoresGet(teamId, teamName, teamTime, competitionId);
    }

    @Override
    public List<Map<String, Object>> adminTeamsScoreCompetitionListGet() {
        return adminDao.adminTeamsScoreCompetitionListGet();
    }

    @Override
    public boolean adminTeamsScoresPut(Integer teamId, Float teamScore, Integer competitionId, Integer teamTime) {
        if (adminDao.adminTeamsScoresPut(teamId, teamScore, competitionId, teamTime)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCompetitionsNamePersonGet() {
        return adminDao.adminStatisticsCompetitionsNamePersonGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCompetitionsNameTeamGet() {
        return adminDao.adminStatisticsCompetitionsNameTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCompetitionsNameGet() {
        return adminDao.adminStatisticsCompetitionsNameGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCompetitionsTimeTeamGet() {
        return adminDao.adminStatisticsCompetitionsTimeTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCompetitionsTimeGet() {
        return adminDao.adminStatisticsCompetitionsTimeGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCompetitionsTimePersonGet() {
        return adminDao.adminStatisticsCompetitionsTimePersonGet();
    }

    @Override
    public List<StatisticsVO> adminStatisticsCompetitionsNameAndTimePersonGet() {
        return adminDao.adminStatisticsCompetitionsNameAndTimePersonGet();
    }

    @Override
    public List<StatisticsVO> adminStatisticsCompetitionsNameAndTimeTeamGet() {
        return adminDao.adminStatisticsCompetitionsNameAndTimeTeamGet();
    }

    @Override
    public List<StatisticsVO> adminStatisticsCompetitionsNameAndTimeGet() {
        return adminDao.adminStatisticsCompetitionsNameAndTimeGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsSexPersonGet() {
        return adminDao.adminStatisticsSexPersonGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsSexTeamGet() {
        return adminDao.adminStatisticsSexTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsSexGet() {
        return adminDao.adminStatisticsSexGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCollegePersonGet() {
        return adminDao.adminStatisticsCollegePersonGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCollegeTeamGet() {
        return adminDao.adminStatisticsCollegeTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsCollegeGet() {
        return adminDao.adminStatisticsCollegeGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsGradePersonGet() {
        return adminDao. adminStatisticsGradePersonGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsGradeTeamGet() {
        return adminDao.adminStatisticsGradeTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsGradeGet() {
        return adminDao.adminStatisticsGradeGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsClassPersonGet() {
        return adminDao.adminStatisticsClassPersonGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsClassTeamGet() {
        return adminDao.adminStatisticsClassTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsClassGet() {
        return adminDao.adminStatisticsClassGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsGradeAndClassPersonGet() {
        return adminDao.adminStatisticsGradeAndClassPersonGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsGradeAndClassTeamGet() {
        return adminDao.adminStatisticsGradeAndClassTeamGet();
    }

    @Override
    public List<Map<String, Object>> adminStatisticsGradeAndClassGet() {
        return adminDao.adminStatisticsGradeAndClassGet();
    }

    @Override
    public Admin adminAdminsGet(String username) {
        return adminDao.adminAdminsGet(username);
    }

    @Override
    public boolean adminAdminsPut(Admin admin) {
        if (adminDao.adminAdminsPut(admin)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminAdminsPost(Admin admin) {
        if (adminDao.adminAdminsPost(admin)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminAdminEmailPut(Admin admin) {
        if (adminDao.adminAdminEmailPut(admin)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean adminAdminsPasswordPut(Admin admin) {
        if (adminDao.adminAdminsPasswordPut(admin)>0) {
            return true;
        }
        return false;
    }
}
