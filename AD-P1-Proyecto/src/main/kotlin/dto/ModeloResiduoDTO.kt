package dto

import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.io.Serializable
import java.util.logging.Logger

var logger: Logger = Logger.getLogger("Azahara y Dani Log")

class ModeloResiduoDTO(año: Int?,
                       mes: String?,
                       lote: Int?,
                       residuo: String?,
                       distrito: String?,
                       nombreDistrito: String?,
                       toneladas: Int?) : Serializable {
    var año: Int? = año
    var mes: String? = mes
    var lote: Int? = lote
    var residuo: String? = residuo
    var distrito: String? = distrito
    var nombreDistrito: String? = nombreDistrito
    var toneladas: Int? = toneladas


    fun DtoToMdeloRediduo() {
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
    constructor(m : ModeloResiduo):this(
             m.año,
            m.mes.toString(),
             m.lote,
             m.residuo.toString(),
             m.distrito,
             m.nombreDistrito,
             m.toneladas)
    }

    fun getStringScv(): String {
        return ""
=======
        return "${this.año.toString()};${this.mes.toString()};${this.lote.toString()}" +
                ";${this.residuo?.toString()};${this.nombreDistrito},${this.toneladas.toString()}"
>>>>>>> parent of b997d54... cambiado alguna cosa del dto
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

