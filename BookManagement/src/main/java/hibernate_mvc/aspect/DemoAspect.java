package hibernate_mvc.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;
import java.util.logging.Logger;


@Aspect
@Component
public class DemoAspect {

    private  Logger loginfo= (Logger) Logger.getLogger(getClass().getName());
    @Before("execution(* hibernate_mvc.service.AuthorService.getAllAuthors())")
    public void gettingAllAuthors()
    {
        System.out.println("---------------------------------------------------------");
        System.out.println("Your are getting all the list of authors");
        System.out.println("---------------------------------------------------------");
    }
}
