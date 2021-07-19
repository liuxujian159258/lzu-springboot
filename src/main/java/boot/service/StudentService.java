package boot.service;

import boot.bean.*;
import boot.vo.admin.CompetitionsHtmlVO;
import boot.vo.student.StudentsMyJionTeamsVO;
import boot.vo.student.StudentsMyTeamsVO;
import boot.vo.student.StudentsScoreVO;
import boot.vo.student.TeamsScoreVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

public interface StudentService {
    // 查询满足条件的所有用户
    public List<Student> findAllStudent(Integer studentSex, String studentCollege, String studentClass, Integer studentGrade);
    // 根据学号或者姓名查询用户
    public Student findStudentByStudentNumberOrName(String studentNumber, String studentName);
    // 根据学号删除用户
    public boolean deleteStudentByStudentNumber(String studentNumber);
    // 用户更新
    public boolean updateStudent(Student student);

    // 用户第一次登陆，向数据库添加
    public boolean addStudent(Student student);
    // 判断是否已经登陆过，即数据库中是否已经有此用户
    public String existStudent(String studentNumber, String studentPassword);
    // 密码更新，即修改了学校的邮箱密码
    public boolean updateStudentPassword(String studentNumber, String studentPassword);
    // 获取学生用户信息
    public Student studentInformationGet(String studentNumber);

    // 信息完善
    public boolean studentInformationsPut(Student student);

    //团队是否存在或者已经解散
    public boolean teamExistOrDismiss(Integer teamId);
    // 获取用户邮箱
    public String studentEmailGet(String studentNumber);
    // 获取竞赛报名邮件模板
    public CompetitionEmail competitionEmailGet(Integer competitionId);
    // 获取所有满足条件的竞赛
    public List<HashMap<String, Object>> studentCompetitionsGet(Integer competitionId, Integer competitionType);
    // 获取具有html的所有竞赛信息
    // 获取满足条件所有竞赛信息
    public List<CompetitionsHtmlVO> studentCompetitionsGetAll();
    // 判断用户是否是队长
    public boolean isTeamCaptain(Integer teamId, String studentNumber);
    // 判断团队人数是否和竞赛人数相等
    public boolean teamPeopleAndCompetitionPeople(Integer teamId, Integer competitionId);
    // 单人竞赛报名
    public boolean studentSignUpPerson(ScoreStudent scoreStudent) throws SQLIntegrityConstraintViolationException;
    // 团队竞赛报名
    public boolean studentSignUpTeam(ScoreTeam scoreTeam) throws SQLIntegrityConstraintViolationException;
    // 单人竞赛取消报名
    public boolean studentCancelPerson(String studentNumber, Integer competitionId, Integer studentTime);
    // 团队竞赛取消报名
    public boolean studentCancelTeam(Integer teamId, Integer competitionId, Integer teamTime);
    // 个人成绩查看
    public List<StudentsScoreVO> studentScoresStudentNumberGet(String studentNumber);
    // 团队成绩查看
    public List<TeamsScoreVO> studentTeamsScoresStudentNumberGet(String studentNumber);

    // 获取用户姓名
    public String studentNameByStudentNumber(String studentNumber);
    // 添加团队成员
    public boolean studentTeamsStudentsPost(List<TeamStudent> teamStudents);
    // 获取管理的团队
    public List<StudentsMyTeamsVO> studentTeamsMyTeams(@Param("studentNumber") String studentNumber);
    // 获取加入的团队
    public List<StudentsMyJionTeamsVO> studentTeamsMyJionTeams(@Param("studentNumber") String studentNumber);
    // 移除团队队长
    public boolean removeTeamCaptain(Integer teamId, String studentNumber);
    // 队长提升
    public boolean addTeamCaptain(Integer teamId, String captainStudentNumber);
    // 团队队长修改
    public boolean studentTeamsCaptainPut(Integer teamId, String studentNumber, String captainStudentNumber);
    // 队员移除
    public boolean studentTeamsStudentsDelete(Integer teamId, String studentNumber);
    // 团队解散
    public boolean studentTeamsDelete(Integer teamId);
    // 团队修改
    public boolean studentTeamsPut(Integer teamId, String teamName, String teamCollege);


}
