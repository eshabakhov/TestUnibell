package test.crm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="client")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "client_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "Имя клиента обязательно для заполнения")
    @Column(name = "name", nullable = false)
    private String name;

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
