import chekData.CheckData

fun main(args: Array<String>) {

    val args = args
    println("Hello World!")
    val election : Int  = getElection(args)
    //poner log
    when (election){
        1 -> beginingParser()
        2 -> beginingSumaryAll()
        3 -> beginingSumaryDistrict()
    }


}
/*
funcion que comprueva los args y si son ciestos debe tomar los ficheros csv
del directorio origen y trasformalos en JSON y XML en el directorio destino. En dicho
directorio destino deberán estar las tres versiones: CSV, JSON y XML.
 */
fun beginingParser(){
    //var isCorrectData = CheckData.parser(args)
}
/*
funcion que comprueva los args y si son ciestos debe tomar la información
de los contenedores y de la recogida, independientemente de la extensión que tenga (si no
corresponde a la extensión o al formato deberá indicar error) y deberá procesarla
generando en directorio_destino un resumen.html, aplicándoles los estilos
 */
fun  beginingSumaryAll(){
  //  CheckData.SumaryAll(args: Array<String>)
}
/*
funcion que comprueva los args y si son ciestos debe tomar la
información de los contenedores y de la recogida, independientemente de la extensión que
tenga (si no corresponde a la extensión o al formato deberá indicar error) y deberá
procesarla generando en directorio_destino un resumen_distrito.html (solo si el distrito
existe, si no deberá mostrar error), aplicándoles los estilos que creas oportunos
 */
fun  beginingSumaryDistrict(){
   // CheckData.SumaryDistrict(args: Array<String>
   // CheckData.District()
}
/*
pasados los parametros del programa devuelve un Int entre 1 y 3 que indica la elecion escogida
por la persona que ha pasado los parametros
3- resumende distrito
2- resumen total
1- parser
 */
fun getElection(args: Array<String>):Int{
    if(args.size == 4){
        return 3
    }
    if(args[1]=="resumen"){
        return 2
    }
    return 1
}