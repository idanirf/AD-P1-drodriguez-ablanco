package chekData

import models.ContenedoresVarios
import models.ModeloResiduo
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger



//Clase finalizada para comprobar


//todo no se si esto esta bien aqui jejej comprobar
private val logger: Logger = Logger.getLogger("Azahara y Dani Log")


class CheckData {


    /**
     * Comprueva que los parametros sean 3 ,
     * el primero parser, y el segundo una path que exixta
     * y tenga dos archivos llamados correctamente y csv
     */
    fun parser(args: Array<String>): Boolean{
        logger.info("chequeamos que existen los ficheros csv en parser: checkData().Parser")

            //args son 3 y primero parser
        if(args.size==3 && args[0]=="parser"){
            if (areFileCsv(args)) return true
        }

        return false
    }

    private fun areFileCsv(args: Array<String>): Boolean {
        return (isFileCsvContenedoresVarios(args) && isFileCsvModeloResiduo(args))
    }


    /**
     * Comprueva que los parametros sean 3 ,
     * el primero resume , y el segundo una path que exixta
     * y tenga dos archivos llamados correctamente en cualquier formato
     */
    fun sumaryAll(args: Array<String>): Boolean{
        logger.info("entrando en sumaryall")
        //args so 3 y primero resume
        if(args.size==3 && args[0]=="resume"){
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
                logger.info("Error: datos incorrectos")
            }
            if(ficheroContenedoresVariosOK && ficheroModeloResiduoOK){
                logger.info("datos correctos")
                return true}
        }

        logger.info("Error: datos incorrectos")
        return false
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
        try {
                if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.csv"))) {
                    logger.info("el fichero es csv")
                    return true
                }
        } catch (e: Exception) {
            logger.info("el fichero no es csv")
        }
        return false
    }

    private fun isFileCsvModeloResiduo(args: Array<String>): Boolean {
        try {
            //exixten los ficheros en csv
            if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.csv"))) {
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
        if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.xml"))) {
            if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.xml"))) {
                //todo log einformation por datos correctos
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

    fun checkDataIntoFiles(args: Array<String>): Boolean {
            //todo solo si da tiempo comprobar que los ficheros tiene todos los objetos que queremos
            //no necesario totalmente solo si da iempo
            //cualquier fichero

            return true
    }

    fun checkDataIntoCsv(args: Array<String>): Boolean {
        //todo solo si da tiempo comprobar que los ficheros tiene todos los objetos que queremos
        //no necesario totalmente solo si da iempo
        //cualquier fichero

        return true

    }



}