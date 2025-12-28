package com.example.SpringConcepts.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class StatusLoggingAspect {
    @Around("execution(* com.example.SpringConcepts.controller.WashingMachineController.getDetails(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("running status fetching ...");
        Object obj = joinPoint.proceed();
        System.out.print("Join Point execution finished");
        return obj;
    }

    @Pointcut("execution(com.example.SpringConcepts.component.UserInfo com.example.SpringConcepts.controller.WashingMachineController.*(..))")
    public void afterReturningPointCutExpression(){}

    @AfterReturning("afterReturningPointCutExpression()")
    public void afterSuccessfulExecutionOfJoinPoint(){
        System.out.println("join point executed successfully");
    }

    @Pointcut("execution(com.example.SpringConcepts.component.UserInfo " +
            "com.example.SpringConcepts.controller.WashingMachineController.getUserInfo(..))")
    public void afterThrowingPointCutExpression(){}

    @AfterThrowing(value = "afterThrowingPointCutExpression()",throwing = "e")
    public void exceptionRaiser(Exception e) throws Exception {
        System.out.println(e.getMessage());
    }
}
