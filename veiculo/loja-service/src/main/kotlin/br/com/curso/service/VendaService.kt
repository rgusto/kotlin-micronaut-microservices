package br.com.curso.service

import br.com.curso.dto.input.VendaInput
import br.com.curso.dto.output.Parcela
import br.com.curso.dto.output.Venda
import br.com.curso.http.VeiculoHttp
import jakarta.inject.Singleton
import java.math.MathContext
import java.math.RoundingMode
import java.time.LocalDate

@Singleton
class VendaService(

    private val veiculoHttp: VeiculoHttp

) {

    fun realizarVenda(vendaInput: VendaInput) {
        val veiculo = veiculoHttp.findById(vendaInput.veiculo)
        var parcelas: List<Parcela> = ArrayList<Parcela>()
        var valorParcela = vendaInput.valor.divide(vendaInput.quantidadeParcelas.toBigDecimal(), 2, RoundingMode.HALF_UP)
        var dataVencimento = LocalDate.now().plusMonths(1)

        for (i in 1..vendaInput.quantidadeParcelas) {
            var parcela = Parcela(valorParcela, dataVencimento.toString())
            parcelas = parcelas.plus(parcela)
            dataVencimento = dataVencimento.plusMonths(1)
        }
        var venda = Venda(vendaInput.cliente, veiculo, vendaInput.valor, parcelas)

        print(venda)

    }

}