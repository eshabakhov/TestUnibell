package test.crm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.crm.dto.Contact;
import test.crm.dto.ResponseDTO;
import test.crm.enums.TypeContactEnum;
import test.crm.service.ContactService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/contacts")
@Tag(name = "Контакты клиентов", description = "Управление контактной информацией клиентов")
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "Добавление контактной информации о клиенте",
                description = "Добавляет контактную информации о клиенте.")
    @ApiResponse(responseCode = "201",
                    description = "Контактная информация о клиенте успешно добавлена",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Contact.class)))
    @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)))
    @ApiResponse(responseCode = "404",
                    description = "Клиент не найден",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)))
    @PostMapping
    public ResponseEntity<Contact> create(
            @RequestParam(value = "client_id") Long clientId,
            @RequestBody @Valid Contact contact) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(contactService.create(clientId, contact));
    }

    @Operation(summary = "Получить список всей контактной информации о клиенте",
                description = "Возвращает список всей контактной информации о клиенте.")
    @ApiResponse(responseCode = "200",
                    description = "Список всех клиентов",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Contact.class)))
    @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)))
    @ApiResponse(responseCode = "404",
                    description = "Клиент не найден",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<Contact>> getList(
            @RequestParam(value = "client_id") Long clientId,
            @RequestParam(value = "type_contact", required = false) @Valid TypeContactEnum typeContactEnum) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactService.getList(clientId, typeContactEnum));
    }
}
