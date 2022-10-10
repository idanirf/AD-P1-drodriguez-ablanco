import chekData.CheckData
import dataOfUse.DataofUse
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import interchange.Csv
import interchange.Json
import interchange.Xml
import mappers.MaperModeloResiduo
import models.ContenedoresVarios
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Logger
import java.util.stream.Stream
import kotlin.streams.toList


private val logger: Logger = Logger.getLogger("Azahara y Dani Log")


//con esto lo probamos
val path : String= Paths.get("").toAbsolutePath().toString()+ File.separator +
        "data"

//para probar el beging
// private val strings = arrayOf("parser", path, path+File.separator + "copia")

//para probar el parse all
private val strings = arrayOf("", path, path+File.separator + "copia")

fun main(args: Array<String>) {

    logger.info(" Iniciando Programa")

    //para falsear los datos ponemos aqui los comando
    val args : Array<String> = strings

    //donde vamos a guardar los datos
    val stringOfData =Paths.get("").toAbsolutePath().toString()+ File.separator +
            "data"+File.separator +"DataOfAllUses"+File.separator +"datos.xml"

    val election : Int  = getElection(args)

    when (election){
        1 -> beginingParser(args,stringOfData)
        2 -> beginingSumaryAll(args,stringOfData)
        3 -> beginingSumaryDistrict(args,stringOfData)
    }

}
/**
 * solo lee de csv
funcion que comprueva los args y si son ciestos debe tomar los ficheros csv
del directorio origen y trasformalos en JSON y XML en el directorio destino. En dicho
directorio destino deberán estar las tres versiones: CSV, JSON y XML.
 */
fun beginingParser(args: Array<String>, stringOfData : String) {
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
        var arrayListOfModeloResiduo = Csv().csvToMoeloResiduo(Path.of(args[1]+File.separator+"modelo_residuos_2021.csv"))
        logger.info(" cogiendo datos de contenedores Varios")
        var arrayListOfContenedoreVarios = Csv().csvToContenedoresVarios(Path.of(args[1]+File.separator+"contenedores_varios.csv"))
        logger.info(" leidos los dos ficheros ")

        //todo Una vez que tengamos cargado los dtos de arraylistModeoResidio con un join o un wait hacer por hilos
        logger.info(" creamos ficheros Modelo residuo ")
        logger.info(" csv ")
        Csv().ModeloRosiduoToCsv(arrayListOfModeloResiduo , Path.of(args[2]))
        logger.info(" json")
        Json<ModeloResiduoDTO>().objectToJson(arrayListOfModeloResiduo , Path.of(args[2]))
        logger.info(" xml ")
        Xml<ModeloResiduoDTO>().objectToXml(arrayListOfModeloResiduo,Path.of(args[2]+File.separator+"modelo_residuos.xml"))

        logger.info(" creados todos los ficheros de modelo residuos")

        //todo Una vez que tengamos cargado los dtos de arraylistContenedores vvarios con un join o un wait hacer por hilos
        logger.info(" creamos ficheros contenedores varios ")
        logger.info(" csv ")
        Csv().ContenedoresVariosToCsv(arrayListOfContenedoreVarios , Path.of(args[2]))
        logger.info(" json")
        Json<ContenedoresVariosDTO>().objectToJson(arrayListOfContenedoreVarios , Path.of(args[2]))
        logger.info(" xml ")
        Xml<ContenedoresVariosDTO>().objectToXml(arrayListOfContenedoreVarios,Path.of(args[2]+File.separator+"contenedores_varios.xml"))

        logger.info(" creados todos los ficheros")

        //todo con hilos esperamos a que esten todos los hilos terminados con join o con whait

    }

    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = "Parser", exito = areCorrectDataInFiles , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xml<DataofUse>().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")

}

/**
 *
resumen path parh
puede leer de csv json o xml, pero tiene que tener todos los datos
funcion que comprueva los args y si son ciestos debe tomar la información
de los contenedores y de la recogida, independientemente de la extensión que tenga (si no
corresponde a la extensión o al formato deberá indicar error) y deberá procesarla
generando en directorio_destino un resumen.html, aplicándoles los estilos
 */
fun  beginingSumaryAll(args: Array<String>, stringOfData: String) {
    logger.info("entramos en beginingSumaryAll")

    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();

    //para ver si ha tenido exito o no
    var exito = false

    var isCorrectData = CheckData().sumaryAll(args)
    //para comprobar los datos tengo que ver si de todos los ficheros que hay en ese directorio hay
    //alguno que se lea y que dentro dependiendo el formato coincida con los datos wue queremos

    //1. sacar todos los ficheros qe hay en el directorio
    //2. ver en cada uno hasta que sea el que queremos
    //3. ver el formato, leerlo segun el formato, y ver si tienen los datos

    //1.sacar todos los ficheros del directorio
    if(args[0]!="resumen"){
        logger.info("el primer args No es resume la opcion no es correcta")
        isCorrectData=false
    }else{
        logger.info("el primer args en resume")
        if (Files.notExists(Path.of(args[1])) && !Files.isDirectory(Path.of(args[1]))){
            logger.info("el path de los archivos No exixte o NO es un directorio")
        }else{
            logger.info("el path de los archivos exixte y es un directorio")

            //listar todos los archivos dentro de un directorio y quedarnos con los de formato correcto
            var regrexJson = Regex(".json$")
            var regrexXml = Regex(".xml$")
            var regrexCsv = Regex(".csv$")

            var ficheros : Stream<Path>  = Files.list(Path.of(args[1]))

            var ficherosReadble = ficheros.filter { p -> Files.isReadable(p) }
            var ficherosJson = ficherosReadble.filter { p -> path.matches(regrexJson) }.toList()
            var ficherosXml = ficherosReadble.filter { p -> path.matches(regrexXml) }.toList()
            var ficherosCsv = ficherosReadble.filter { p -> path.matches(regrexCsv) }.toList()

            //con cada uno porbamos si se pueden leer y son de los que queremos, quitaremos con excepciones

            var pathModeloResiduo : Path? = null
            var pathContenedoresVarios : Path? = null

            pathModeloResiduo = searchCorrectFileInJsonFilesModeloResiduo(ficherosJson)

            if (pathModeloResiduo == null){
                pathModeloResiduo = searchCorrectFileInxmlFilesModeloResiduo(ficherosXml)
            }
            if (pathModeloResiduo == null) {
                pathModeloResiduo = searchCorrectFileInCsvFilesModeloResiduo(ficherosCsv)
            }

            if (pathModeloResiduo==null){
                logger.info("no hay ningun archivo en la path que contenga los datos necesarios ")
            }else{
                logger.info("exixte un fichero con los datos necesarios para modelo residuo, buscamos para contenedores varios")

                pathModeloResiduo = searchCorrectFileInJsonFilesContenedoresVarios(ficherosJson)

                if (pathModeloResiduo == null){
                    pathModeloResiduo = searchCorrectFileInxmlFilesContenedoresVarios(ficherosXml)
                }
                if (pathModeloResiduo == null) {
                    pathModeloResiduo = searchCorrectFileInCsvFilesContenedoresVarios(ficherosCsv)
                }

                if (pathModeloResiduo==null){
                    logger.info("no exixte ningun fichero que contenga las columnas y en el orden necesarios para crear Contenedores vartios")

                    //todo termina pasandole a check data

                }else{

                    logger.info("exixten los dos archivos necesarios para hacer el resumen")
                    exito = doResumen(pathContenedoresVarios,pathModeloResiduo)

                }
            }
        }
    }

    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = "resume", exito = exito , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xml<DataofUse>().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")
}

fun doResumen(pathOfContenedoresVarios : Path, pathDeModeloResiduo : Path) : Boolean {
    logger.info("los datos de la path son correctos")

    //Todo hacer con distintos hilos
    logger.info(" cogiendo datos de archivo Modelo residuo ")
    var arrayListOfModeloResiduoDTO = Csv().csvToMoeloResiduo(pathDeModeloResiduo)
    var arrayListOfModeloResiduo = doMappetToModeloResiduo(arrayListOfModeloResiduoDTO)
        logger.info(" cogiendo datos de contenedores Varios")
    var arrayListOfContenedoreVariosDTO = Csv().csvToContenedoresVarios(pathOfContenedoresVarios)
    var arrayListOfContenedoreVarios = doMappetToContenedresVarios(arrayListOfContenedoreVariosDTO)
    //todo esperara con un oin o un wait a que los procesos terminen
    //hacer el resume
}

fun doMappetToContenedresVarios(array: ArrayList<ContenedoresVariosDTO>): ArrayList<ContenedoresVarios> {

    MaperModeloResiduo

    try {

        return array.stream().map { x ->  }
    }catch (e: Exception){
        logger.info("no se ha conseguido pasar de modelo a object")
    }
}

fun doMappetToModeloResiduo(arrayListOfModeloResiduoDTO: ArrayList<ModeloResiduoDTO>): Any {

}

fun searchCorrectFileInCsvFilesContenedoresVarios(ficherosCsv: MutableList<Path>): Path? {
    var encontrado1 = false
    while (ficherosCsv.size != 0 || encontrado1 == true) {
        var ficheroCorrecto: ArrayList<ContenedoresVariosDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosCsv.get(0)
            ficheroCorrecto = Csv().csvToContenedoresVarios(ficherosCsv.removeAt(0))
            encontrado1 = true
            return pathEncontrada
            logger.info("fichero tiene las columnas correctas y en el orden correcto")

        } catch (e: Exception) {
            logger.info("fichero no tiene las columnas correctas en el orden correcto")
        }
    }
    return null

}

fun searchCorrectFileInxmlFilesContenedoresVarios(ficherosXml: MutableList<Path>): Path? {
    var encontrado1 = false
    while (ficherosXml.size != 0 || encontrado1 == true) {
        var ficheroCorrecto: ArrayList<ContenedoresVariosDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosXml.get(0)
            ficheroCorrecto = Csv().csvToContenedoresVarios(ficherosXml.removeAt(0))
            encontrado1 = true
            return pathEncontrada
            logger.info("fichero tiene las columnas correctas y en el orden correcto")

        } catch (e: Exception) {
            logger.info("fichero no tiene las columnas correctas en el orden correcto")
        }
    }
    return null

}

fun searchCorrectFileInJsonFilesContenedoresVarios(ficherosJson: MutableList<Path>): Path? {
    var encontrado1 = false
    while (ficherosJson.size != 0 || encontrado1 == true) {
        var ficheroCorrecto: ArrayList<ContenedoresVariosDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosJson.get(0)
            ficheroCorrecto = Csv().csvToContenedoresVarios(ficherosJson.removeAt(0))
            encontrado1 = true
            return pathEncontrada
            logger.info("fichero tiene las columnas correctas y en el orden correcto")

        } catch (e: Exception) {
            logger.info("fichero no tiene las columnas correctas en el orden correcto")
        }
    }
    return null
}

fun searchCorrectFileInCsvFilesModeloResiduo(ficherosCsv: MutableList<Path>): Path?{

    var encontrado1 = false
    while (ficherosCsv.size != 0 || encontrado1 == true) {
        var ficheroCorrecto: ArrayList<ModeloResiduoDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosCsv.get(0)
            ficheroCorrecto = Csv().csvToMoeloResiduo(ficherosCsv.removeAt(0))
            encontrado1 = true
            return pathEncontrada
            logger.info("fichero tiene las columnas correctas y en el orden correcto")

        } catch (e: Exception) {
            logger.info("fichero no tiene las columnas correctas en el orden correcto")
        }
    }
    return null

}

fun searchCorrectFileInxmlFilesModeloResiduo(ficherosXml: MutableList<Path>): Path? {

    var encontrado1 = false
    while (ficherosXml.size != 0 || encontrado1 == true) {
        var ficheroCorrecto: ArrayList<ModeloResiduoDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosXml.get(0)
            ficheroCorrecto = Xml<ModeloResiduoDTO>().xmlToModeloResiduo(ficherosXml.removeAt(0))
            encontrado1 = true
            return pathEncontrada
            logger.info("fichero tiene las columnas correctas y en el orden correcto")

        } catch (e: Exception) {
            logger.info("fichero no tiene las columnas correctas en el orden correcto")
        }
    }
    return null
}

private fun searchCorrectFileInJsonFilesModeloResiduo(ficherosJson: MutableList<Path>): Path? {
    var encontrado1 = false
    while (ficherosJson.size != 0 || encontrado1 == true) {
        var ficheroCorrecto: ArrayList<ModeloResiduoDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosJson.get(0)
            ficheroCorrecto = Json<ModeloResiduoDTO>().jsonToModeloResiduo(ficherosJson.removeAt(0))
            encontrado1 = true
            return pathEncontrada
            logger.info("fichero tiene las columnas correctas y en el orden correcto")

        } catch (e: Exception) {
            logger.info("fichero no tiene las columnas correctas en el orden correcto")
        }
    }
    return null
}

/**
funcion que comprueva los args y si son ciestos debe tomar la
información de los contenedores y de la recogida, independientemente de la extensión que
tenga (si no corresponde a la extensión o al formato deberá indicar error) y deberá
procesarla generando en directorio_destino un resumen_distrito.html (solo si el distrito
existe, si no deberá mostrar error), aplicándoles los estilos que creas oportunos
 */
fun  beginingSumaryDistrict(args: Array<String>, stringOfData: String) {
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
        //    var arrayListOfModeloResiduo = InterchangeModeloResiduo<ModeloResiduo>().csvToObject(Path.of(args[1]))
        logger.info(" cogiendo datos de contenedores Varios")
        //   var arrayListOfContenedoreVarios = InterchangeContenedoresVarios<ContenedoresVarios>().csvToObject(Path.of(args[1]))

        //todo esperara con un oin o un wait a que los procesos terminen

        //val isCorrectDistrict= CheckData().district(args[1], arrayListOfModeloResiduo,arrayListOfContenedoreVarios)

        if (isCorrectData){
            logger.info("el distrito es correcto")
           // Resume().resumeDistrict(args[1], arrayListOfModeloResiduo ,arrayListOfContenedoreVarios)
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
        logger.info(" como los args son 4 devuelve elecion 3 ")
        return 3
    }else if(args[0]=="resumen"){
        logger.info(" como los args no son 4  y es resumen devuelve elecion 2 ")
        return 2
    }else{
        logger.info(" como no es ninguna de las anteriores devuelve 1 ")
        return 1}

}

