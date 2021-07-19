package boot.controller;

import boot.bean.*;
import boot.exception.AffectedRowsException;
import boot.http.RestMock;
import boot.http.bean.LoginTokenBean;
import boot.http.bean.StudentInfo;
import boot.service.AdminService;
import boot.service.StudentService;
import boot.util.*;
import boot.vo.*;
import boot.vo.admin.CompetitionsHtmlVO;
import boot.vo.student.StudentsMyJionTeamsVO;
import boot.vo.student.StudentsMyTeamsVO;
import boot.vo.student.StudentsScoreVO;
import boot.vo.student.TeamsScoreVO;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import javafx.geometry.Pos;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/student")
public class StudentController {

    // 网络请求
    @Resource
    private RestMock restMock;

    // Java邮箱发送
    @Resource
    private JavaMailSender mailSender;

    // studentService
    @Resource
    private StudentService studentService;

    // adminService
    @Resource
    private AdminService adminService;

    // 学生用户登陆验证接口
    @PostMapping("/login")
    public ResultVO studentLoginPost(String username, String password) {
        ResultVO resultVO;
        try{
            System.out.println(username);
            System.out.println(password);
            Map<String, Object> jsonMap = new HashMap<>(6);
            Map<String, Object> map = new HashMap<>();
            jsonMap.put("app_os", 2);
            jsonMap.put("name", username);
            jsonMap.put("pwd", password);
            String sendPost = restMock.sendPost(jsonMap);
            LoginTokenBean loginTokenBean = JSONObject.parseObject(sendPost, LoginTokenBean.class);
            String sendGet = restMock.sendGet(loginTokenBean.getData().getLogin_token());
            StudentInfo studentInfo = JSONObject.parseObject(sendGet, StudentInfo.class);
            if (studentInfo.getCode().equals(1)) {
                Student student = new Student();
                student.setStudentNumber(studentInfo.getData().getXykh());
                student.setStudentName(studentInfo.getData().getXm());
                student.setStudentPassword(password);
                student.setStudentSex(Integer.parseInt(studentInfo.getData().getXbm()));
                student.setStudentCollege(studentInfo.getData().getDwmc());
                Integer studentGrade = Integer.parseInt(studentInfo.getData().getJxrq().substring(0,4));
                student.setStudentGrade(studentGrade);
                System.out.println(student);
                Map<String,String> payload = new HashMap<>();
                String existStudentPassword = studentService.existStudent(student.getStudentNumber(), student.getStudentPassword());
                System.out.println(existStudentPassword);
                if (existStudentPassword!= null) {
                    if ( !existStudentPassword.equals(student.getStudentPassword())) {
                        studentService.updateStudentPassword(student.getStudentNumber(), student.getStudentPassword());
                        System.out.println("学生用户密码已经更新");
                    }
                    payload.put("userName",username);
                    payload.put("userNumber",student.getStudentNumber());
                    payload.put("userRole", "student");
                    // 生成JWT的token
                    String token = JwtUtil.getToken(payload);
                    map.put("token", token);
                }else {
                    studentService.addStudent(student);
                    payload.put("userName",username);
                    payload.put("userNumber",student.getStudentNumber());
                    payload.put("userRole", "student");
                    // 生成JWT的token
                    String token = JwtUtil.getToken(payload);
                    map.put("token", token);
                }
                resultVO = ResultVoUtil.success(map, "登陆成功", 200);
            }else {
                resultVO = ResultVoUtil.error("邮箱或者密码错误,登陆失败", 400);
            }
        }catch (NullPointerException e){
            resultVO = ResultVoUtil.error("邮箱或者密码错误,登陆失败", 400);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("登陆失败", 500);
        }
        return resultVO;
    }

    // 获取学生用户信息
    @GetMapping("/informations")
    public ResultVO studentInformationsGet (@RequestHeader("token") String studentToken) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String userNumber = verify.getClaim("userNumber").asString();
            Student student = studentService.studentInformationGet(userNumber);
            if (student != null) {
                map.put("student", student);
                resultVO = ResultVoUtil.success(map, "学生用户信息获取成功", 200);
            }else {
                resultVO = ResultVoUtil.error("学生用户信息为空", 400);
            }
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("学生用户信息获取失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 信息完善接口
    @PutMapping("/informations")
    public ResultVO studentInformationsPut (Student student) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            System.out.println(student);
            if (studentService.studentInformationsPut(student)){
                map.put("student", student);
                resultVO = ResultVoUtil.success(map, "信息完善成功", 200);
            }else {
                resultVO = ResultVoUtil.error("信息完善失败", 400);
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVoUtil.error("信息完善失败", 500);
        }
        return resultVO;
    }

    // 获取竞赛列表
    @GetMapping("/competitions/competitionList")
    public ResultVO studentCompetitionsCompetitionList () {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> competitionList = adminService.adminEmailsCompetitions();
            map.put("competitionList", competitionList);
            resultVO = ResultVoUtil.success(map, "竞赛列表和技术时间列表获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("竞赛列表和技术时间列表获取失败", 500);
        }
        return resultVO;
    }

    // 获取满足条件所有竞赛信息
    @GetMapping("/competitions")
    public ResultVO studentCompetitionsGet (Integer competitionId, Integer competitionType) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            System.out.println(competitionId+ ": " + competitionType);
            List<HashMap<String, Object>> competitions = studentService.studentCompetitionsGet(competitionId, competitionType);
            map.put("competitions", competitions);
            resultVO = ResultVoUtil.success(map, "所有竞赛信息获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("所有信息获取列表失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 获取具有html的所有竞赛信息
    // 获取满足条件所有竞赛信息
    @GetMapping("/competitions/all")
    public ResultVO studentCompetitionsAllGet () {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            List<CompetitionsHtmlVO> competitions = studentService.studentCompetitionsGetAll();
            map.put("competitions", competitions);
            resultVO = ResultVoUtil.success(map, "所有竞赛信息获取成功", 200);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("所有信息获取列表失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 用户竞赛报名
    @PostMapping("/competitions/signUp")
    public ResultVO studentCompetitionsSignUpPost (@RequestHeader("token") String studentToken, Integer competitionId, String competitionName, Integer competitionType, Integer teamId) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            Integer competitionTime = Calendar.getInstance().get(Calendar.YEAR);
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber + ": " + competitionId + ": " + competitionName + ": " + competitionType + ": "+ teamId);
            if (competitionType == 0){
                if (studentService.studentSignUpPerson(new ScoreStudent(studentNumber,competitionId,competitionName,0.0,competitionTime))) {
                    CompetitionEmail competitionEmail = studentService.competitionEmailGet(competitionId);
                    Admin admin = adminService.adminEmailsGet("admin");
                    String studentEmail = studentService.studentEmailGet(studentNumber);
                    System.out.println(competitionEmail);
                    System.out.println(studentEmail);
                    System.out.println(admin);
                    new Thread(() -> {
                        try {
                            EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), studentEmail, competitionEmail.getSubject(), competitionEmail.getText());
                        } catch (MessagingException e) {
                            String errorText = "向学号：" + studentNumber + ",学号：" + studentNumber + "同学，邮箱:" + studentEmail +"发送报名邮件失败";
                            try {
                                EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), admin.getEmail(), competitionEmail.getSubject(), errorText);
                            } catch (MessagingException ex) {
                                ex.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                    }).start();
                    map.put("studentNumber", studentNumber);
                    map.put("competitionId", competitionId);
                    map.put("teamId", teamId);
                    resultVO = ResultVoUtil.success(map, "用户竞赛报名成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("用户已经报名此竞赛，用户竞赛报名成功失败", 400);
                }
            }else {
                if (studentService.isTeamCaptain(teamId, studentNumber) && studentService.teamExistOrDismiss(teamId)) {
                    if (studentService.teamPeopleAndCompetitionPeople(teamId, competitionId)){
                        if (studentService.studentSignUpTeam(new ScoreTeam(teamId, competitionId, competitionName, 0.0, competitionTime))) {
                            ThreadPoolExecutor defaultThreadPoolExecutor = ThreadPoolExecutorUtil.createDefaultThreadPoolExecutor();
                            String[] strings = adminService.adminEmailsTeamsGet(teamId);
                            Admin admin = adminService.adminEmailsGet("admin");
                            CompetitionEmail competitionEmail = studentService.competitionEmailGet(competitionId);
                            for (String string : strings) {
                                defaultThreadPoolExecutor.execute(() -> {
                                    try {
                                        System.out.println(admin);
                                        System.out.println(competitionEmail);
                                        System.out.println(string);
                                        EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), string, competitionEmail.getSubject(), competitionEmail.getText());
                                    } catch (Exception e) {
                                        System.out.println("向邮箱"+string+"发送邮件失败");
                                        String errorText = "团队编号：" + teamId + ",竞赛名称：" + competitionName +",向团队成员邮箱"+string+"发送报名邮件失败";
                                        try {
                                            EmailUtil.qqEmailSend(admin.getEmail(), admin.getEmailPassword(), admin.getEmail(), competitionEmail.getSubject(), errorText);
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                        e.printStackTrace();
                                    }
                                });
                            }
                            map.put("studentNumber", studentNumber);
                            map.put("competitionId", competitionId);
                            map.put("teamId", teamId);
                            resultVO = ResultVoUtil.success(map, "团队竞赛报名成功", 200);
                        }else {
                            resultVO = ResultVoUtil.error("团队已经报名此竞赛，团队竞赛报名失败", 400);
                        }
                    }else {
                        resultVO = ResultVoUtil.error("团队成员数量不符合，团队竞赛报名失败", 400);
                    }
                }else {
                    resultVO = ResultVoUtil.error("用户不是队长,团队已经解散或者不存在，团队竞赛报名失败", 400);
                }
            }
        }catch (NullPointerException n) {
            resultVO = ResultVoUtil.error("团队成员数量不符合或者团队编号有误，团队竞赛报名失败", 400);
            n.printStackTrace();
        }
        catch (Exception e) {
            if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                resultVO = ResultVoUtil.error("已经报名此竞赛，竞赛报名失败", 400);
            }else if (e.getCause() instanceof NullPointerException){
                resultVO = ResultVoUtil.error("团队成员数量不符合，团队竞赛报名失败", 400);
            }
            else {
                resultVO = ResultVoUtil.error("团队成员数量不符合或者团队编号有误，团队竞赛报名失败", 500);
            }
            e.printStackTrace();
        }
        return resultVO;
    }

    // 用户取消报名
    @DeleteMapping("/competitions/cancel")
    public ResultVO StudentCompetitionsCancelDelete (@RequestHeader("token") String studentToken, Integer competitionId, String competitionName, Integer competitionType,Integer competitionTime, Integer teamId) {
        ResultVO resultVO;
        try {
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber + ": " + competitionId + ": " + competitionName + ": " + competitionType + ": " +competitionTime+ ": " + teamId);
            Map<String, Object> map = new HashMap<>();
            if (competitionType == 0){
                if (studentService.studentCancelPerson(studentNumber, competitionId, competitionTime)){
                    map.put("studentNumber", studentNumber);
                    map.put("competitionId", competitionId);
                    map.put("competitionTime", competitionTime);
                    map.put("team", null);
                    resultVO = ResultVoUtil.success(map, "用户取消竞赛报名成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("用户取消竞赛报名失败", 400);
                }
            }else {
                if (studentService.isTeamCaptain(teamId, studentNumber)){
                    if (studentService.studentCancelTeam(teamId, competitionId, competitionTime)){
                        map.put("studentNumber", studentNumber);
                        map.put("competitionId", competitionId);
                        map.put("competitionTime", competitionTime);
                        map.put("teamId", null);
                        resultVO = ResultVoUtil.success(map, "用户取消竞赛报名成功", 200);
                    }else {
                        resultVO = ResultVoUtil.error("用户竞赛取消报名失败", 400);
                    }
                }else {
                    resultVO = ResultVoUtil.error("用户不是队长,用户竞赛取消报名失败", 400);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVoUtil.error("用户取消报名失败", 500);
        }
        return resultVO;
    }

    // 个人成绩查看
    @GetMapping("/scores/studentNumber")
    public ResultVO studentScoresStudentNumberGet (@RequestHeader("token") String studentToken) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber);
            List<StudentsScoreVO> studentsScoreVOS = studentService.studentScoresStudentNumberGet(studentNumber);
            map.put("students", studentsScoreVOS);
            resultVO = ResultVoUtil.success(map, "用户成绩获取成功", 200);
        }catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVoUtil.error("用户成绩获取失败", 500);
        }
        return resultVO;
    }

    // 团队成绩查看
    @GetMapping("/teams/scores/studentNumber")
    public ResultVO studentTeamsScoresStudentNumberGet (@RequestHeader("token") String studentToken) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber);
            List<TeamsScoreVO> teamsScoreVOS = studentService.studentTeamsScoresStudentNumberGet(studentNumber);
            map.put("teams", teamsScoreVOS);
            resultVO = ResultVoUtil.success(map, "团队成绩获取成功", 200);
        }catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVoUtil.error("团队成绩获取失败", 500);
        }
        return resultVO;
    }

    // 团队注册
    @PostMapping("/teams")
    public ResultVO studentTeamsPost (@RequestHeader("token") String studentToken, String teamName, String teamStudent, String teamCollege){
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            String studentName = studentService.studentNameByStudentNumber(studentNumber);
            String[] split = teamStudent.split(",");
            Team team = new Team(null, teamName, teamCollege, 0);
            List<TeamStudent> teamStudentList = new ArrayList<>();
            teamStudentList.add(new TeamStudent(null, studentNumber, studentName, 1));
            for (int i = 0; i < split.length-1; i=i+2) {
                int j=i+1;
                System.out.println(split[i] + ": " + split[j]+": 0");
                teamStudentList.add(new TeamStudent(null, split[i], split[j], 0));
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
            resultVO = ResultVoUtil.error("此团队已经被注册,团队添加失败", 500);
        }catch (Exception e){
            resultVO = ResultVoUtil.error("团队添加失败", 500);
        }
        return resultVO;
    }

    // 添加团队成员
    @PostMapping("teams/students")
    public ResultVO studentTeamsStudentsPost(@RequestHeader("token") String studentToken, Integer teamId, String teamStudent) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            if (studentService.isTeamCaptain(teamId, studentNumber)) {
                List<TeamStudent> teamStudentList = new ArrayList<>();
                String[] split = teamStudent.split(",");
                for (int i = 0; i < split.length-1; i=i+2) {
                    int j=i+1;
                    System.out.println(split[i] + ": " + split[j]+": 0");
                    teamStudentList.add(new TeamStudent(teamId, split[i], split[j], 0));
                }
                if (studentService.studentTeamsStudentsPost(teamStudentList)) {
                    map.put("teamId", teamId);
                    resultVO = ResultVoUtil.success(map, "团队成员添加成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("团队成员添加失败", 400);
                }
            }else {
                resultVO = ResultVoUtil.error("此用户不是队长，无法添加队员", 400);
            }
        }catch (AffectedRowsException e){
            resultVO = ResultVoUtil.error("此团队成员已经存在，无法添加", 500);
        }
        catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVoUtil.error("团队成员添加失败", 500);
        }
        return resultVO;
    }

    // 团队信息获取
    @GetMapping("/teams/students")
    public ResultVO studentTeamsStudentsGet (@RequestHeader("token") String studentToken) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber);
            List<StudentsMyTeamsVO> MyTeam = studentService.studentTeamsMyTeams(studentNumber);
            List<StudentsMyJionTeamsVO> MyJionTeam = studentService.studentTeamsMyJionTeams(studentNumber);
            map.put("myTeam", MyTeam);
            map.put("myJionTeam", MyJionTeam);
            resultVO = ResultVoUtil.success(map, "团队信息获取成功", 200);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVoUtil.error("团队信息获取失败", 500);
        }
        return resultVO;
    }

    // 团队队长修改
    @PutMapping("/teams/captain")
    public ResultVO studentTeamsCaptainPut (@RequestHeader("token") String studentToken, Integer teamId, String captainStudentNumber){
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(teamId+": "+studentNumber+ ": "+captainStudentNumber);
            if (studentService.isTeamCaptain(teamId, studentNumber)) {
                if (studentService.studentTeamsCaptainPut(teamId, studentNumber, captainStudentNumber)) {
                    map.put("teamId", teamId);
                    map.put("captainStudentNumber", captainStudentNumber);
                    resultVO = ResultVoUtil.success(map, "团队队长修改成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("团队队长修改失败", 500);
                }
            }else {
                resultVO = ResultVoUtil.error("队长信息有误,团队队长修改失败", 400);
            }
        }catch (AffectedRowsException e){
            resultVO = ResultVoUtil.error("团队队长修改失败", 500);
            e.printStackTrace();
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("团队队长修改失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 团队队员移除
    @DeleteMapping("/teams/students")
    public ResultVO studentTeamsStudentsDelete(@RequestHeader("token") String studentToken, Integer teamId, String studentNumber) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String captainStudentNumber = verify.getClaim("userNumber").asString();
            System.out.println(teamId+": "+captainStudentNumber+": " + ":"+ studentNumber);
            if (studentService.isTeamCaptain(teamId, captainStudentNumber)){
                if (studentService.studentTeamsStudentsDelete(teamId, studentNumber)) {
                    map.put("teamId", teamId);
                    map.put("studentNumber", studentNumber);
                    resultVO = ResultVoUtil.success(map, "队员移除成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("队员移除失败", 400);
                }
            }else {
                resultVO = ResultVoUtil.error("用户不是队长，队员移除失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("队员移除失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 团队删除
    @DeleteMapping("/teams")
    public ResultVO studentTeamsDelete (@RequestHeader("token") String studentToken, Integer teamId) {
        ResultVO resultVO;
        try{
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber+": "+teamId);
            if (studentService.isTeamCaptain(teamId, studentNumber)) {
                if (studentService.studentTeamsDelete(teamId)) {
                    map.put("teamId", teamId);
                    resultVO = ResultVoUtil.success(map, "团队解散成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("团队解散失败", 400);
                }
            }else {
                resultVO = ResultVoUtil.error("队长信息有误，团队解散失败", 400);
            }
        }catch (Exception e){
            resultVO = ResultVoUtil.error("团队解散失败", 500);
            e.printStackTrace();
        }
        return resultVO;
    }

    // 团队修改
    @PutMapping("/teams")
    public ResultVO studentTeamsPut (@RequestHeader("token") String studentToken, Integer teamId, String teamName, String teamCollege) {
        ResultVO resultVO;
        try {
            Map<String, Object> map = new HashMap<>();
            DecodedJWT verify = JwtUtil.verify(studentToken);//验证令牌
            String studentNumber = verify.getClaim("userNumber").asString();
            System.out.println(studentNumber+": "+teamId+": "+teamName+": "+teamCollege);
            if (studentService.isTeamCaptain(teamId,studentNumber)) {
                if (studentService.studentTeamsPut(teamId, teamName, teamCollege)) {
                    map.put("teamId", teamId);
                    map.put("teamName", teamName);
                    map.put("teamCollege", teamCollege);
                    resultVO = ResultVoUtil.success(map, "团队信息修改成功", 200);
                }else {
                    resultVO = ResultVoUtil.error("团队信息修改失败", 400);
                }
            }else {
                resultVO = ResultVoUtil.error("队长信息有误，团队信息修改失败", 400);
            }
        }catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVoUtil.error("团队信息修改失败", 500);
        }
        return resultVO;
    }



    @PostMapping("/user/login")
    public ResultVO login(User user) {
        System.out.println("用户名："+user.getUsername());
        System.out.println("密码："+user.getPassword());
        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("app_os", 2);
        jsonMap.put("name", user.getUsername());
        jsonMap.put("pwd", user.getPassword());
        String sendPost = restMock.sendPost(jsonMap);
        LoginTokenBean loginTokenBean = JSONObject.parseObject(sendPost, LoginTokenBean.class);
        String sendGet = restMock.sendGet(loginTokenBean.getData().getLogin_token());
        StudentInfo studentInfo = JSONObject.parseObject(sendGet, StudentInfo.class);
        System.out.println(studentInfo);
        ResultVO resultVO;
        HashMap<String, Object> hashMap = new HashMap<>();
        try{
            Map<String,String> payload = new HashMap<>();
            payload.put("userName",user.getUsername());
            payload.put("userRole", "student");
            // 生成JWT的token
            String token = JwtUtil.getToken(payload);
            hashMap.put("token", token);
            resultVO = ResultVoUtil.success(hashMap, "认证成功", 200);
        }catch (Exception e){
            resultVO = ResultVoUtil.error("认证失败", 400);
        }
        return resultVO;
    }

    @GetMapping("/user/test")
    public ResultVO test() {
        Map<String,Object> map = new HashMap<>();
        // 处理业务逻辑
        ResultVO resultVO;
        map.put("token", "登陆成功");
        resultVO = ResultVoUtil.success(map, "测试成功",200);
        return resultVO;
    }

    @GetMapping("/user/MyUserList")
    public ResultVO MyUser() {
        List<UserVO> userVOS = new ArrayList<>();
        List<UserCompetitionVO> userCompetitionVOS = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            userCompetitionVOS.add(new UserCompetitionVO(i,"无线电定向\"猎狐竞赛\"",10+i));
        }
        UserVO userVO = new UserVO("320170940841","刘绪俭1","信息科学与工程学院","2017电子商务1","2017","2925906232@qq.com","17325013945", userCompetitionVOS);
        UserVO userVO1 = new UserVO("320170940841","刘绪俭1","信息科学与工程学院","2017电子商务1","2017","2925906232@qq.com","17325013945", userCompetitionVOS);
        userVOS.add(userVO);
        userVOS.add(userVO1);
        Map<String, List> map = new HashMap<>();
        map.put("userVO", userVOS);
        ResultVO resultVO = ResultVoUtil.success(map, "返回用户数据成功", 200);
        return resultVO;
    }

    @GetMapping("/user/TeamList")
    public ResultVO TeamList() {
        List<TeamVO> TeamList = new ArrayList<>();
        List<TeamStudentsVO> teamStudentsVOS = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            teamStudentsVOS.add(new TeamStudentsVO("32017094094"+i,"刘绪俭"+i,i));
        }
        TeamVO teamVO = new TeamVO(1, "刘绪俭1队",  "无线电定向\"猎狐竞赛\"", 77.1, 2018, teamStudentsVOS);
        TeamVO teamVO1 = new TeamVO(2, "刘绪俭2队",  "无线电定向\"猎狐竞赛\"", 77.2, 2019, teamStudentsVOS);
        TeamList.add(teamVO);
        TeamList.add(teamVO1);
        Map<String, List> map = new HashMap<>();
        map.put("TeamList", TeamList);
        return ResultVoUtil.success(map, "返回团队数据成功", 200);
    }
    @GetMapping("/user/competitionList")
    public ResultVO competitionList() {
        List<CompetitionVO>competitionList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            competitionList.add(new CompetitionVO(i, "无线电定向\"猎狐竞赛\"", "无线电测向仪被广泛地应用于军事侦察、天文气象、救援监护和航空定位等领域。本活动是利用手中的测向机运用测向技术接收摩尔斯电码信号寻找自动发射电台的运动，在这个过程中看谁找到的电台多，用的时间少，谁就取得胜利。主要考察参赛者测向技术，耐力与速度，路径决策力与意志品质"
            , "暂无", "暂无", "暂无", "暂无", "暂无"));
        }
        Map<String, List> map = new HashMap<>();
        map.put("competitionList", competitionList);
        return ResultVoUtil.success(map, "返回竞赛数据成功", 200);
    }
    /**
     * competitionName
     * studentTime
     */
    @GetMapping("/userCSV/{competitionName}/{studentTime}")
    public String findBuyCSV(HttpServletResponse response, @PathVariable("competitionName") String competitionName, @PathVariable("studentTime") Integer studentTime) {
        List<Map<String, Object>> dataList;
        List<Student> students = new ArrayList<>();// 查询到要导出的信息
        for (int i = 0; i < 5; i++) {
            students.add(new Student("32017094084"+i,"1998095","来参加",0,"土木学院","2017土木",2017,"234324@qq.com","17325013945"));
        }
        if (students.size() == 0) {
            System.out.println("下载错误");
        }
        String sTitle = "学号,密码,姓名,性别,学院,班级,年纪,邮箱,手机号码";
        String fName = competitionName+"_"+studentTime+"_";
        String mapKey = "studentNumber,studentPassword,studentName,studentSex,studentCollege,studentClass,studentGrade,studentEmail,studentPhone";
        dataList = new ArrayList<>();
        Map<String, Object> map;
        for (Student student : students) {
            map = new HashMap<>();
            map.put("studentNumber",student.getStudentNumber());
            map.put("studentPassword", student.getStudentPassword());
            map.put("studentName", student.getStudentName());
            map.put("studentSex", student.getStudentSex());
            map.put("studentCollege", student.getStudentCollege());
            map.put("studentClass", student.getStudentClass());
            map.put("studentGrade", student.getStudentGrade());
            map.put("studentEmail", student.getStudentEmail());
            map.put("studentPhone", student.getStudentPhone());
            dataList.add(map);
        }
        try (OutputStream os = response.getOutputStream()) {
            CSVUtil.responseSetProperties(fName, response);
            boolean b = CSVUtil.doExport(dataList, sTitle, mapKey, os);
            if(b == false) {
                System.out.println("下载错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("下载成功");
        return "文件下载";
    }

    @GetMapping("send")
    private String send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("694380623@qq.com");
        message.setTo("2925906232@qq.com");
        message.setSubject("java Mail");
        message.setText("你好，刘绪俭");
        message.setCc("2925906232@qq.com");
        Thread thread = new Thread(() -> mailSender.send(message));
        thread.start();
        return "发送成功";
    }
}
