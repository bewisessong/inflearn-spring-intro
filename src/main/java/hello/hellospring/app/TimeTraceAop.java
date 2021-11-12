package hello.hellospring.app;

import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // SpringConfig에 등록하지않을 경우 사용
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")   // hello.hellospring 패키지 하위의 모든 클래스(파라미터)
    //@Around("execution(* hello.hellospring.service..*(..))")    // service 패키지만 실행
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint에서 여러 조작 가능
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed(); // 실제 서비스 호출
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
