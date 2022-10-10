package interchange

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.sun.source.tree.IfTree
import dataOfUse.DataofUse
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Logger

class Xml<T> {

    private val logger: Logger = Logger.getLogger("Azahara y Dani Log")

    /**
    funcion que pasa de un xml a una lista de objetos
     */
    fun xmlToObject(p : Path, classValue: ClassValue<T>): ArrayList<T>{
        logger.info(" entrado en funcion  xml to object")

        var fichero = p.toFile()
        var mapper = XmlMapper();
        var arrayOfObjects = ArrayList<T>()

        try {

            arrayOfObjects = mapper.readValue(
                fichero, mapper.typeFactory.constructCollectionType(
                    ArrayList::class.java,
                    classValue.javaClass)
            )

        } catch (e : IOException) {
            logger.info("error al leer el xml")
        }

        return  arrayOfObjects
    }


    /**
     * funcion que le pasas un path y una lista de objetos y te ecrive un fihero xml con la lista
     */
    fun objectToXml(objects: ArrayList<T>, p: Path): File {

        logger.info(" entrado en funcion  objectToXml")

        var fichero = p.toFile()
        var mapper = XmlMapper();
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(fichero, objects);
        } catch (e : IOException) {
            logger.info("error al crear Xml")
        }

        return p.toFile()

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

    fun xmlToModeloResiduo(p : Path): ArrayList<T> {

            logger.info(" entrado en funcion ")

            var fichero = p.toFile()
            var mapper = XmlMapper();
            var arrayOfObjects = ArrayList<T>()

            try {
                arrayOfObjects = mapper.readValue(
                    fichero,
                    mapper.typeFactory.constructCollectionType(
                        ArrayList::class.java, ModeloResiduoDTO::class.java)
                )

            } catch (e : IOException) {
                logger.info("error al leer el xml de Modelo residuo")
            }
            return  arrayOfObjects
    }
    fun xmlToContenedoresVarios(p : Path): ArrayList<T> {

        logger.info(" entrado en funcion ")

        var fichero = p.toFile()
        var mapper = XmlMapper();
        var arrayOfObjects = ArrayList<T>()

        try {
            arrayOfObjects = mapper.readValue(
                fichero,
                mapper.typeFactory.constructCollectionType(
                    ArrayList::class.java, ContenedoresVariosDTO::class.java)
            )

        } catch (e : IOException) {
            logger.info("error al leer el xml de Contenedores Varios")
        }
        return  arrayOfObjects
    }


}