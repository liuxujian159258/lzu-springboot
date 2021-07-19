package boot.service.impl;

import boot.bean.Admin;
import boot.bean.Competition;
import boot.bean.Team;
import boot.bean.TeamStudent;
import boot.exception.AffectedRowsException;
import boot.service.AdminService;
import boot.util.EmailUtil;
import boot.util.ThreadPoolExecutorUtil;
import boot.vo.admin.StudentsDownloadVO;
import boot.vo.admin.StudentsVO;
import boot.vo.admin.TeamsVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class AdminServiceImplTest {

    @Resource
    private AdminService adminService;
    @Resource
    private JavaMailSender mailSender;

    @Test
    void findByUserName() {
        String s= "admin";
        String password = "123456";
        boolean byUserName = adminService.findByUserName(s, password);
        System.out.println(byUserName);
    }

    @Test
    void updateAdmin() {
        Admin admin = new Admin("admin1", "1234567", "2925906", "zdplzphfcfgxbfci");
        boolean b = adminService.updateAdmin(admin);
        System.out.println(b);
    }

    @Test
    void addAdmin() {
        Admin admin = new Admin("admin2", "123456", "2925906", "zdplzphfcfgxbfci");
        boolean b = adminService.addAdmin(admin);
        System.out.println(b);
    }

    @Test
    void deleteAdmin() {
        Admin admin = new Admin("admin1", "1234567", "2925906", "zdplzphfcfgxbfci");
        boolean b = adminService.deleteAdmin(admin);
        System.out.println(b);
    }

    @Test
    void adminStudentsGet() {
        List<StudentsVO> studentsVO = adminService.adminStudentsCompetitionsGet(null,null);
        System.out.println(studentsVO);
    }

    @Test
    void adminStudentsPut() {
        System.out.println(adminService.adminStudentsPut("320170940841", "29259062321@qq.com", "173250139451"));
    }

    @Test
    void adminStudentsDelete() {
        System.out.println(adminService.adminStudentsDelete("320170940846"));
    }

    @Test
    void adminStudentsDownload() {
        List<StudentsDownloadVO> studentsVOS = adminService.adminStudentsDownload("实践技能提升竞赛AI挑战赛", 2017);
        System.out.println(studentsVOS);
    }

    @Test
    void adminTeamsGet () {
        List<TeamsVO> teamsVOS = adminService.adminTeamsCompetitionsGet(1, null);
        System.out.println(teamsVOS);
    }

    @Test
    void adminTeamsPut () {
        boolean b = adminService.adminTeamsPut(1, "刘绪俭11队", "信息科学与工程学院1");
        System.out.println(b);
    }

    @Test
    void adminTeamsDelete () {
        boolean b = adminService.adminTeamsDelete(1);
        System.out.println(b);
    }

    @Test
    void adminTeamsDownload() {
        System.out.println(adminService.adminTeamsDownload("群体时空数据的语义分析", null));
    }

    @Test
    void adminTeamsPost () throws SQLIntegrityConstraintViolationException {
        Team team = new Team(null, "刘绪俭测试13队", "测试学院", 0);
        List<TeamStudent> teamStudents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            teamStudents.add(new TeamStudent(6,"320170940846"+i,"刘绪俭6"+i,0));
        }
        System.out.println(adminService.adminTeamsPost(team, teamStudents));
    }

    @Test
    void adminTeamsTeamIdStudentNumberPut () {
        try {
            adminService.adminTeamsTeamIdStudentNumberPut(14,"3201709408407");
        }catch (AffectedRowsException e) {
            System.out.println("修改失败");
        }
    }

    @Test
    void adminTeamsStudentsDelete() {
        boolean b = adminService.adminTeamsStudentsDelete(14, "320170940847");
        System.out.println(b);
    }

    @Test
    void adminCompetitionsGet() {
        System.out.println(adminService.adminCompetitionsGet());
    }

    @Test
    void adminCompetitionsPut() {
        Competition competition = new Competition();
        competition.setCompetitionId(11);
        competition.setCompetitionName("测试竞赛213333333");
        competition.setCompetitionType(0);
        competition.setCompetitionPeople(1);
        competition.setCompetitionDepartment("信息科学与工程学院");
        adminService.adminCompetitionsPut(competition);
    }

    @Test
    void adminCompetitionsPost () {
        Competition competition = new Competition();
        competition.setCompetitionName("测试竞赛");
        competition.setCompetitionType(0);
        competition.setCompetitionPeople(1);
        competition.setCompetitionDepartment("信息科学与工程学院");
        System.out.println(adminService.adminCompetitionsPost(competition));
    }

    @Test
    void adminCompetitionsDelete () {
        System.out.println(adminService.adminCompetitionsDelete(14));
    }

    @Test
    void adminEmailsPut() {
        Admin admin = new Admin("admin1", "1234567", "29259062321@qq.com", "zdplzphfcfgxbfci");
        System.out.println(adminService.adminEmailsPut(admin));
    }

    @Test
    void adminEmailsOne () throws MessagingException {
        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1709329960@qq.com");
        message.setTo("2925906232@qq.com");
        message.setSubject("java Mail");
        message.setText("你好，刘绪俭");
        message.setCc("2925906232@qq.com");
        mailSender.send(message);*/
        /*JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setUsername("1709329960@qq.com");
        sender.setPassword("tbpooetvttaqccgj");
        sender.setPort(456);
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.ssl", "true");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.starttls.required", "true");
        sender.setJavaMailProperties(p);
        MimeMessage message = sender.createMimeMessage();
        //这里的utf-8解决 邮件 内容乱码
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        //当前发送人邮箱（也就是自己）
        helper.setFrom("1709329960@qq.com");
        //发送到的邮箱地址
        helper.setTo("2925906232@qq.com");
        //邮件主题、标题
        helper.setSubject("java Mail");
        //类容
        helper.setText("你好，刘绪俭");
        sender.send(message);
*/
       // EmailUtil.qqEmailSend("1709329960@qq.com", "tbpooetvttaqccgj", "2925906232@qq.com", "兰州大学科技月", "你已经获得兰州大学科技月活动竞赛三等奖");
        String[] strings = new String[2];
        strings[0] = "2925906232@qq.com";
        strings[1] = "1709329960@qq.com";
        EmailUtil.qqEmailSendMass("1709329960@qq.com", "tbpooetvttaqccgj", strings, "兰州大学科技月", "你已经获得兰州大学科技月活动竞赛三等奖");
    }

    @Test
    void adminEmailsMass () {
        try {
            ThreadPoolExecutor defaultThreadPoolExecutor = ThreadPoolExecutorUtil.createDefaultThreadPoolExecutor();
            String[] strings = adminService.adminEmailsOne("实践技能提升竞赛AI挑战赛", 2017);
            for (String string : strings) {
                defaultThreadPoolExecutor.submit(new Thread(() -> {
                    try {
                        EmailUtil.qqEmailSend("1709329960@qq.com", "tbpooetvttaqccgj", string, "兰州大学科技月", "你已经获得兰州大学科技月活动竞赛三等奖");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }));
            }
            defaultThreadPoolExecutor.shutdown();
            // EmailUtil.qqEmailSendMass("1709329960@qq.com", "tbpooetvttaqccgj", strings, "兰州大学科技月", "你已经获得兰州大学科技月活动竞赛三等奖");
        }catch (Exception e){
            e.printStackTrace();
        }
        // String[] strings1 = adminService.adminEmailsTeam("群体时空数据的语义分析", 2017);

    }

    @Test
    void adminStudentsScoreGet () {
        List<StudentsDownloadVO> adminStudentsScoreGet = adminService.adminStudentsScoreGet(null, null, null, null);
        System.out.println(adminStudentsScoreGet);
    }

    @Test
    void adminStudentsScorePut() {
        System.out.println(adminService.adminStudentsScorePut("320170940841", 99.98, 0, 2017));
    }

    @Test
    void adminTeamsScoresGet() {
        System.out.println(adminService.adminTeamsScoresGet(null, "刘绪俭", null, null));
    }

    @Test
    void adminStatisticsCompetitionsNamePersonGet () {
        /*List<Map<String, Object>> mapList1 = adminService.adminStatisticsCompetitionsNamePersonGet();
        List<Map<String, Object>> mapList2 = adminService.adminStatisticsCompetitionsNameTeamGet();
        Map<String, Long> map1 = new HashMap<>();
        mapList1.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k, v)->{
            map1.put((String) k,v.stream().mapToLong(e->(Long)e.get("number")).sum());
        });
        Map<String, Long> map2 = new HashMap<>();
        mapList2.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k, v)->{
            map2.put((String) k,v.stream().mapToLong(e->(Long) e.get("number")).sum());
        });
        map1.forEach((k,v)->map2.merge(k,v, Long::sum));
        System.out.println(mapList1);
        System.out.println(mapList2);
        System.out.println(map2);
        adminService.adminStatisticsCompetitionsNameGet();*/
        /*System.out.println();
        System.out.println(adminService.adminStatisticsCompetitionsTimePersonGet());
        System.out.println(adminService.adminStatisticsCompetitionsTimeTeamGet());
        System.out.println(adminService.adminStatisticsCompetitionsTimeGet());
        System.out.println();
        System.out.println(adminService.adminStatisticsCompetitionsNameAndTimePersonGet());
        System.out.println(adminService. adminStatisticsCompetitionsNameAndTimeTeamGet());
        System.out.println(adminService. adminStatisticsCompetitionsNameAndTimeGet());
        System.out.println();
        System.out.println(adminService.adminStatisticsSexPersonGet());
        System.out.println(adminService.adminStatisticsSexTeamGet());
        System.out.println(adminService.adminStatisticsSexGet());
        System.out.println();
        System.out.println(adminService.adminStatisticsCollegePersonGet());
        System.out.println(adminService.adminStatisticsCollegeTeamGet());
        System.out.println(adminService.adminStatisticsCollegeGet());
        System.out.println();
        System.out.println(adminService.adminStatisticsGradePersonGet());
        System.out.println(adminService.adminStatisticsGradeTeamGet());
        System.out.println(adminService.adminStatisticsGradeGet());
        System.out.println();
        System.out.println(adminService.adminStatisticsClassPersonGet());
        System.out.println(adminService.adminStatisticsClassTeamGet());
        System.out.println(adminService.adminStatisticsClassGet());
        System.out.println();*/
        System.out.println(adminService.adminStatisticsGradeAndClassPersonGet());
        System.out.println(adminService.adminStatisticsGradeAndClassTeamGet());
        System.out.println(adminService.adminStatisticsGradeAndClassGet());
    }

    @Test
    void adminAdminsPost () {
        // System.out.println(adminService.adminAdminsPost(new Admin("admin3", "123456", "2925906@qq.com", "zdplzphfcfgxbfci")));
        // System.out.println(adminService.adminAdminEmailPut(new Admin("admin3", "123456", "2925906999@qq.com", "zdplzphfcfgxbfci")));
        System.out.println(adminService.adminAdminsPasswordPut(new Admin("admin3", "1234569999", "2925906999@qq.com", "zdplzphfcfgxbfci")));
    }

}
