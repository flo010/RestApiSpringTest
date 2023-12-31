package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new UserData("User", "thief")));
            log.info("Preloading " + userRepository.save(new UserData("Bilbo Baggins", "burglar")));
            log.info("Preloading " + employeeRepository.save(new Employee("employee", "burglar")));
            log.info("Preloading " + employeeRepository.save(new Employee("Frodo Baggins", "thief")));
            };
    }

}