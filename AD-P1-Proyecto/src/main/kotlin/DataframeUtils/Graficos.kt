package DataframeUtils

import jetbrains.letsPlot.*
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomBar
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.geom.geom_tile
import jetbrains.letsPlot.label.ggtitle
import jetbrains.letsPlot.label.xlab
import jetbrains.letsPlot.label.ylab
import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toMap
import java.awt.Color
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID
import kotlin.io.path.exists
import kotlin.io.path.isDirectory

class Graficos {


    fun doGraficoTotalToneladas(toneladasPorResiduo: DataFrame<Any?>, directoriodeResumen: Path): Any? {
        println(toneladasPorResiduo.columnNames())
        //creamos directorio para las imagenes si no esta ya
        if (directoriodeResumen.exists()&& directoriodeResumen.isDirectory()){
            logger.info("el directrio e imagenes ya existe")
        }else{
            logger.info("el directrio  imagenes No existe")
            Files.createDirectory(directoriodeResumen)
        }
        logger.info("Gráfico con el total de toneladas por residuo en ese distrito.")
        var plot = letsPlot(data = toneladasPorResiduo.toMap()) + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            width = 0.3,
            fill = Color.ORANGE

        ) {
            x = "Residuo"; y = "Suma"
        } + xlab("Residuo") + ylab("Suma") + ggtitle("Toneladas por residuo")

        var nombrePlot = UUID.randomUUID().toString()+".png"
        ggsave(plot,nombrePlot,1,null,directoriodeResumen.toString())
        return nombrePlot
    }

    fun doGraficoDeEstadicticas(
        estadisticastotales : DataFrame<Any?>?,
        directoriodeResumen: Path
    ): Any? {

        //creamos directorio para las imagenes si no esta ya
        if (directoriodeResumen.exists()&& directoriodeResumen.isDirectory()){
            logger.info("el directrio e imagenes ya existe")
        }else{
            logger.info("el directrio  imagenes No existe")
            Files.createDirectory(directoriodeResumen)
        }

        println(estadisticastotales.toString())
        logger.info("Gráfico con el total de toneladas por residuo en ese distrito.")
        var plot = letsPlot(data = estadisticastotales?.toMap()) + geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            width = 0.3,
            fill = Color.black
        ) {
            x = "Residuo"; y = "Maximo de Toneladas"
        } + xlab("Residuo") + ylab("Toneladas"
        )+ ggtitle("Minimo, maximo, media Y desciacion de Toneladas"
        )+ geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            fill = Color.ORANGE
        ) {
            x = "Residuo"; y = "Media de Toneladas"
        } + geomBar(
            stat = Stat.identity,
            alpha = 0.3,
            fill = Color.YELLOW,
        ) {
            x = "Residuo"; y = "Minimo de Toneladas"
        } + geomBar(
            stat = Stat.identity,
            alpha = 0.3,
            fill = Color.GRAY,
        ) {
            x = "Residuo"; y = "Desciacion de Toneladas"
        } + geomBar(
            stat = Stat.identity,
            alpha = 0.3,
            fill = Color.DARK_GRAY,
        )

        //var plot = ggplot { toneladasPorResiduo.toMap()}+geomTile{ x = "Residuo"; y = "Suma"} + ggtitle("Toneladas por residuo")
        var nombrePlot = UUID.randomUUID().toString()+".png"
        ggsave(plot,nombrePlot,1,null,directoriodeResumen.toString())
        return nombrePlot

    }

    fun doGraficoDeMedia(contenedores: DataFrame<Any?>, directoriodeResumen: Path): Any? {
        logger.info("Gráfico con el total de contenedores por distrito")

        var plot = ggplot (contenedores.toMap())+ geomBar(
            stat = Stat.identity,
            alpha = 0.8,
            width = 0.3,
            fill = Color.ORANGE

        ) {
            x = "Distrito" ; y = "Numero de contenedores"
        } + xlab("Distrito") + ylab("Contenedores")+ ggtitle("Grafica de contenedores")

        var nombrePlot = UUID.randomUUID().toString()+".png"
        ggsave(plot,nombrePlot,1,null,directoriodeResumen.toString())
        return nombrePlot


    }

    fun getgraficoMediaToneladasMensuales(filasCvmediaToneladasMensuales: DataFrame<Any?>, directoriodeResumen: Path): Any ?{
        logger.info(
            "Gráfico de media de toneladas mensuales de recogida de basura por distrito." +
                    " cada tipo de basura agrupadas por distrito"
        )
        var plot = ggplot(filasCvmediaToneladasMensuales.toMap()){x = "Nombre Distrito"; y = "Residuo"; fill = "Media de toneladas"
        } + geomTile(color = "white",
                    linetype = 1)

        var nombrePlot = UUID.randomUUID().toString()+".png"
        ggsave(plot,nombrePlot,1,null,directoriodeResumen.toString())
        return nombrePlot
    }

}