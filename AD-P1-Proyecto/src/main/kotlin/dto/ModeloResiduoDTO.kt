package dto

import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.io.Serializable
import java.util.logging.Logger

var logger: Logger = Logger.getLogger("Azahara y Dani Log")

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
        residuo: String?,
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
            getMes(this.mes),
            this.lote,
            getTipoResiduo(this.residuo),
            this.distrito,
            this.nombreDistrito,
            this.toneladas,
        )
    }

    fun getStringScv(): String {
        return "${this.año.toString()};${this.mes.toString()};${this.lote.toString()}" +
                ";${this.residuo?.toString()};${this.nombreDistrito},${this.toneladas.toString()}"
    }

    fun getStringXml(): String {
        return ""
    }

    fun getStringJson(): String {
        return ""
    }
    private fun getMes(s: String?): Meses? {
        logger.info(" entrado en get mes")
       if (s==null) return null
        when(s){
            "ENERO" -> return Meses.ENERO
            "FEBRERO"->return Meses.FEBRERO
            "MARZO"-> return  Meses.MARZO
            "ABRIL"-> return  Meses.ABRIL
            "MAYO" -> return  Meses.MAYO
            "JUNIO" -> return  Meses.JUNIO
            "JULIO" -> return  Meses.JULIO
            "AGOSTO" -> return  Meses.AGOSTO
            "SEPTIEMBRE"-> return  Meses.SEPTIEMBRE
            "OCTUBRE"-> return  Meses.OCTUBRE
            "NOVIEMBRE" ->return  Meses.NOVIEMBRE
            "DICIEMBRE" ->return  Meses.DICIEMBRE
             else -> return null
        }
        return null
    }
    private fun getTipoResiduo(s: String?): TipoResiduo? {
        logger.info(" entrado en get tipo residuo")
        if (s==null) return null

        when(s){
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES"-> return TipoResiduo.ENVASES
            "VIDRIO"-> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON"-> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS"-> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL"-> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL"-> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS"-> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS"-> return TipoResiduo.ANIMALES_MUERTOS
            "RCD"-> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA"-> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES"-> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
            else -> return null
        }
        return TipoResiduo.DESCONOCIDO
    }
}

