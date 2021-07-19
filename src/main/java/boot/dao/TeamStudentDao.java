package boot.dao;

import boot.bean.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamStudentDao {
    // 查询满足条件的所有成员
    public List<TeamStudent> findAllTeamStudent(int teamId);
    // 根据teamId，studentNumber删除团队成员
    public int deleteByTeamIdStudentNumber(@Param("teamId") Integer teamId, @Param("studentNumber") String studentNumber);
    // 添加团队成员
    public int addTeamStudent(TeamStudent teamStudent);
    // 更新团队
    public int updateTeamStudent(TeamStudent teamStudent);
    // 移出团队队长
    public int removeCaptain(Integer teamId);
    // 提升为团队队长
    public int studentCaptain(Integer teamId, String studentNumber);
}
