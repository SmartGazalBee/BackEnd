package Matdol.SmartGazalBee.User.Repository;

import Matdol.SmartGazalBee.User.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
