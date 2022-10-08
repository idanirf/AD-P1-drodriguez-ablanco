package mappers

import dto.ModeloResiduoDTO
import dto.logger
import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo

class MaperModeloResiduo {

    fun modeloResituoToDto(pojo: ModeloResiduo): ModeloResiduoDTO {

        return ModeloResiduoDTO(
            pojo.año,
            pojo.mes.toString(),
            pojo.lote,
            pojo.residuo.toString(),
            pojo.distrito,
            pojo.nombreDistrito,
            pojo.toneladas
        )
    }

    fun tdoToModrloResiduo(dto: ModeloResiduoDTO): ModeloResiduo {
        return ModeloResiduo(
            dto.año,
            getMes(dto.mes),
            dto.lote,
            getTipoResiduo(dto.residuo),
            dto.distrito,
            dto.nombreDistrito,
            dto.toneladas
        )

    }

    private fun getMes(s: String?): Meses? {
        logger.info(" entrado en get mes")
        if (s == null) return null
        when (s) {
            "ENERO" -> return Meses.ENERO
            "FEBRERO" -> return Meses.FEBRERO
            "MARZO" -> return Meses.MARZO
            "ABRIL" -> return Meses.ABRIL
            "MAYO" -> return Meses.MAYO
            "JUNIO" -> return Meses.JUNIO
            "JULIO" -> return Meses.JULIO
            "AGOSTO" -> return Meses.AGOSTO
            "SEPTIEMBRE" -> return Meses.SEPTIEMBRE
            "OCTUBRE" -> return Meses.OCTUBRE
            "NOVIEMBRE" -> return Meses.NOVIEMBRE
            "DICIEMBRE" -> return Meses.DICIEMBRE
            else -> return null
        }
        return null
    }

    private fun getTipoResiduo(s: String?): TipoResiduo? {
        logger.info(" entrado en get tipo residuo")
        if (s == null) return null

        when (s) {
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES" -> return TipoResiduo.ENVASES
            "VIDRIO" -> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON" -> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS" -> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL" -> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL" -> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS" -> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS" -> return TipoResiduo.ANIMALES_MUERTOS
            "RCD" -> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA" -> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES" -> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
            else -> return null
        }
        return TipoResiduo.DESCONOCIDO
    }


}



