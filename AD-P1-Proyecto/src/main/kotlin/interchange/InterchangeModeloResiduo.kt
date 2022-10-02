package interchange

import enums.Meses
import enums.TipoResiduo
import models.ModeloResiduo
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.LogManager
import java.util.logging.Logger
import java.util.stream.Collectors


class InterchangeModeloResiduo<ModeloResiduo> (){

    var logger: Logger = Logger.getLogger("Azahara y Dani Log")
    //logger.info(" entra a Interchange de Modelo residuo")

        //funciones que pasan de un tipo a otro
    /**
     * funcion que pasandole una linea de un scv te debuelve un ModeloResiduo
     */
    private fun getModelRediduo(linea : String): models.ModeloResiduo {
        logger.info(" entra a  getModel resituo")

        val campos  = linea.split(";")

        //todo no se por que no funciona crear un modelo rsiduo, pero olo dejo ahoi y miramos

       return ModeloResiduo(
           año = campos[0].toIntOrNull(),
            mes = getMes(campos[1]),
            lote = campos[2].toIntOrNull() ,
            residuo = getTipoResiduo(campos[3]),
            distrito = campos[4],
            nombreDistrito = campos[5],
            toneladas = campos[6].toIntOrNull()
        )
    }

    private fun getMes(s: String): Meses? {
        println("log de que entra a  get mes")
        when(s){
            "ENERO" -> return Meses.ENERO
            "FEBRERO"->return Meses.FEBRERO
            "MARZO"-> return  Meses.MARZO
            "ABRIL"-> return  Meses.ABRIL
            "MAYO" -> return  Meses.MAYO
            "JUNIO" -> return  Meses.JUNIO
            "JULIO" -> return  Meses.JULIO
            "AGOSTO" -> return  Meses.AGOSTO
            "SEPTIEMBRE"-> return  Meses.SEPTIEMBRE
            "OCTUBRE"-> return  Meses.OCTUBRE
            "NOVIEMBRE" ->return  Meses.NOVIEMBRE
            "DICIEMBRE" ->return  Meses.DICIEMBRE

        }
        return null
    }

    /**
     * PASADA UNA STRING NOS DEVIELVE EL PIDO DE RESIDU QUE ES, Y SI NO ESTÁ EN LA LISTA PONE DESCONOCIDO
     */
    private fun getTipoResiduo(s: String): TipoResiduo {
        println("log de que entra a  get tipo residuo")
        when(s){
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES"-> return TipoResiduo.ENVASES
            "VIDRIO"-> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON"-> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS"-> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL"-> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL"-> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS"-> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS"-> return TipoResiduo.ANIMALES_MUERTOS
            "RCD"-> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA"-> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES"-> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
        }
        return TipoResiduo.DESCONOCIDO
    }

    /**
    entra una path y extrae un alista de ModeloResiduo
     */
    fun csvToObject(p : Path): ArrayList<models.ModeloResiduo>{
            println(" todo log para decir que memos entrado a csvToObjecto")
        val br =BufferedReader(FileReader(p.toFile()))
        var lista = ArrayList<models.ModeloResiduo>()

             try {
                if(Files.exists(p)){

                    var lineas = br.readText()
                    var modelosResiduosCollection= Files.lines(p)
                        .skip(1)
                        .map(this::getModelRediduo)
                        .collect(Collectors.toList());
                    modelosResiduosCollection.forEach { m -> lista.add(m) }

                }
            }catch (e : Exception) {
                println(" entro en la excepcion por csvtoobject")

            }finally {
                br.close()
            }



        return lista
        }

    /**
     * funcion que combiente una lista de ModelResituo en lista de strings tipo csv
     */
    fun objectToCsv(a : ArrayList<models.ModeloResiduo>, p : Path ):File{
        println("loh entro en objectToCsv")

        var listaString = StringBuilder().append("año;mes;Meses;lote;Int;residuo;TipoResiduo;distrito;nombreDistrito;toneladas\n")
        a.forEach { m -> listaString.append(getStringToModeloResiduo(m)) }


        var f: File = writeInFile(p, listaString)

        return f
    }

    /**
     * en la path mira si hay fichero y si no lo crea y la string que la pasas
     * se la añade al ficehero
     */
    private fun writeInFile(p: Path, listaString: java.lang.StringBuilder): File {
        var f: File
        if (Files.notExists(p)) {
            f = File(p.toString())
        } else {
            f = p.toFile()
        }
        val bw = BufferedWriter(FileWriter(f))
        try {
            bw.write(listaString.toString())

        } catch (e: Exception) {
            println("log de eror al convertir objeto a csv")
        } finally {
            bw.close()
        }
        return f
    }

    private fun getStringToModeloResiduo(m : models.ModeloResiduo): String {
        return m.getStringScv()
    }

    /**
    funcion que pasa de un json a una lista de objetos
     */
    fun jsonToObject(p : Path): ArrayList<models.ModeloResiduo>{

        println(" todo log para decir que memos entrado a csvToObjecto")
        val resultList= ArrayList<models.ModeloResiduo>()
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
    fun objectToJson(modelosR: ArrayList<models.ModeloResiduo>, p: Path):File{

        /**
        funcionará cuando tengamos el gson correcto
        var gson = GsonBuilder().setPrettyPrinting().create()
        var json = gson.toJson(modelosR)
         return = writeInFile(p, json)

         */
        return p.toFile()

    }


    
//fun xmlToObject(f : File) Sequence<T>{}

// fun objectToXml(Sequence<T>){}


}