package dto

import java.io.Serializable

// @Serializable
data class ContenedoresVariosDTO(
    val codigoInternoSituado: String?,
    val tipoContenedor: String?,
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
): Serializable {} //todo preguntar a Jose sí es lo mismo que @Serializable y sí es diferente en que se diferencia