package DataframeUtils

import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*

class Consultas {
    fun getToneladasPorResiduo(filasMr: DataFrame<Any?>): Any {
        logger.info("Suma de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { sum("Toneladas") into "Suma" }
    }

    fun getMaximo(filasMr: DataFrame<Any?>): Any{
        logger.info("maximo de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { max("Toneladas") into "Maximo de Toneladas" }
    }
    fun getMinimo(filasMr: DataFrame<Any?>): Any {
        logger.info("minimo de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { min("Toneladas") into "Minimo de Toneladas" }
    }
    fun getMedia(filasMr: DataFrame<Any?>): Any {
        logger.info("Media de toneladas")
        return filasMr.groupBy("Residuo")
            .aggregate { mean("Toneladas") into "Media de Toneladas" }
    }

    fun getContenedoresDeCadaTipo(filasCv: DataFrame<Any?>): Any {
        logger.info("Número de contenedores de cada tipo que hay en este distrito")
        return  filasCv.groupBy("Tipo Contenedor")
            .aggregate { count() into  "Numero de contenedores de ese tipo" }
    }

    fun getNumeroContenedoresPorDistrito(filasCv: DataFrame<Any?>): Any {
        logger.info("nuemro de contenedores por dstrito")
        return filasCv.groupBy("Distrito").aggregate { count() into "Numero de contenedores" }
    }

    fun getmediaDeContenedoresDeCadaTipo(filasCv: DataFrame<Any?>): Any {
        logger.info("Media de contenedores de cada tipo que hay en cada distrito")
        return  filasCv.groupBy("Distrito")
            .aggregate{ groupBy("Tipo") into "tipo De Contenedor" }
            .aggregate { mean("Tipo de contenedor") into "numero"}
    }

    fun getmediaToneladasAnuales(filasCv: DataFrame<Any?>): Any {
        logger.info(
            "Media de toneladas anuales de recogidas por" +
                    " cada tipo de basura agrupadas por distrito"
        )
        return filasCv.groupBy("Distrito")
            .aggregate{ groupBy("Tipo") into "tipo De Basura" }
            .aggregate { mean("Tipo de Basura") into "media"}

    }

    fun getmaxToneladasPorDistrito(filasCv: DataFrame<Any?>): Any {
        logger.info(
            "Máximo de toneladas anuales de recogidas por cada tipo\n" +
                    "    de basura agrupadas por distrito."
        )
        return filasCv.groupBy("Distrito")
            .aggregate{ groupBy("Tipo") into "tipo De Basura" }
            .aggregate { max("Tipo de Basura") into "maximo"}


    }

    fun getminToneladasPorDistrito(filasCv: DataFrame<Any?>): Any {

        logger.info(
            " mínimo de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )
        return filasCv.groupBy("Distrito")
            .aggregate{ groupBy("Tipo") into "tipo De Basura" }
            .aggregate { min("Tipo de Basura") into "maximo"}

    }

    fun getMedToneladasPorDistrito(filasCv: DataFrame<Any?>): Any {

        logger.info(
            " media de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )
        return filasCv.groupBy("Distrito")
            .aggregate{ groupBy("Tipo") into "tipo De Basura" }
            .aggregate { mean("Tipo de Basura") into "maximo"}

    }

    fun getsumToneladasPorDistrito(filasCv: DataFrame<Any?>): Any {
        logger.info(
            " suma de todas las toneladas. "
        )
        return filasCv.sum("Toneladas")
    }

    fun sumToneladasPorDistritoPorTipo(filasCv: DataFrame<Any?>): Any {
        logger.info("Por cada distrito obtener para cada tipo de residuo la cantidad recogida.")
        return filasCv.groupBy("Distrito")
            .aggregate { sum("Toneladas") into "suma por distrito"}
    }

    companion object {
        fun getDesviacion(filasMr: DataFrame<Any?>): Any {
            logger.info("Desviacion de toneladas")
            return filasMr.groupBy("Residuo")
                .aggregate { max("Toneladas") into "Desciacion de Toneladas" }
        }
    }


}