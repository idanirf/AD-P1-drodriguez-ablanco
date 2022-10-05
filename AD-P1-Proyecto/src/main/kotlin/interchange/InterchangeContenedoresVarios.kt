package interchange

import enums.TipoContenedor
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger
import java.util.stream.Collectors

class InterchangeContenedoresVarios<ContenedoresVarios> {
    var logger: Logger = Logger.getLogger("Azahara y Dani Log")

    //funciones que pasan de un tipo a otro
   // fun csvToObject(f : File): Sequence<T>{}
   // fun xmlToObject(f : File) Sequence<T>{}
   // fun objectToCsv(Sequence<T>):File{ }

    /*
funcion que pasa de un json a una lista de objetos

 */
    fun jsonToObject(p : Path): ArrayList<models.ContenedoresVarios>{
        logger.info("Accediendo a la función JSON to Object")

        val resultList= ArrayList<models.ContenedoresVarios>()
        /**
        try {
        val gson: Unit = GsonBuilder().setPrettyPrinting().create()
        resultList = gson.fromJson(json, ArrayList.class)
        }catch (e:Exception){
        println("log de excepcion en pasr de json a objeto")
        }
         */
        return  resultList
    }
    /**
     * funcion que le pasas un path y una lista de objetos y te ecrive un fihero j son con la lista
     */
    fun objectToJson(modelosR: ArrayList<models.ContenedoresVarios>, p: Path): File {
        logger.info("Accediendo a la función Object to JSON")
        /**
        funcionará cuando tengamos el gson correcto
        var gson = GsonBuilder().setPrettyPrinting().create()
        var json = gson.toJson(modelosR)
        return = writeInFile(p, json)

         */
        return p.toFile()

    }

    fun csvToObject(p: Path): ArrayList<ContenedoresVarios> {
        logger.info("Accediendo a la función CSV to Object")
        val br = BufferedReader(FileReader(p.toFile()))
        var lista = ArrayList<ContenedoresVarios>()

        try {
            if (Files.exists(p)){
                var line = br.readLine()
                var contenedoresVariosCollection = Files.lines(p)
                    .skip(1)
                    .map(this::getContenedoresVarios)
                    .collect(Collectors.toList())
                contenedoresVariosCollection.forEach { m -> lista.add(m)}
            }
        } catch (e: Exception){
            logger.severe(e.toString())
        }finally {
            br.close()
        }
        return  lista
    }

    private  fun getContenedoresVarios(line: String): ContenedoresVarios{
        val campos = line.split(";")

        return ContenedoresVarios(
            codigoInternoSituado = campos[0],
            tipoContenedor = getEnumTipoContenedor(campos[1]),
            modelo = campos[2],
            descripcionModelo = campos[3],
            cantidad = campos[4],
            lote = campos[5],
            distrito = campos[6],
            barrio = campos[7],
            tipoVia = campos[8],
            nombre = campos[9],
            numero = campos[10],
            coordenadaX = campos[11],
            coordenadaY = campos[12],
            TAG = campos[13]
       )
    }

    fun getEnumTipoContenedor(s: String): TipoContenedor {
        when (s) {
            "Envases" -> return TipoContenedor.ENVASES
            "Organica" ->return TipoContenedor.ORGANICA
            "Resto" -> return TipoContenedor.RESTO
            "Papel y carton" -> return TipoContenedor.PAPEL_Y_CARTÓN
            "Vidrio" -> return TipoContenedor.VIDRIO
        }
        return TipoContenedor.DESCONOCIDO
    }
    // fun objectToXml(Sequence<T>){}

    fun writeInFile(p: Path, listaString: java.lang.StringBuilder): File {
        logger.info("Escribiendo en fichero")
        var f: File
        if (Files.notExists(p)) {
            logger.info("El fichero no existe")
            f = File(p.toString())
            logger.info("creado")
        } else {
            logger.info("el fichero existe")
            f = p.toFile()
        }
        val bw = BufferedWriter(FileWriter(f))
        try {
            logger.info("Escribiendo en el fichero")
            bw.write(listaString.toString())

        } catch (e: Exception) {
            logger.warning("El fichero existe")
        } finally {
            bw.close()
            logger.info("Cerrando el escritor del fichero")
        }
        logger.info("Cerrando el escritor del fichero")
        return f
    }
}