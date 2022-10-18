package DataframeUtils

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import interchange.Csv
import logger
import models.ModeloResiduo
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.readJson
import java.nio.file.Path

class GetDataFrame {

    fun dataFrameModeloResiduo(pathMR: Path, district: String): DataFrame<Any?>? {

        if (pathMR.toString().endsWith(".csv")) {
            logger.info("buscando distrito en el fichero Contenedores varios csv")
            return DataFrame.readCSV(pathMR.toFile(), ';')
                .filter { x -> x.getValue<String>("Nombre Distrito").equals(district, true) }


        } else if (pathMR.toString().endsWith(".json")) {
            logger.info("buscando distrito \$district en el fichero Modelo Residio json ")
            return DataFrame.readJson(pathMR.toFile())
                .filter{  x -> x.getValue<String>("Nombre Distrito").equals(district, true) }

        } else if (pathMR.toString().endsWith(".xml")){
            //Todo no se si funcionará con xml
            logger.info("buscando distrito \$district en el fichero Modelo Residio xml ")
            return  DataFrame.read(pathMR.toFile())
                .filter{ x -> x.getValue<String>("Nombre Distrito").equals(district, true) }

        }
        logger.info("error devolvemos null")
        return null
    }

    fun dataframeContenedoresVarios(pathCV: Path, district: String): DataFrame<Any?>? {

            if (pathCV.toString().endsWith(".csv")) {

                logger.info("buscando distrito en el fichero Contenedores varios csv")
                var df = DataFrame.readCSV(pathCV.toFile(), ';')
                return df.filter { x -> x.getValue<String>(df.columnNames().get(6)).equals(district, true) }


            } else if (pathCV.toString().endsWith(".json")) {

                logger.info("buscando distrito en el fichero Contenedores varios json")
                return DataFrame.readJson(pathCV.toFile())
                    .filter { x -> x.getValue<String>("Distrito").equals(district, true) }

            } else if (pathCV.toString().endsWith(".xml")){

                //Todo no se si funcionará con xml
                logger.info("buscando distrito en el fichero Contenedores varios xml")
                return DataFrame.read(pathCV.toFile())
                    .filter { x -> x.getValue<String>("Distrito").equals(district, true) }

            }
        logger.info("error devolvemos null")
        return null
    }

    fun dataFrameModeloResiduoTotal(pathMR: Path): DataFrame<Any?>? {
        println("modelo residuo de : "+pathMR)
        if (pathMR.toString().endsWith(".csv")) {
            logger.info("buscando  MOdelo residuo csv")
            var dF =DataFrame.readCSV(pathMR.toFile(), ';')
            var casteo = dF.cast<ModeloResiduoDTO>()
            println("casteado "+casteo.columnNames())
            return casteo

        } else if(pathMR.toString().endsWith(".json")) {
            logger.info("buscando fichero Modelo Residio json ")
            var dF = DataFrame.readJson(pathMR.toFile())
            var casteo= dF.cast<ModeloResiduoDTO>()
            println("casteado" +casteo.columnNames())
            return casteo

        } else if (pathMR.toString().endsWith(".xml")){
            //Todo no se si funcionará con xml
            logger.info("buscando fichero Modelo Residio xml ")
            var dF =  DataFrame.read(pathMR.toFile())
            var casteo= dF.cast<ModeloResiduo>()
            println(casteo.columnNames())
            return casteo
        }
        logger.info("error devolvemos null")
        return null
    }

    fun dataframeContenedoresVariosTotal(pathCV: Path) : DataFrame<Any?>?{
        println("contnedores varios de : " +pathCV)


        if (pathCV.toString().endsWith(".csv")) {
            var lista = Csv().csvToContenedoresVarios(pathCV)

            logger.info("buscando  Contenedores varios csv")
            var dF =lista.toDataFrame()
            var casteo = dF.cast<ContenedoresVariosDTO>()
            println(casteo.columnNames())
            return casteo

        } else if (pathCV.toString().endsWith(".json")) {
            logger.info("buscando  Contenedores varios json")
            var dF = DataFrame.readJson(pathCV.toFile())
            var casteo = dF.cast<ContenedoresVariosDTO>()
            println(casteo.columnNames())
            return casteo

        } else  if (pathCV.toString().endsWith(".xml")){

            logger.info("buscando Contenedores varios xml")
            var dF = DataFrame.read(pathCV.toFile())
            var casteo = dF.cast<ContenedoresVariosDTO>()
            println(casteo.columnNames())
            return casteo

        }
        logger.info("error devolvemos null")
        return null
    }
}