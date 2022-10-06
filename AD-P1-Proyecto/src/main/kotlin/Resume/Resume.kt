package Resume

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import models.ContenedoresVarios
import models.ModeloResiduo

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
    fun resumeDistrict(district : String, sM : ArrayList<ModeloResiduo>, sCV : ArrayList<ContenedoresVarios>){
        //Todo hacer
    }

    /**
    funcion que devuelve un resumen en html del contenido de la secuencia
    resumen.html, aplicándoles los estilos que creas
    oportunos, con la siguiente información:
    - Titulo: Resumen de recogidas de basura y reciclaje de Madrid
    - Fecha de generación: Fecha y hora en formato español.
    - Autores: Nombre y apellidos de los dos autores.
    - Número de contenedores de cada tipo que hay en cada distrito.
    - Media de contenedores de cada tipo que hay en cada distrito.
    - Gráfico con el total de contenedores por distrito.
    - Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
    distrito.
    - Gráfico de media de toneladas mensuales de recogida de basura por distrito.
    - Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
    de basura agrupadas por distrito.
    - Suma de todo lo recogido en un año por distrito.
    - Por cada distrito obtener para cada tipo de residuo la
     */
    fun resumeAll(sM : ArrayList<ModeloResiduo>, sCV : ArrayList<ContenedoresVarios>){
        //Todo hacer
    }

}