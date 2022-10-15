package Resume


import DataframeUtils.Consultas
import DataframeUtils.GetDataFrame
import DataframeUtils.Graficos
import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.size
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
        logger.info("cojemos datos de mr")
        var filasMr = GetDataFrame().dataFrameModeloResiduo(pathMR, district)
        println(filasMr?.size())
        logger.info("cojemos datos de cv")
        var filasCv = GetDataFrame().dataframeContenedoresVarios(pathCV, district)
        println(filasCv?.size())

        logger.info("mirando que  mr no este vacio")
        if (filasMr?.count() == 0) {
            logger.info(
                "no existe el distrito en el fichero modelo residuo, " +
                        "por lo que las consultas relacionadas no se harán"
            )
        } else if (filasMr != null) {

                logger.info("existe el distrito en los ficheros en modelo Residuo")

                var toneladasPorResiduo = Consultas().getToneladasPorResiduo(filasMr)
                println(toneladasPorResiduo)

                var graficoDeTotalToneladas = Graficos().doGraficoTotalToneladas()
            println(graficoDeTotalToneladas)

                var estadisticasPorResiduoMax = Consultas().getMaximo(filasMr)
            println(estadisticasPorResiduoMax)

                var estadisticasPorResiduoMin = Consultas().getMinimo(filasMr)
            println(estadisticasPorResiduoMin)

                var estadisticasPorResiduoMed = Consultas().getMedia(filasMr)
            println(estadisticasPorResiduoMed)

                var estadisticasPorResiduoDesv = Consultas.getDesviacion(filasMr)
            println(estadisticasPorResiduoDesv)

                var graficoDemaxMinMedYDes = Graficos().doGraficoDeEstadicticas(filasMr)
            println(graficoDemaxMinMedYDes)


            }
            if (filasCv?.count() == 0) {
                logger.info(
                    "no existe el distrito en el fichero Contenedores varios, " +
                            "por lo que las consultas relacionadas no se harán"
                )
            } else if (filasCv != null) {

                    logger.info("existe el distrito en los ficheros en contenedoresvarios")

                    var contenedoresPorTipo = Consultas().getContenedoresDeCadaTipo(filasCv)
                println(contenedoresPorTipo)





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
                logger.info("cojemos datos e mr")
                var filasMr: DataFrame<Any?>? = GetDataFrame().dataFrameModeloResiduoTotal(pathMR)
                println("filas Mr" + filasMr?.columns())
                logger.info("cojemos datos e cv")
                var filasCv: DataFrame<Any?>? = GetDataFrame().dataframeContenedoresVariosTotal(pathCV)
                println("filas cv" + filasCv?.columns())

                logger.info("miramos si cv esta vacio")
                if (filasCv?.count() == 0) {
                    logger.info("no existe datos contenedores varios")
                } else if (filasCv != null) {
                    logger.info("existe datos en contenedores varios")

                    var numeroContenedoresPorDistrito = Consultas().getNumeroContenedoresPorDistrito(filasCv)
                    println()

                    var mediaDeContenedoresDeCadaTipo = Consultas().getmediaDeContenedoresDeCadaTipo(filasCv)

                    println()
                    var garficoDeMediaDeCadaTipo = Graficos().doGraficoDeMedia(filasCv)

                    println()
                }
                if (filasMr?.count() == 0) {
                    logger.info("no existe datos Modelo Resifduo")
                } else if (filasMr != null) {

                    logger.info("existe datos en Modelo Resifduo")

                    var mediaToneladasAnuales = Consultas().getmediaToneladasAnuales(filasMr)

                    println()
                    var graficoMediaToneladasMensuales = Graficos().getgraficoMediaToneladasMensuales(filasMr)

                    println()
                    var maxToneladasPorDistrito = Consultas().getmaxToneladasPorDistrito(filasMr)

                    println()
                    var minToneladasPorDistrito = Consultas().getminToneladasPorDistrito(filasMr)

                    println()
                    var medToneladasPorDistrito = Consultas().getMedToneladasPorDistrito(filasMr)

                    println()
                    var sumToneladasPorDistrito = Consultas().getsumToneladasPorDistrito(filasMr)

                    println()
                    var sumToneladasPorDistritoPorTipo = Consultas().sumToneladasPorDistritoPorTipo(filasMr)

                }
                return true
            }

        }


