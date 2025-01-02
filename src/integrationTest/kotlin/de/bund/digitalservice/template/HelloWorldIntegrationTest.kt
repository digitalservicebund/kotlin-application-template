package de.bund.digitalservice.template

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloWorldIntegrationTest(
    @Autowired val webTestClient: WebTestClient,
) {
    @Test
    fun `should expose hello world`() {
        webTestClient
            .get()
            .uri("/")
            .exchange()
            .expectStatus()
            .isOk()
    }
}
