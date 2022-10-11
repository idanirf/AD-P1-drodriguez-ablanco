package models

import enums.Meses
import enums.TipoResiduo
import java.io.Serializable
import java.time.LocalDate

 class ModeloResiduo (año:Int?, mes: Meses?, lote: Int?, residuo: TipoResiduo?, distrito : String?
                     , nombreDistrito: String?, toneladas: Int?): Serializable {
    val año : Int?=año
     //todo no esta pasando bien a meses al dto
    val mes : Meses? = mes
    val lote : Int? = lote
    var residuo : TipoResiduo? = residuo
    val distrito : String? = distrito
    val nombreDistrito : String? = nombreDistrito
    val toneladas : Int? = toneladas

     /**
      * devuelve una strig de este objeto en csv
      */


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModeloResiduo

        if (año != other.año) return false
        if (mes != other.mes) return false
        if (lote != other.lote) return false
        if (residuo != other.residuo) return false
        if (distrito != other.distrito) return false
        if (nombreDistrito != other.nombreDistrito) return false
        if (toneladas != other.toneladas) return false

        return true
    }

    override fun hashCode(): Int {
        var result = año ?: 0
        result = 31 * result + (mes?.hashCode() ?: 0)
        result = 31 * result + (lote ?: 0)
        result = 31 * result + (residuo?.hashCode() ?: 0)
        result = 31 * result + (distrito?.hashCode() ?: 0)
        result = 31 * result + (nombreDistrito?.hashCode() ?: 0)
        result = 31 * result + (toneladas ?: 0)
        return result
    }

     fun getStringScv(): String {
         return "$año";"$mes";"$lote";"$residuo";"$distrito";"$nombreDistrito";"$toneladas"
     }

     override fun toString(): String {
         return "ModeloResiduo(año=$año, mes=$mes, lote=$lote, residuo=$residuo, distrito=$distrito, nombreDistrito=$nombreDistrito, toneladas=$toneladas)"
     }


 }