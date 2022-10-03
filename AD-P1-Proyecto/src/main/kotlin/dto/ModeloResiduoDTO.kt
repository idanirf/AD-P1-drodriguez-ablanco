package dto

import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.io.Serializable

class ModeloResiduoDTO (): Serializable {
    var año: Int? = null
    var mes: String? = null
    var lote: Int? = null
    var residuo: String? = null
    var distrito: String? = null
    var nombreDistrito: String? = null
    var toneladas: Int? = null

    fun modeloRediduoToDTO(
        año: Int?,
        mes: String?,
        lote: Int?,
        residuo: String,
        distrito: String?,
        nombreDistrito: String?,
        toneladas: Int?
    ) {
        this.año = año
        this.mes = mes
        this.lote = lote
        this.residuo = residuo
        this.distrito = distrito
        this.nombreDistrito = nombreDistrito
        this.toneladas = toneladas
    }

    fun DtoTomodeloRediduo() {
        ModeloResiduo(
            this.año,
            this.mes,
            this.lote,
            this.residuo,
            this.distrito,
            this.nombreDistrito,
            this.toneladas,
        )
    }

    fun getStringScv(): String {
        return "${this.año.toString()};${this.mes.toString()};${this.lote.toString()}" +
                ";${this.residuo?.name};${this.nombreDistrito},${this.toneladas.toString()}"
    }

    fun getStringXml(): String {
        return ""
    }

    fun getStringJson(): String {
        return ""
    }
}
    
