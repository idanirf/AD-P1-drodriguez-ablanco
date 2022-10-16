import Resume.ResumenDataFrame
import chekData.CheckData
import dataOfUse.DataofUse
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO

import interchange.Csv
import interchange.Jsonc
import interchange.Xmlc
import mappers.MaperModeloResiduo
import mappers.MapperContenedoresVarios
import models.ContenedoresVarios
import models.ModeloResiduo
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Logger
import java.util.stream.Stream


val logger: Logger = Logger.getLogger("Azahara y Dani Log")


//con esto lo probamos
val path : String= Paths.get("").toAbsolutePath().toString()+ File.separator +
        "data"

//para probar el parser
//private val strings = arrayOf("parser", path, path+File.separator + "copia")

//para probar el resume
private val strings = arrayOf("resumen", path, path+File.separator + "copia")

//para probar el resume district
//private val strings = arrayOf("resumen","CARABANCHEL", path, path+File.separator + "copia")


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
        2 -> beginingSumary(args,stringOfData)
        3-> beginingSumary(args,stringOfData)
        4 -> opcionIncorrecta(stringOfData)
    }

}

fun opcionIncorrecta(stringOfData: String) {
    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();

    logger.info("la opcion selecionada no es correcta ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = "Error", exito = false , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xmlc().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")
}

/**
 Comprueva los args y si son ciertos debe tomar los ficheros csv
del directorio origen y transformar en JSON y XML en el directorio destino. En dicho
directorio destino deberán estar las tres versiones: CSV, JSON y XML.
 */
fun beginingParser(args: Array<String>, stringOfData : String) {

    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();

    logger.info(" Entrado en begininParser ")

    //comprobamos datos
    val thereAreFiles = CheckData().parser(args)
    logger.info(" los datos son corrctos = $thereAreFiles")

    var areCorrectDataInFiles : Boolean = false
    if(thereAreFiles){
        //comprobar si los csv son tienen los datos correctos
        areCorrectDataInFiles = CheckData().checkDataIntoCsv(args)
        logger.info(" los datos son corrctos = $thereAreFiles")
    }

    if(areCorrectDataInFiles){

        //Todo hacer con distintos hilos
        var arrayListOfModeloResiduo = getModeloResiduotoCSV(args)
        var arrayListOfContenedoreVarios = getContenedoresVariosCSV(args)

        logger.info(" leidos los dos ficheros ")

        //todo Una vez que tengamos cargado los dtos de arraylistModeoResidio con un join o un wait hacer por hilos
        createFilesModeloresiduo(arrayListOfModeloResiduo, args)

        logger.info(" creados todos los ficheros de modelo residuos")

        //todo Una vez que tengamos cargado los dtos de arraylistContenedores vvarios con un join o un wait hacer por hilos
        createFilesContenedoreVarios(arrayListOfContenedoreVarios, args)

        logger.info(" creados todos los ficheros")

        //todo con hilos esperamos a que esten todos los hilos terminados con join o con whait

    }

    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = "Parser", exito = areCorrectDataInFiles , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xmlc().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")

}

private fun createFilesContenedoreVarios(
    arrayListOfContenedoreVarios: ArrayList<ContenedoresVariosDTO>,
    args: Array<String>) {
    logger.info(" creamos ficheros contenedores varios ")
    logger.info(" csv ")
    Csv().ContenedoresVariosToCsv(arrayListOfContenedoreVarios, Path.of(args[2]))
    logger.info(" json")
    Jsonc().contenedoresVariosToAJson(Path.of(args[2]), arrayListOfContenedoreVarios)
    logger.info(" xml ")
    Xmlc().contenedoresVariosDtoToXml(
        arrayListOfContenedoreVarios,
        Path.of(args[2] + File.separator + "contenedores_varios.xml")
    )
}

private fun createFilesModeloresiduo(
    arrayListOfModeloResiduo: ArrayList<ModeloResiduoDTO>,
    args: Array<String>
) {
    logger.info(" creamos ficheros Modelo residuo ")
    logger.info(" csv ")
    Csv().ModeloRosiduoToCsv(arrayListOfModeloResiduo, Path.of(args[2]))
    logger.info(" json")
    Jsonc().modeloResiduoDtoToAJson(Path.of(args[2]), arrayListOfModeloResiduo)
    logger.info(" xml ")
    Xmlc().modeloResiduoDtoToXml(
        arrayListOfModeloResiduo,
        Path.of(args[2] + File.separator + "modelo_residuos.xml")
    )
}

private fun getContenedoresVariosCSV(args: Array<String>): ArrayList<ContenedoresVariosDTO> {
    logger.info(" cogiendo datos de contenedores Varios")
    var arrayListOfContenedoreVarios =
        Csv().csvToContenedoresVarios(Path.of(args[1] + File.separator + "contenedores_varios.csv"))
    return arrayListOfContenedoreVarios
}

private fun getModeloResiduotoCSV(args: Array<String>): ArrayList<ModeloResiduoDTO> {
    logger.info(" cogiendo datos de archivo Modelo residuo ")
    var arrayListOfModeloResiduo =
        Csv().csvToMoeloResiduo(Path.of(args[1] + File.separator + "modelo_residuos_2021.csv"))
    return arrayListOfModeloResiduo
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
fun  beginingSumary(args: Array<String>, stringOfData: String) {
    logger.info("entramos en beginingSumaryAll")

    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();

    //para ver si ha tenido exito o no
    var exito = false
    var isCorrectData = false
    var tipoOpcion ="resumen"
    var directorioDeorigen = Path.of(args[1])
    var directoriodeResumen = Path.of(args[2])


    if (args.size==4){
        tipoOpcion="resumen district"
        directorioDeorigen = Path.of(args[2])
        directoriodeResumen = Path.of(args[3])
        logger.info("la opcion es resumen district porque los args son 4")
    }

        //1.sacar todos los ficheros del directorio
        if (Files.notExists(directorioDeorigen) && !Files.isDirectory(directorioDeorigen)){
            logger.info("el path de los archivos No exixte o NO es un directorio")

        }else{
            logger.info("el path de los archivos exixte y es un directorio")

            //listar todos los archivos dentro de un directorio y que sean leibles
            var ficherosReadble : List<Path>  = Files.list(directorioDeorigen).filter { p -> Files.isReadable(p) }.toList()

            // quedarnos con los de formato correcto
            logger.info("buscamos si hay xml")
            var ficherosXml = ficherosReadble.map { x -> x.toString() }.filter{x-> x.endsWith(".xml")}
                .map { x-> Path.of(x) }.toMutableList()
            logger.info("encontramos ${ficherosXml.size}")

            logger.info("buscamos si hay csv")
            var ficherosCsv = ficherosReadble.map { x -> x.toString() }.filter{x-> x.endsWith(".csv")}
                .map { x-> Path.of(x) }.toMutableList()
            logger.info("encontramos ${ficherosCsv.size}")

            logger.info("buscamos si hay json")
            var ficherosJson = ficherosReadble.map { x -> x.toString() }.filter{x-> x.endsWith(".json")}
                .map { x-> Path.of(x) }.toMutableList()
            logger.info("encontramos ${ficherosJson.size}")

            //con cada uno porbamos si se pueden leer y son de los que queremos, quitaremos con excepciones
            var pathModeloResiduo : Path? = null
            var pathContenedoresVarios : Path? = null

            logger.info("buscamos entre todas la correcta de Modeo residuo")
            pathModeloResiduo = getPactCorrectOfModeloResiduo(pathModeloResiduo, ficherosJson, ficherosXml, ficherosCsv)

            if (pathModeloResiduo==null){
                logger.info("no hay ningun archivo en la path que contenga los datos necesarios ")
            }else{
                logger.info("exixte un fichero con los datos necesarios para modelo residuo, buscamos para contenedores varios")
                logger.info("buscamos entre todas la correcta de Modeo residuo")
                pathContenedoresVarios = getCorrectPathOfContenedoresVarios(pathContenedoresVarios, ficherosJson, ficherosXml, ficherosCsv)

                if (pathContenedoresVarios==null){
                    logger.info("no exixte ningun fichero que contenga las columnas y en el orden necesarios para crear Contenedores vartios")

                    exito = false
                }else{

                    logger.info("exixten los dos archivos necesarios para hacer el resumen")
                    if(tipoOpcion == "resumen district"){

                        exito = doResumen(args[1],pathContenedoresVarios,pathModeloResiduo, stringOfData)
                    }else{

                        exito = doResumen("",pathContenedoresVarios,pathModeloResiduo, stringOfData)
                    }


                }
            }
        }


    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = tipoOpcion, exito = exito , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xmlc().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")
}

private fun getCorrectPathOfContenedoresVarios(
    pathContenedoresVarios: Path?,
    ficherosJson: MutableList<Path>,
    ficherosXml: MutableList<Path>,
    ficherosCsv: MutableList<Path>
): Path? {
    var pathContenedoresVarios1 = pathContenedoresVarios
    pathContenedoresVarios1 = searchCorrectFileInJsonFilesContenedoresVarios(ficherosJson)

    if (pathContenedoresVarios1 == null) {
        pathContenedoresVarios1 = searchCorrectFileInxmlFilesContenedoresVarios(ficherosXml)
    }
    if (pathContenedoresVarios1 == null) {
        pathContenedoresVarios1 = searchCorrectFileInCsvFilesContenedoresVarios(ficherosCsv)
    }
    return pathContenedoresVarios1
}

private fun getPactCorrectOfModeloResiduo(
    pathModeloResiduo: Path?,
    ficherosJson: MutableList<Path>,
    ficherosXml: MutableList<Path>,
    ficherosCsv: MutableList<Path>
): Path? {
    var pathModeloResiduo1 = pathModeloResiduo
    pathModeloResiduo1 = searchCorrectFileInJsonFilesModeloResiduo(ficherosJson)

    if (pathModeloResiduo1 == null) {
        pathModeloResiduo1 = searchCorrectFileInxmlFilesModeloResiduo(ficherosXml)
    }
    if (pathModeloResiduo1 == null) {
        pathModeloResiduo1 = searchCorrectFileInCsvFilesModeloResiduo(ficherosCsv)
    }
    return pathModeloResiduo1
}

fun doResumen(distrito: String , pathOfContenedoresVarios : Path, pathDeModeloResiduo : Path, stringOfData: String) : Boolean {
    logger.info("los datos de la path son correctos")

    var tipoOpcion = ""
    var exito = false
    if (distrito.equals("")){
        tipoOpcion="resume all"
        logger.info("entramos a la opcion resume all  porque no hay distrito $distrito")
        exito = ResumenDataFrame().resumenFrame(pathDeModeloResiduo, pathOfContenedoresVarios)

    }else{
        tipoOpcion="resume District"
        logger.info("entramos a la opcion resume distrito porque el distrito es  $distrito")
        exito = ResumenDataFrame().resumeDistrictFrame(pathDeModeloResiduo, pathOfContenedoresVarios, distrito)
    }

    logger.info("fin de tarea ")

return true

}

private fun getModeloResiduoDtoToFile(
    pathOfContenedoresVarios: Path,
    arrayListOfModeloResiduoDTO: List<ModeloResiduoDTO>,
    pathDeModeloResiduo: Path
) {
    var arrayListOfModeloResiduoDTO1 = arrayListOfModeloResiduoDTO
    if (pathOfContenedoresVarios.endsWith(".json")) {
        logger.info(" cogiendo datos de contenedores Varios desde json")
        arrayListOfModeloResiduoDTO1 = Jsonc()
            .readJsontoModeloresiduoDto(pathDeModeloResiduo)

    } else if (pathOfContenedoresVarios.endsWith(".csv")) {
        logger.info(" cogiendo datos de contenedores Varios desde csv")
        arrayListOfModeloResiduoDTO1 = Csv()
            .csvToMoeloResiduo(pathDeModeloResiduo)

    } else {
        logger.info(" cogiendo datos de contenedores Varios desde xml")
        arrayListOfModeloResiduoDTO1 = Xmlc()
            .xmlToModeloresiduoDto(pathDeModeloResiduo)
    }
}

private fun getContenedoresVariosDtoToFile(
    pathOfContenedoresVarios: Path,
    arrayListOfContenedoreVariosDTO: List<ContenedoresVariosDTO>
): List<ContenedoresVariosDTO> {
    var arrayListOfContenedoreVariosDTO1 = arrayListOfContenedoreVariosDTO
    if (pathOfContenedoresVarios.endsWith(".json")) {
        logger.info(" cogiendo datos de contenedores Varios desde json")
        arrayListOfContenedoreVariosDTO1 = Jsonc()
            .readJsontoContenedoresvariosDto(pathOfContenedoresVarios)

    } else if (pathOfContenedoresVarios.endsWith(".csv")) {
        logger.info(" cogiendo datos de contenedores Varios desde csv")
        arrayListOfContenedoreVariosDTO1 = Csv()
            .csvToContenedoresVarios(pathOfContenedoresVarios)

    } else {
        logger.info(" cogiendo datos de contenedores Varios desde xml")
        arrayListOfContenedoreVariosDTO1 = Xmlc()
            .xmlToContenedoresVariosDto(pathOfContenedoresVarios)
    }
    return arrayListOfContenedoreVariosDTO1
}

fun doMappetToContenedresVarios(array: ArrayList<ContenedoresVariosDTO>):
        ArrayList<ContenedoresVarios> {
    logger.info("entramos")

    var mapper = MapperContenedoresVarios()
    var arrayOfContenedoresVarios = ArrayList<ContenedoresVarios>()

    try {
        //aqui falla pero no se porque
        //por cada uno lo mapeamos y guardamos
         array.stream().forEach { x -> arrayOfContenedoresVarios.add(mapper.tdoToContenedoresVarios(x))}

    }catch (e: Exception){
        logger.info("no se ha conseguido pasar de modelo a object")
        e.printStackTrace()
    }
    return arrayOfContenedoresVarios

}

fun doMappetToModeloResiduo(array: ArrayList<ModeloResiduoDTO>):
        ArrayList<ModeloResiduo> {
    logger.info("entramos")

    var mapper = MaperModeloResiduo()
    var arrayOfModeloResiduo = ArrayList<ModeloResiduo>()

    try {
        //por cada uno lo mapeamos y guardamos
        array.stream().forEach(){ x -> arrayOfModeloResiduo.add(mapper.tdoToModrloResiduo(x))}

    }catch (e: Exception){
        logger.info("no se ha conseguido pasar de modelo a object")
        e.printStackTrace()
    }
    return arrayOfModeloResiduo
}

fun searchCorrectFileInCsvFilesContenedoresVarios(ficherosCsv: MutableList<Path>): Path? {
    var encontrado1 = false
    while (ficherosCsv.size != 0 && encontrado1 != true) {
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
    while (ficherosXml.size != 0 && encontrado1 != true) {
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
    while (ficherosJson.size != 0 && encontrado1 != true) {
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
    while (ficherosCsv.size != 0 && encontrado1 != true) {
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
    while (ficherosXml.size != 0 && encontrado1 != true) {
        var ficheroCorrecto: ArrayList<ModeloResiduoDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosXml.get(0)
            ficheroCorrecto = Xmlc().xmlToModeloresiduoDto(ficherosXml.removeAt(0))
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
    while ((ficherosJson.size!= 0) && (encontrado1!= true)) {


        logger.info("json sice es ${ficherosJson.size}  y encontraso es $encontrado1")
        var ficheroCorrecto: ArrayList<ModeloResiduoDTO> = ArrayList()
        try {
            var pathEncontrada = ficherosJson.get(0)
            ficheroCorrecto = Jsonc().readJsontoModeloresiduoDto(ficherosJson.removeAt(0))
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
pasados los parametros del programa devuelve un Int entre 1 y 3 que indica la elecion escogida
por la persona que ha pasado los parametros
3- resumende distrito
2- resumen total
1- parser
 */
fun getElection(args: Array<String>):Int{
    logger.info(" Entrado en get Elecion ")

    if(args.size == 4){
        logger.info(" como los args son 4 es la opcion resume district ")
        if(args[0]=="resumen"){
            logger.info("el rimer args en resume, por lo que la elecion en 3 resumen district")
            return 3
        }else{
            logger.info("la opcion no es correcta")
            return  4
        }
    }else if(args[0]=="resumen" && args.size == 3){
        logger.info(" como los args son 3  y es la primera opcion es resumen devuelve elecion 2 resumen all ")
        return 2
    }else if(args.size==3 && args[0]=="parser"){
        logger.info(" los args son 3 y opcion es parser por lo que es parser opcion 1 ")
        return 1
    }else{
        logger.info("los parametros pasados no son ninguno de los anteriores")
        return 4}

}

