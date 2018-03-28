import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO review: niepotrzebne importy
// TODO review: niepotrzebne komentarze
@EnableAutoConfiguration // aplikacja sama się skonfiguruje, załaduje potrzebne moduły itp. w zaleznosci co dodaliśmy w pom.xml
@ComponentScan({"controller","configuration","repository"}) // skanuje inne komponenty znajdujace sie w tym samym pakiecie ( w tym
                                                //przypadku dodalismy do skanowania dwa inne pakiety
@EnableJpaRepositories("repository")
@EnableTransactionManagement
@EntityScan(basePackages="model")

public class Main {



    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}