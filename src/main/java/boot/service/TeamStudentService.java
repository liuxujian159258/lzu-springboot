package boot.service;

import boot.bean.TeamStudent;
import java.util.List;

public interface TeamStudentService {
    // 查询满足条件的所有成员
    public List<TeamStudent> findAllTeamStudent(int teamId);
    // 根据teamId，studentNumber删除团队成员
    public boolean deleteByTeamIdStudentNumber(Integer teamId, String studentNumber);
    // 添加团队成员
    public boolean addTeamStudent(TeamStudent teamStudent);
    // 更新团队
    public boolean updateTeamStudent(TeamStudent teamStudent);
    // 移出团队队长
    public boolean removeCaptain(Integer teamId);
    // 提升为团队队长
    public boolean studentCaptain(Integer teamId, String studentNumber);
}
