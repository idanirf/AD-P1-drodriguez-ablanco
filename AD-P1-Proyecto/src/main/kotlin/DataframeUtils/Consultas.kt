package DataframeUtils

import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import java.nio.file.Path
import javax.management.Query.div


class Consultas {
    fun getToneladasPorResiduo(filasMr: DataFrame<Any?>): DataFrame<Any?> {
        logger.info("Suma de toneladas")
        return filasMr.groupBy("residuo")
            .aggregate { sum("toneladas") into "Suma" }
    }

    fun getMaximo(filasMr: DataFrame<Any?>): DataFrame<Any?>{
        logger.info("maximo de toneladas")
        return filasMr.groupBy("residuo")
            .aggregate { max("toneladas") into "Maximo de Toneladas" }
    }
    fun getMinimo(filasMr: DataFrame<Any?>): DataFrame<Any?>{
        logger.info("minimo de toneladas")
        return filasMr.groupBy("residuo")
            .aggregate { min("toneladas") into "Minimo de Toneladas" }
    }
    fun getMedia(filasMr: DataFrame<Any?>): DataFrame<Any?> {
        logger.info("Media de toneladas")
        return filasMr.groupBy("residuo")
            .aggregate { mean("toneladas") into "Media de Toneladas" }
    }

    fun getContenedoresDeCadaTipo(filasCv: DataFrame<Any?>): DataFrame<Any?>{
        logger.info("Número de contenedores de cada tipo que hay en este distrito")
        return  filasCv.groupBy("tipoContenedor")
            .aggregate { count() into  "Numero de contenedores de ese tipo" }
    }

    fun getNumeroContenedoresPorDistrito(filasCv: DataFrame<Any?>): DataFrame<Any?> {
        //Esta bien
        logger.info("nuemro de contenedores por dstrito")
        return filasCv.groupBy("distrito").aggregate { count() into "Numero de contenedores" }
    }

    fun getmediaDeContenedoresDeCadaTipo(filasCv: DataFrame<Any?>):  DataFrame<Any?> {


        logger.info("Media de contenedores de cada tipo que hay en cada distrito")

        return filasCv.groupBy("distrito", "tipoContenedor")
            .aggregate { mean("numero") into "Media" }

    }

    fun getmediaToneladasAnuales(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
        //no coje año
        var columnaAño = filasMr.columnNames().get(0)
        println(filasMr.columnNames())
        logger.info(
            "Media de toneladas anuales de recogidas por" +
                    " cada tipo de basura agrupadas por distrito"
        )

         return filasMr.groupBy(columnaAño, "distrito", "tipoContenedor").sortBy("distrito")
            .aggregate { mean("cantidad") into "Media de toneladas" }



    }

    fun getmaxToneladasPorDistrito(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
        //sale ok
        logger.info(
            "Máximo de toneladas anuales de recogidas por cada tipo\n" +
                    "    de basura agrupadas por distrito."
        )

        //println(filasMr.columnNames())

        var año = filasMr.columnNames().get(0)
        var f = filasMr.groupBy(año, "distrito", "tipoContenedor")
            .aggregate { max("cantidad") into "maximo"}.sortBy("distrito")

        return f

    }

    fun getminToneladasPorDistrito(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        //sale ok
        logger.info(
            " mínimo de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )

        var año = filasCv.columnNames().get(0)
        var f = filasCv.groupBy(año, "distrito", "tipoContenedor")
            .aggregate { min("cantidad") into "minimo"}.sortBy("distrito")

        return f
    }

    fun getMedToneladasPorDistrito(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        //sale ok
        logger.info(
            " media de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )
        var año = filasCv.columnNames().get(0)
        var f = filasCv.groupBy(año, "distrito", "tipoContenedor")
            .aggregate { mean("cantidad") into "media"}.sortBy("distrito")

        return f
    }

    fun getsumToneladasPorDistrito(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        logger.info(
            " suma de todas las toneladas. "
        )
        return filasCv.groupBy("distrito")
            .aggregate {  sum("cantidad") into "Toneladas Toales"}
    }

    fun sumToneladasPorDistritoPorTipo(filasCv: DataFrame<Any?>):  DataFrame<Any?> {
        logger.info("Por cada distrito obtener para cada tipo de residuo la cantidad recogida.")
        return filasCv.groupBy("distrito","tipoContenedor")
            .aggregate { sum("cantidad") into "Suma por distrito y tipo"}.sortBy("distrito")
    }

    fun getMediaToneladasMensuales(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
        logger.info( "media de toneladas mensuales de recogida de basura por distrito")

        return  filasMr.groupBy("mes", "distrito", "tipoContenedor").sortBy("distrito")
            .aggregate { mean("cantidad") into "Media de toneladas" }
    }

    fun getDesToneladasPorDistrito(filasMr: DataFrame<Any?>): DataFrame<Any?> {
        logger.info("desviación de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito.")
        var año = filasMr.columnNames().get(0)
        var f = filasMr.groupBy(año, "distrito", "tipoContenedor")
            .aggregate { std("cantidad") into "desviacion"}.sortBy("distrito")

        return f
    }

    fun getEstadisticasTotalPorAño(maxToneladasPorDistrito: DataFrame<Any?>, minToneladasPorDistrito: DataFrame<Any?>, medToneladasPorDistrito: DataFrame<Any?>, desvToneladasPorDistrito: DataFrame<Any?>): DataFrame<Any?>? {

        var f = maxToneladasPorDistrito.join(minToneladasPorDistrito)
            .join(medToneladasPorDistrito).join(desvToneladasPorDistrito)
        println(f)
        return f

    }

    companion object {
        fun getDesviacion(filasMr: DataFrame<Any?>):  DataFrame<Any?> {
            logger.info("Desviacion de toneladas")
            return filasMr.groupBy("tipoContenedor")
                .aggregate { max("cantidad") into "Desciacion de Toneladas" }
        }

        fun joinEstadisticas(estadisticasPorResiduoMax: DataFrame<Any?>,
                             estadisticasPorResiduoMin: DataFrame<Any?>,
                             estadisticasPorResiduoMed: DataFrame<Any?>,
                             estadisticasPorResiduoDesv: DataFrame<Any?>,
                             directoriodeResumen: Path
        ): DataFrame<Any?>? {

            var datosDesvYMed = estadisticasPorResiduoDesv.join(estadisticasPorResiduoMed,"tipoContenedor")
            var datosDesvYMedYMin = datosDesvYMed.join(estadisticasPorResiduoMin,"tipoContenedor")
            var datosUnion = datosDesvYMedYMin.join(estadisticasPorResiduoMax,"tipoContenedor")
            return datosUnion
        }
    }


}