package Resume


import DataframeUtils.Consultas
import DataframeUtils.GetDataFrame
import DataframeUtils.Graficos
import html.CreateHtml
import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import java.nio.file.Path
import java.time.LocalDate


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

    fun resumeDistrictFrame(pathMR: Path, pathCV: Path, district: String, directoriodeResumen: Path): String {

        //para ver el tiempo que tarda
        var tInit = System.currentTimeMillis();

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
        var estadisticasTotales : DataFrame<Any?>? = null


        logger.info("mirando que  mr no este vacio")
        if (filasMr?.count() == 0) {
            logger.info(
                "no existe el distrito en el fichero modelo residuo, " +
                        "por lo que las consultas relacionadas no se harán"
            )
        } else if (filasMr != null) {
            var columnasMr = filasMr.columnNames()

                logger.info("existe el distrito en los ficheros en modelo Residuo")

                toneladasPorResiduo = Consultas().getToneladasPorResiduo(filasMr,columnasMr)
       //         println(toneladasPorResiduo)

                graficoDeTotalToneladas = Graficos().doGraficoTotalToneladas(toneladasPorResiduo,directoriodeResumen,columnasMr)
           // println(graficoDeTotalToneladas)

                estadisticasPorResiduoMax = Consultas().getMaximo(filasMr,columnasMr)
           // println(estadisticasPorResiduoMax)

                estadisticasPorResiduoMin = Consultas().getMinimo(filasMr,columnasMr)
           // println(estadisticasPorResiduoMin)

                estadisticasPorResiduoMed = Consultas().getMedia(filasMr,columnasMr)
            // println(estadisticasPorResiduoMed)

                estadisticasPorResiduoDesv = Consultas.getDesviacion(filasMr,columnasMr)
            //println(estadisticasPorResiduoDesv)

                estadisticasTotales = Consultas.joinEstadisticas( estadisticasPorResiduoMax,
                    estadisticasPorResiduoMin,
                    estadisticasPorResiduoMed,
                    estadisticasPorResiduoDesv,
                    directoriodeResumen,columnasMr)

                graficoDemaxMinMedYDes = Graficos().doGraficoDeEstadicticas(
                    estadisticasTotales,directoriodeResumen,columnasMr)

                  // println(graficoDemaxMinMedYDes)


            }
            if (filasCv?.count() == 0) {
                logger.info(
                    "no existe el distrito en el fichero Contenedores varios, " +
                            "por lo que las consultas relacionadas no se harán"
                )
            } else if (filasCv != null) {

                    logger.info("existe el distrito en los ficheros en contenedoresvarios")
                var nombresCv= filasCv.columnNames()

                     contenedoresPorTipo = Consultas().getContenedoresDeCadaTipo(filasCv,nombresCv)
                println(contenedoresPorTipo)


            }
        //para ver cuanto tarda
        var tFinal = System.currentTimeMillis();
        var tDiference= tFinal - tInit;
        var momentoDeRealizacion = LocalDate.now()


        logger.info("pasando datos al html")
        var html : String = CreateHtml().htmlResumeDistrict(
            toneladasPorResiduo,
            graficoDeTotalToneladas ,
            estadisticasTotales,
            graficoDemaxMinMedYDes,
            contenedoresPorTipo,
            tDiference,
            momentoDeRealizacion
        )


        logger.info("pasando string de html")

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
            fun resumenFrame(pathMR: Path, pathCV: Path, directoriodeResumen: Path): String {

                logger.info("entramos")
                //para ver el tiempo que tarda
                var tInit = System.currentTimeMillis();

                //obtenemos los dataframe correspondientes con la clase GetDataFrame
                logger.info("cojemos datos e mr")
                var filasMr: DataFrame<Any?>? = GetDataFrame().dataFrameModeloResiduoTotal(pathMR)
                //var filasMr : DataFrame<Any?>? = filasMrsinCast?.cast<ModeloResiduo>()
                println(filasMr?.columnNames())
                logger.info("cojemos datos e cv")
                var filasCv: DataFrame<Any?>? = GetDataFrame().dataframeContenedoresVariosTotal(pathCV)
               // var filasCv: DataFrame<Any?>? = filasCvsinCast?.cast<ContenedoresVarios>()
                println(filasCv?.columnNames())

                var numeroContenedoresPorDistrito : DataFrame<Any?>? = null
                var mediaDeContenedoresDeCadaTipo : DataFrame<Any?>? = null
                var  garficoDecontenedoresPorDistrito : Any? = null
                var mediaToneladasAnuales : DataFrame<Any?>? = null
                var mediaToneladasMensuales : DataFrame<Any?>? = null
                var graficoMediaToneladasMensuales : Any? = null
                var maxToneladasPorDistrito : DataFrame<Any?>? = null
                var minToneladasPorDistrito : DataFrame<Any?>? = null
                var medToneladasPorDistrito : DataFrame<Any?>? = null
                var sumToneladasPorDistrito : DataFrame<Any?>? = null
                var totalEstadirticasPorDistrito : DataFrame<Any?>? = null
                var sumToneladasPorDistritoPorTipo : DataFrame<Any?>? = null
                var totalEstadisticasPorAño : DataFrame<Any?>? = null
                var desvToneladasPorDistrito : DataFrame<Any?>? = null



                logger.info("miramos si cv esta vacio")
                if (filasCv?.count() == 0) {
                    logger.info("no existe datos contenedores varios")
                } else if (filasCv != null) {
                    logger.info("existe datos en contenedores varios")
                    var nombreCol = filasCv.columnNames()

                    //ok
                     numeroContenedoresPorDistrito = Consultas().getNumeroContenedoresPorDistrito(filasCv, nombreCol)


                    //ok gracias a jeremy que me lo ha esplicado
                    mediaDeContenedoresDeCadaTipo = Consultas().getmediaDeContenedoresDeCadaTipo(filasCv, nombreCol)
                    //println(mediaDeContenedoresDeCadaTipo)
//ok

                    //ok
                    garficoDecontenedoresPorDistrito = Graficos().doGraficoDeMedia(numeroContenedoresPorDistrito, directoriodeResumen, nombreCol)
                    //println(garficoDeMediaDeCadaTipo)



                }
                if (filasMr?.count() == 0) {
                    logger.info("no existe datos Modelo Resifduo")
                } else if (filasMr != null) {
                    var nombreCol= filasMr.columnNames()

                    logger.info("existe datos en Modelo Resifduo")
                    mediaToneladasMensuales = Consultas().getMediaToneladasMensuales(filasMr,nombreCol)
                    //println(mediaToneladasMensuales)

                    //ok
                    graficoMediaToneladasMensuales = Graficos().getgraficoMediaToneladasMensuales(mediaToneladasMensuales, directoriodeResumen,nombreCol)
                    //println(graficoMediaToneladasMensuales)



                    //ok
                     mediaToneladasAnuales = Consultas().getmediaToneladasAnuales(filasMr,nombreCol)
                   //  println(mediaToneladasAnuales)


                    //ok
                    maxToneladasPorDistrito = Consultas().getmaxToneladasPorDistrito(filasMr,nombreCol)
                    //println(maxToneladasPorDistrito)

                    //ok
                     minToneladasPorDistrito = Consultas().getminToneladasPorDistrito(filasMr,nombreCol)
                    //println(minToneladasPorDistrito)

                    //ok
                    medToneladasPorDistrito = Consultas().getMedToneladasPorDistrito(filasMr,nombreCol)
                    //println(medToneladasPorDistrito)

                    //ok
                    sumToneladasPorDistrito = Consultas().getsumToneladasPorDistrito(filasMr,nombreCol)
                    //println(sumToneladasPorDistrito)

                    //ok
                    desvToneladasPorDistrito = Consultas().getDesToneladasPorDistrito(filasMr,nombreCol)
                    //println(sumToneladasPorDistrito)

                    //ok
                    sumToneladasPorDistritoPorTipo = Consultas().sumToneladasPorDistritoPorTipo(filasMr,nombreCol)
                    //println(sumToneladasPorDistritoPorTipo)

                    totalEstadisticasPorAño = Consultas().getEstadisticasTotalPorAño(
                        maxToneladasPorDistrito,
                        minToneladasPorDistrito,
                        medToneladasPorDistrito,
                        desvToneladasPorDistrito,nombreCol
                    )

                }
                //para ver cuanto tarda
                var tFinal = System.currentTimeMillis();
                var tDiference= tFinal - tInit;
                var momentoDeRealizacion = LocalDate.now()


                /**


                - Gráfico con el total de contenedores por distrito.
                Gráfico de media de toneladas mensuales de recogida de basura por distrito.

                 */
                var html : String = CreateHtml().htmlResume(
                    numeroContenedoresPorDistrito ,
                    mediaDeContenedoresDeCadaTipo,
                    garficoDecontenedoresPorDistrito,
                    mediaToneladasAnuales,
                    graficoMediaToneladasMensuales ,
                    totalEstadisticasPorAño ,
                    sumToneladasPorDistrito ,
                    sumToneladasPorDistritoPorTipo,
                    tDiference,
                    momentoDeRealizacion
                )
                return html
            }

        }


