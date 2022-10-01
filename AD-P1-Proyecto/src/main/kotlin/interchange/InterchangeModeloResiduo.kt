package interchange

import enums.Meses
import enums.TipoResiduo
import models.loadContenedoresVariosCSV
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Path
import java.util.Collections
import java.util.stream.Collector
import java.util.stream.Collectors

class InterchangeModeloResiduo<ModeloResiduo> {


        //funciones que pasan de un tipo a otro
    /**
     * funcion que pasandole una linea de un scv te debuelve un ModeloResiduo
     */
    private fun getModelRediduo(linea : String):ModeloResiduo {
        println("log de que entra a  getModel resituo")

        val campos  = linea.split(";")

        //todo no se por que no funciona crear un modelo rsiduo, pero olo dejo ahoi y miramos
       return ModeloResiduo(año = campos[0].toIntOrNull(),
            mes = campos[1].toIntOrNull(),
            lote = campos[2].toIntOrNull() ,
            residuo = getTipoResiduo(campos[3]),
            distrito = campos[2],
            nombreDistrito = campos[2],
            toneladas = campos[2].toIntOrNull()
        )
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
    fun csvToObject(p : Path): ArrayList<ModeloResiduo>{
            println(" todo log para decir que memos entrado a csvToObjecto")
        val br =BufferedReader(FileReader(p.toFile()))

         var modeloResiduos = ArrayList<ModeloResiduo>()
        try {
                if(Files.exists(p)){

                    var lineas = br.readText()
                    var modelosResiduosList = Files.lines(p)
                        .skip(1)
                        .map(this::getModelRediduo)
                        .collect(Collectors.toList());

                        modeloResiduos.addAll(modelosResiduosList)

                }
            }catch (e : Exception) {
                println(" entro en la excepcion por csvtoobject")
            }finally {
                br.close()
            }


        return modeloResiduos
        }
        //fun jsonToObject(f : File) Sequence<T>{}
        // fun xmlToObject(f : File) Sequence<T>{}
        // fun objectToCsv(Sequence<T>):File{ }
        // fun objectToJson(Sequence<T>){}
        // fun objectToXml(Sequence<T>){}


}