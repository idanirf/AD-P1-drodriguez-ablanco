package Resume


import dto.ModeloResiduoDTO
import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.values
import java.io.File
import java.nio.file.Path
import java.util.stream.Collectors

class ResumenDataFrame {

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

    fun resumeDistrictFrame(pathMR: Path, pathCV: Path, district: String): Boolean {

        //para ver si exixte el distrito


        var districtsInModeloResiduo = DataFrame.read(pathMR.toFile())
            .filter { x -> x.get("Distrito") == district }

        logger.info("buscando distrito en el fichero Modelo Residio ")

        var districtsInContenedoresVarios = DataFrame.read(pathCV.toFile())
            .filter { x -> x.get("Distrito") == district }

        logger.info("buscando distrito en el fichero Contenedores varios ")


        if (districtsInModeloResiduo.count() == 0 && (districtsInContenedoresVarios.count() == 0)) {
            logger.info("no existe el distrito en el fichero")
        } else {
            logger.info("existe el distrito en el fichero")

            var tInit = System.currentTimeMillis();


            //todo no se si está bien
            logger.info("Número de contenedores de cada tipo que hay en este distrito")
            var contenedoresPorTipo =
                districtsInContenedoresVarios.groupBy("Tipo Contenedor").aggregate { count() }.print()

            //todo no se si está bien
            logger.info("Total de toneladas recogidas en ese distrito por residuo.")
            var toneladasPorResiduo = districtsInModeloResiduo.groupBy("Residuo").aggregate { sum("Toneladas") }.print()

            //todo no se como hacerlo pero es fráfico de lo anterior
            logger.info("Gráfico con el total de toneladas por residuo en ese distrito.")


            //todo no se como hacerlo
            logger.info("Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.")
            var estadisticasPorResiduoMaxYMin = districtsInModeloResiduo.groupBy("Residuo")
                .aggregate { max("Toneladas") }
                .aggregate { min("Toneladas") }.print()

            var estadisticasPorResiduoMedYDesv = districtsInModeloResiduo.groupBy("Residuo")
                .aggregate { mean("Toneladas") }
                .aggregate { std("Toneladas") }.print()


            //todo no se como hacerlo
            logger.info("Gráfica del máximo, mínimo y media por meses en dicho distrito.")


            //para ver cuanto tarda
            var tFinal = System.currentTimeMillis();
            var tDiference = tFinal - tInit;

            //Todo devuelve si consigue
            logger.info("falta hacer resimen district")
            return true

        }

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
    fun resumenFrame(pathMR: Path, pathCV: Path): Boolean {
//para ver el tiempo que tarda
        var tInit = System.currentTimeMillis();

        logger.info("entramos")
        //todo hacer consultas

        logger.info("numero de contenedores por distrito")
        var c = HashMap<String?, Int>()


        logger.info("Media de contenedores de cada tipo que hay en cada distrito")
        var d: HashMap<String?, HashMap<String?, Int>>
        //              distrito        tipo    cuantos/total
        //todo no se como hacerlo


        logger.info("Gráfico con el total de contenedores por distrito")
        //todo no se como hacerlo

        logger.info(
            "Media de toneladas anuales de recogidas por" +
                    " cada tipo de basura agrupadas por distrito"
        )
        //todo no se como hacerlo

        logger.info(
            "Gráfico de media de toneladas mensuales de recogida de basura por distrito." +
                    " cada tipo de basura agrupadas por distrito"
        )
        //todo no se como hacerlo

        logger.info(
            "Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo\n" +
                    "    de basura agrupadas por distrito."
        )
        //todo no se como hacerlo

        logger.info("Suma de todo lo recogido en un año por distrito")
        //todo no se como hacerlo

        logger.info("Por cada distrito obtener para cada tipo de residuo la cantidad recogida.")
        //todo no se como hacerlo


        //para ver cuanto tarda
        var tFinal = System.currentTimeMillis();
        var tDiference = tFinal - tInit;
        //todo hacerhtml con las consultas

        //Todo devuelve si consigue
        logger.info("falta hacer resimen ")
        return true

    }
}

