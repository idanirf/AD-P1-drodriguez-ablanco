package Resume


import DataframeUtils.Consultas
import DataframeUtils.GetDataFrame
import DataframeUtils.Graficos
import html.CreateHtml
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

        logger.info("cojemos datos de cv")
        var filasCv = GetDataFrame().dataframeContenedoresVarios(pathCV, district)

        //datos usados en el html
        var toneladasPorResiduo : DataFrame<Any?>? = null
        var graficoDeTotalToneladas : Any? = null
        var estadisticasPorResiduoMax : DataFrame<Any?>? = null
        var estadisticasPorResiduoMin : DataFrame<Any?>? = null
        var estadisticasPorResiduoMed : DataFrame<Any?>? = null
        var estadisticasPorResiduoDesv : DataFrame<Any?>? = null
        var graficoDemaxMinMedYDes : Any? = null
        var contenedoresPorTipo : DataFrame<Any?>? = null


        logger.info("mirando que  mr no este vacio")
        if (filasMr?.count() == 0) {
            logger.info(
                "no existe el distrito en el fichero modelo residuo, " +
                        "por lo que las consultas relacionadas no se harán"
            )
        } else if (filasMr != null) {

                logger.info("existe el distrito en los ficheros en modelo Residuo")

                toneladasPorResiduo = Consultas().getToneladasPorResiduo(filasMr)
                println(toneladasPorResiduo)

                graficoDeTotalToneladas = Graficos().doGraficoTotalToneladas()
            println(graficoDeTotalToneladas)

                estadisticasPorResiduoMax = Consultas().getMaximo(filasMr)
            println(estadisticasPorResiduoMax)

                estadisticasPorResiduoMin = Consultas().getMinimo(filasMr)
            println(estadisticasPorResiduoMin)

                estadisticasPorResiduoMed = Consultas().getMedia(filasMr)
            println(estadisticasPorResiduoMed)

                estadisticasPorResiduoDesv = Consultas.getDesviacion(filasMr)
            println(estadisticasPorResiduoDesv)

                graficoDemaxMinMedYDes = Graficos().doGraficoDeEstadicticas(filasMr)
            println(graficoDemaxMinMedYDes)


            }
            if (filasCv?.count() == 0) {
                logger.info(
                    "no existe el distrito en el fichero Contenedores varios, " +
                            "por lo que las consultas relacionadas no se harán"
                )
            } else if (filasCv != null) {

                    logger.info("existe el distrito en los ficheros en contenedoresvarios")

                     contenedoresPorTipo = Consultas().getContenedoresDeCadaTipo(filasCv)
                println(contenedoresPorTipo)


            }

        var html : String = CreateHtml().htmlResumeDistrict(
            toneladasPorResiduo,
            graficoDeTotalToneladas ,
            estadisticasPorResiduoMax,
            estadisticasPorResiduoMin,
            estadisticasPorResiduoMed,
            estadisticasPorResiduoDesv,
            graficoDemaxMinMedYDes,
            contenedoresPorTipo
        )




        return html
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

                logger.info("cojemos datos e cv")
                var filasCv: DataFrame<Any?>? = GetDataFrame().dataframeContenedoresVariosTotal(pathCV)


                var numeroContenedoresPorDistrito : DataFrame<Any?>? = null
                var mediaDeContenedoresDeCadaTipo : DataFrame<Any?>? = null
                var garficoDeMediaDeCadaTipo : Any? = null
                var mediaToneladasAnuales : DataFrame<Any?>? = null
                var mediaToneladasMensuales : DataFrame<Any?>? = null
                var graficoMediaToneladasMensuales : Any? = null
                var maxToneladasPorDistrito : DataFrame<Any?>? = null
                var minToneladasPorDistrito : DataFrame<Any?>? = null
                var medToneladasPorDistrito : DataFrame<Any?>? = null
                var sumToneladasPorDistrito : DataFrame<Any?>? = null
                var sumToneladasPorDistritoPorTipo : DataFrame<Any?>? = null



                logger.info("miramos si cv esta vacio")
                if (filasCv?.count() == 0) {
                    logger.info("no existe datos contenedores varios")
                } else if (filasCv != null) {
                    logger.info("existe datos en contenedores varios")

                    //ok
                     numeroContenedoresPorDistrito = Consultas().getNumeroContenedoresPorDistrito(filasCv)


                    //no da fallo pero no está correcto
                    mediaDeContenedoresDeCadaTipo = Consultas().getmediaDeContenedoresDeCadaTipo(filasCv)
                    //println(mediaDeContenedoresDeCadaTipo)

                    //falta gráfica
                    garficoDeMediaDeCadaTipo = Graficos().doGraficoDeMedia(filasCv)
                    //println(garficoDeMediaDeCadaTipo)
                }
                if (filasMr?.count() == 0) {
                    logger.info("no existe datos Modelo Resifduo")
                } else if (filasMr != null) {

                    logger.info("existe datos en Modelo Resifduo")

                    //no da fallo pero no está bien
                     mediaToneladasAnuales = Consultas().getmediaToneladasAnuales(filasMr)
                   //  println(mediaToneladasAnuales)

                    //no da fallo pero no está bien
                    mediaToneladasMensuales = Consultas().getMediaToneladasMensuales(filasMr)
                    //println(mediaToneladasMensuales)

                    //no da fallo pero no está bien
                     graficoMediaToneladasMensuales = Graficos().getgraficoMediaToneladasMensuales(filasMr)
                    //println(graficoMediaToneladasMensuales)

                    //no da fallo pero no está bien//falta anual
                    maxToneladasPorDistrito = Consultas().getmaxToneladasPorDistrito(filasMr)
                    //println(maxToneladasPorDistrito)

                    //no da fallo pero no está bien//falta anual
                     minToneladasPorDistrito = Consultas().getminToneladasPorDistrito(filasMr)
                    //println(minToneladasPorDistrito)

                    //no da fallo pero no está bien//falta anual
                    medToneladasPorDistrito = Consultas().getMedToneladasPorDistrito(filasMr)
                    //println(medToneladasPorDistrito)


                    //ok
                    sumToneladasPorDistrito = Consultas().getsumToneladasPorDistrito(filasMr)
                    //println(sumToneladasPorDistrito)

                    //ok
                    sumToneladasPorDistritoPorTipo = Consultas().sumToneladasPorDistritoPorTipo(filasMr)
                    //println(sumToneladasPorDistritoPorTipo)

                }

                var html : String = CreateHtml().htmlResume(
                    numeroContenedoresPorDistrito ,
                    mediaDeContenedoresDeCadaTipo,
                    garficoDeMediaDeCadaTipo,
                    mediaToneladasAnuales,
                    mediaToneladasMensuales,
                    graficoMediaToneladasMensuales,
                    maxToneladasPorDistrito ,
                    minToneladasPorDistrito ,
                    medToneladasPorDistrito ,
                    sumToneladasPorDistrito ,
                    sumToneladasPorDistritoPorTipo
                )
                return true
            }

        }


