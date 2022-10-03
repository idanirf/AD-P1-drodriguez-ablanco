package dto

import java.awt.DisplayMode

@Serializable
data class ContenedoresVariosDTO(
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