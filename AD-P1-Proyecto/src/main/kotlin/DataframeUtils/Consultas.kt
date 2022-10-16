package DataframeUtils

import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import java.nio.file.Path
import javax.management.Query.div


class Consultas {
    fun getToneladasPorResiduo(filasMr: DataFrame<Any?>): DataFrame<Any?> {
        logger.info("Suma de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { sum("Toneladas") into "Suma" }
    }

    fun getMaximo(filasMr: DataFrame<Any?>): DataFrame<Any?>{
        logger.info("maximo de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { max("Toneladas") into "Maximo de Toneladas" }
    }
    fun getMinimo(filasMr: DataFrame<Any?>): DataFrame<Any?>{
        logger.info("minimo de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { min("Toneladas") into "Minimo de Toneladas" }
    }
    fun getMedia(filasMr: DataFrame<Any?>): DataFrame<Any?> {
        logger.info("Media de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { mean("Toneladas") into "Media de Toneladas" }
    }

    fun getContenedoresDeCadaTipo(filasCv: DataFrame<Any?>): DataFrame<Any?>{
        logger.info("Número de contenedores de cada tipo que hay en este distrito")
        return  filasCv.groupBy("Tipo Contenedor")
            .aggregate { count() into  "Numero de contenedores de ese tipo" }
    }

    fun getNumeroContenedoresPorDistrito(filasCv: DataFrame<Any?>): DataFrame<Any?> {
        //Esta bien
        logger.info("nuemro de contenedores por dstrito")
        return filasCv.groupBy("Distrito").aggregate { count() into "Numero de contenedores" }
    }

    fun getmediaDeContenedoresDeCadaTipo(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        //todo no me sale
        logger.info("Media de contenedores de cada tipo que hay en cada distrito")
        return filasCv.groupBy("Distrito", "Tipo Contenedor").sortBy("Distrito").aggregate { count() into "Numero de contenedores" }

    }

    fun getmediaToneladasAnuales(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
        //no coje año
        println( filasMr.get(1))
        logger.info(
            "Media de toneladas anuales de recogidas por" +
                    " cada tipo de basura agrupadas por distrito"
        )
        //todo no se como hacerlo
       return filasMr.groupBy("Nombre Distrito", "Residuo").aggregate { max()}



    }

    fun getmaxToneladasPorDistrito(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
        //sale ok
        logger.info(
            "Máximo de toneladas anuales de recogidas por cada tipo\n" +
                    "    de basura agrupadas por distrito."
        )

        //println(filasMr.columnNames())

        return filasMr.groupBy("Nombre Distrito", "Residuo")
            .aggregate { max("Toneladas") into "maximo"}.sortBy("Nombre Distrito")



    }

    fun getminToneladasPorDistrito(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        //sale ok
        logger.info(
            " mínimo de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )
        return filasCv.groupBy("Nombre Distrito", "Residuo")
            .aggregate { min("Toneladas") into "minimo"}.sortBy("Nombre Distrito")
    }

    fun getMedToneladasPorDistrito(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        //sale ok
        logger.info(
            " media de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )
        return filasCv.groupBy("Nombre Distrito", "Residuo")
            .aggregate { mean("Toneladas") into "media"}.sortBy("Nombre Distrito")
    }

    fun getsumToneladasPorDistrito(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        logger.info(
            " suma de todas las toneladas. "
        )
        return filasCv.groupBy("Nombre Distrito")
            .aggregate {  sum("Toneladas") into "Toneladas Toales"}
    }

    fun sumToneladasPorDistritoPorTipo(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        logger.info("Por cada distrito obtener para cada tipo de residuo la cantidad recogida.")
        return filasCv.groupBy("Nombre Distrito","Residuo")
            .aggregate { sum("Toneladas") into "Suma por distrito y tipo"}.sortBy("Nombre Distrito")
    }

    fun getMediaToneladasMensuales(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
        logger.info( "media de toneladas mensuales de recogida de basura por distrito." +
            " cada tipo de basura agrupadas por distrito")
        return filasMr.get("Nombre Distrito", "Residuo", "Toneladas")




    }

    companion object {
        fun getDesviacion(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
            logger.info("Desviacion de toneladas")
            return filasMr.groupBy("Residuo")
                .aggregate { max("Toneladas") into "Desciacion de Toneladas" }
        }

        fun joinEstadisticas(estadisticasPorResiduoMax: DataFrame<Any?>,
                             estadisticasPorResiduoMin: DataFrame<Any?>,
                             estadisticasPorResiduoMed: DataFrame<Any?>,
                             estadisticasPorResiduoDesv: DataFrame<Any?>,
                             directoriodeResumen: Path
        ): DataFrame<Any?>? {

            var datosDesvYMed = estadisticasPorResiduoDesv.join(estadisticasPorResiduoMed,"Residuo")
            var datosDesvYMedYMin = datosDesvYMed.join(estadisticasPorResiduoMin,"Residuo")
            var datosUnion = datosDesvYMedYMin.join(estadisticasPorResiduoMax,"Residuo")
            return datosUnion
        }
    }


}