package boot.dao;

import boot.bean.ScoreStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreStudentDao {
    // 查询满足条件的用户的成绩
    public List<ScoreStudent> findAllScoreUser(@Param("studentNumber") String studentNumber, @Param("competitionId") Integer competitionId, @Param("studentTime") Integer studentTime);
    // 根据studentNumber，competitionId，userTime删除成绩
    public int deleteByStudentNumberCompetitionIdUserTime(@Param("studentNumber") String studentNumber, @Param("competitionId") Integer competitionId, @Param("studentTime") Integer studentTime);
    // 添加成绩
    public int addScoreUser(ScoreStudent scoreStudent);
    // 更新用户成绩
    public int updateScoreUser(ScoreStudent scoreStudent);
    // 根据studentNumber删除用户成绩
    public int deleteByStudentNumber(@Param("studentNumber") String studentNumber);
}
