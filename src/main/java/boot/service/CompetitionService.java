package boot.service;

import boot.bean.Competition;
import java.util.List;

public interface CompetitionService {
    // 查询满足条件的所有竞赛
    public List<Competition> findAllCompetition(int competitionType);
    // 根据竞赛id或者名称查询竞赛
    public Competition findCompetitionByCompetitionIdOrCompetitionName(Integer competitionId, String competitionName);
    // 根据竞赛Id删除竞赛
    public boolean deleteByCompetitionId(int CompetitionId);
    // 添加竞赛
    public boolean addCompetition(Competition competition);
    // 根据竞赛Id更新竞赛
    public boolean updateCompetition(Competition competition);
}
