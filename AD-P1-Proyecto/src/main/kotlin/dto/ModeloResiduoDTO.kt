package dto

import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.util.logging.Logger


var logger: Logger = Logger.getLogger("Azahara y Dani Log")

//para pasar a json y a xml
@kotlinx.serialization.Serializable
class ModeloResiduoDTO(


    var año: Int? ,
    var mes: String? ,
    var lote: Int? ,
    var residuo: String? ,
    var distrito: String?,
    var nombreDistrito: String? ,
    var toneladas: Int? ,
){
    override fun toString(): String {
        return "ModeloResiduoDTO(año=$año, mes=$mes, lote=$lote, residuo=$residuo, distrito=$distrito, nombreDistrito=$nombreDistrito, toneladas=$toneladas)"
    }


}

