package interchange

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import enums.Meses
import enums.TipoContenedor
import enums.TipoResiduo
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger
import java.util.stream.Collectors

class Csv {

    private val logger: Logger = Logger.getLogger("Azahara y Dani Log")

    //---------------------------Modelo residuo---------------------------------------
    /**
    entra una path y extrae un alista de ModeloResiduo
     */
    public fun csvToMoeloResiduo(p : Path): ArrayList<ModeloResiduoDTO>{
        logger.info(" entrado en csvToObjecto")

        val br = BufferedReader(FileReader(p.toFile()))
        var lista = ArrayList<models.ModeloResiduo>()

        try {
            if(Files.exists(p)){

                var lineas = br.readText()
                var modelosResiduosCollection= Files.lines(p)
                    .skip(1)
                    .map(this::getModelRediduo)
                    .collect(Collectors.toList());
                modelosResiduosCollection.forEach { m -> lista.add(m.DtoToMdeloRediduo()) }

            }
        }catch (e : Exception) {
            println(" entro en la excepcion por csvtoobject")

        }finally {
            br.close()
        }
        return lista
    }

    /**
     * funcion que combiente una lista de ModelResituo en lista de strings tipo csv
     */
    public fun ModeloRosiduoToCsv(a : ArrayList<ModeloResiduoDTO>, p : Path): File {
        logger.info(" entrado en Modelo residuo ToCsv")

        var listaString = StringBuilder().append("año;mes;Meses;lote;Int;residuo;TipoResiduo;distrito;nombreDistrito;toneladas\n")
        a.forEach { m -> listaString.append(getStringToModeloResiduoCSV(m)) }


        var fi: File = writeInFile(p, listaString)

        return fi
    }

    //-------------------------------------Contenedores varios ------------------------


    public fun csvToContenedoresVarios(p: Path): ArrayList<ContenedoresVariosDTO> {
        logger.info("Accediendo a la función CSV to Contenedores varios")
        val br = BufferedReader(FileReader(p.toFile()))
        var lista = ArrayList<ContenedoresVariosDTO>()

        try {
            if (Files.exists(p)){
                var line = br.readLine()
                var contenedoresVariosCollection = Files.lines(p)
                    .skip(1)
                    .map(this::getContenedoresVariosDto)
                    .collect(Collectors.toList())
                contenedoresVariosCollection.forEach { m -> lista.add(m)}
            }
        } catch (e: Exception){
            logger.severe(e.toString())
        }finally {
            br.close()
        }
        return  lista
    }

    //Todo falta funcion de contenedores varios a csv

    //    -------------otros extras--------------------------------------------------

    fun writeInFile(p: Path, listaString: java.lang.StringBuilder): File {
        logger.info("Escribiendo en fichero")
        var f: File
        if (Files.notExists(p)) {
            logger.info("El fichero no existe")
            f = File(p.toString())
            logger.info("creado")
        } else {
            logger.info("el fichero existe")
            f = p.toFile()
        }
        val bw = BufferedWriter(FileWriter(f))
        try {
            logger.info("Escribiendo en el fichero")
            bw.write(listaString.toString())

        } catch (e: Exception) {
            logger.warning("El fichero existe")
        } finally {
            bw.close()
            logger.info("Cerrando el escritor del fichero")
        }
        logger.info("saliendo de writeFile")
        return f
    }


    private fun getStringToModeloResiduoCSV(m : models.ModeloResiduo): String {
        return m.getStringScv()
    }

    private fun getContenedoresVariosDto(line: String): ContenedoresVariosDTO {
        val campos = line.split(";")

        return ContenedoresVariosDTO(
            codigoInternoSituado = campos[0],
            tipoContenedor = campos[1],
            modelo = campos[2],
            descripcionModelo = campos[3],
            cantidad = campos[4],
            lote = campos[5],
            distrito = campos[6],
            barrio = campos[7],
            tipoVia = campos[8],
            nombre = campos[9],
            numero = campos[10],
            coordenadaX = campos[11],
            coordenadaY = campos[12],
            TAG = campos[13]
        )
    }


    /**
     * funcion que pasandole una linea de un scv te debuelve un ModeloResiduo
     */
    private fun getModelRediduo(linea : String): ModeloResiduoDTO {
        logger.info(" entra a  getModel resituo")

        val campos  = linea.split(";")

        return ModeloResiduoDTO(
            año = campos[0].toIntOrNull(),
            mes = campos[1],
            lote = campos[2].toIntOrNull() ,
            residuo = campos[3],
            distrito = campos[4],
            nombreDistrito = campos[5],
            toneladas = campos[6].toIntOrNull()
        )
    }



    //----------------------enumn que no se usan porque son para pojo y no dto

    private fun getMes(s: String): Meses? {
        logger.info(" entrado en get mes")
        when(s){
            "ENERO" -> return Meses.ENERO
            "FEBRERO"->return Meses.FEBRERO
            "MARZO"-> return  Meses.MARZO
            "ABRIL"-> return  Meses.ABRIL
            "MAYO" -> return  Meses.MAYO
            "JUNIO" -> return  Meses.JUNIO
            "JULIO" -> return  Meses.JULIO
            "AGOSTO" -> return  Meses.AGOSTO
            "SEPTIEMBRE"-> return  Meses.SEPTIEMBRE
            "OCTUBRE"-> return  Meses.OCTUBRE
            "NOVIEMBRE" ->return  Meses.NOVIEMBRE
            "DICIEMBRE" ->return  Meses.DICIEMBRE

        }
        return null
    }

    private fun getTipoResiduo(s: String): TipoResiduo {
        logger.info(" entrado en get tipo residuo")

        when(s){
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES"-> return TipoResiduo.ENVASES
            "VIDRIO"-> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON"-> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS"-> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL"-> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL"-> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS"-> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS"-> return TipoResiduo.ANIMALES_MUERTOS
            "RCD"-> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA"-> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES"-> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
        }
        return TipoResiduo.DESCONOCIDO
    }

    fun getEnumTipoContenedor(s: String): TipoContenedor {
        when (s) {
            "Envases" -> return TipoContenedor.ENVASES
            "Organica" ->return TipoContenedor.ORGANICA
            "Resto" -> return TipoContenedor.RESTO
            "Papel y carton" -> return TipoContenedor.PAPEL_Y_CARTÓN
            "Vidrio" -> return TipoContenedor.VIDRIO
        }
        return TipoContenedor.DESCONOCIDO
    }



}