package epf.min2.projet_materiel_mobile

data class Pays(
    val name:Name,
    val capital:List<String>,
    val flag:Flags,
    val continents:List<String>,
    val population:Int
)

data class Name(
    val common:String,
    val official:String
)

data class Flags(
    val png:String
)