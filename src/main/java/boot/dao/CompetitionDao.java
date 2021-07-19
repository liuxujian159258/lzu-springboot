package boot.dao;

import boot.bean.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompetitionDao {
    // 查询满足条件的所有竞赛
    public List<Competition> findAllCompetition(int competitionType);
    // 根据竞赛id或者名称查询竞赛
    public Competition findCompetitionByCompetitionIdOrCompetitionName(@Param("competitionId") Integer competitionId, @Param("competitionName") String competitionName);
    // 根据竞赛Id删除竞赛
    public int deleteByCompetitionId(int CompetitionId);
    // 添加竞赛
    public int addCompetition(Competition competition);
    // 根据竞赛Id更新竞赛
    public int updateCompetition(Competition competition);
}
