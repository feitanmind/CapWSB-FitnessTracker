package com.capgemini.wsb.fitnesstracker.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Slf4j
@Component
class GeneralServiceAspect {

    @Pointcut("execution(* com.capgemini.wsb.fitnesstracker.*.internal.*Service.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
        public void beforeServiceCall(JoinPoint joinPoint) {
        String log = formatString(joinPoint);
        System.out.println("Before: " + log);
    }
    @AfterReturning(value = "execution(* com.capgemini.wsb.fitnesstracker.*.internal.*Service.*(..))",returning = "obj")
    public void afterServiceCall(JoinPoint joinPoint, Object obj)
    {
        String log = formatString(joinPoint);
        String logRet = obj != null ?obj.toString() : "no content";
        System.out.println("After: " + log + " Return: "+logRet);
    }
    private String formatString(JoinPoint joinPoint)
    {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String returnType = signature.getReturnType().getTypeName();
        returnType = returnType.lastIndexOf('.') != -1 ? returnType.substring(returnType.lastIndexOf('.')+1) : returnType;
        String serviceName = signature.getMethod().getDeclaringClass().getName();
        serviceName = serviceName.substring(serviceName.lastIndexOf('.')+1);
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<String> argsWithTypes = new ArrayList<>();
        Arrays.stream(args).forEach(g -> argsWithTypes.add( (g.getClass().getName().lastIndexOf(".") != -1 ? g.getClass().getName().substring(g.getClass().getName().lastIndexOf(".")+1):g.getClass().getName() )+ " " + g.toString()));
        String argsString = argsWithTypes.toString().replace('[','(').replace(']',')');
        return returnType+ " "+serviceName+"."+ methodName + argsString;
    }

}