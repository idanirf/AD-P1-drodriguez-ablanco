package chekData

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import interchange.Csv
import interchange.Jsonc
import interchange.Xmlc
import models.ContenedoresVarios
import models.ModeloResiduo
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger



//Clase finalizada para comprobar


private val logger: Logger = Logger.getLogger("Azahara y Dani Log")


class CheckData {




    private fun areFileCsv(args: Array<String>): Boolean {
        return (isFileCsvContenedoresVarios(args) && isFileCsvModeloResiduo(args))
    }


    private fun areFilesXmlContenedoresVarios(args: Array<String>): Boolean {
        try {
            if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.Xml"))) {
                logger.info("el fichero es xml")
                return true
            }
        } catch (e: Exception) {
            logger.info("el fichero no es xml")
        }
        return false
    }

    private fun areFilesXmlModeloResiduo(args: Array<String>): Boolean {
        try {
            //exixten los ficheros en csv
            if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.xml"))) {
                logger.info("el fichero es xml")
                return true
            }
        } catch (e: Exception) {
            logger.info("Eel fichero no es xml")
        }
        return false
    }

    private fun areFileJsonContenedoresVarios(args: Array<String>): Boolean {
        try {
            if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.json"))) {
                logger.info("el fichero es json")
                return true
            }
        } catch (e: Exception) {
            logger.info("el fichero no es json")
        }
        return false

    }

    private fun areFileJsonModeloResiduo(args: Array<String>): Boolean {
        logger.info("comenzando el check")
        logger.info(args[2] + File.separator + "modelo_residuos_2021.json")
        try {
            //exixten los ficheros en csv
            if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.json"))) {
                logger.info("el fichero es json")
                return true
            }
        } catch (e: Exception) {
            logger.info("Eel fichero no es json")
        }
        return false
    }

    private fun isFileCsvContenedoresVarios(args: Array<String>): Boolean {

        logger.info("comenzando el check en " +args[1] + File.separator + "contenedores_varios.csv")
        try {
                if (Files.exists(Path.of(args[1] + File.separator + "contenedores_varios.csv"))) {
                    logger.info("el fichero es csv")
                    return true
                }
        } catch (e: Exception) {
            logger.info("el fichero no es csv")
        }
        return false
    }

    private fun isFileCsvModeloResiduo(args: Array<String>): Boolean {
        logger.info( "comenzando el check en " + args[1] + File.separator + "modelo_residuos_2021.csv")
        try {
            //exixten los ficheros en csv
            if (Files.exists(Path.of(args[1] + File.separator + "modelo_residuos_2021.csv"))) {
                    logger.info("el fichero es csv")
                    return true
            }
        } catch (e: Exception) {
            logger.info("Eel fichero no es csv")
        }
        return false

    }


    /*
    chekea que en la path esten los ficheros con los nombres correctos y en xml
     */
    private fun areFilesXml(args: Array<String>): Boolean {
        logger.info("entrando en areFilesXml")
        if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.xml"))) {
            if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.xml"))) {
                logger.info("formato de fichero correcto")
                return true
            }
        }
        return false
    }

    /**
     * Comprueva que los parametros sean 4 ,
     * el primero resume , y el tercero una path que exixta
     * y tenga dos archivos llamados correctamente en cualquier formato
     */
    fun sumaryDistrict(args: Array<String>): Boolean {
        logger.info("entra en sumaryDistrict de check data")

            if(args.size==4 && args[0]=="resume"){
                var ficheroModeloResiduoOK : Boolean = false
                var ficheroContenedoresVariosOK: Boolean = false
                try {
                    //ves si el fichero 1 es correcto en algun formato

                    if(isFileCsvModeloResiduo(args) || areFileJsonModeloResiduo (args)|| areFilesXmlModeloResiduo (args)){
                        ficheroModeloResiduoOK = true
                    }
                    //ver si el fichero 2 es corrcto en algun formato

                    if(isFileCsvContenedoresVarios(args) || areFileJsonContenedoresVarios(args) || areFilesXmlContenedoresVarios(args)){
                        ficheroModeloResiduoOK = true
                    }

                }catch(e:Exception ){
                    logger.info("los ficheros no son correctos")
                }
                if(ficheroContenedoresVariosOK && ficheroModeloResiduoOK){
                    logger.info("los ficheros soncorrectos ")
                    return true}
            }else{
                logger.info("los parametros no son correctos")
            }

            return false

    }

    /**
     * comprueva que el distrito elegido "parametro2"
     * este en la bbdd del csv o otro formato para poder sacar sus datos
     */
    fun district(district : String,
                 arrayListOfModeloResiduo : ArrayList<ModeloResiduo>,
                 arrayListOfContenedoreVarios: ArrayList<ContenedoresVarios>): Boolean {
        logger.info("entrando e funcion check Data District")
        //para hacerlo necesitamos saver si el distrito está entre los que hay en los dos archivos
        var isDistitIn1 : Boolean = arrayListOfContenedoreVarios.stream()
            .map { x -> x.distrito }.anyMatch { x -> x.toString()== district }

        var isDistitIn2 : Boolean = arrayListOfModeloResiduo.stream()
            .map { x -> x.distrito }.anyMatch { x -> x.toString()== district }
        if (isDistitIn1 && isDistitIn2){
            logger.info("el distrito está en los dos ficheros")
            return true
        }
        logger.info("el distrito NO está en los dos ficheros")
        return false
    }

    fun encontrarFicherosCorrectosEnELDirectoriodeModeloResiduo(directorioDeorigen: Path): Path? {



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
            return getPactCorrectOfModeloResiduo(pathModeloResiduo, ficherosJson, ficherosXml, ficherosCsv)




    }

    private fun getPactCorrectOfModeloResiduo(pathModeloResiduo: Path?,
                                              ficherosJson: MutableList<Path>,
                                              ficherosXml: MutableList<Path>,
                                              ficherosCsv: MutableList<Path>): Path? {

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



    fun encontrarFicherosCorrectosEnELDirectoriodeContenedoresVarios(directorioDeorigen: Path): Path? {

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
        return  getCorrectPathOfContenedoresVarios(pathContenedoresVarios, ficherosJson, ficherosXml, ficherosCsv)

    }

    private fun getCorrectPathOfContenedoresVarios(pathContenedoresVarios: Path?,
                                                   ficherosJson: MutableList<Path>,
                                                   ficherosXml: MutableList<Path>,
                                                   ficherosCsv: MutableList<Path>): Path ?{

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




}