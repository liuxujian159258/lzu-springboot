package boot.dao;

import boot.bean.*;
import boot.vo.admin.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDao {
    // 查询管理员
    public Admin findByUserName(@Param("username") String username, @Param("password") String password);
    // 更新管理员信息
    public int updateAdmin(Admin admin);
    // 管理员注册
    public int addAdmin(Admin admin);
    // 管理员注销
    public int deleteAdmin(Admin admin);

    // 获取所有用户信息或根据学生用户学号、姓名查询
    public List<Student> adminStudentsGet(@Param("studentNumber") String studentNumber, @Param("studentName") String studentName);
    // 获取用户竞赛列表数据或根据学生用户学号、姓名查询
    public List<StudentsVO> adminStudentsCompetitionsGet(@Param("studentNumber") String studentNumber, @Param("studentName") String studentName);
    // 修改用户信息
    public int adminStudentsPut(@Param("studentNumber") String studentNumber, @Param("studentEmail") String studentEmail, @Param("studentPhone") String studentPhone);
    // 删除用户
    public int adminStudentsDelete(@Param("studentNumber") String studentNumber);
    // 数据下载
    public List<StudentsDownloadVO> adminStudentsDownload(@Param("competitionName") String competitionName, @Param("studentTime") Integer studentTime);

    // 获取所有满足条件的团队竞赛信息列表(根据团队编号或者名称查询团队信息）
    public List<TeamsVO> adminTeamsCompetitionsGet(@Param("teamId") Integer teamId, @Param("teamName") String teamName);
    // 修改团队信息
    public int adminTeamsPut(@Param("teamId") Integer teamId, @Param("teamName") String teamName, @Param("teamCollege") String teamCollege);
    // 删除团队
    public int adminTeamsDelete(@Param("teamId") Integer teamId);
    // 团队信息下载
    public List<TeamsDownloadVO> adminTeamsDownload(@Param("competitionName") String competitionName, @Param("teamTime") Integer teamTime);
    // 团队人数加一
    public int adminTeamsUpdatePeople(@Param("teamId") Integer teamId);
    // 团队人数减一
    public int adminTeamsUpdatePeopleSub(@Param("teamId") Integer teamId);
    // 添加团队
    public int adminTeamsPost(Team team);
    // 修改团队队长
    public int adminTeamsTeamIdStudentNumberPut(Integer teamId, String studentNumber);
    // 获取未报名竞赛团队信息
    public List<TeamsNotSignUpVO> adminTeamsNotSignUpGet(@Param("teamId") Integer teamId, @Param("teamName") String teamName);
    // 获取已经删除的团队信息
    public List<TeamsVO> adminTeamsDeleteGet(@Param("teamId") Integer teamId, @Param("teamName") String teamName);
    //永久删除团队信息
    public int adminTeamsDeleteForeverDelete(@Param("teamId") Integer teamId);
    // 永久删除团队成员信息
    public int adminTeamsStudentsDeleteForeverDelete(@Param("teamId") Integer teamId);
    // 永久删除团队竞赛信息
    public int adminTeamsScoreTeamDeleteForeverDelete(@Param("teamId") Integer teamId);
    // 取消团队删除
    public int adminTeamsDeleteCancelPut(@Param("teamId") Integer teamId);
    // 移出团队队员
    public int adminTeamsStudentsDelete(@Param("teamId") Integer teamId, @Param("studentNumber") String studentNumber);

    // 获取所有竞赛信息列表
    public List<CompetitionsHtmlVO> adminCompetitionsGet();
    // 根据竞赛编号获取竞赛信息
    public CompetitionsHtmlVO adminCompetitionsCompetitionIdGet(@Param("competitionId") Integer competitionId);
    // 修改竞赛信息
    public int adminCompetitionsPut(Competition competition);
    // 更新单人竞赛成绩中的竞赛名称
    public int studentScoreCompetitionName(@Param("competitionId") Integer competitionId, @Param("competitionName") String competitionName);
    // 更新团队竞赛成绩中的竞赛名称
    public int teamScoreCompetitionName(@Param("competitionId") Integer competitionId, @Param("competitionName") String competitionName);
    // 修改竞赛网页信息
    public int adminCompetitionsHtmlPut(CompetitionHtml competitionHtml);
    // 添加竞赛
    public int adminCompetitionsPost(Competition competition);
    // 添加竞赛网页信息
    public int adminCompetitionsHtmlPost(CompetitionHtml competitionHtml);
    // 删除竞赛
    public int adminCompetitionsDelete(@Param("competitionId") Integer competitionId);
    // 恢复竞赛
    public int adminCompetitionsRecover(@Param("competitionId") Integer competitionId);

    // 修改管理员发送邮箱
    public int adminEmailsPut(Admin admin);
    // 获取竞赛列表
    public List<Map<String, Object>> adminEmailsCompetitions();
    // 获取竞赛类型
    public int adminEmailCompetitionType (@Param("competitionName") String competitionName);
    // 获取管理员邮箱
    public Admin adminEmailsGet(@Param("username") String username);
    // 获取团队成员邮箱
    public String[] adminEmailsTeamsGet(@Param("teamId") Integer teamId);
    // 单人竞赛邮件群发
    public String[] adminEmailsOne(@Param("competitionName") String competitionName, @Param("studentTime") Integer studentTime);
    // 团队竞赛邮件群发
    public String[] adminEmailsTeam(@Param("competitionName") String competitionName, @Param("teamTime") Integer teamTime);
    // 获取竞赛报名邮件模板
    public List<CompetitionEmail> adminEmailsCompetitionEmailsGet ();
    // 添加竞赛报名邮件模板
    public int adminEmailsCompetitionEmailsPost (CompetitionEmail competitionEmail);
    // 修改竞赛报名邮件模板
    public int adminEmailsCompetitionEmailsPut (CompetitionEmail competitionEmail);

    // 获取所有用户成绩
    public List<StudentsDownloadVO> adminStudentsScoreGet(@Param("studentNumber") String studentNumber, @Param("studentName") String studentName, @Param("studentTime") Integer studentTime, @Param("competitionId") Integer competitionId);
    // 获取单人竞赛列表
    public List<Map<String, Object>> adminStudentsScoreCompetitionListGet();
    // 获取时间列表
    public List<Map<String, Object>> adminStudentsScoreCompetitionTimeListGet();
    // 添加、修改用户成绩
    public int adminStudentsScorePut(@Param("studentNumber") String studentNumber, @Param("studentScore") Double studentScore, @Param("competitionId") Integer competitionId, @Param("studentTime") Integer studentTime);
    // 获取所有满足条件的团队成绩
    public List<TeamsScoresVO> adminTeamsScoresGet(@Param("teamId") Integer teamId, @Param("teamName") String teamName, @Param("teamTime") Integer teamTime, @Param("competitionId") Integer competitionId);
    // 获取团队竞赛列表
    public List<Map<String, Object>> adminTeamsScoreCompetitionListGet();
    // 添加、修改团队成绩
    public int adminTeamsScoresPut(@Param("teamId") Integer teamId, @Param("teamScore") Float teamScore, @Param("competitionId") Integer competitionId, @Param("teamTime") Integer teamTime);

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
    public Admin adminAdminsGet(@Param("username") String username);
    // 更新管理员信息
    public int adminAdminsPut(Admin admin);
    // 添加管理员
    public int adminAdminsPost(Admin admin);
    // 管理员邮箱更新
    public int adminAdminEmailPut(Admin admin);
    // 管理员密码更新
    public int adminAdminsPasswordPut(Admin admin);




}
