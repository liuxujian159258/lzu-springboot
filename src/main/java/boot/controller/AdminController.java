package boot.controller;

import boot.bean.*;
import boot.exception.AffectedRowsException;
import boot.service.AdminService;
import boot.service.StudentService;
import boot.util.*;
import boot.vo.ResultVO;
import boot.vo.admin.*;
import boot.vo.request.QueryInfoStudentsScoresVO;
import boot.vo.request.QueryInfoTeamsScoresVO;
import boot.vo.request.QueryInfoVO;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // AdminService
    @Resource
    private AdminService adminService;
    // 文件上传目录
    @Value("${file.absolutePath}")
    private String absolutePath;
    // 文件上传服务器地址
    @Value("${file.uploadIP}")
    private String uploadIP;

    public AdminController() {
    }


    // 管理员登陆验证接口
    @PostMapping("/login")
    public ResultVO AdminLogin(Admin admin) {
        System.out.println(admin.getPassword());
        System.out.println(admin.getUsername());
        ResultVO resultVO;
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            Map<String, String> payload = new HashMap<>();
            boolean adminServiceByUserName = adminService.findByUserName(admin.getUsername(), admin.getPassword());
            if (adminServiceByUserName) {
                payload.put("userName", admin.getUsername());
                payload.put("userRole", "admin");
                // 生成JWT的token
                String token = JwtUtil.getToken(payload);
                hashMap.put("token", token);
                resultVO = ResultVoUtil.success(hashMap, "认证成功", 200);
            } else {
                resultVO = ResultVoUtil.error("认证失败", 400);
            }
        } catch (Exception e) {
            resultVO = ResultVoUtil.error("认证失败", 400);
        }
        return resultVO;
    }

    // 获取用户信息列表或者学生用户学号、姓名查询
    @GetMapping("/students")
    public ResultVO adminStudents (QueryInfoVO queryInfo) {
        ResultVO resultVO;
        try{
            System.out.println(queryInfo);
            Map<String, Object> map = new HashMap<>();
            PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
            if (queryInfo.getQuery().isEmpty()) {
                List<Student> studentsVOS = adminService.adminStudentsGet(null, null);
                PageInfo<Student> studentsVOPageInfo = new PageInfo<>(studentsVOS);
                map.put("students", studentsVOPageInfo.getList());
                map.put("total", studentsVOPageInfo.getTotal());
                map.put("pageNum", studentsVOPageInfo.getPageNum());
                resultVO = ResultVoUtil.success(map, "获取所有用户信息成功", 200);
            } else {
                try{
                    Long l = Long.parseLong(queryInfo.getQuery().trim());
                    System.out.println(l);
                    List<Student> studentsVOS = adminService.adminStudentsGet(queryInfo.getQuery(), null);
                    PageInfo<Student> studentsVOPageInfo = new PageInfo<>(studentsVOS);
                    map.put("students", studentsVOPageInfo.getList());
                    map.put("total", studentsVOPageInfo.getTotal());
                    map.put("pageNum", studentsVOPageInfo.getPageNum());
                    resultVO = ResultVoUtil.success(map, "获取所有用户信息成功", 200);
                }catch (NumberFormatException e){
                    System.out.println("用户输入的为姓名:"+queryInfo.getQuery());
                    List<Student> studentsVOS = adminService.adminStudentsGet(null, queryInfo.getQuery());
                    PageInfo<Student> studentsVOPageInfo = new PageInfo<>(studentsVOS);
                    map.put("students", studentsVOPageInfo.getList());
                    map.put("total", studentsVOPageInfo.getTotal());
                    map.put("pageNum", studentsVOPageInfo.getPageNum());
                    resultVO = ResultVoUtil.success(map, "获取所有用户信息成功", 200);
                }
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("获取所有用户信息失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取用户竞赛列表数据或者学生用户学号、姓名查询
    @GetMapping("/students/competitions")
    public ResultVO adminStudentsCompetitionsGet (QueryInfoVO queryInfo) {
        ResultVO resultVO;
        try{
            System.out.println(queryInfo);
            Map<String, Object> map = new HashMap<>();
            PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
            if (queryInfo.getQuery().isEmpty()) {
                List<StudentsVO> studentsVOS = adminService.adminStudentsCompetitionsGet(null, null);
                PageInfo<StudentsVO> studentsVOPageInfo = new PageInfo<>(studentsVOS);
                map.put("students", studentsVOPageInfo.getList());
                map.put("total", studentsVOPageInfo.getTotal());
                map.put("pageNum", studentsVOPageInfo.getPageNum());
                resultVO = ResultVoUtil.success(map, "获取用户竞赛信息成功", 200);
            } else {
                try{
                    Long l = Long.parseLong(queryInfo.getQuery().trim());
                    System.out.println(l);
                    List<StudentsVO> studentsVOS = adminService.adminStudentsCompetitionsGet(queryInfo.getQuery(), null);
                    PageInfo<StudentsVO> studentsVOPageInfo = new PageInfo<>(studentsVOS);
                    map.put("students", studentsVOPageInfo.getList());
                    map.put("total", studentsVOPageInfo.getTotal());
                    map.put("pageNum", studentsVOPageInfo.getPageNum());
                    resultVO = ResultVoUtil.success(map, "获取用户竞赛信息成功", 200);
                }catch (NumberFormatException e){
                    System.out.println("用户输入的为姓名:"+queryInfo.getQuery());
                    List<StudentsVO> studentsVOS = adminService.adminStudentsCompetitionsGet(null, queryInfo.getQuery());
                    PageInfo<StudentsVO> studentsVOPageInfo = new PageInfo<>(studentsVOS);
                    map.put("students", studentsVOPageInfo.getList());
                    map.put("total", studentsVOPageInfo.getTotal());
                    map.put("pageNum", studentsVOPageInfo.getPageNum());
                    resultVO = ResultVoUtil.success(map, "获取用户竞赛信息成功", 200);
                }
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("获取用户竞赛信息失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 修改用户信息
    @PutMapping("/students")
    public ResultVO adminStudents (String studentNumber, String studentEmail, String studentPhone) {
        ResultVO resultVO;
        Map<String, Object> map = new HashMap<>();
        try{
            System.out.println(studentNumber + ": " + studentEmail + ": " + studentPhone);
            if (adminService.adminStudentsPut(studentNumber, studentEmail, studentPhone)) {
                map.put("studentNumber", studentNumber);
                map.put("studentEmail", studentEmail);
                map.put("studentPhone", studentPhone);
                resultVO = ResultVoUtil.success(map, "修改用户信息成功", 200);
            } else {
                resultVO = ResultVoUtil.error("修改用户信息失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("修改用户信息失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 删除用户
    @DeleteMapping("/students")
    public ResultVO adminStudentsDelete (String studentNumber) {
        ResultVO resultVO;
        Map<String, Object> map = new HashMap<>();
        try{
            System.out.println(studentNumber);
            if (adminService.adminStudentsDelete(studentNumber)) {
                map.put("studentNumber", studentNumber);
                resultVO = ResultVoUtil.success(map, "删除用户成功", 200);
            } else {
                resultVO = ResultVoUtil.error("删除用户失败", 400);
            }
        }catch (Exception e) {
                resultVO = ResultVoUtil.error("删除用户失败", 500);
        }
        return resultVO;
    }

    // 用户信息下载
    @GetMapping("/students/download")
    public ResultVO adminStudentsDownload (String competitionName, Integer studentTime) {
        ResultVO resultVO;
        Map<String, Object> map = new HashMap<>();
        try{
            System.out.println(competitionName+": "+studentTime);
            List<StudentsDownloadVO> studentsVOS = adminService.adminStudentsDownload(competitionName, studentTime);
            map.put("studentsVOS", studentsVOS);
            resultVO = ResultVoUtil.success(map, "文件信息获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("用户信息下载失败", 200);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 以文件形式下载用户信息
    @GetMapping("/students/download/files")
    public void adminStudentsDownloadFiles (HttpServletResponse response, String competitionName, Integer studentTime){
        List<Map<String, Object>> dataList;
        try{
            System.out.println(competitionName+": "+studentTime);
            List<StudentsDownloadVO> studentsVOS = adminService.adminStudentsDownload(competitionName, studentTime);
            String sTitle = "学号,姓名,性别,学院,班级,年纪,邮箱,手机号码,竞赛编号,竞赛名称,竞赛成绩,竞赛时间";
            String fName = competitionName+"_"+studentTime+"_";
            String mapKey = "studentNumber,studentPassword,studentName,studentSex,studentCollege,studentClass,studentGrade,studentEmail,studentPhone,competitionId,competitionName,studentScore,studentTime";
            dataList = new ArrayList<>();
            Map<String, Object> map;
            for (StudentsDownloadVO student : studentsVOS) {
                map = new HashMap<>();
                map.put("studentNumber",student.getStudentNumber());
                map.put("studentName", student.getStudentName());
                map.put("studentSex", student.getStudentSex());
                map.put("studentCollege", student.getStudentCollege());
                map.put("studentClass", student.getStudentClass());
                map.put("studentGrade", student.getStudentGrade());
                map.put("studentEmail", student.getStudentEmail());
                map.put("studentPhone", student.getStudentPhone());
                map.put("competitionId",student.getCompetitionId());
                map.put("competitionName",student.getCompetitionName());
                map.put("studentScore",student.getStudentScore());
                map.put("studentTime",student.getStudentTime());
                dataList.add(map);
            }
            OutputStream os = response.getOutputStream();
            CSVUtil.responseSetProperties(fName, response);
            boolean b = CSVUtil.doExport(dataList, sTitle, mapKey, os);
            if(b == false) {
                System.out.println("下载错误");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("下载成功");
    }

    // 获取所有团队信息列表
    @GetMapping("/teams")
    public ResultVO adminTeamsGet (QueryInfoVO queryInfo) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(queryInfo);
            PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
            if (queryInfo.getQuery().isEmpty()) {
                List<TeamsVO> teamsVOS = adminService.adminTeamsCompetitionsGet(null, null);
                PageInfo<TeamsVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                System.out.println(teamsVOS);
                map.put("teams", teamsVOPageInfo.getList());
                map.put("pageNum", teamsVOPageInfo.getPageNum());
                map.put("total", teamsVOPageInfo.getTotal());
                resultVO = ResultVoUtil.success(map, "团队信息获取成功", 200);
            }else {
                try {
                    Integer l = Integer.parseInt(queryInfo.getQuery().trim());
                    System.out.println(l);
                    List<TeamsVO> teamsVOS = adminService.adminTeamsCompetitionsGet(l, null);
                    PageInfo<TeamsVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                    map.put("teams", teamsVOPageInfo.getList());
                    map.put("pageNum", teamsVOPageInfo.getPageNum());
                    map.put("total", teamsVOPageInfo.getTotal());
                    resultVO = ResultVoUtil.success(map, "团队信息获取成功", 200);
                }catch (NumberFormatException e) {
                    List<TeamsVO> teamsVOS = adminService.adminTeamsCompetitionsGet(null, queryInfo.getQuery());
                    PageInfo<TeamsVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                    map.put("teams", teamsVOPageInfo.getList());
                    map.put("pageNum", teamsVOPageInfo.getPageNum());
                    map.put("total", teamsVOPageInfo.getTotal());
                    resultVO = ResultVoUtil.success(map, "团队信息获取成功", 200);
                }
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("团队信息获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 修改团队信息
    @PutMapping("/teams")
    public ResultVO adminTeamsPut (Integer teamId, String teamName, String teamCollege) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(teamId+": "+teamName+": "+teamCollege);
            if (adminService.adminTeamsPut(teamId, teamName, teamCollege)) {
                map.put("teamId", teamId);
                map.put("teamName", teamName);
                map.put("teamCollege", teamCollege);
                resultVO = ResultVoUtil.success(map, "团队信息修改成功", 200);
            }else {
                resultVO = ResultVoUtil.error("团队信息修改失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("团队信息修改失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 删除团队
    @DeleteMapping("/teams")
    public ResultVO adminTeamsDelete (Integer teamId) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminTeamsDelete(teamId)){
                map.put("teamId", teamId);
                resultVO = ResultVoUtil.success(map, "删除团队成功", 200);
            }else {
                resultVO = ResultVoUtil.error("删除团队失败", 400);
            }

        }catch (Exception e){
          resultVO = ResultVoUtil.error("删除团队失败", 500);
          e.printStackTrace();
        }
        return resultVO;
    }

    // 团队信息下载
    @GetMapping("/teams/download")
    public ResultVO adminTeamsDownload (String competitionName, Integer teamTime) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<TeamsDownloadVO> teamsDownloadVOS = adminService.adminTeamsDownload(competitionName, teamTime);
            map.put("teamsDownloadVOS", teamsDownloadVOS);
            resultVO = ResultVoUtil.success(map, "团队信息下载成功", 200);
        }catch (Exception e){
            resultVO = ResultVoUtil.error("团队信息下载失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 添加团队
    @PostMapping("/teams")
    public ResultVO adminTeamsPost (String teamName, String teamStudent, String teamCollege) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            String[] split = teamStudent.split(",");
            Team team = new Team(null, teamName, teamCollege, 0);
            List<TeamStudent> teamStudentList = new ArrayList<>();
            int k=0;
            for (int i = 0; i < split.length-1; i=i+2) {
                int j=i+1;
                if (k==0) {
                    System.out.println(split[i] + ": " + split[j]+": 1");
                    teamStudentList.add(new TeamStudent(null, split[i], split[j], 1));
                    k=1;
                }else {
                    System.out.println(split[i] + ": " + split[j]+": 0");
                    teamStudentList.add(new TeamStudent(null, split[i], split[j], 0));
                }
            }
            if (adminService.adminTeamsPost(team, teamStudentList)){
                map.put("teamName", teamName);
                map.put("teamCollege", teamCollege);
                resultVO = ResultVoUtil.success(map, "团队添加成功", 200);
                System.out.println(team);
                System.out.println(teamStudentList);
            }else {
                resultVO = ResultVoUtil.error("团队添加失败", 400);
            }
        }catch (AffectedRowsException e){
            resultVO = ResultVoUtil.error("此团队已经被注册,团队添加失败", 400);
        }
        catch (Exception e) {
            if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                resultVO = ResultVoUtil.error("团队成员重复，竞赛报名失败", 400);
            }else if (e.getCause() instanceof AffectedRowsException) {
                resultVO = ResultVoUtil.error("此团队已经被注册,团队添加失败", 400);
            } else {
                resultVO = ResultVoUtil.error("竞赛报名失败", 500);
            }
            e.printStackTrace();
        }
        return resultVO;
    }

    // 修改团队队长
    @PutMapping("/teams/teamId/studentNumber")
    public ResultVO adminTeamsTeamIdStudentNumberPut (Integer teamId, String studentNumber){
        ResultVO resultVO;
        try{
            System.out.println(teamId+" : "+studentNumber);
            Map<String, Object> map = new HashMap<>();
            boolean b = adminService.adminTeamsTeamIdStudentNumberPut(teamId, studentNumber);
            if (b){
                map.put("teamId", teamId);
                map.put("studentNumber", studentNumber);
                resultVO = ResultVoUtil.success(map, "修改团队队长成功", 200);
            }else {
                resultVO = ResultVoUtil.error("修改团队队长失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("修改团队队长失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取未报名竞赛团队信息
    @GetMapping("/teams/NotSignUp")
    public ResultVO adminTeamsNotSignUpGet (QueryInfoVO queryInfo) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(queryInfo);
            PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
            if (queryInfo.getQuery().isEmpty()) {
                List<TeamsNotSignUpVO> teamsVOS = adminService.adminTeamsNotSignUpGet(null, null);
                PageInfo<TeamsNotSignUpVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                System.out.println(teamsVOS);
                map.put("teams", teamsVOPageInfo.getList());
                map.put("pageNum", teamsVOPageInfo.getPageNum());
                map.put("total", teamsVOPageInfo.getTotal());
                resultVO = ResultVoUtil.success(map, "未报名竞赛团队信息获取成功", 200);
            }else {
                try {
                    Integer l = Integer.parseInt(queryInfo.getQuery().trim());
                    System.out.println(l);
                    List<TeamsNotSignUpVO> teamsVOS = adminService.adminTeamsNotSignUpGet(l, null);
                    PageInfo<TeamsNotSignUpVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                    map.put("teams", teamsVOPageInfo.getList());
                    map.put("pageNum", teamsVOPageInfo.getPageNum());
                    map.put("total", teamsVOPageInfo.getTotal());
                    resultVO = ResultVoUtil.success(map, "未报名竞赛团队信息获取成功", 200);
                }catch (NumberFormatException e) {
                    List<TeamsNotSignUpVO> teamsVOS = adminService.adminTeamsNotSignUpGet(null, queryInfo.getQuery());
                    PageInfo<TeamsNotSignUpVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                    map.put("teams", teamsVOPageInfo.getList());
                    map.put("pageNum", teamsVOPageInfo.getPageNum());
                    map.put("total", teamsVOPageInfo.getTotal());
                    resultVO = ResultVoUtil.success(map, "未报名竞赛团队信息获取成功", 200);
                }
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("未报名竞赛团队信息获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取已删除竞赛团队信息
    @GetMapping("/teams/delete/cancel")
    public ResultVO adminTeamsDeleteGet (QueryInfoVO queryInfo) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(queryInfo);
            PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
            if (queryInfo.getQuery().isEmpty()) {
                List<TeamsVO> teamsVOS = adminService.adminTeamsDeleteGet(null, null);
                PageInfo<TeamsVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                System.out.println(teamsVOS);
                map.put("teams", teamsVOPageInfo.getList());
                map.put("pageNum", teamsVOPageInfo.getPageNum());
                map.put("total", teamsVOPageInfo.getTotal());
                resultVO = ResultVoUtil.success(map, "获取已经删除的团队信息成功", 200);
            }else {
                try {
                    Integer l = Integer.parseInt(queryInfo.getQuery().trim());
                    System.out.println(l);
                    List<TeamsVO> teamsVOS = adminService.adminTeamsDeleteGet(null, null);
                    PageInfo<TeamsVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                    map.put("teams", teamsVOPageInfo.getList());
                    map.put("pageNum", teamsVOPageInfo.getPageNum());
                    map.put("total", teamsVOPageInfo.getTotal());
                    resultVO = ResultVoUtil.success(map, "获取已经删除的团队信息成功", 200);
                }catch (NumberFormatException e) {
                    List<TeamsVO> teamsVOS = adminService.adminTeamsDeleteGet(null, null);
                    PageInfo<TeamsVO> teamsVOPageInfo = new PageInfo<>(teamsVOS);
                    map.put("teams", teamsVOPageInfo.getList());
                    map.put("pageNum", teamsVOPageInfo.getPageNum());
                    map.put("total", teamsVOPageInfo.getTotal());
                    resultVO = ResultVoUtil.success(map, "获取已经删除的团队信息成功", 200);
                }
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("获取已经删除的团队信息失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 未报名竞赛团队永久删除
    @DeleteMapping("/teams/delete/forever/notSignUp")
    public ResultVO adminTeamsDeleteForeverNotSignUpDelete (Integer teamId) {
        ResultVO resultVO;
        try{
            System.out.println(teamId);
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminTeamsDeleteForeverNotSignUpDelete(teamId)){
                map.put("teamId", teamId);
                resultVO = ResultVoUtil.success(map, "删除团队成功", 200);
            }else {
                resultVO = ResultVoUtil.error("删除团队成功", 400);
            }

        }catch (Exception e) {
            resultVO = ResultVoUtil.error("删除团队失败",500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 逻辑删除团队永久删除
    @DeleteMapping("/teams/delete/forever/team")
    public ResultVO adminTeamsDeleteForeverTeamDelete (Integer teamId) {
        ResultVO resultVO;
        try{
            System.out.println(teamId);
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminTeamsDeleteForeverTeamDelete(teamId)){
                map.put("teamId", teamId);
                resultVO = ResultVoUtil.success(map, "删除团队成功", 200);
            }else {
                resultVO = ResultVoUtil.error("删除团队成功", 400);
            }

        }catch (Exception e) {
            resultVO = ResultVoUtil.error("删除团队失败",500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 恢复逻辑删除的团队
    @PutMapping("/teams/delete/cancel")
    public ResultVO adminTeamsDeleteCancelPut (Integer teamId) {
        ResultVO resultVO;
        try{
            System.out.println(teamId);
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminTeamsDeleteCancelPut(teamId)){
                map.put("teamId", teamId);
                resultVO = ResultVoUtil.success(map, "团队恢复成功", 200);
            }else {
                resultVO = ResultVoUtil.error("团队恢复成功", 400);
            }

        }catch (Exception e) {
            resultVO = ResultVoUtil.error("团队恢复失败",500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 移出团队队员
    @DeleteMapping("/teams/students")
    @Transactional(rollbackFor = AffectedRowsException.class)
    public ResultVO adminTeamsStudentsDelete (Integer teamId, String studentNumber) {
        ResultVO resultVO;
        try{
            System.out.println(teamId+" : "+studentNumber);
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminTeamsStudentsDelete(teamId, studentNumber)){
                map.put("teamId", teamId);
                map.put("studentNumber", studentNumber);
                resultVO = ResultVoUtil.success(map, "团队队员移出成功", 200);
            }else {
                resultVO = ResultVoUtil.error("团队队员移出失败", 400);
            }

        }catch (Exception e) {
            resultVO = ResultVoUtil.error("团队队员移出失败",500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取所有竞赛信息列表
    @GetMapping("/competitions")
    public ResultVO adminCompetitionsGet () {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<CompetitionsHtmlVO> competitions = adminService.adminCompetitionsGet();
            map.put("competitions", competitions);
            resultVO = ResultVoUtil.success(map, "竞赛信息列表获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛信息获取列表失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 根据竞赛编号获取竞赛
    @GetMapping("/competitions/competitionId")
    public ResultVO adminCompetitionsCompetitionIdGet (Integer competitionId) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            CompetitionsHtmlVO competitions = adminService.adminCompetitionsCompetitionIdGet(competitionId);
            map.put("competitions", competitions);
            resultVO = ResultVoUtil.success(map, "竞赛信息获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛信息列表失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 图片上传接口
    @PostMapping("/competitions/images")
    public ResultVO AdminImagesPost(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ResultVO resultVO;
        try {
            System.out.println(new File(absolutePath + multipartFile.getOriginalFilename()).getAbsolutePath());
            multipartFile.transferTo(new File(absolutePath + multipartFile.getOriginalFilename()));
            System.out.println(multipartFile.getOriginalFilename());
            Map<String, Object> map = new HashMap<>();
            String filePath = uploadIP + multipartFile.getOriginalFilename();
            map.put("path", filePath);
            resultVO = ResultVoUtil.success(map, "图片上传成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("图片上传失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 修改竞赛信息
    @PutMapping("/competitions")
    @Transactional(rollbackFor = RuntimeException.class)
    public ResultVO adminCompetitionsPut (CompetitionsHtmlVO competition) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (competition.getCompetitionHtml() == null || competition.getCompetitionHtml().isEmpty()) {
                CompetitionsHtmlVO oldCompetition = adminService.adminCompetitionsCompetitionIdGet(competition.getCompetitionId());
                if (oldCompetition.getCompetitionName().equals(competition.getCompetitionName())) {
                    if (adminService.adminCompetitionsPut(new Competition(competition.getCompetitionId(), competition.getCompetitionName(), competition.getCompetitionType(), competition.getCompetitionPeople(), competition.getCompetitionDepartment(), competition.getIsDelete()))) {
                        map.put("competitionId", competition.getCompetitionId());
                        map.put("competitionName", competition.getCompetitionName());
                        resultVO = ResultVoUtil.success(map, "竞赛信息修改成功", 200);
                    }else {
                        resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
                    }
                }else {
                    if (oldCompetition.getCompetitionType() == 0) {
                        System.out.println("更新单人竞赛成绩竞赛名称");
                        if (adminService.adminCompetitionsPut(new Competition(competition.getCompetitionId(), competition.getCompetitionName(), competition.getCompetitionType(), competition.getCompetitionPeople(), competition.getCompetitionDepartment(), competition.getIsDelete()))
                        && adminService.studentScoreCompetitionName(competition.getCompetitionId(), competition.getCompetitionName())) {
                            map.put("competitionId", competition.getCompetitionId());
                            map.put("competitionName", competition.getCompetitionName());
                            resultVO = ResultVoUtil.success(map, "竞赛信息修改成功", 200);
                        }else {
                            resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
                        }
                    }else {
                        System.out.println("更新团队竞赛成绩竞赛名称");
                        if (adminService.adminCompetitionsPut(new Competition(competition.getCompetitionId(), competition.getCompetitionName(), competition.getCompetitionType(), competition.getCompetitionPeople(), competition.getCompetitionDepartment(), competition.getIsDelete()))
                                && adminService.teamScoreCompetitionName(competition.getCompetitionId(), competition.getCompetitionName())) {
                            map.put("competitionId", competition.getCompetitionId());
                            map.put("competitionName", competition.getCompetitionName());
                            resultVO = ResultVoUtil.success(map, "竞赛信息修改成功", 200);
                        }else {
                            resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
                        }
                    }
                }
            }else {
                CompetitionsHtmlVO oldCompetition = adminService.adminCompetitionsCompetitionIdGet(competition.getCompetitionId());
                if(oldCompetition.getCompetitionName().equals(competition.getCompetitionName())) {
                    if (adminService.adminCompetitionsAllPut(competition)) {
                        map.put("competitionId", competition.getCompetitionId());
                        map.put("competitionName", competition.getCompetitionName());
                        resultVO = ResultVoUtil.success(map, "竞赛信息修改成功", 200);
                    }else {
                        resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
                    }
                }else {
                    if (oldCompetition.getCompetitionType() == 0) {
                        System.out.println("更新单人竞赛成绩竞赛名称");
                        if (adminService.adminCompetitionsAllPut(competition) && adminService.studentScoreCompetitionName(competition.getCompetitionId(), competition.getCompetitionName())) {
                            map.put("competitionId", competition.getCompetitionId());
                            map.put("competitionName", competition.getCompetitionName());
                            resultVO = ResultVoUtil.success(map, "竞赛信息修改成功", 200);
                        }else {
                            resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
                        }
                    }else {
                        System.out.println("更新团队竞赛成绩竞赛名称");
                        if (adminService.adminCompetitionsAllPut(competition) && adminService.teamScoreCompetitionName(competition.getCompetitionId(), competition.getCompetitionName())) {
                            map.put("competitionId", competition.getCompetitionId());
                            map.put("competitionName", competition.getCompetitionName());
                            resultVO = ResultVoUtil.success(map, "竞赛信息修改成功", 200);
                        }else {
                            resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
                        }
                    }
                }

            }

        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛信息修改失败", 500);
            e.printStackTrace();

        }
        return resultVO;
    }

    // 添加竞赛
    @PostMapping("/competitions")
    public ResultVO adminCompetitionsPost (CompetitionsHtmlVO competition) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminCompetitionsAllPost(competition)) {
                map.put("competitionId", competition.getCompetitionId());
                map.put("competitionName", competition.getCompetitionName());
                resultVO = ResultVoUtil.success(map, "竞赛添加成功", 200);
            }else {
                resultVO = ResultVoUtil.error("竞赛添加失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛添加失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 删除竞赛
    @DeleteMapping("/competitions")
    public ResultVO adminCompetitionsDelete (Integer competitionId) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(competitionId);
            if (adminService.adminCompetitionsDelete(competitionId)) {
                map.put("competitionId", competitionId);
                resultVO = ResultVoUtil.success(map, "竞赛删除成功", 200);
            }else {
                resultVO = ResultVoUtil.error("竞赛删除失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛删除失败", 500);
            e.printStackTrace();
        }
        return  resultVO;
    }

    // 恢复竞赛
    @DeleteMapping("/competitions/recover")
    public ResultVO adminCompetitionsRecover(Integer competitionId) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(competitionId);
            if (adminService.adminCompetitionsRecover(competitionId)) {
                map.put("competitionId", competitionId);
                resultVO = ResultVoUtil.success(map, "竞赛恢复成功", 200);
            }else {
                resultVO = ResultVoUtil.error("竞赛恢复失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛恢复失败", 500);
            e.printStackTrace();
        }
        return  resultVO;
    }

    // 修改管理员发送邮箱
    @PutMapping("/emails")
    public ResultVO adminEmailsPut (@RequestHeader("token") String adminToken, Admin admin) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(adminToken+": "+admin);
            DecodedJWT verify = JwtUtil.verify(adminToken);//验证令牌
            String userName = verify.getClaim("userName").asString();
            admin.setUsername(userName);
            if (adminService.adminEmailsPut(admin)) {
                map.put("username", admin.getUsername());
                map.put("email", admin.getEmail());
                resultVO = ResultVoUtil.success(map, "管理员邮箱修改成功", 200);
            }else {
                resultVO = ResultVoUtil.error("管理员邮箱修改失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("管理员邮箱修改失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取竞赛列表和时间列表
    @GetMapping("/emails/competitions/times")
    public ResultVO adminEmailsCompetitionsTimesGet () {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> competitionList = adminService.adminEmailsCompetitions();
            List<Map<String, Object>> timeList = adminService.adminStudentsScoreCompetitionTimeListGet();
            for (int i = 0; i < timeList.size(); i++) {
                timeList.get(i).put("timeId", i+1);
            }
            map.put("competitionList", competitionList);
            map.put("timeList", timeList);
            resultVO = ResultVoUtil.success(map, "竞赛列表和技术时间列表获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛列表和技术时间列表获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 单独用户发送邮箱信息
    @PostMapping("/emails/one")
    public ResultVO adminEmailsOne (@RequestHeader("token") String adminToken, Email email){
        ResultVO resultVO;
        try {
            System.out.println(email+": "+adminToken);
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(adminToken);//验证令牌
            String userName = verify.getClaim("userName").asString();
            Admin admin = adminService.adminEmailsGet(userName);
            email.setFrom(admin.getEmail());
            email.setFromPassword(admin.getEmailPassword());
            new Thread(()-> {
                try {
                    EmailUtil.qqEmailSend(email.getFrom(),email.getFromPassword(),email.getTo(),email.getSubject(),email.getText());
                } catch (MessagingException e) {
                    String text = "向邮箱"+email.getTo()+"发送邮件失败";
                    try {
                        EmailUtil.qqEmailSend(email.getFrom(),email.getFromPassword(),email.getTo(),email.getSubject(),text);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
            map.put("from", email.getFrom());
            map.put("to",email.getTo());
            resultVO = ResultVoUtil.success(map, "邮件发送成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("邮件发送失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 邮件群发
    @PostMapping("/emails/mass")
    public ResultVO adminEmailsMass (@RequestHeader("token") String adminToken, Email email) {
        ResultVO resultVO;
        try {
            System.out.println(email+": "+email);
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(adminToken);//验证令牌
            String userName = verify.getClaim("userName").asString();
            Admin admin = adminService.adminEmailsGet(userName);
            int i = adminService.adminEmailCompetitionType(email.getCompetitionName());
            email.setFrom(admin.getEmail());
            email.setFromPassword(admin.getEmailPassword());
            email.setCompetitionType(i);
            ThreadPoolExecutor defaultThreadPoolExecutor = ThreadPoolExecutorUtil.createDefaultThreadPoolExecutor();
            if (email.getCompetitionType()>0){
                String[] strings = adminService.adminEmailsTeam(email.getCompetitionName(), email.getCompetitionTime());
                for (String string : strings) {
                    defaultThreadPoolExecutor.execute(() -> {
                        try {
                            EmailUtil.qqEmailSend(email.getFrom(),email.getFromPassword(),string,email.getSubject(),email.getText());
                        } catch (Exception e) {
                            System.out.println("向邮箱"+string+"发送邮件失败");
                            String text = "向邮箱"+string+"发送邮件失败";
                            try {
                                EmailUtil.qqEmailSend(email.getFrom(),email.getFromPassword(),email.getFrom(),email.getSubject(),text);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                    });
                }
            }else {
                String[] strings = adminService.adminEmailsOne(email.getCompetitionName(), email.getCompetitionTime());
                for (String string : strings) {
                    defaultThreadPoolExecutor.execute(() -> {
                        try {
                            EmailUtil.qqEmailSend(email.getFrom(),email.getFromPassword(),string,email.getSubject(),email.getText());
                        } catch (Exception e) {
                            System.out.println("向邮箱"+string+"发送邮件失败");
                            String text = "向邮箱"+string+"发送邮件失败";
                            try {
                                EmailUtil.qqEmailSend(email.getFrom(),email.getFromPassword(),email.getFrom(),email.getSubject(),text);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                    });
                }
            }
            map.put("from", email.getFrom());
            map.put("competitionName", email.getCompetitionName());
            map.put("competitionTime", email.getCompetitionTime());
            resultVO = ResultVoUtil.success(map, "邮件群发成功", 200);
        }catch (Exception e){
            resultVO = ResultVoUtil.error("邮件群发失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取竞赛报名邮件模板
    @GetMapping("/emails/competitionEmails")
    public ResultVO adminEmailsCompetitionEmailsGet () {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<CompetitionEmail> competitionEmails = adminService.adminEmailsCompetitionEmailsGet();
            map.put("competitionEmails", competitionEmails);
            resultVO = ResultVoUtil.success(map, "获取竞赛报名邮件模板成功", 200);
        }catch (Exception e){
            resultVO = ResultVoUtil.error("获取竞赛报名邮件模板失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 添加竞赛报名邮件模板
    @PostMapping("/emails/competitionEmails")
    public ResultVO adminEmailsCompetitionEmailsPost (CompetitionEmail competitionEmail) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminEmailsCompetitionEmailsPost(competitionEmail)) {
                map.put("competitionId", competitionEmail.getCompetitionId());
                map.put("competitionName", competitionEmail.getCompetitionName());
                resultVO = ResultVoUtil.success(map, "添加竞赛报名邮件模板成功", 200);
            }else {
                resultVO = ResultVoUtil.error("添加竞赛报名邮件模板失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("添加竞赛报名邮件模板失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 修改竞赛报名邮件模板
    @PutMapping("/emails/competitionEmails")
    public ResultVO adminEmailsCompetitionEmailsPut (CompetitionEmail competitionEmail) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminEmailsCompetitionEmailsPut(competitionEmail)){
                map.put("competitionId", competitionEmail.getCompetitionId());
                map.put("competitionName", competitionEmail.getCompetitionName());
                resultVO = ResultVoUtil.success(map, "修改竞赛报名邮件模板成功", 200);
            }else {
                resultVO = ResultVoUtil.error("修改竞赛报名邮件模板失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("修改竞赛报名邮件模板失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取所有满足条件的用户成绩
    @GetMapping("/students/scores")
    public ResultVO adminStudentsScoresGet (QueryInfoStudentsScoresVO queryInfo) {
        ResultVO resultVO;
        Map<String, Object> map = new HashMap<>();
        try{
            System.out.println(queryInfo);
            PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
            Long.parseLong(queryInfo.getStudentNumberOrName());
            if (queryInfo.getStudentTime()>0) {
                List<StudentsDownloadVO> students = adminService.adminStudentsScoreGet(queryInfo.getStudentNumberOrName(), null, queryInfo.getStudentTime(), queryInfo.getCompetitionId());
                PageInfo<StudentsDownloadVO> pageInfo = new PageInfo<>(students);
                map.put("total", pageInfo.getTotal());
                map.put("pageNum", pageInfo.getPageNum());
                map.put("students", pageInfo.getList());
                resultVO = ResultVoUtil.success(map, "获取所有满足条件的用户成绩获取成功", 200);
            }else {
                List<StudentsDownloadVO> students = adminService.adminStudentsScoreGet(queryInfo.getStudentNumberOrName(), null, null, queryInfo.getCompetitionId());
                PageInfo<StudentsDownloadVO> pageInfo = new PageInfo<>(students);
                map.put("total", pageInfo.getTotal());
                map.put("pageNum", pageInfo.getPageNum());
                map.put("students", pageInfo.getList());
                resultVO = ResultVoUtil.success(map, "获取所有满足条件的用户成绩获取成功", 200);
            }
        }catch (NumberFormatException e) {
            if (queryInfo.getStudentTime()>0) {
                List<StudentsDownloadVO> students = adminService.adminStudentsScoreGet(null, queryInfo.getStudentNumberOrName(), queryInfo.getStudentTime(), queryInfo.getCompetitionId());
                map.put("students", students);
                PageInfo<StudentsDownloadVO> pageInfo = new PageInfo<>(students);
                map.put("total", pageInfo.getTotal());
                map.put("pageNum", pageInfo.getPageNum());
                map.put("students", pageInfo.getList());
                resultVO = ResultVoUtil.success(map, "获取所有满足条件的用户成绩获取成功", 200);
            }else {
                List<StudentsDownloadVO> students = adminService.adminStudentsScoreGet(null, queryInfo.getStudentNumberOrName(), null, queryInfo.getCompetitionId());
                map.put("students", students);
                PageInfo<StudentsDownloadVO> pageInfo = new PageInfo<>(students);
                map.put("total", pageInfo.getTotal());
                map.put("pageNum", pageInfo.getPageNum());
                map.put("students", pageInfo.getList());
                resultVO = ResultVoUtil.success(map, "获取所有满足条件的用户成绩获取成功", 200);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("获取所有满足条件的用户成绩获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取单人竞赛列表和竞赛时间列表
    @GetMapping("/students/scores/competitions/times")
    public ResultVO adminStudentsScoresCompetitionsTimesGet (){
        ResultVO resultVO;
        try {
           Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> competitionList = adminService.adminStudentsScoreCompetitionListGet();
            List<Map<String, Object>> timeList = adminService.adminStudentsScoreCompetitionTimeListGet();
            for (int i = 0; i < timeList.size(); i++) {
                timeList.get(i).put("timeId", i+1);
            }
            map.put("competitionList", competitionList);
            map.put("timeList", timeList);
            resultVO = ResultVoUtil.success(map, "单人竞赛列表和技术时间列表获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("单人竞赛列表和技术时间列表获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 添加、修改用户成绩
    @PutMapping("/students/scores")
    public ResultVO adminStudentsScorePut (String studentNumber, Double studentScore, Integer competitionId, Integer studentTime) {
        ResultVO resultVO;
        try {
            System.out.println(studentNumber+": " +studentScore+ ": " +competitionId+":" +studentTime);
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminStudentsScorePut(studentNumber, studentScore, competitionId, studentTime)) {
                map.put("studentNumber", studentNumber);
                map.put("studentScore", studentScore);
                map.put("competitionId", competitionId);
                resultVO = ResultVoUtil.success(map, "添加、修改用户成绩成功", 200);
            }else {
                resultVO = ResultVoUtil.error("添加、修改用户成绩失败", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("添加、修改用户成绩失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 邮件通知个人成绩
    @PostMapping("/students/scores/email")
    public ResultVO adminStudentsScoresEmailPost (@RequestHeader("token") String adminToken, String studentNumber, String studentName, Double studentScore, Integer competitionId, String competitionName, Integer studentTime, String studentEmail) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            System.out.println(adminToken + ": " + studentNumber +": " + studentName +": " + studentScore +": " + competitionId +": " + competitionName +": " +studentTime +": " + studentEmail);
            DecodedJWT verify = JwtUtil.verify(adminToken);//验证令牌
            String userName = verify.getClaim("userName").asString();
            Admin admin = adminService.adminEmailsGet(userName);
            if (admin != null) {
                System.out.println(admin);
                String subject = "兰州大学信息科技活动月"+competitionName+"竞赛成绩通知";
                String text = "学号：" + studentNumber + ",姓名：" + studentName + "同学您好，您在" + studentTime +"年度的竞赛编号：" + competitionId +"，竞赛名称：" + competitionName + "，竞赛成绩为"+studentScore;
                new Thread(() -> {
                    try {
                        EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), studentEmail, subject, text);
                    } catch (MessagingException e) {
                        String errorText = "向学号：" + studentNumber + ",姓名：" + studentName + "同学，邮箱:" + studentEmail +"发送成绩通知邮件失败";
                        try {
                            EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), admin.getEmail(), subject, errorText);
                        } catch (MessagingException ex) {
                            ex.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }).start();
                map.put("studentNumber", studentNumber);
                map.put("studentScore", studentScore);
                map.put("competitionName", competitionName);
                map.put("studentEmail", studentEmail);
                resultVO = ResultVoUtil.success(map, "用户成绩邮件后台已经发送", 200);
            } else {
                resultVO = ResultVoUtil.error("管理员邮箱未完善，发送失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("用户成绩邮件后台发送失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取所有满足条件的团队成绩
    @GetMapping("/teams/scores")
    public ResultVO adminTeamsScoresGet (QueryInfoTeamsScoresVO queryInfo) {
        ResultVO resultVO;
        Map<String, Object> map = new HashMap<>();
        try{
            PageHelper.startPage(queryInfo.getPageNum(), queryInfo.getPageSize());
            Integer teamId = Integer.parseInt(queryInfo.getTeamIdOrName());
            if (queryInfo.getTeamTime()>0){
                List<TeamsScoresVO> teamsScoresVOS = adminService.adminTeamsScoresGet(teamId, null, queryInfo.getTeamTime(), queryInfo.getCompetitionId());
                PageInfo<TeamsScoresVO> teamsScoresVOPageInfo = new PageInfo<>(teamsScoresVOS);
                map.put("total", teamsScoresVOPageInfo.getTotal());
                map.put("pageNum", teamsScoresVOPageInfo.getPageNum());
                map.put("teams", teamsScoresVOPageInfo.getList());
                resultVO = ResultVoUtil.success(map, "满足条件的团队成绩获取成功", 200);
            }else {
                List<TeamsScoresVO> teamsScoresVOS = adminService.adminTeamsScoresGet(teamId, null, null, queryInfo.getCompetitionId());
                PageInfo<TeamsScoresVO> teamsScoresVOPageInfo = new PageInfo<>(teamsScoresVOS);
                map.put("total", teamsScoresVOPageInfo.getTotal());
                map.put("pageNum", teamsScoresVOPageInfo.getPageNum());
                map.put("teams", teamsScoresVOPageInfo.getList());
                resultVO = ResultVoUtil.success(map, "满足条件的团队成绩获取成功", 200);
            }
        }catch (NumberFormatException e){
            if (queryInfo.getTeamTime()>0){
                List<TeamsScoresVO> teamsScoresVOS = adminService.adminTeamsScoresGet(null, queryInfo.getTeamIdOrName(), queryInfo.getTeamTime(), queryInfo.getCompetitionId());
                PageInfo<TeamsScoresVO> teamsScoresVOPageInfo = new PageInfo<>(teamsScoresVOS);
                map.put("total", teamsScoresVOPageInfo.getTotal());
                map.put("pageNum", teamsScoresVOPageInfo.getPageNum());
                map.put("teams", teamsScoresVOPageInfo.getList());
                resultVO = ResultVoUtil.success(map, "满足条件的团队成绩获取成功", 200);
            }else {
                List<TeamsScoresVO> teamsScoresVOS = adminService.adminTeamsScoresGet(null, queryInfo.getTeamIdOrName(), null, queryInfo.getCompetitionId());
                PageInfo<TeamsScoresVO> teamsScoresVOPageInfo = new PageInfo<>(teamsScoresVOS);
                map.put("total", teamsScoresVOPageInfo.getTotal());
                map.put("pageNum", teamsScoresVOPageInfo.getPageNum());
                map.put("teams", teamsScoresVOPageInfo.getList());
                resultVO = ResultVoUtil.success(map, "满足条件的团队成绩获取成功", 200);
            }

        }catch (Exception e){
                resultVO = ResultVoUtil.error("满足条件的团队成绩获取失败", 500);
                e.printStackTrace();
        }
        System.out.println(queryInfo);
        return resultVO;
    }

    // 获取团队竞赛列表和竞赛时间列表
    @GetMapping("/teams/scores/competitions/times")
    public ResultVO adminTeamsScoresCompetitionsTimesGet (){
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> competitionList = adminService.adminTeamsScoreCompetitionListGet();
            List<Map<String, Object>> timeList = adminService.adminStudentsScoreCompetitionTimeListGet();
            for (int i = 0; i < timeList.size(); i++) {
                timeList.get(i).put("timeId", i+1);
            }
            map.put("competitionList", competitionList);
            map.put("timeList", timeList);
            resultVO = ResultVoUtil.success(map, "团队竞赛列表和技术时间列表获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("团队竞赛列表和技术时间列表获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 添加、修改团队成绩
    @PutMapping("/teams/scores")
    public ResultVO adminTeamsScoresPut (Integer teamId, Float teamScore, Integer competitionId, Integer teamTime) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            System.out.println(teamId+ ": " +teamScore+ ": "+ competitionId + ": "+ teamTime);
            if (adminService.adminTeamsScoresPut(teamId,teamScore,competitionId, teamTime)){
                map.put("teamId", teamId);
                map.put("teamScore", teamScore);
                resultVO = ResultVoUtil.success(map, "团队成绩修改成功", 200);
            }else {
                resultVO = ResultVoUtil.error("团队成绩修改失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("团队成绩修改失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 邮件通知团队成绩
    @PostMapping("/teams/scores/emails")
    public ResultVO adminTeamsScoresEmailsPost (@RequestHeader("token") String adminToken, Integer teamId, String teamName, Float teamScore, Integer competitionId, String competitionName, Integer teamTime) {
        ResultVO resultVO ;
        try {
            System.out.println(adminToken + ": " + teamId + ": " + teamName + ": " + teamScore + ": " + competitionId + ": " + competitionName  + ": " + teamTime);
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(adminToken);//验证令牌
            String userName = verify.getClaim("userName").asString();
            Admin admin = adminService.adminEmailsGet(userName);
            if (admin != null) {
                ThreadPoolExecutor defaultThreadPoolExecutor = ThreadPoolExecutorUtil.createDefaultThreadPoolExecutor();
                String[] strings = adminService.adminEmailsTeamsGet(teamId);
                if (strings == null || strings.length ==0) {
                    resultVO = ResultVoUtil.error("团队成员的邮箱未完善，邮件发送失败", 400);
                }else {
                    String subject = "兰州大学信息科技活动月"+competitionName+"竞赛成绩通知";
                    String text = "您好，您所在的团队，团队编号：" + teamId + ",团队名称：" + teamName + "在" + teamTime + "年度的竞赛编号：" + competitionId +"，竞赛名称：" + competitionName + "，竞赛成绩为" +teamScore;
                    for (String string : strings) {
                        defaultThreadPoolExecutor.execute(() -> {
                            try {
                                EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), string, subject, text);
                            } catch (Exception e) {
                                System.out.println("向邮箱"+string+"发送邮件失败");
                                String errorText = "团队编号：" + teamId + ",团队名称：" + teamName+",向团队成员邮箱"+string+"发送邮件失败";
                                try {
                                    EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), admin.getEmail(), subject, errorText);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                e.printStackTrace();
                            }
                        });
                    }
                    resultVO = ResultVoUtil.success(map, "团队成绩邮件发送成功", 200);
                }
            }else {
                resultVO = ResultVoUtil.error("管理员邮箱未完善，发送失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("团队成绩邮件发送失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 按竞赛分类返回数据
    @GetMapping("/statistics/competitionName")
    public ResultVO adminStatisticsCompetitionNameGet (){
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsCompetitionsNamePersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsCompetitionsNameTeamGet();
            // List<Map<String, Object>> mapList3 = adminService.adminStatisticsCompetitionsNameTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k, v)->{
                map1.put((String) k,v.stream().mapToLong(e-> (Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k, v)->{
                map2.put((String) k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k, v)->{
                map3.put((String) k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按竞赛分类获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按竞赛分类获取数据失败", 500);
        }
        return resultVO;
    }


    // 按竞赛时间返回数据
    @GetMapping("/statistics/competitionTime")
    public ResultVO adminStatisticsCompetitionTimeGet (){
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsCompetitionsTimePersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsCompetitionsTimeTeamGet();
            // List<Map<String, Object>> mapList3 = adminService.adminStatisticsCompetitionsTimeTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.get("competitionTime")+"")).forEach((k, v)->{
                map1.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.get("competitionTime")+"")).forEach((k, v)->{
                map2.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.get("competitionTime")+"")).forEach((k, v)->{
                map3.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按竞赛时间获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按竞赛时间获取数据失败",500);
        }
        return resultVO;
    }

    // 按竞赛和时间返回数据
    @GetMapping("/statistics/competitionName/competitionTime")
    public ResultVO adminStatisticsCompetitionNameCompetitionTimeGet (){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<StatisticsVO> mapList1 = adminService.adminStatisticsCompetitionsNameAndTimePersonGet();
            List<StatisticsVO> mapList2 = adminService.adminStatisticsCompetitionsNameAndTimeTeamGet();
            // List<StatisticsVO> mapList3 = adminService.adminStatisticsCompetitionsNameAndTimeTeamGet();
            List<StatisticsVO> mapList3 = mapList2;
            Map<String, Integer> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.getCompetitionName()+it.getCompetitionTime())).forEach((k, v)->{
                map1.put(k,v.stream().mapToInt(e->e.getNumber()).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Integer) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Integer> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.getCompetitionName()+it.getCompetitionTime())).forEach((k, v)->{
                map2.put(k,v.stream().mapToInt(e-> e.getNumber()).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Integer) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Integer> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.getCompetitionName()+it.getCompetitionTime())).forEach((k, v)->{
                map3.put(k,v.stream().mapToInt(e-> e.getNumber()).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Integer::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Integer) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按竞赛时间获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按竞赛时间获取数据失败", 500);

        }
        return resultVO;
    }

    // 按性别返回数据
    @GetMapping("/statistics/sex")
    public ResultVO adminStatisticsSexGet (){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsSexPersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsSexTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.get("studentSex")+"")).forEach((k, v)->{
                map1.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                StatisticsDataVO statisticsDataVO = new StatisticsDataVO();
                System.out.println(entry.getKey());
                if ("0".toString().equals(entry.getKey())) {
                    statisticsDataVO.setName("男");
                }else {
                    statisticsDataVO.setName("女");
                }
                statisticsDataVO.setValue((Long) entry.getValue());
                statisticsVOS1.add(statisticsDataVO);
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.get("studentSex")+"")).forEach((k, v)->{
                map2.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                StatisticsDataVO statisticsDataVO = new StatisticsDataVO();
                System.out.println(entry.getKey());
                if ("0".toString().equals(entry.getKey())) {
                    statisticsDataVO.setName("男");
                }else {
                    statisticsDataVO.setName("女");
                }
                statisticsDataVO.setValue((Long) entry.getValue());
                statisticsVOS2.add(statisticsDataVO);
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.get("studentSex")+"")).forEach((k, v)->{
                map3.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                StatisticsDataVO statisticsDataVO = new StatisticsDataVO();
                System.out.println(entry.getKey());
                if ("0".toString().equals(entry.getKey())) {
                    statisticsDataVO.setName("男");
                }else {
                    statisticsDataVO.setName("女");
                }
                statisticsDataVO.setValue((Long) entry.getValue());
                statisticsVOS3.add(statisticsDataVO);
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按性别获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按性别获取数据成功", 500);
        }
        return resultVO;
    }

    // 按学院返回数据
    @GetMapping("/statistics/college")
    public ResultVO adminStatisticsCollegeGet (){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsCollegePersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsCollegeTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.get("studentCollege")+"")).forEach((k, v)->{
                map1.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.get("studentCollege")+"")).forEach((k, v)->{
                map2.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.get("studentCollege")+"")).forEach((k, v)->{
                map3.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按学院获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按学院获取数据成功", 500);
        }
        return resultVO;
    }

    // 按年级返回数据
    @GetMapping("/statistics/grade")
    public ResultVO adminStatisticsGradeGet (){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsGradePersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsGradeTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.get("studentGrade")+"")).forEach((k, v)->{
                map1.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.get("studentGrade")+"")).forEach((k, v)->{
                map2.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.get("studentGrade")+"")).forEach((k, v)->{
                map3.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按年级获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按年级获取数据失败", 500);
        }
        return resultVO;
    }

    // 按班级返回数据
    @GetMapping("/statistics/class")
    public ResultVO adminStatisticsClassGet (){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsClassPersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsClassTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> it.get("studentClass")+"")).forEach((k, v)->{
                map1.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> it.get("studentClass")+"")).forEach((k, v)->{
                map2.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> it.get("studentClass")+"")).forEach((k, v)->{
                map3.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按班级获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按班级获取数据失败", 500);
        }
        return resultVO;
    }

    // 按年级和班级返回数据
    @GetMapping("/statistics/grade/class")
    public ResultVO adminStatisticsGradeAndClassGet (){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList1 = adminService.adminStatisticsGradeAndClassPersonGet();
            List<Map<String, Object>> mapList2 = adminService.adminStatisticsGradeAndClassTeamGet();
            List<Map<String, Object>> mapList3 = mapList2;
            Map<String, Long> map1 = new HashMap<>();
            mapList1.stream().collect(Collectors.groupingBy(it -> (String)it.get("studentClass")+it.get("studentGrade"))).forEach((k, v)->{
                map1.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS1 = new LinkedList<>();
            Iterator iterator1 = map1.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator1.next();
                statisticsVOS1.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personCompetitions", statisticsVOS1);
            Map<String, Long> map2 = new HashMap<>();
            mapList2.stream().collect(Collectors.groupingBy(it -> (String)it.get("studentClass")+it.get("studentGrade"))).forEach((k, v)->{
                map2.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            List<StatisticsDataVO> statisticsVOS2 = new LinkedList<>();
            Iterator iterator2 = map2.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator2.next();
                statisticsVOS2.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("teamCompetitions", statisticsVOS2);
            Map<String, Long> map3 = new HashMap<>();
            mapList3.stream().collect(Collectors.groupingBy(it -> (String)it.get("studentClass")+it.get("studentGrade"))).forEach((k, v)->{
                map3.put(k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
            });
            map1.forEach((k,v)->map3.merge(k,v, Long::sum));
            List<StatisticsDataVO> statisticsVOS3 = new LinkedList<>();
            Iterator iterator3 = map3.entrySet().iterator();
            while (iterator3.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator3.next();
                statisticsVOS3.add(new StatisticsDataVO((String) entry.getKey(), (Long) entry.getValue()));
            }
            map.put("personAndTeamCompetitions", statisticsVOS3);
            resultVO = ResultVoUtil.success(map, "按年级和班级获取数据成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("按年级和班级获取数据失败", 500);
        }
        return resultVO;
    }

    // 获取管理员对象信息
    @GetMapping("/admins")
    public ResultVO adminAdminsGet (@RequestHeader("token") String adminToken) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(adminToken);//验证令牌
            String userName = verify.getClaim("userName").asString();
            Admin admin = adminService.adminAdminsGet(userName);
            if (admin != null) {
                map.put("admin", admin);
                resultVO = ResultVoUtil.success(map, "管理员信息获取成功", 200);
            }else {
                resultVO = ResultVoUtil.error("无此管理员信息", 400);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("添加管理员失败", 500);
        }
        return resultVO;
    }

    // 更新管理员信息
    @PutMapping("/admins")
    public ResultVO adminAdminsPut (Admin admin) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            System.out.println(admin);
            if (adminService.adminAdminsPut(admin)) {
                map.put("admin", admin);
                resultVO = ResultVoUtil.success(map, "管理员信息更新成功", 200);
            }else {
                resultVO = ResultVoUtil.error("管理员信息更新失败", 400);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("管理员信息更新失败", 500);
        }
        return resultVO;
    }

    // 添加管理员
    @PostMapping("/admins")
    public ResultVO adminAdminsPost (Admin admin) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminAdminsPost(admin)) {
                map.put("admin", admin);
                resultVO = ResultVoUtil.success(map, "添加管理员成功", 200);
            }else {
                resultVO = ResultVoUtil.error("添加管理员失败", 400);

            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("添加管理员失败", 500);
        }
        return resultVO;
    }

    // 邮箱管理员更新
    @PutMapping("/admins/email")
    public ResultVO adminAdminEmailPut (Admin admin) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminAdminEmailPut(admin)) {
                map.put("admin", admin);
                resultVO = ResultVoUtil.success(map, "管理员邮箱更新成功", 200);
            }else {
                resultVO = ResultVoUtil.error("管理员邮箱更新失败", 400);

            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("管理员邮箱更新失败", 500);
        }
        return resultVO;
    }

    // 管理员密码更新
    @PutMapping("/admins/password")
    public ResultVO adminAdminsPasswordPut (Admin admin) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            if (adminService.adminAdminsPasswordPut(admin)) {
                map.put("admin", admin);
                resultVO = ResultVoUtil.success(map, "管理员密码修改成功", 200);
            }else {
                resultVO = ResultVoUtil.error("管理员密码修改失败", 400);

            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("管理员密码修改失败", 500);
        }
        return resultVO;
    }

    // 测试
    @PostMapping("/test")
    public ResultVO test(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ResultVO resultVO = null;
        // String absolutePath = new File("src/main/resources/static/image/").getAbsolutePath()+"/";
        String absolutePath = "/www/server/lzumanagement/vueServer/dist/img/";
        System.out.println(new File(absolutePath + multipartFile.getOriginalFilename()).getAbsolutePath());
        multipartFile.transferTo(new File(absolutePath + multipartFile.getOriginalFilename()));
        System.out.println(multipartFile.getOriginalFilename());
        Map<String, Object> map = new HashMap<>();
        String filePath = "http://121.36.32.198:8080/img/" + multipartFile.getOriginalFilename();
        map.put("path", filePath);
        resultVO = ResultVoUtil.success(map, "图片上传成功", 200);
        return resultVO;
    }

    @GetMapping("/hello")
    public String AdminHello() {
        return "admin hello";
    }
}
