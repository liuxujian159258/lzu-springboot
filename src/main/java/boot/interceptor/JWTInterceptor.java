package boot.interceptor;

import boot.util.JwtUtil;
import boot.util.ResultVoUtil;
import boot.vo.MetaVO;
import boot.vo.ResultVO;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "token");
        // 如果是OPTIONS请求则结束
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        ResultVO resultVO;
        Map<String,Object> map =new HashMap<>();
        // 获取请求头中的令牌
        try{
            String token = request.getHeader("token");
            if (token.equals(null)) {
                resultVO = ResultVoUtil.error("拒绝访问", 403);
            }
            DecodedJWT verify = JwtUtil.verify(token);//验证令牌
            String userRole = verify.getClaim("userRole").asString();
            if (userRole.equals("student")) {
                System.out.println("学生token："+token);
                map.put("token", token);
                resultVO = ResultVoUtil.success(map, "登陆成功", 200);
                return true;
            }
            if (userRole.equals("admin")) {
                resultVO = ResultVoUtil.error("管理员账号，无法访问", 403);
            }else {
                resultVO = ResultVoUtil.error("拒绝无法访问", 403);
            }
        }catch (SignatureVerificationException e) {
            resultVO = ResultVoUtil.error("无效签名", 403);
            e.printStackTrace();
        }catch (TokenExpiredException e) {
            resultVO = ResultVoUtil.error("token过期", 403);
            e.printStackTrace();
        }catch (AlgorithmMismatchException e) {
            resultVO = ResultVoUtil.error("token算法不一致", 403);
            e.printStackTrace();
        }catch (NullPointerException e) {
            resultVO = ResultVoUtil.error("拒绝访问", 403);
        }catch (Exception e) {
            resultVO = ResultVoUtil.error("token无效", 403);
            e.printStackTrace();
        }
        // 将resultVO转为json
        String json = new ObjectMapper().writeValueAsString(resultVO);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
