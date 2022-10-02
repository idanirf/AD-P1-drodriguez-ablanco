package Resume

import models.ContenedoresVarios
import models.ModeloResiduo

class Resume {
    //esta clase se va a encargar de que cuando le pasemos una lista haga el resumen

    /*
    funcion que devuelve un resumen en html de el distrito selecionado
     */
    fun resumeDistrict(district : String, sM : ArrayList<ModeloResiduo>, sCV : ArrayList<ContenedoresVarios>){}

    /*
    funcion que devuelve un resumen en html del contenido de la secuencia
     */
    fun resumeAll(sM : ArrayList<ModeloResiduo>, sCV : ArrayList<ContenedoresVarios>){}

}