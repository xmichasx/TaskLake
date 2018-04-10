import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan({"controller","configuration","repository"})
@EnableJpaRepositories("repository")
@EnableTransactionManagement
@EntityScan(basePackages="model")
// TODO review: można się pokusić o zmianę nazwy tej klasy, dobrą prakytką jest również stworzenie pakietu dla źródeł (np. com.xmichasx.tasklake)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}