package html

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.io.html
import org.jetbrains.kotlinx.dataframe.io.toHTML

class CreateHtml {

    fun htmlResume(
        numeroContenedoresPorDistrito: DataFrame<Any?>?,
        mediaDeContenedoresDeCadaTipo: DataFrame<Any?>?,
        garficoDeMediaDeCadaTipo: Any?,
        mediaToneladasAnuales: DataFrame<Any?>?,
        mediaToneladasMensuales: DataFrame<Any?>?,
        graficoMediaToneladasMensuales: Any?,
        maxToneladasPorDistrito: DataFrame<Any?>?,
        minToneladasPorDistrito: DataFrame<Any?>?,
        medToneladasPorDistrito: DataFrame<Any?>?,
        sumToneladasPorDistrito: DataFrame<Any?>?,
        sumToneladasPorDistritoPorTipo: DataFrame<Any?>?
    ): String {

        return ""
    }

    fun htmlResumeDistrict(toneladasPorResiduo: DataFrame<Any?>?,
                           graficoDeTotalToneladas: Any?,
                           estadisticasPorResiduoMax: DataFrame<Any?>?,
                           estadisticasPorResiduoMin: DataFrame<Any?>?,
                           estadisticasPorResiduoMed: DataFrame<Any?>?,
                           estadisticasPorResiduoDesv: DataFrame<Any?>?,
                           graficoDemaxMinMedYDes: Any?,
                           contenedoresPorTipo: DataFrame<Any?>?): String {


        var html : String = ("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Estadísticas</title>
            </head>
            <body>
            <div style="background-color:  #86b754 ; border-width: 10px; border-style: solid; border-color: #599459 ; text-align: center;
             top: 0;  width: 100%;   position:fixed; line-height:100%; text-color: #355835 ;">
                <h1>Resumen de recogidas de basura y reciclaje de Madrid</h1>
            </div>
            <div style="background-color: #b9f3b9  ;">
                //quiero que este div pueda desplazarse hacia arriva y hacia abajo
                //quiero que los dos divs de dentro se distingan por colores
                <div>

                    <ul style="list-style-type: none">
                        <li>
                            <br>
                            <br>
                            <br>
                            <h2>Contenidos</h2>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;" >1. Número de contenedores de cada tipo que hay en este distrito.</a> <br>
                           
                        </li>
                        <li> <br>
                            <a href="" style="color: #355835 ;">2. Total de toneladas recogidas en ese distrito por residuo.</a> <br>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;">3.Gráfico con el total de toneladas por residuo en ese distrito.</a>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;">4.Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.</a>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;">5.Gráfica del máximo, mínimo y media por meses en dicho distrito.</a>
                        </li>
                        

                    </ul>
                    <br>
                    <br>
                </div>
                <div>

                    <ul>
                        <li>
                            <h2>1. Número de contenedores de cada tipo que hay en este distrito.</h2>
                             <br>
                             ${contenedoresPorTipo?.html()}

                        </li>
                        <li>
                            <h2>2. Total de toneladas recogidas en ese distrito por residuo.</h2>
                            <br>
                             ${toneladasPorResiduo?.html()} 
                            
                        </li>
                        <li>
                            <h2>3.Gráfico con el total de toneladas por residuo en ese distrito.</h2>
                             <br>
                             faltaGrafico
                             
                     
                        </li>
                        <li>
                            <h2>4.Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.</h2>
                            <br>
                            ${estadisticasPorResiduoMax?.toHTML()}
                           ${estadisticasPorResiduoMin?.toHTML()}
                           ${estadisticasPorResiduoMed?.toHTML()}
                           ${estadisticasPorResiduoDesv?.toHTML()}
                            
                            
                        </li>
                        <li>
                            <h2>5.Gráfica del máximo, mínimo y media por meses en dicho distrito.</h2>
                             faltaGrafico
                        </li>
                        
                    </ul>

                </div>
            </div>

            <div style="background-color: #804000 ; border-width: 2px; border-style: solid; border-color: #000000; text-align: center;
             bottom: 0;  width: 100%;  font-size: 15px; line-height:60%">
                <p>Autores: Azahara Blanco Rodríguez y Daniel Rodriguez Sanchez</p>
                <p> Fecha de generación de documento: Fecha y hora en formato español. Tiempo de generación del mismo en milisegundos : </p>
            </div>



            </body>
            </html>
        """.trimIndent()




        )
        return html

    }


}