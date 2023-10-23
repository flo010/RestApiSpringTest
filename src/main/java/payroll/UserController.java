package payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/user")
    List<UserData> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/user")
    UserData newUser(@RequestBody UserData newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/user/{id}")
    UserData one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    UserData replaceUser(@RequestBody UserData newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newUser.getName());
                    employee.setRole(newUser.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
