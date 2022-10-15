package DataframeUtils

import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.count
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.getValue
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.readJson
import java.nio.file.Path

class GetDataFrame {

    fun dataFrameModeloResiduo(pathMR: Path, district: String): DataFrame<Any?> {
        if (pathMR.endsWith(".csv")) {
            logger.info("buscando distrito en el fichero Contenedores varios csv")
            return DataFrame.readCSV(pathMR.toFile(), ';')
                .filter { x -> x.getValue<String>("Distrito").equals(district, true) }


        } else if (pathMR.endsWith(".json")) {
            logger.info("buscando distrito \$district en el fichero Modelo Residio json ")
            return DataFrame.readJson(pathMR.toFile())
                .filter{  x -> x.getValue<String>("Nombre Distrito").equals(district, true) }

        } else {
            //Todo no se si funcionará con xml
            logger.info("buscando distrito \$district en el fichero Modelo Residio xml ")
            return  DataFrame.read(pathMR.toFile())
                .filter{ x -> x.getValue<String>("Nombre Distrito").equals(district, true) }

        }
    }

    fun dataframeContenedoresVarios(pathCV: Path, district: String): DataFrame<Any?> {

            if (pathCV.endsWith(".csv")) {

                logger.info("buscando distrito en el fichero Contenedores varios csv")
                return DataFrame.readCSV(pathCV.toFile(), ';')
                    .filter { x -> x.getValue<String>("Distrito").equals(district, true) }


            } else if (pathCV.endsWith(".json")) {

                logger.info("buscando distrito en el fichero Contenedores varios json")
                return DataFrame.readJson(pathCV.toFile())
                    .filter { x -> x.getValue<String>("Distrito").equals(district, true) }

            } else {

                //Todo no se si funcionará con xml
                logger.info("buscando distrito en el fichero Contenedores varios xml")
                return DataFrame.read(pathCV.toFile())
                    .filter { x -> x.getValue<String>("Distrito").equals(district, true) }

            }

    }

    fun dataFrameModeloResiduoTotal(pathMR: Path): DataFrame<Any?> {
        if (pathMR.endsWith(".csv")) {
            logger.info("buscando  Contenedores varios csv")
            return DataFrame.readCSV(pathMR.toFile(), ';')

        } else if (pathMR.endsWith(".json")) {
            logger.info("buscando fichero Modelo Residio json ")
            return DataFrame.readJson(pathMR.toFile())
        } else {
            //Todo no se si funcionará con xml
            logger.info("buscando fichero Modelo Residio xml ")
            return  DataFrame.read(pathMR.toFile())
        }
    }

    fun dataframeContenedoresVariosTotal(pathCV: Path) : DataFrame<Any?>{


        if (pathCV.endsWith(".csv")) {
            logger.info("buscando  Contenedores varios csv")
            return DataFrame.readCSV(pathCV.toFile(), ';')

        } else if (pathCV.endsWith(".json")) {
            logger.info("buscando  Contenedores varios json")
            return DataFrame.readJson(pathCV.toFile())

        } else {
            //Todo no se si funcionará con xml
            logger.info("buscando Contenedores varios xml")
            return DataFrame.read(pathCV.toFile())

        }
    }
}