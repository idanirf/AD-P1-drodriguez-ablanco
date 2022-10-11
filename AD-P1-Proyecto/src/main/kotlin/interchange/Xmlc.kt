package interchange

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dataOfUse.DataofUse
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Path
import java.util.logging.Logger

class Xmlc {

    private var logger: Logger = Logger.getLogger("Azahara y Dani Log")


    fun xmlToModeloresiduoDto(p : Path): ArrayList<ModeloResiduoDTO>{
        logger.info(" entrado en funcion  xml to object")


        try {

            val file = p.toFile()
            if (file.exists()) {
                return XML.decodeFromString<ArrayList<ModeloResiduoDTO>>(file.readText())
            } else {
                throw IllegalArgumentException("El fichero ${file.absolutePath} no existe")
            }
        } catch (e : IOException) {
            logger.info("error al leer el xml")
        }

        return  ArrayList<ModeloResiduoDTO>()
    }

    fun xmlToContenedoresVariosDto(p : Path): ArrayList<ContenedoresVariosDTO>{
        logger.info(" entrado en funcion  xml to object")


        try {

            val file = p.toFile()
            if (file.exists()) {
                return XML.decodeFromString<ArrayList<ContenedoresVariosDTO>>(file.readText())
            } else {
                throw IllegalArgumentException("El fichero ${file.absolutePath} no existe")
            }
        } catch (e : IOException) {
            logger.info("error al leer el xml")
        }

        return  ArrayList<ContenedoresVariosDTO>()
    }


    fun writeData(p: Path, data : DataofUse) {

        logger.info(" entrado en funcion  write xml data")




        var fichero = p.toFile()
        var mapper = XmlMapper();
        var bw = BufferedWriter(FileWriter(fichero,true))
        try {
            logger.info("escribiendo en el fichero")
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var xmlString: String = mapper.writeValueAsString(data)
            bw.newLine()
            bw.write(xmlString)

        } catch (e : IOException) {
            logger.info("error al crear Xml")
        }finally {
            bw.close()
        }
        logger.info("se ha creado el  Xml")
    }

    fun contenedoresVariosDtoToXml(array: ArrayList<ContenedoresVariosDTO>, p :Path) {
        logger.info("empezando")
        val file = p.toFile()
        val xml = XML { indent = 4 }
        file.writeText(xml.encodeToString(array))
    }
    fun modeloResiduoDtoToXml(array: ArrayList<ModeloResiduoDTO> , p : Path) {
        logger.info("empezando")
        val file = p.toFile()
        val xml = XML { indent = 4 }
        file.writeText(xml.encodeToString(array))
    }


}