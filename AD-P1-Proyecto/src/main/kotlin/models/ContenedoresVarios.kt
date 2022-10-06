package models

import enums.TipoContenedor
import java.io.File

 class ContenedoresVarios(codigoInternoSituado: String?,
                          tipoContenedor: TipoContenedor?,
                          modelo: String?,
                          descripcionModelo: String?,
                          cantidad: String?,
                          lote: String?,
                          distrito: String?,
                          barrio: String?,
                          tipoVia: String?,
                          nombre: String?,
                          numero: String?,
                          coordenadaX: String?,
                          coordenadaY: String?,
                          TAG: String?


)  {
     val codigoInternoSituado: String?= codigoInternoSituado
     val tipoContenedor: TipoContenedor?=tipoContenedor
     val modelo: String?=modelo
     val descripcionModelo: String?= descripcionModelo
     val cantidad: String?= cantidad
     val lote: String?= lote
     val distrito: String?= distrito
     val barrio: String? = barrio
     val tipoVia: String?= tipoVia
     val nombre: String?= nombre
     val numero: String?= numero
     val coordenadaX: String?= coordenadaX
     val coordenadaY: String?=coordenadaY
     val TAG: String? = TAG

     fun getStringCSV(): String {
        return "${this.codigoInternoSituado};${this.tipoContenedor.toString()};${this.modelo};${this.descripcionModelo};" +
                "${this.cantidad};${this.lote};${this.distrito};${this.barrio};${this.tipoVia};${this.nombre};${this.numero};" +
                "${this.coordenadaX};${this.coordenadaY};${this.TAG}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ContenedoresVarios) return false

        if (codigoInternoSituado != other.codigoInternoSituado) return false
        if (tipoContenedor != other.tipoContenedor) return false
        if (modelo != other.modelo) return false
        if (descripcionModelo != other.descripcionModelo) return false
        if (cantidad != other.cantidad) return false
        if (lote != other.lote) return false
        if (distrito != other.distrito) return false
        if (barrio != other.barrio) return false
        if (tipoVia != other.tipoVia) return false
        if (nombre != other.nombre) return false
        if (numero != other.numero) return false
        if (coordenadaX != other.coordenadaX) return false
        if (coordenadaY != other.coordenadaY) return false
        if (TAG != other.TAG) return false

        return true
    }

    override fun hashCode(): Int {
        var result = codigoInternoSituado?.hashCode() ?: 0
        result = 31 * result + (tipoContenedor?.hashCode() ?: 0)
        result = 31 * result + (modelo?.hashCode() ?: 0)
        result = 31 * result + (descripcionModelo?.hashCode() ?: 0)
        result = 31 * result + (cantidad?.hashCode() ?: 0)
        result = 31 * result + (lote?.hashCode() ?: 0)
        result = 31 * result + (distrito?.hashCode() ?: 0)
        result = 31 * result + (barrio?.hashCode() ?: 0)
        result = 31 * result + (tipoVia?.hashCode() ?: 0)
        result = 31 * result + (nombre?.hashCode() ?: 0)
        result = 31 * result + (numero?.hashCode() ?: 0)
        result = 31 * result + (coordenadaX?.hashCode() ?: 0)
        result = 31 * result + (coordenadaY?.hashCode() ?: 0)
        result = 31 * result + (TAG?.hashCode() ?: 0)
        return result
    }

}

fun loadContenedoresVariosCSV(csvFile: File): List<ContenedoresVarios>{
    val contenedoresVarios: List<ContenedoresVarios> = csvFile.readLines()
        .drop(1)
        .map { it.split(";") }
        .map{
            it.map { campo -> campo.trim() }
            ContenedoresVarios(
                codigoInternoSituado = it[0],
                tipoContenedor = getEnumTipoContenedor(it[1]),
                modelo = it[2],
                descripcionModelo = it[3],
                cantidad = it[4],
                lote = it[5],
                distrito = it[6],
                barrio = it[7],
                tipoVia = it[8],
                nombre = it[9],
                numero = it[10],
                coordenadaX = it[11],
                coordenadaY = it[12],
                TAG = it[13]
            )
        }
    return contenedoresVarios
}

fun getEnumTipoContenedor(s: String): TipoContenedor {
    when (s) {
        "Envases" -> return TipoContenedor.ENVASES
        "Organica" ->return TipoContenedor.ORGANICA
        "Resto" -> return TipoContenedor.RESTO
        "Papel y carton" -> return TipoContenedor.PAPEL_Y_CARTÓN
        "Vidrio" -> return TipoContenedor.VIDRIO
    }
    return TipoContenedor.DESCONOCIDO
}




