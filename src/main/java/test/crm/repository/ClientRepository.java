package test.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.crm.dto.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
