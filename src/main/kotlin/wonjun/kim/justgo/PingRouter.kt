package wonjun.kim.justgo

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Component
class PingRouter {

    @Bean
    fun get() = router {
        "/".nest {
            GET("") { ServerResponse.ok().body(fromValue("Hello~")) }
        }
    }

}