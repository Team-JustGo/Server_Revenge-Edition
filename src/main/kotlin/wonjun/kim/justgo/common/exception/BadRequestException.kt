package wonjun.kim.justgo.common.exception

import org.springframework.http.HttpStatus


class BadRequestException(reason: String = "Bad request") : JustGoException(HttpStatus.BAD_REQUEST, reason)
