package wonjun.kim.justgo.common.exception

import org.springframework.http.HttpStatus

class ResourceNotFoundException(reason: String = "Resource not found") : JustGoException(HttpStatus.NOT_FOUND, reason)
