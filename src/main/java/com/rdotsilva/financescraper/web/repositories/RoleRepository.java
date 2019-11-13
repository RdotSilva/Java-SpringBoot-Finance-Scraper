package com.rdotsilva.financescraper.web.repositories;

import com.rdotsilva.financescraper.web.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}