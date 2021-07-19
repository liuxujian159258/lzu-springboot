package boot.dao;

import boot.bean.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamDao {
    // 查询满足条件的所有团队
    public List<Team> findAllTeam();
    // 根据团队编号或者团队名称查询
    public List<Team> findTeamByTeamIdOrName(@Param("teamId") Integer teamId, @Param("teamName") String teamName);
    // 根据团队编号删除团队
    public int deleteTeamByTeamId(int teamId);
    // 用户注册新团队
    public int addTeam(Team team);
    // 根据团队Id修改团队信息
    public int updateTeam(Team team);
    // 判断团队是否存在
    public Team existTeam(@Param("teamName") String teamName);

}
