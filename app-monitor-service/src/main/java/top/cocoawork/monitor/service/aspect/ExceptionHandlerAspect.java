package top.cocoawork.monitor.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.service.api.exception.ExceptionEnum;
import top.cocoawork.monitor.service.api.exception.ServiceException;

import javax.sound.midi.Soundbank;
import javax.validation.ConstraintViolationException;

@Component
@Aspect
public class ExceptionHandlerAspect {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    @Pointcut("execution(* top.cocoawork.monitor.service..*.*(..))")
    public void pointcut(){}

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void catchException(JoinPoint joinPoint, Exception e){

        logger.error("Service执行异常:{}, 参数列表:{}, 异常原因:{}, 异常信息:{}", joinPoint.getSignature().toLongString(), joinPoint.getArgs(), e.getMessage(), e.getStackTrace());

        if (e instanceof ConstraintViolationException) {
            throw new ServiceException(ExceptionEnum.PARAMETER_EXCEPTION.getCode(), e.getMessage());
        }
        if (e instanceof ServiceException) {
            throw (ServiceException)e;
        }

        if (e instanceof Exception) {
            throw new ServiceException(-1, e.getMessage(), e);
        }

    }



}
