package boot.service;

import boot.bean.ScoreTeam;
import java.util.List;

public interface ScoreTeamService  {
    // 查询满足条件的所有团队成绩
    public List<ScoreTeam> findAllScoreTeam(Integer teamId, Integer competitionId, Integer teamTime);
    // 根据团队Id，竞赛Id，竞赛时间删除成绩
    public boolean deleteByTeamIdCompetitionIdTeamTime(Integer teamId, Integer competitionId, Integer teamTime);
    // 添加团队成绩
    public boolean addTeamScore(ScoreTeam scoreTeam);
    // 更新团队成绩
    public boolean updateTeamScore(ScoreTeam scoreTeam);
}
