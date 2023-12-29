package ackermann.ilnara.apirest

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
@OpenAPIDefinition(
	info = Info(
		title = "Minha API de Música",
		version = "1.0",
		description = "Esta API fornece acesso aos dados de música."
	))
@SpringBootApplication
class ApiRestApplication

fun main(args: Array<String>) {
	runApplication<ApiRestApplication>(*args)
}
