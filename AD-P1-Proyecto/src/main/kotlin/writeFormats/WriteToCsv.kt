package writeFormats

class WriteToCsv {
//esta clase se encargará que pasandole una string haga un csv y al reves

    fun csvToString(linea : String): List<String>{
        return linea.split(";")
    }

    fun stringToCsv(lista : List<String>): String{
        return lista.joinToString(";")
    }
}