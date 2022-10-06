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
    fun getStringScv(): String {
        return "$año";"$mes";"$lote";"$residuo";"$distrito";"$nombreDistrito";"$toneladas"
    }

    var año: Int? = año
    var mes: String? = mes
    var lote: Int? = lote
    var residuo: String? = residuo
    var distrito: String? = distrito
    var nombreDistrito: String? = nombreDistrito
    var toneladas: Int? = toneladas



}

