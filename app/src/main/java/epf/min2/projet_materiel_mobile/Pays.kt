package epf.min2.projet_materiel_mobile

import java.io.Serializable

data class Pays(
    val name:Name,
    val capital:List<String>,
    val flags:Flag,
    val continents:List<String>,
    val population:Int,
    val languages:List<String>,
    val area:Int,
    val region:String
): Serializable

data class Name(
    val common:String,
    val official:String
) : Serializable

data class Flag(
    val png:String,
    val alt:String
) : Serializable
data class Currency (
    val AOA: AOA
): Serializable
data class AOA(
    val name:String,
    val symbol:String
) : Serializable