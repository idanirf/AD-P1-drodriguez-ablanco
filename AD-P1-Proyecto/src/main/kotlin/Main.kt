import chekData.CheckData

fun main(args: Array<String>) {

    val args : Array<String> = arrayOf("parser","a","a","a")

    println("Hello World!")
    val election : Int  = getElection(args)
    //poner log
    when (election){
        1 -> beginingParser(args)
        2 -> beginingSumaryAll(args)
        3 -> beginingSumaryDistrict(args)
    }


    //para comprobar
    println("la elecione es $election")



}
/*
funcion que comprueva los args y si son ciestos debe tomar los ficheros csv
del directorio origen y trasformalos en JSON y XML en el directorio destino. En dicho
directorio destino deberán estar las tres versiones: CSV, JSON y XML.
 */
fun beginingParser(args: Array<String>) {
    //TODO añadir log de que ha entrado en esa elecion
    //comprobamos datos
    val isCorrectData = CheckData().parser(args)
    //TODO añadir log de si la elecion es correcta o no
    //si es correctos llamamos  intercange para hacerlo en los 3 formatos con hilos

    //para comprobar
    println(isCorrectData)

}
/*
funcion que comprueva los args y si son ciestos debe tomar la información
de los contenedores y de la recogida, independientemente de la extensión que tenga (si no
corresponde a la extensión o al formato deberá indicar error) y deberá procesarla
generando en directorio_destino un resumen.html, aplicándoles los estilos
 */
fun  beginingSumaryAll(args: Array<String>) {
    //TODO añadir log de que ha entrado en esa elecion
    val isCorrectData = CheckData().sumaryAll(args)
    //TODO añadir log de si la elecion es correcta o no
    //si es correctos llamamos  resume para hacer html
    
    //para comprobar
    println(isCorrectData)
}
/*
funcion que comprueva los args y si son ciestos debe tomar la
información de los contenedores y de la recogida, independientemente de la extensión que
tenga (si no corresponde a la extensión o al formato deberá indicar error) y deberá
procesarla generando en directorio_destino un resumen_distrito.html (solo si el distrito
existe, si no deberá mostrar error), aplicándoles los estilos que creas oportunos
 */
fun  beginingSumaryDistrict(args: Array<String>) {
    //TODO añadir log de que ha entrado en esa elecion
    val isCorrectData = CheckData().sumaryDistrict(args)
    //TODO añadir log de si la elecion es correcta o no
    //si es correctos llamamos  resume para hacer html

    //para comprobar
    println(isCorrectData)
    //TODO añadir log de si la elecion es correcta o no
    val isCorrctDistrict = CheckData().district()
}
/*
pasados los parametros del programa devuelve un Int entre 1 y 3 que indica la elecion escogida
por la persona que ha pasado los parametros
3- resumende distrito
2- resumen total
1- parser
 */
fun getElection(args: Array<String>):Int{
    //TODO añadir log de que ha entrado selecionar
    if(args.size == 4){
        return 3
    }else if(args[0]=="resumen"){
        return 2
    }else{return 1}

}

