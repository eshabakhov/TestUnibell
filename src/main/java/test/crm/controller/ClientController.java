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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import test.crm.dto.Client;
import test.crm.dto.ResponseDTO;
import test.crm.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/clients")
@Tag(name = "Клиенты", description = "Управление клиентами")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Добавление нового клиента",
                description = "Добавляет нового клиента в систему.")
    @ApiResponse(responseCode = "201",
                    description = "Клиент успешно добавлен",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)))
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody @Valid Client client) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.create(client));
    }

    @Operation(summary = "Получить список всех клиентов",
                description = "Возвращает всех клиентов.")
    @ApiResponse(responseCode = "200",
                    description = "Список всех клиентов",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class)))
    @GetMapping
    public ResponseEntity<List<Client>> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.getList(page, pageSize));
    }

    @Operation(summary = "Получить информацию о клиенте",
                description = "Возвращает информацию о клиенте по ID.")
    @ApiResponse(responseCode = "200",
                description = "Информация о клиенте",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "404",
                description = "Клиент не найден",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ResponseDTO.class)))
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> get(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientService.get(id));
    }
}
