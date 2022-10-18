package mappers

import dto.ContenedoresVariosDTO
import models.ContenedoresVarios

class MapperContenedoresVarios {

    fun contenedoresVariosToDto(pojo: ContenedoresVarios): ContenedoresVariosDTO {

        return ContenedoresVariosDTO(
            codigoInternoSituado = pojo.codigoInternoSituado,
            // tipoContenedor = getTipoContenedor(pojo.tipoContenedor),
            tipoContenedor = pojo.tipoContenedor,
            modelo = pojo.modelo,
            descripcionModelo = pojo.descripcionModelo,
            cantidad = pojo.cantidad,
            lote = pojo.lote,
            distrito = pojo.distrito,
            barrio = pojo.barrio,
            tipoVia = pojo.tipoVia,
            nombre = pojo.nombre,
            numero = pojo.numero?.toIntOrNull(),
            coordenadaX = pojo.coordenadaX,
            coordenadaY = pojo.coordenadaY,
            TAG = pojo.TAG
        )
    }


    fun tdoToContenedoresVarios(dto: ContenedoresVariosDTO): ContenedoresVarios {

        //Todo creo que falla esto
        return ContenedoresVarios(
            codigoInternoSituado = dto.codigoInternoSituado,
            tipoContenedor = dto.tipoContenedor,
            modelo = dto.modelo,
            descripcionModelo = dto.descripcionModelo,
            cantidad = dto.cantidad,
            lote = dto.lote,
            distrito = dto.distrito,
            barrio = dto.barrio,
            tipoVia = dto.tipoVia,
            nombre = dto.nombre,
            numero = dto.numero.toString(),
            coordenadaX = dto.coordenadaX,
            coordenadaY = dto.coordenadaY,
            TAG = dto.TAG
        )
    }
}




