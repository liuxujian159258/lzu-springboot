package boot.service;

import boot.bean.ScoreStudent;

import java.util.List;

public interface ScoreStudentService {
    // 查询满足条件的用户的成绩
    public List<ScoreStudent> findAllScoreUser(String studentNumber, Integer competitionId, Integer studentTime);
    // 根据studentNumber，competitionId，userTime删除成绩
    public boolean deleteByStudentNumberCompetitionIdUserTime(String studentNumber, Integer competitionId, Integer studentTime);
    // 添加成绩
    public boolean addScoreUser(ScoreStudent scoreStudent);
    // 更新用户成绩
    public boolean updateScoreUser(ScoreStudent scoreStudent);
    // 根据studentNumber删除用户成绩
    public boolean deleteByStudentNumber(String studentNumber);

}
