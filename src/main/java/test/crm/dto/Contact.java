package test.crm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import test.crm.enums.TypeContactEnum;
import test.crm.validation.ValidContact;

@Entity
@Table(name="contact")
@Getter
@Setter
@ToString
@ValidContact
@RequiredArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_generator")
    @SequenceGenerator(name = "contact_generator", sequenceName = "contact_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @JsonProperty(value = "type_contact")
    @NotNull(message = "Тип контакта клиента не должен быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "type_contact", nullable = false)
    private TypeContactEnum typeContact;

    @JsonProperty(value = "value_contact")
    @NotBlank(message = "Контакт обязателен для заполнения")
    @Column(name = "value_contact", nullable = false)
    private String valueContact;

    @ManyToOne
    private Client client;

    @JsonProperty(value = "is_exist")
    @Column(name = "is_exist", nullable = false)
    private boolean isExist;

    @PrePersist
    public void prePersist() {
        if (!isExist) {
            isExist = true;
        }
    }
}
