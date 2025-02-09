package test.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.crm.dto.Contact;
import test.crm.enums.TypeContactEnum;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByClientId(Long clientId);
    List<Contact> findByClientIdAndTypeContact(Long clientId, TypeContactEnum typeContactEnum);
}
