package Resume


import DataframeUtils.Consultas
import DataframeUtils.GetDataFrame
import DataframeUtils.Graficos
import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import java.nio.file.Path


class ResumenDataFrame {

    /**
    funcion que devuelve un resumen en html de el distrito selecionado
    información:
    - Titulo: Resumen de recogidas de basura y reciclaje de “Distrito”
    - Fecha de generación: Fecha y hora en formato español.
    - Autores: Nombre y apellidos de los dos autores.
    - Número de contenedores de cada tipo que hay en este distrito.
    - Total de toneladas recogidas en ese distrito por residuo.
    - Gráfico con el total de toneladas por residuo en ese distrito.
    - Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.
    - Gráfica del máximo, mínimo y media por meses en dicho distrito.
    - Tiempo de generación del mismo en milisegundos.
     */

    fun resumeDistrictFrame(pathMR: Path, pathCV: Path, district: String): Boolean {

        //obtenemos los dataframe correspondientes con la clase GetDataFrame
        var filasMr = GetDataFrame().dataFrameModeloResiduo(pathMR, district)
        var filasCv = GetDataFrame().dataframeContenedoresVarios(pathCV,district)


        if (filasMr.count() == 0 ) {
            logger.info("no existe el distrito en el fichero modelo residuo, " +
                    "por lo que las consultas relacionadas no se harán")
        } else {
            logger.info("existe el distrito en los ficheros en modelo Residuo")

            var toneladasPorResiduo = Consultas().getToneladasPorResiduo(filasMr)

            var graficoDeTotalToneladas = Graficos().doGraficoTotalToneladas()

            var estadisticasPorResiduoMax = Consultas().getMaximo(filasMr)

            var estadisticasPorResiduoMin = Consultas().getMinimo(filasMr)

            var estadisticasPorResiduoMed = Consultas().getMedia(filasMr)

            var estadisticasPorResiduoDesv = Consultas.getDesviacion(filasMr)

            var graficoDemaxMinMedYDes = Graficos().doGraficoDeEstadicticas(filasMr)


        }
        if (filasMr.count() == 0 ) {
                logger.info("no existe el distrito en el fichero Contenedores varios, " +
                        "por lo que las consultas relacionadas no se harán")
        } else {
                logger.info("existe el distrito en los ficheros en contenedoresvarios")

                var contenedoresPorTipo =Consultas().getContenedoresDeCadaTipo(filasCv)


        }
        return true

    }






    /**
    funcion que devuelve un resumen en html del contenido de la secuencia
    resumen.html, aplicándoles los estilos que creas
    oportunos, con la siguiente información:
    - Titulo: Resumen de recogidas de basura y reciclaje de Madrid
    - Fecha de generación: Fecha y hora en formato español.
    - Autores: Nombre y apellidos de los dos autores.
    - Número de contenedores de cada tipo que hay en cada distrito.
    - Media de contenedores de cada tipo que hay en cada distrito.
    - Gráfico con el total de contenedores por distrito.
    - Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
    distrito.
    - Gráfico de media de toneladas mensuales de recogida de basura por distrito.
    - Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
    de basura agrupadas por distrito.
    - Suma de todo lo recogido en un año por distrito.
    - Por cada distrito obtener para cada tipo de residuo la cantidad recogida.
    - Tiempo de generación del mismo en milisegundos.
     */
    fun resumenFrame(pathMR: Path, pathCV: Path): Boolean {

        logger.info("entramos")

        //obtenemos los dataframe correspondientes con la clase GetDataFrame
        var filasMr : DataFrame<Any?> = GetDataFrame().dataFrameModeloResiduoTotal(pathMR)
        var filasCv : DataFrame<Any?> = GetDataFrame().dataframeContenedoresVariosTotal(pathCV)


        if (filasCv.count()==0){
            logger.info("no existe datos contenedores varios")
        } else {
            logger.info("existe datos en contenedores varios")

            var numeroContenedoresPorDistrito = Consultas().getNumeroContenedoresPorDistrito(filasCv)

            var mediaDeContenedoresDeCadaTipo = Consultas().getmediaDeContenedoresDeCadaTipo(filasCv)

            var garficoDeMediaDeCadaTipo = Graficos().doGraficoDeMedia(filasCv)

        }



        var mediaToneladasAnuales = Consultas().getmediaToneladasAnuales(filasCv)

        var graficoMediaToneladasMensuales = Graficos().getgraficoMediaToneladasMensuales(filasCv)

        var maxToneladasPorDistrito = Consultas().getmaxToneladasPorDistrito(filasCv)

        var minToneladasPorDistrito = Consultas().getminToneladasPorDistrito(filasCv)

        var medToneladasPorDistrito = Consultas().getMedToneladasPorDistrito(filasCv)

        var sumToneladasPorDistrito = Consultas().getsumToneladasPorDistrito(filasCv)

        var sumToneladasPorDistritoPorTipo = Consultas().sumToneladasPorDistritoPorTipo(filasCv)



        return true

    }
}

