package br.com.curso.controller

import br.com.curso.model.Veiculo
import br.com.curso.service.VeiculoService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/veiculos")
class VeiculoController(

    private val veiculoService: VeiculoService

) {

    @Post
    fun create(veiculo: Veiculo): HttpResponse<Veiculo> {
        return HttpResponse.created(veiculoService.create(veiculo))
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long): HttpResponse<Veiculo> {
        return HttpResponse.ok(veiculoService.findById(id));
    }


}