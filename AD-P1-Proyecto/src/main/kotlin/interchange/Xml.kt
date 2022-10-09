package interchange

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.sun.source.tree.IfTree
import dataOfUse.DataofUse
import java.io.File
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
        try {
            logger.info("escribiendo en el fichero")
            mapper.writeValue(fichero, data);
        } catch (e : IOException) {
            logger.info("error al crear Xml")
        }
        logger.info("se ha creado el  Xml")
    }
}