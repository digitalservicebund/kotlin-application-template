package de.bund.digitalservice.template

import kotlinx.coroutines.reactor.mono
import org.springframework.http.MediaType.TEXT_HTML_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloWorldController {
    @GetMapping(path = ["/"], produces = [TEXT_HTML_VALUE])
    fun get(): Mono<String> =
        mono {
            """
            <html>
                <head>
                   <title>Hello, World!</title>
                </head>
                <body>
                    <h1>Hello, World!</h1>
                </body>            
            </html>
            """.trimIndent()
        }
}
