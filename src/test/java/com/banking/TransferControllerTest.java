package com.banking;

import com.banking.controller.TransferController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

class TransferControllerTest {

    private WebTestClient testClient;

    @BeforeAll
    void setUp() throws Exception {
        this.testClient = WebTestClient
                .bindToController(new TransferController(Objects::isNull))
                .build();
    }

    @Test
    void greeting() throws Exception {
        this.testClient.get().uri("/v1/transfer") //
            .exchange() //
            .expectStatus().isOk() //
            .expectBody(String.class).isEqualTo("{\"id\":1,\"content\":\"Hello, World!\"}");
    }
}
