package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserData, Long> {

}