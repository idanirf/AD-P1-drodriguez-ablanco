package Resume

import models.ContenedoresVarios
import models.ModeloResiduo

class Resume {
    //esta clase se va a encargar de que cuando le pasemos una lista haga el resumen

    /*
    funcion que devuelve un resumen en html de el distrito selecionado
     */
    fun resumeDistrict(district : String, sM : Sequence<ModeloResiduo>, sCV : Sequence<ContenedoresVarios>){}

    /*
    funcion que devuelve un resumen en html del contenido de la secuencia
     */
    fun resumeAll(sM : Sequence<ModeloResiduo>, sCV : Sequence<ContenedoresVarios>){}

}