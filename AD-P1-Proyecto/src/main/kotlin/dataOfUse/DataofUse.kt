package dataOfUse

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.chrono.IsoChronology
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Logger
import javax.print.attribute.standard.MediaSize.ISO


//todo no se si esto esta bien aqui jejej comprobar porque no me van los logs aqui
private val logger: Logger = Logger.getLogger("Azahara y Dani Log")

/**
 * clase que se dedica a:
 * Por cada ejecución debemos guardar un fichero bitacora.xml donde tengamos en este
XML un listado de las ejecuciones con la siguiente información:
- ID de la ejecución en base a uuid
- Instante: Instante de la ejecución en formato ISO 8601
- Tipo de opción elegida (parser, resumen global, resumen ciudad).
- Éxito: si tuvo éxito o no su procesamiento.
- Tiempo de ejecución: tiempo de ejecución si tuvo éxito en milisegundos.
 */
class DataofUse(tipoOpcion : String, exito: Boolean, tiempoEjecucion : Long) {

    val id = UUID.randomUUID()

    //SO 8601: la porción de la fecha sigue el formato YYYY-MM-DD ejemplo: 2016-06-01T14:41:36-08:00.
    val instanteTemporal = LocalDateTime.now()
    val instante  = instanteTemporal.format(DateTimeFormatter.ISO_DATE_TIME)

    val tipoOpcion : String =  tipoOpcion
    var exito : Boolean = exito
    val tiempoDeEjecucion : Long = tiempoEjecucion

    init {
        logger.info("cerrando el escritor del fichero")

        //path del archivo

        val path : String= Paths.get("").toString()+ File.separator +
                "scr" + File.separator +
                "main" + File.separator +
                "kotlin" +File.separator +
                "dataOfUse" +File.separator +
                "DataRunnin.xml"

        //ver si existe el fichero si no crearlo
        logger.info("comprobando que es fichero exixte")
        if(Files.notExists(Path.of(path))){Files.createFile(Path.of(path))}
        var file : File = Path.of(path).toFile()

        //crear una string de xml de este objeto
        //todo crear xml con los datos
        var string : String = ""

        //añadir datos
        var bw = BufferedWriter(FileWriter(file))
        try {
            //Todo comprobar que escrive sin sobreescribir lo anterior
            bw.write(string)
            logger.info("escrito ")
        }catch (e:Exception){
            logger.info("error: cno se a podido escribir ")
        }finally{
            bw.close()
            logger.info("finalizado el bw ")
        }

    }


}