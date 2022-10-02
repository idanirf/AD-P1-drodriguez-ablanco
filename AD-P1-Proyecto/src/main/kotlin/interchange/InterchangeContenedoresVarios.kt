package interchange

import java.io.File
import java.nio.file.Path

class InterchangeContenedoresVarios<ContenedoresVarios> {

    //funciones que pasan de un tipo a otro
   // fun csvToObject(f : File): Sequence<T>{}
   // fun xmlToObject(f : File) Sequence<T>{}
   // fun objectToCsv(Sequence<T>):File{ }

    /*
funcion que pasa de un json a una lista de objetos

 */
    fun jsonToObject(p : Path): ArrayList<models.ContenedoresVarios>{

        println(" todo log para decir que memos entrado a csvToObjecto")
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

        /**
        funcionará cuando tengamos el gson correcto
        var gson = GsonBuilder().setPrettyPrinting().create()
        var json = gson.toJson(modelosR)
        return = writeInFile(p, json)

         */
        return p.toFile()

    }

    fun csvToObject(of: Path): ArrayList<ContenedoresVarios> {
        return  ArrayList()
    }
    // fun objectToXml(Sequence<T>){}

}