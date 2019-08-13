package com.team11.dataanalytics.aspect;

import com.alibaba.fastjson.JSON;
import com.team11.dataanalytics.annotation.SystemLog;
import com.team11.dataanalytics.dao.SysLogRepository;
import com.team11.dataanalytics.domain.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SystemAspect {

    private static Logger LOG= LoggerFactory.getLogger(SystemAspect.class);

    @Autowired
    private SysLogRepository sysLogRepository;

    //切入点
    @Pointcut("@annotation(com.team11.dataanalytics.annotation.SystemLog)")
    public void logPoinCut(){
    }

    //切入后配置通知
    @AfterReturning("logPoinCut()")
    public  void saveLog(JoinPoint joinPoint){
        SysLog sysLog=new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        SystemLog myLog = method.getAnnotation(SystemLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
//        String params = JSON.toJSONString(args);
//        sysLog.setParams(params);

        sysLog.setCreateDate(new Date());
        //获取用户名
        //此例子用到了ShiroUtils框架来实现获取用户名，此处还可以用session来获取登录操作名
        //例：HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        sysLog.setUserName((String) session.getAttribute("user"));
        //获取用户ip地址
        //HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //sysLog.setIp(IPUtils.getIpAddr(request));

        //调用service保存SysLog实体类到数据库
        sysLogRepository.save(sysLog);

    }

}
