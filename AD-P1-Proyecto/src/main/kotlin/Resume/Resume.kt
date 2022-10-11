package Resume

import dto.logger
import models.ContenedoresVarios
import models.ModeloResiduo
import java.util.Collections
import java.util.stream.Collector
import java.util.stream.Collectors


class Resume {
    //esta clase se va a encargar de que cuando le pasemos una lista haga el resumen

    /**
    funcion que devuelve un resumen en html de el distrito selecionado
    información:
    - Titulo: Resumen de recogidas de basura y reciclaje de “Distrito”
    - Fecha de generación: Fecha y hora en formato español.
    - Autores: Nombre y apellidos de los dos autores.
    - Número de contenedores de cada tipo que hay en este distrito.
    - Total de toneladas recogidas en ese distrito por residuo.
    - Gráfico con el total de toneladas por residuo en ese distrito.
    - Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.
    - Gráfica del máximo, mínimo y media por meses en dicho distrito.
    - Tiempo de generación del mismo en milisegundos.
     */
    fun resumeDistrict(district: String, sM: ArrayList<ModeloResiduo>, sCV: ArrayList<ContenedoresVarios>): Boolean {
        //para ver el tiempo que tarda
        var tInit = System.currentTimeMillis();
        //Todo hacer

        logger.info("Número de contenedores de cada tipo que hay en este distrito")
        var numContenedoresPorTipo = sCV.stream()
            .filter { a -> a.barrio == district }
            .collect(Collectors.groupingBy { x -> x.tipoContenedor })
            .map { (a, b) -> b.stream().map { b.count() }.toList() }


        //todo no se como hacerlo

        logger.info("Total de toneladas recogidas en ese distrito por residuo.")
        //todo no se como hacerlo

        logger.info("Gráfico con el total de toneladas por residuo en ese distrito.")
        //todo no se como hacerlo

        logger.info("Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.")
        //todo no se como hacerlo

        logger.info("Gráfica del máximo, mínimo y media por meses en dicho distrito.")
        //todo no se como hacerlo


        //para ver cuanto tarda
        var tFinal = System.currentTimeMillis();
        var tDiference = tFinal - tInit;

        //Todo devuelve si consigue
        return false
    }


    /**
    funcion que devuelve un resumen en html del contenido de la secuencia
    resumen.html, aplicándoles los estilos que creas
    oportunos, con la siguiente información:
    - Titulo: Resumen de recogidas de basura y reciclaje de Madrid
    - Fecha de generación: Fecha y hora en formato español.
    - Autores: Nombre y apellidos de los dos autores.
    ok- Número de contenedores de cada tipo que hay en cada distrito.
    - Media de contenedores de cada tipo que hay en cada distrito.
    - Gráfico con el total de contenedores por distrito.
    - Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
    distrito.
    - Gráfico de media de toneladas mensuales de recogida de basura por distrito.
    - Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
    de basura agrupadas por distrito.
    - Suma de todo lo recogido en un año por distrito.
    - Por cada distrito obtener para cada tipo de residuo la cantidad recogida.
    - Tiempo de generación del mismo en milisegundos.
     */
    fun resumeAll(sM : ArrayList<ModeloResiduo>, sCV : ArrayList<ContenedoresVarios>): Boolean{
        //para ver el tiempo que tarda
        var tInit = System.currentTimeMillis();

        //todo hacer consultas

        logger.info("numero de contenedores por distrito")
        var c = HashMap<String?,Int>()
         sCV.stream()
            .filter{a -> a.tipoContenedor!=null}
            .collect(Collectors.groupingBy { a -> a.tipoContenedor })
            .forEach { a, b -> c.put(a, b.size)  }
        println(c)

        logger.info("Media de contenedores de cada tipo que hay en cada distrito")
        var d : HashMap<String?,HashMap<String?,Int>>
        //              distrito        tipo    cuantos/total
        //todo no se como hacerlo


        logger.info("Gráfico con el total de contenedores por distrito")
        //todo no se como hacerlo

        logger.info("Media de toneladas anuales de recogidas por" +
                " cada tipo de basura agrupadas por distrito")
        //todo no se como hacerlo

        logger.info("Gráfico de media de toneladas mensuales de recogida de basura por distrito." +
                " cada tipo de basura agrupadas por distrito")
        //todo no se como hacerlo

        logger.info("Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo\n" +
                "    de basura agrupadas por distrito.")
        //todo no se como hacerlo

        logger.info("Suma de todo lo recogido en un año por distrito")
        //todo no se como hacerlo

        logger.info("Por cada distrito obtener para cada tipo de residuo la cantidad recogida.")
        //todo no se como hacerlo


        //para ver cuanto tarda
        var tFinal = System.currentTimeMillis();
        var tDiference= tFinal - tInit;
        //todo hacerhtml con las consultas

        return false
    }

}