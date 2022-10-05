import Resume.Resume
import chekData.CheckData
import dataOfUse.DataofUse
import interchange.InterchangeContenedoresVarios
import interchange.InterchangeModeloResiduo
import models.ContenedoresVarios
import models.ModeloResiduo
import java.nio.file.Path
import java.util.logging.Logger



private val logger: Logger = Logger.getLogger("Azahara y Dani Log")

private val strings = arrayOf("parser", "a", "a", "a")

fun main(args: Array<String>) {

    logger.info(" Iniciando Programa")


    //para falsear los datos ponemos aqui los comando
    val args : Array<String> = arrayOf("parser","a","a","a")

    println("Hello World!")
    val election : Int  = getElection(args)
    //poner log
    when (election){
        1 -> beginingParser(args)
        2 -> beginingSumaryAll(args)
        3 -> beginingSumaryDistrict(args)
    }

    //para comprobar
    logger.info("la elecione es $election")

}
/**
 * solo lee de csv
funcion que comprueva los args y si son ciestos debe tomar los ficheros csv
del directorio origen y trasformalos en JSON y XML en el directorio destino. En dicho
directorio destino deberán estar las tres versiones: CSV, JSON y XML.
 */
fun beginingParser(args: Array<String>) {
    logger.info(" Entrado en begininParser ")

    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();


    //comprobamos datos
    val thereAreFiles = CheckData().parser(args)
    logger.info(" los datos son corrctos = $thereAreFiles")
    //si es correctos llamamos  intercange para hacerlo en los 3 formatos con hilos

    var areCorrectDataInFiles : Boolean = false
    if(thereAreFiles){
        //comprobar si los csv son tienen los datos correctos
        areCorrectDataInFiles = CheckData().checkDataIntoCsv(args)
        logger.info(" los datos son corrctos = $thereAreFiles")
    }

    if(areCorrectDataInFiles){
        //Todo hacer con distintos hilos
        logger.info(" cogiendo datos de archivo Modelo residuo ")
        var arrayListOfModeloResiduo = InterchangeModeloResiduo<ModeloResiduo>().csvToObject(Path.of(args[1]))
        logger.info(" cogiendo datos de contenedores Varios")
        var arrayListOfContenedoreVarios = InterchangeModeloResiduo<ContenedoresVarios>().csvToObject(Path.of(args[1]))


        //todo Una vez que tengamos cargado los dtos de arraylistModeoResidio con un join o un wait hacer por hilos
        logger.info(" mandando por hilos las creaciones de ficheros Modelo residuo ")
        InterchangeModeloResiduo<ModeloResiduo>().objectToCsv( arrayListOfModeloResiduo , Path.of(args[2]))
        InterchangeModeloResiduo<ModeloResiduo>().objectToJson(arrayListOfModeloResiduo ,Path.of(args[2]))
        InterchangeModeloResiduo<ModeloResiduo>().objectToXml(arrayListOfModeloResiduo ,Path.of(args[2]))

        //todo Una vez que tengamos cargado los dtos de arraylistContenedores vvarios con un join o un wait hacer por hilos
        logger.info(" mandando por hilos las creaciones de ficheros Contenedores varios ")
        InterchangeModeloResiduo<ModeloResiduo>().objectToCsv( arrayListOfModeloResiduo , Path.of(args[2]))
        InterchangeModeloResiduo<ModeloResiduo>().objectToJson(arrayListOfModeloResiduo ,Path.of(args[2]))
        InterchangeModeloResiduo<ModeloResiduo>().objectToXml(arrayListOfModeloResiduo ,Path.of(args[2]))

        //todo con hilos esperamos a que esten todos los hilos terminados con join o con whait

    }

    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;
    //aqui ahy que poner en el archivo de guardar area lo que hemos hecho
     DataofUse(tipoOpcion = "Parser", exito = areCorrectDataInFiles , tiempoEjecucion = tDiference)

}

/**
 * puede leer de csv json o xml, pero tiene que tener todos los datos
 *
funcion que comprueva los args y si son ciestos debe tomar la información
de los contenedores y de la recogida, independientemente de la extensión que tenga (si no
corresponde a la extensión o al formato deberá indicar error) y deberá procesarla
generando en directorio_destino un resumen.html, aplicándoles los estilos
 */
fun  beginingSumaryAll(args: Array<String>) {
    logger.info("entramos en beginingSumaryAll")
    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();

    val isCorrectData = CheckData().sumaryAll(args)
    if (isCorrectData){
        logger.info("los datos de la path son correctos")

        //si es correctos llamamos  resume para hacer html

        //Todo hacer con distintos hilos
        logger.info(" cogiendo datos de archivo Modelo residuo ")
        var arrayListOfModeloResiduo = InterchangeModeloResiduo<ModeloResiduo>().csvToObject(Path.of(args[1]))
        logger.info(" cogiendo datos de contenedores Varios")
        var arrayListOfContenedoreVarios = InterchangeContenedoresVarios<ContenedoresVarios>().csvToObject(Path.of(args[1]))

        //todo esperara con un oin o un wait a que los procesos terminen
        Resume().resumeAll(arrayListOfModeloResiduo ,arrayListOfContenedoreVarios)

    }

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;
    //aqui ahy que poner en el archivo de guardar area lo que hemos hecho
    DataofUse(tipoOpcion = "Sumary all", exito = isCorrectData , tiempoEjecucion = tDiference)

}
/**
funcion que comprueva los args y si son ciestos debe tomar la
información de los contenedores y de la recogida, independientemente de la extensión que
tenga (si no corresponde a la extensión o al formato deberá indicar error) y deberá
procesarla generando en directorio_destino un resumen_distrito.html (solo si el distrito
existe, si no deberá mostrar error), aplicándoles los estilos que creas oportunos
 */
fun  beginingSumaryDistrict(args: Array<String>) {
    logger.info("ha entrado en beginingSumaryDistrict")

    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();


    val isCorrectData = CheckData().sumaryDistrict(args)
    logger.info("los datos correctos es : " + isCorrectData)
    //si es correctos llamamos  resume para hacer html


    //cojemos datos
    if (isCorrectData){
        logger.info("los datos de la path son correctos")

        //si es correctos llamamos

        //Todo hacer con distintos hilos
        logger.info(" cogiendo datos de archivo Modelo residuo ")
        var arrayListOfModeloResiduo = InterchangeModeloResiduo<ModeloResiduo>().csvToObject(Path.of(args[1]))
        logger.info(" cogiendo datos de contenedores Varios")
        var arrayListOfContenedoreVarios = InterchangeContenedoresVarios<ContenedoresVarios>().csvToObject(Path.of(args[1]))

        //todo esperara con un oin o un wait a que los procesos terminen

        val isCorrectDistrict= CheckData().district(args[1], arrayListOfModeloResiduo,arrayListOfContenedoreVarios)

        if (isCorrectData){
            logger.info("el distrito es correcto")
            Resume().resumeDistrict(args[1], arrayListOfModeloResiduo ,arrayListOfContenedoreVarios)
        }else{
            logger.info("el distrito NO es correcto")
        }


    }else{
        logger.info("los datos de la path NO son correctos")
    }
    

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;
    //aqui ahy que poner en el archivo de guardar area lo que hemos hecho
    DataofUse(tipoOpcion = "Sumary District", exito = isCorrectData , tiempoEjecucion = tDiference)
}
/**
pasados los parametros del programa devuelve un Int entre 1 y 3 que indica la elecion escogida
por la persona que ha pasado los parametros
3- resumende distrito
2- resumen total
1- parser
 */
fun getElection(args: Array<String>):Int{
    logger.info(" Entrado en get Elecion ")

    if(args.size == 4){
        return 3
    }else if(args[0]=="resumen"){
        return 2
    }else{return 1}

}

