package chekData

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class CheckData {


    /**
     * Comprueva que los parametros sean 3 ,
     * el primero parser, y el segundo una path que exixta
     * y tenga dos archivos llamados correctamente y csv
     */
    fun parser(args: Array<String>): Boolean{
        //todo log information por entrando a funcion parser de checkdata
            //args so 3 y primero parser
        if(args.size==3 && args[0]=="parser"){
            if (areFileCsv(args)) return true
        }

        return false
    }

    /*
    chekea que en la path esten los ficheros con los nombres correctos y en csv
     */
    private fun areFileCsv(args: Array<String>): Boolean {
        try {
            //exixten los ficheros en csv
            if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.csv"))) {
                if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.csv"))) {
                    //todo log einformation por datos correctos
                    return true
                }
            }
        } catch (e: Exception) {
            //todo log exception por datos incorrectos
        }
        return false
    }

    /**
     * Comprueva que los parametros sean 3 ,
     * el primero resume , y el segundo una path que exixta
     * y tenga dos archivos llamados correctamente en cualquier formato
     */
    fun sumaryAll(args: Array<String>): Boolean{
        //todo log information por entrando a funcion sumaryall de checkdata
        //args so 3 y primero resume
        if(args.size==3 && args[0]=="resume"){
            try {
                //exixten los ficheros en csv
                if(areFileCsv(args)) return true
                //exixten los ficheros en json
                if (areFileJson(args)) return true
                //exixten los ficheros en xml
                if (areFilesXml(args)) return true

            }catch(e:Exception ){
                //todo log exception por datos incorrectos
            }
        }
        return false
    }

    /*
    chekea que en la path esten los ficheros con los nombres correctos y en json
     */
    private fun areFileJson(args: Array<String>): Boolean {
        if (Files.exists(Path.of(args[2] + File.separator + "modelo_residuos_2021.json"))) {
            if (Files.exists(Path.of(args[2] + File.separator + "contenedores_varios.json"))) {
                //todo log einformation por datos correctos
                return true
            }
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
        //todo log information por entrando a funcion Sumaridistrict de checkdata
        if(args.size==3 && args[0]=="resume"){
            if (areFileCsv(args)) return true
            if (areFileJson(args)) return true
            if (areFilesXml(args)) return true
        }
        return true
    }

    /**
     * comprueva que el distrito elegido "parametro2"
     * este en la bbdd del csv o otro formato para poder sacar sus datos
     */
    fun district(): Boolean {
        //todo log information por entrando a funcion district de checkdata
        //para hacerlo necesitamos saver si el distrito está entre los que hay en el archivo
        //por ahora no puedo hacerlo
        return true
    }


}