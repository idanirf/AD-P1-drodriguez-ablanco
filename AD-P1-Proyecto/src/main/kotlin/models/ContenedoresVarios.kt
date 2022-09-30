package models

import java.io.File

data class ContenedoresVarios(
    val codigoInternoSituado: String,
    val tipoContenedor: String,
    val modelo: String,
    val descripcionModelo: String,
    val cantidad: String,
    val lote: String,
    val distrito: String,
    val barrio: String,
    val tipoVia: String,
    val nombre: String,
    val numero: String,
    val coordenadaX: String,
    val coordenadaY: String,
    val TAG: String

)

fun loadContenedoresVariosCSV(csvFile: File): List<ContenedoresVarios>{
    val contenedoresVarios: List<ContenedoresVarios> = csvFile.readLines()
        .drop(1)
        .map { it.split(";") }
        .map{
            it.map { campo -> campo.trim() }
            ContenedoresVarios(
                codigoInternoSituado = it[0],
                tipoContenedor = it[2],
                modelo = it[3],
                descripcionModelo = it[4],
                cantidad = it[5],
                lote = it[6],
                distrito = it[7],
                barrio = it[8],
                tipoVia = it[9],
                nombre = it[10],
                numero = it[11],
                coordenadaX = it[12],
                coordenadaY = it[13],
                TAG = it[14]
            )
        }
    return contenedoresVarios
}


