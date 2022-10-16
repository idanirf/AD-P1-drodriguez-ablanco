package DataframeUtils

import logger
import org.jetbrains.kotlinx.dataframe.DataFrame

class Graficos {
    fun doGraficoTotalToneladas(toneladasPorResiduo: DataFrame<Any?>): Any? {
        logger.info("Gráfico con el total de toneladas por residuo en ese distrito.")


        return null
    }

    fun doGraficoDeEstadicticas(filasMr: DataFrame<Any?>): Any? {
        //todo no se como hacerlo
        logger.info("Gráfica del máximo, mínimo y media por meses en dicho distrito.")
        return null
    }

    fun doGraficoDeMedia(filasCv: DataFrame<Any?>): Any? {
        logger.info("Gráfico con el total de contenedores por distrito")
        //todo no se como hacerlo
        return null
    }

    fun getgraficoMediaToneladasMensuales(filasCv: DataFrame<Any?>): Any ?{
        logger.info(
            "Gráfico de media de toneladas mensuales de recogida de basura por distrito." +
                    " cada tipo de basura agrupadas por distrito"
        )
        return null
    }

}