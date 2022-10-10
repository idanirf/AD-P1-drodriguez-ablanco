package interchange

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import models.ContenedoresVarios
import models.ModeloResiduo
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger

class Json<T> {

    private val logger: Logger = Logger.getLogger("Azahara y Dani Log")

    /**
    funcion que pasa de un json a una lista de objetos
     */
    fun jsonToModeloResiduo(p : Path): ArrayList<T>{
        logger.info(" entrado en funcion  jsonto object")

        var fichero = p.toFile()
        var mapper = ObjectMapper();
        var arrayOfObjects = ArrayList<T>()

        try {
            arrayOfObjects = mapper.readValue(
                fichero,
                mapper.typeFactory.constructCollectionType(
                    ArrayList::class.java, ModeloResiduoDTO::class.java)
            )

        } catch (e : IOException) {
            logger.info("error al leer el json de Modelo residuo")
        }
        return  arrayOfObjects
    }
    /**
    funcion que pasa de un json a una lista de objetos
     */
    fun jsonToConteedoresVarios(p : Path): ArrayList<T>{
        logger.info(" entrado en funcion  jsonto object")

        var fichero = p.toFile()
        var mapper = ObjectMapper();
        var arrayOfObjects = ArrayList<T>()

        try {
            arrayOfObjects = mapper.readValue(
                fichero,
                mapper.typeFactory.constructCollectionType(
                    ArrayList::class.java, ContenedoresVariosDTO::class.java))

        } catch (e : IOException) {
            logger.info("error al leer el json de Modelo residuo")
        }
        return  arrayOfObjects
    }


    /**
     * funcion que le pasas un path y una lista de objetos y te ecrive un fihero j son con la lista
     */
    fun objectToJson(objects: ArrayList<T>, p: Path): File {

        logger.info(" entrado en funcion  objectToJson")
        if(Files.exists(p)&& Files.isDirectory(p)){
            logger.info("el directorio existe")
        }else{
            Files.createDirectory(p)
        }


        var nombreFile: String
        if(objects.get(0) is ModeloResiduoDTO){
            nombreFile="modelo_residuo"
        }else{
            nombreFile="contenedores_varios"
        }


        if (Files.notExists(Path.of(p.toString()+File.separator+nombreFile+".json"))){
            Files.createFile(Path.of(p.toString()+File.separator+nombreFile+".json"))
            logger.info("creando el fichero json ya que no exixte")
        }else{
            logger.info("cel fichero json ya exixte")
        }




        var fichero : File = File(p.toString()+File.separator+nombreFile+".json")
        var mapper = ObjectMapper();
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(fichero, objects);
        } catch (e : IOException) {
            logger.info("error al crear json")
        }

        return p.toFile()

    }

}