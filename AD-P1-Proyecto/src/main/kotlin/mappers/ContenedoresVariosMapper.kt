package mappers

import dto.ContenedoresVariosDTO
import enums.TipoContenedor
import models.ContenedoresVarios

fun ContenedoresVariosDTO.toDto(): ContenedoresVariosDTO{
    return ContenedoresVariosDTO(
        codigoInternoSituado = this.codigoInternoSituado,
        tipoContenedor = this.tipoContenedor,
        modelo = this.modelo,
        descripcionModelo = this.descripcionModelo,
        cantidad = this.cantidad,
        lote = this.lote,
        distrito = this.distrito,
        barrio = this.barrio,
        tipoVia = this.tipoVia,
        nombre = this.nombre,
        numero = this.numero,
        coordenadaX = this.coordenadaX,
        coordenadaY = this.coordenadaY,
        TAG = this.TAG
    )
}
