package interchange

import dto.ModeloResiduoDTO
import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.LogManager
import java.util.logging.Logger
import java.util.stream.Collectors


class InterchangeModeloResiduo<ModeloResiduo> (){

    var logger: Logger = Logger.getLogger("Azahara y Dani Log")
    //logger.info(" entra a Interchange de Modelo residuo")
    
    //remodelando solo lo necesario
    fun dtoToString(m : ModeloResiduoDTO):String {
        return "${m.año};${m.mes};${m.lote};${m.residuo};${m.distrito};${m.nombreDistrito};${m.toneladas};"
    }
    fun stringToDto(campos : ArrayList<String> ): ModeloResiduoDTO{
        return ModeloResiduoDTO(
            año =campos[0].toIntOrNull(),
            mes = campos[1],
            lote = campos[2].toIntOrNull() ,
            residuo = campos[3],
            distrito = campos[4],
            nombreDistrito = campos[5],
            toneladas = campos[6].toIntOrNull()
        )
    }
    fun objectToDto(m : models.ModeloResiduo):ModeloResiduoDTO{
        return ModeloResiduoDTO(
            año =m.año,
            mes = m.mes.toString(),
            lote = m.lote,
            residuo = m.residuo.toString(),
            distrito = m.distrito,
            nombreDistrito = m.nombreDistrito,
            toneladas = m.toneladas
        )
    }
    fun dtoToObject(m: ModeloResiduoDTO): models.ModeloResiduo{
        return ModeloResiduo(
            año =m.año,
            mes = getMes(m.mes),
            lote = m.lote,
            residuo = getTipoResiduo(m.residuo),
            distrito = m.distrito,
            nombreDistrito = m.nombreDistrito,
            toneladas = m.toneladas
        )
    }



    private fun getMes(s: String?): Meses? {
        logger.info(" entrado en get mes")
        when(s){
            "ENERO" -> return Meses.ENERO
            "FEBRERO"->return Meses.FEBRERO
            "MARZO"-> return  Meses.MARZO
            "ABRIL"-> return  Meses.ABRIL
            "MAYO" -> return  Meses.MAYO
            "JUNIO" -> return  Meses.JUNIO
            "JULIO" -> return  Meses.JULIO
            "AGOSTO" -> return  Meses.AGOSTO
            "SEPTIEMBRE"-> return  Meses.SEPTIEMBRE
            "OCTUBRE"-> return  Meses.OCTUBRE
            "NOVIEMBRE" ->return  Meses.NOVIEMBRE
            "DICIEMBRE" ->return  Meses.DICIEMBRE

        }
        return null
    }

    /**
     * PASADA UNA STRING NOS DEVIELVE EL PIDO DE RESIDU QUE ES, Y SI NO ESTÁ EN LA LISTA PONE DESCONOCIDO
     */
    private fun getTipoResiduo(s: String?): TipoResiduo? {
        logger.info(" entrado en get tipo residuo")

        when(s){
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES"-> return TipoResiduo.ENVASES
            "VIDRIO"-> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON"-> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS"-> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL"-> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL"-> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS"-> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS"-> return TipoResiduo.ANIMALES_MUERTOS
            "RCD"-> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA"-> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES"-> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
        }
        return TipoResiduo.DESCONOCIDO
    }







}