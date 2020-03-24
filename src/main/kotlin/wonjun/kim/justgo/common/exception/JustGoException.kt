package wonjun.kim.justgo.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class JustGoException(
    status: HttpStatus,
    reason: String
) : ResponseStatusException(status, reason)
