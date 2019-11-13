package com.rdotsilva.financescraper.web.repositories;

import com.rdotsilva.financescraper.web.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}