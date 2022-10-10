package mappers

import dto.ContenedoresVariosDTO
import models.ContenedoresVarios

class MapperContenedoresVarios {

        fun contenedoresVariosToDto(pojo: ContenedoresVarios): ContenedoresVariosDTO {

            return ContenedoresVariosDTO(
                codigoInternoSituado = pojo.codigoInternoSituado,
                // tipoContenedor = getTipoContenedor(pojo.tipoContenedor),
                tipoContenedor =pojo.tipoContenedor,
                modelo = pojo.modelo,
                descripcionModelo = pojo.descripcionModelo,
                cantidad = pojo.cantidad,
                lote = pojo.lote,
                distrito = pojo.distrito,
                barrio = pojo.barrio,
                tipoVia = pojo.tipoVia,
                nombre = pojo.nombre,
                numero = pojo.numero,
                coordenadaX = pojo.coordenadaX,
                coordenadaY = pojo.coordenadaY,
                TAG = pojo.TAG
            )
        }



    }

    fun tdoToContenedoresVarios(dto: ContenedoresVariosDTO): ContenedoresVarios {

        return ContenedoresVarios(
            codigoInternoSituado = dto.codigoInternoSituado,
            //tipoContenedor = getTipoContenedor(pojo.tipoContenedor),
            tipoContenedor =dto.tipoContenedor,
            modelo = dto.modelo,
            descripcionModelo = dto.descripcionModelo,
            cantidad = dto.cantidad,
            lote = dto.lote,
            distrito = dto.distrito,
            barrio = dto.barrio,
            tipoVia = dto.tipoVia,
            nombre = dto.nombre,
            numero = dto.numero,
            coordenadaX = dto.coordenadaX,
            coordenadaY = dto.coordenadaY,
            TAG = dto.TAG
        )
    }




    fun ContenedoresVariosDTO.toDto(): ContenedoresVariosDTO {
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
