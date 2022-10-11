package dto

import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.util.logging.Logger


var logger: Logger = Logger.getLogger("Azahara y Dani Log")

//para pasar a json y a xml
@kotlinx.serialization.Serializable
class ModeloResiduoDTO(año: Int?,
                       mes: String?,
                       lote: Int?,
                       residuo: String?,
                       distrito: String?,
                       nombreDistrito: String?,
                       toneladas: Int?){


    var año: Int? = año
    var mes: String? = mes
    var lote: Int? = lote
    var residuo: String? = residuo
    var distrito: String? = distrito
    var nombreDistrito: String? = nombreDistrito
    var toneladas: Int? = toneladas
    override fun toString(): String {
        return "ModeloResiduoDTO(año=$año, mes=$mes, lote=$lote, residuo=$residuo, distrito=$distrito, nombreDistrito=$nombreDistrito, toneladas=$toneladas)"
    }


}

