package epf.min2.projet_materiel_mobile

data class Pays(
    val name:Name,
    val capital:List<String>,
    val flags:Flag,
    val continents:List<String>,
    val population:Int
)

data class Name(
    val common:String,
    val official:String
)

data class Flag(
    val png:String
)