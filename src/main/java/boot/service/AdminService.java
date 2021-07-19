package boot.service;

import boot.bean.*;
import boot.vo.admin.*;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface AdminService {
    // 查询管理员
    public boolean findByUserName(String username, String password);
    // 更新管理员信息
    public boolean updateAdmin(Admin admin);
    // 管理员注册
    public boolean addAdmin(Admin admin);
    // 管理员注销
    public boolean deleteAdmin(Admin admin);

    // 获取所有用户信息或根据学生用户学号、姓名查询
    public List<Student> adminStudentsGet(String studentNumber, String studentName);
    // 获取用户竞赛列表数据
    public List<StudentsVO> adminStudentsCompetitionsGet(String studentNumber, String studentName);
    // 修改用户信息
    public boolean adminStudentsPut(String studentNumber, String studentEmail, String studentPhone);
    // 删除用户
    public boolean adminStudentsDelete(String studentNumber);
    // 用户信息下载
    public List<StudentsDownloadVO> adminStudentsDownload(String competitionName, Integer studentTime);

    // 获取所有满足条件的团队信息列表(根据团队编号或者名称查询团队信息）
    public List<TeamsVO> adminTeamsCompetitionsGet(Integer teamId, String teamName);
    // 修改团队信息
    public boolean adminTeamsPut(Integer teamId, String teamName, String teamCollege);
    // 团队人数加一
    public boolean adminTeamsUpdatePeople(Integer teamId);
    // 团队人数减一
    public boolean adminTeamsUpdatePeopleSub(Integer teamId);
    // 删除团队
    public boolean adminTeamsDelete(Integer teamId);
    // 团队信息下载
    public List<TeamsDownloadVO> adminTeamsDownload(String competitionName, Integer teamTime);
    // 添加团队及成员
    public boolean adminTeamsPost(Team team, List<TeamStudent> teamStudents) throws SQLIntegrityConstraintViolationException;
    // 修改团队队长
    public boolean adminTeamsTeamIdStudentNumberPut(Integer teamId, String studentNumber);
    // 移出团队队员
    public boolean adminTeamsStudentsDelete(Integer teamId, String studentNumber);
    // 获取未报名竞赛团队信息
    public List<TeamsNotSignUpVO> adminTeamsNotSignUpGet(Integer teamId, String teamName);
    // 获取已经删除的团队信息
    public List<TeamsVO> adminTeamsDeleteGet(Integer teamId, String teamName);
    //永久删除未报名竞赛团队
    public boolean adminTeamsDeleteForeverNotSignUpDelete(Integer teamId);
    // 永久逻辑删除团队
    public boolean adminTeamsDeleteForeverTeamDelete(Integer teamId);
    // 取消团队删除
    public boolean adminTeamsDeleteCancelPut(Integer teamId);

    // 获取所有竞赛信息列表
    public List<CompetitionsHtmlVO> adminCompetitionsGet();
    // 根据竞赛编号获取竞赛信息
    public CompetitionsHtmlVO adminCompetitionsCompetitionIdGet(Integer competitionId);
    // 修改竞赛信息
    public boolean adminCompetitionsPut(Competition competition);
    // 更新单人竞赛成绩中的竞赛名称
    public boolean studentScoreCompetitionName(@Param("competitionId") Integer competitionId, @Param("competitionName") String competitionName);
    // 更新团队竞赛成绩中的竞赛名称
    public boolean teamScoreCompetitionName(@Param("competitionId") Integer competitionId, @Param("competitionName") String competitionName);
    // 修改竞赛网页信息
    public boolean adminCompetitionsHtmlPut(CompetitionHtml competitionHtml);
    // 修改竞赛所有信息
    public boolean adminCompetitionsAllPut(CompetitionsHtmlVO competitionsHtmlVO);
    // 添加竞赛
    public int adminCompetitionsPost(Competition competition);
    // 添加竞赛网页信息
    public boolean adminCompetitionsHtmlPost(CompetitionHtml competitionHtml);
    // 添加竞赛所有信息
    public boolean adminCompetitionsAllPost(CompetitionsHtmlVO competitionsHtmlVO);
    // 删除竞赛
    public boolean adminCompetitionsDelete(Integer competitionId);
    // 恢复竞赛
    public boolean adminCompetitionsRecover(Integer competitionId);

    // 修改管理员发送邮箱
    public boolean adminEmailsPut(Admin admin);
    // 获取竞赛列表
    public List<Map<String, Object>> adminEmailsCompetitions();
    // 获取竞赛类型
    public int adminEmailCompetitionType (String competitionName);
    // 获取管理员邮箱
    public Admin adminEmailsGet(String username);
    // 获取团队成员邮箱
    public String[] adminEmailsTeamsGet(Integer teamId);
    // 单人竞赛邮件群发
    public String[] adminEmailsOne(String competitionName, Integer studentTime);
    // 团队竞赛邮件群发
    public String[] adminEmailsTeam(String competitionName, Integer teamTime);
    // 获取竞赛报名邮件模板
    public List<CompetitionEmail> adminEmailsCompetitionEmailsGet ();
    // 添加竞赛报名邮件模板
    public boolean adminEmailsCompetitionEmailsPost (CompetitionEmail competitionEmail);
    // 修改竞赛报名邮件模板
    public boolean adminEmailsCompetitionEmailsPut (CompetitionEmail competitionEmail);

    // 获取所有用户成绩
    public List<StudentsDownloadVO> adminStudentsScoreGet(String studentNumber, String studentName, Integer studentTime, Integer competitionId);
    // 获取单人竞赛列表
    public List<Map<String, Object>> adminStudentsScoreCompetitionListGet();
    // 获取时间列表
    public List<Map<String, Object>> adminStudentsScoreCompetitionTimeListGet();
    // 添加、修改用户成绩
    public boolean adminStudentsScorePut(String studentNumber, Double StudentScore, Integer competitionId, Integer studentTime);
    // 获取所有满足条件的态团队成绩
    public List<TeamsScoresVO> adminTeamsScoresGet(Integer teamId, String teamName, Integer teamTime, Integer competitionId);
    // 获取团队竞赛列表
    public List<Map<String, Object>> adminTeamsScoreCompetitionListGet();
    // 添加、修改团队成绩
    public boolean adminTeamsScoresPut(Integer teamId, Float teamScore, Integer competitionId, Integer teamTime);

    // 按竞赛分类返回数据(个人）
    public List<Map<String, Object>> adminStatisticsCompetitionsNamePersonGet();
    // 按竞赛分类返回数据（团队）
    public List<Map<String, Object>> adminStatisticsCompetitionsNameTeamGet();
    // 按竞赛分类返回数据（个人和团队)
    public List<Map<String, Object>> adminStatisticsCompetitionsNameGet();
    // 按竞赛时间返回数据(个人）
    public List<Map<String, Object>> adminStatisticsCompetitionsTimePersonGet();
    // 按竞赛时间返回数据（团队）
    public List<Map<String, Object>> adminStatisticsCompetitionsTimeTeamGet();
    // 按竞赛时间返回数据（个人和团队）
    public List<Map<String, Object>> adminStatisticsCompetitionsTimeGet();
    // 按竞赛和时间返回数据（个人）
    public List<StatisticsVO> adminStatisticsCompetitionsNameAndTimePersonGet();
    // 按竞赛和时间返回数据（团队）
    public List<StatisticsVO> adminStatisticsCompetitionsNameAndTimeTeamGet();
    // 按竞赛和时间返回数据（个人和团队）
    public List<StatisticsVO> adminStatisticsCompetitionsNameAndTimeGet();
    // 按性别返回数据（个人）
    public List<Map<String, Object>> adminStatisticsSexPersonGet();
    // 按性别返回数据（团队）
    public List<Map<String, Object>> adminStatisticsSexTeamGet();
    // 按性别返回数据（个人和团队）
    public List<Map<String, Object>> adminStatisticsSexGet();
    // 按学院返回数据（个人）
    public List<Map<String, Object>> adminStatisticsCollegePersonGet();
    // 按学院返回数据（团队）
    public List<Map<String, Object>> adminStatisticsCollegeTeamGet();
    // 按学院返回数据（个人和团队）
    public List<Map<String, Object>> adminStatisticsCollegeGet();
    // 按年级返回数据（个人）
    public List<Map<String, Object>> adminStatisticsGradePersonGet();
    // 按年级返回数据（团队）
    public List<Map<String, Object>> adminStatisticsGradeTeamGet();
    // 按年级返回数据（个人和团队）
    public List<Map<String, Object>> adminStatisticsGradeGet();
    // 按班级返回数据（个人）
    public List<Map<String, Object>> adminStatisticsClassPersonGet();
    // 按班级返回数据（团队）
    public List<Map<String, Object>> adminStatisticsClassTeamGet();
    // 按班级返回数据（个人和团队）
    public List<Map<String, Object>> adminStatisticsClassGet();
    // 按年级和班级返回数据（个人）
    public List<Map<String, Object>> adminStatisticsGradeAndClassPersonGet();
    // 按年级和班级返回数据（团队）
    public List<Map<String, Object>> adminStatisticsGradeAndClassTeamGet();
    // 按年级和班级返回数据（个人和团队）
    public List<Map<String, Object>> adminStatisticsGradeAndClassGet();

    // 获取管理员信息
    public Admin adminAdminsGet(String username);
    // 更新管理员信息
    public boolean adminAdminsPut(Admin admin);
    // 添加管理员
    public boolean adminAdminsPost(Admin admin);
    // 管理员邮箱更新
    public boolean adminAdminEmailPut(Admin admin);
    // 管理员密码更新
    public boolean adminAdminsPasswordPut(Admin admin);


}
