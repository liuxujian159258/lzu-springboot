package boot.dao;

import boot.bean.*;
import boot.vo.admin.CompetitionsHtmlVO;
import boot.vo.student.StudentsMyJionTeamsVO;
import boot.vo.student.StudentsMyTeamsVO;
import boot.vo.student.StudentsScoreVO;
import boot.vo.student.TeamsScoreVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface StudentDao {
    // 查询满足条件的所有用户
    public List<Student> findAllStudent(@Param("studentSex") Integer studentSex, @Param("studentCollege") String studentCollege, @Param("studentClass") String studentClass, @Param("studentGrade") Integer studentGrade);
    // 根据学号或者姓名查询用户
    public Student findStudentByStudentNumberOrName(@Param("studentNumber") String studentNumber, @Param("studentName") String studentName);
    // 根据学号删除用户
    public int deleteStudentByStudentNumber(String studentNumber);
    // 用户更新
    public int updateStudent(Student student);

    // 用户第一次登陆，向数据库添加
    public int addStudent(Student student);
    // 判断是否已经登陆过，即数据库中是否已经有此用户
    public String existStudent(@Param("studentNumber") String studentNumber, @Param("studentPassword") String studentPassword);
    // 密码更新，即修改了学校的邮箱密码
    public int updateStudentPassword(@Param("studentNumber") String studentNumber, @Param("studentPassword") String studentPassword);
    // 获取学生用户信息
    public Student studentInformationGet(@Param("studentNumber") String studentNumber);

    // 信息完善接口
    public int studentInformationsPut(Student student);

    //团队是否存在或者已经解散
    public int teamExistOrDismiss(@Param("teamId") Integer teamId);
    // 获取用户邮箱
    public String studentEmailGet(@Param("studentNumber") String studentNumber);
    // 获取竞赛报名邮件模板
    public CompetitionEmail competitionEmailGet(@Param("competitionId") Integer competitionId);
    // 获取所有满足条件的竞赛
    public List<HashMap<String, Object>> studentCompetitionsGet(@Param("competitionId") Integer competitionId, @Param("competitionType") Integer competitionType);
    // 获取具有html的所有竞赛信息
    // 获取满足条件所有竞赛信息
    public List<CompetitionsHtmlVO> studentCompetitionsGetAll();
    // 判断用户是否是队长
    public String isTeamCaptain(@Param("teamId") Integer teamId, @Param("studentNumber") String studentNumber);
    // 判断团队人数是否和竞赛人数相等
    public Integer teamPeopleAndCompetitionPeople(@Param("teamId") Integer teamId, @Param("competitionId") Integer competitionId);
    // 单人竞赛报名
    public int studentSignUpPerson(ScoreStudent scoreStudent);
    // 团队竞赛报名
    public int studentSignUpTeam(ScoreTeam scoreTeam);
    // 单人竞赛取消报名
    public int studentCancelPerson(@Param("studentNumber") String studentNumber, @Param("competitionId") Integer competitionId, @Param("studentTime") Integer studentTime);
    // 团队竞赛取消报名
    public int studentCancelTeam(@Param("teamId") Integer teamId, @Param("competitionId") Integer competitionId, @Param("teamTime") Integer teamTime);
    // 个人成绩查看
    public List<StudentsScoreVO> studentScoresStudentNumberGet(@Param("studentNumber") String studentNumber);
    // 团队成绩查看
    public List<TeamsScoreVO> studentTeamsScoresStudentNumberGet(@Param("studentNumber") String studentNumber);

    // 获取用户姓名
    public String studentNameByStudentNumber(@Param("studentNumber") String studentNumber);
    // 团队注册
    public int studentTeamsPost(@Param("studentNumber") String studentNumber, @Param("teamName") String teamName, @Param("teamCollege") String teamCollege);
    // 添加团队成员
    public int studentTeamsStudentsPost(List<TeamStudent> teamStudents);
    // 获取管理的团队
    public List<StudentsMyTeamsVO> studentTeamsMyTeams(@Param("studentNumber") String studentNumber);
    // 获取加入的团队
    public List<StudentsMyJionTeamsVO> studentTeamsMyJionTeams(@Param("studentNumber") String studentNumber);
    // 移除团队队长
    public int removeTeamCaptain(@Param("teamId") Integer teamId, @Param("studentNumber") String studentNumber);
    // 队长提升
    public int addTeamCaptain(@Param("teamId") Integer teamId, @Param("captainStudentNumber") String captainStudentNumber);
    // 队员移除
    public int studentTeamsStudentsDelete(@Param("teamId") Integer teamId, @Param("studentNumber") String studentNumber);
    // 团队解散
    public int studentTeamsDelete(@Param("teamId") Integer teamId);
    // 团队修改
    public int studentTeamsPut(@Param("teamId") Integer teamId, @Param("teamName") String teamName, @Param("teamCollege") String teamCollege);


   }
