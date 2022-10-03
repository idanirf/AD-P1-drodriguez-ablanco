package models

import enums.TipoContenedor
import java.io.File

data class ContenedoresVarios(
    val codigoInternoSituado: String?,
    val tipoContenedor: TipoContenedor?,
    val modelo: String?,
    val descripcionModelo: String?,
    val cantidad: String?,
    val lote: String?,
    val distrito: String?,
    val barrio: String?,
    val tipoVia: String?,
    val nombre: String?,
    val numero: String?,
    val coordenadaX: String?,
    val coordenadaY: String?,
    val TAG: String?

) {

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


