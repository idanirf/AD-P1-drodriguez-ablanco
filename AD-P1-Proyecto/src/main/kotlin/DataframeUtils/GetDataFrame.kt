package DataframeUtils

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import interchange.Csv
import interchange.Jsonc
import logger
import mappers.MaperModeloResiduo
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


        } else if (pathMR.toString().endsWith(".json")||pathMR.toString().endsWith(".Json")) {
            logger.info("buscando distrito \$district en el fichero Modelo Residio json ")
            logger.info("buscando fichero Modelo Residio json ")

            //pasamos a objetodto
            var dto = Jsonc().readJsontoModeloresiduoDto(pathMR)
            println("ejemplo de dto: "+ dto.get(1).toString())
            //pasmoa a objeto cara castear Toneladas
            var ob = ArrayList<ModeloResiduo>()
            dto.stream().forEach{x -> ob.add(MaperModeloResiduo().tdoToModrloResiduo(x))}
            println("ejemplo de ob: "+ ob.get(1).toString())
            //pasamos de nuevo a dataframe
            var dF = ob.toDataFrame()

            return dF
                .filter{  x -> x.getValue<String>(dF.columnNames().get(5)).equals(district, true) }

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
               var df= DataFrame.readJson(pathCV.toFile())
                    return df.filter { x -> x.getValue<String>(df.columnNames().get(6)).equals(district, true) }

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

        } else if (pathMR.toString().endsWith(".json")) {
            logger.info("buscando fichero Modelo Residio json ")
            return DataFrame.readJson(pathMR.toFile())
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