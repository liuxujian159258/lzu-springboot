package boot.dao;

import boot.bean.ScoreTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScoreTeamDao {
    // 查询满足条件的所有团队成绩
    public List<ScoreTeam> findAllScoreTeam(@Param("teamId") Integer teamId, @Param("competitionId") Integer competitionId, @Param("teamTime") Integer teamTime);
    // 根据团队Id，竞赛Id，竞赛时间删除成绩
    public int deleteByTeamIdCompetitionIdTeamTime(@Param("teamId") Integer teamId, @Param("competitionId") Integer competitionId, @Param("teamTime") Integer teamTime);
    // 添加团队成绩
    public int addTeamScore(ScoreTeam scoreTeam);
    // 更新团队成绩
    public int updateTeamScore(ScoreTeam scoreTeam);
}
