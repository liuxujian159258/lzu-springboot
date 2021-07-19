package boot.service;

import boot.bean.Team;
import java.util.List;

public interface TeamService {
    // 查询满足条件的所有团队
    public List<Team> findAllTeam();
    // 根据团队编号或者团队名称查询
    public List<Team> findTeamByTeamIdOrName(Integer teamId, String teamName);
    // 根据团队编号删除团队
    public boolean deleteTeamByTeamId(int teamId);
    // 用户注册新团队
    public boolean addTeam(Team team);
    // 根据团队Id修改团队信息
    public boolean updateTeam(Team team);
    // 判断团队是否存在
    public boolean existTeam(String teamName);
}
