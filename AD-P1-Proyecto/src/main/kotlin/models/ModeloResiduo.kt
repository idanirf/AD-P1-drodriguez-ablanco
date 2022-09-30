package models

import enums.Meses
import enums.TipoResiduo
import java.io.Serializable
import java.time.LocalDate

class ModeloResiduo (

    val año : Int,
    val mes : Meses,
    val lote : Int,
    val residuo : TipoResiduo,
    val distrito : String,
    val nombreDistrito : String,
    val toneladas : Int,

    //hay un numero final que no se

//imprementamos para que sea un objeto seriablizable
): Serializable {

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
        var result = año
        result = 31 * result + mes.hashCode()
        result = 31 * result + lote
        result = 31 * result + residuo.hashCode()
        result = 31 * result + distrito.hashCode()
        result = 31 * result + nombreDistrito.hashCode()
        result = 31 * result + toneladas
        return result
    }
}