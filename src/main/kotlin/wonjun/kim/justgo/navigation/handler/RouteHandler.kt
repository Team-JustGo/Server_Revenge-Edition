package wonjun.kim.justgo.navigation.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import wonjun.kim.justgo.navigation.router.request.RouteRequest

@Component
class RouteHandler(private val navigationRepository: NavigationRepository) {

    fun getRoute(serverRequest: ServerRequest) =
        RouteRequest.from(serverRequest.queryParams().toSingleValueMap()).toMono().map {

        }

}
