package interchange

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Path

class Jsonc {

    fun contenedoresVariosToAJson(p : Path, array : ArrayList<ContenedoresVariosDTO>){


        val file = p.toFile()
        val json = Json { prettyPrint = true }
        file.writeText(json.encodeToString(array))



}
    fun readJsontoContenedoresvariosDto(p : Path): List<ContenedoresVariosDTO> {

        val file = p.toFile()
        if (file.exists()) {
            return Json.decodeFromString<List<ContenedoresVariosDTO>>(file.readText())
        } else {
            throw IllegalArgumentException("El fichero ${p} no existe")
        }
    }
    fun modeloResiduoDtoToAJson(p : Path, array : ArrayList<ModeloResiduoDTO>){


        val file = p.toFile()
        val json = Json { prettyPrint = true }
        file.writeText(json.encodeToString(array))



    }
    fun readJsontoModeloresiduoDto(p : Path): ArrayList<ModeloResiduoDTO> {

        val file = p.toFile()
        if (file.exists()) {
            return Json.decodeFromString<ArrayList<ModeloResiduoDTO>>(file.readText())
        } else {
            throw IllegalArgumentException("El fichero ${p} no existe")
        }
    }

}