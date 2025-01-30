rootProject.name = "Labs"
include("lab2")
include("cli")
include("Lab2ver2")
include("Lab2ver2:entities")
findProject(":Lab2ver2:entities")?.name = "entities"
include("Lab2ver2:DAO")
findProject(":Lab2ver2:DAO")?.name = "DAO"
include("Lab2ver2:Controller")
findProject(":Lab2ver2:Controller")?.name = "Controller"
include("Lab2ver2:Service")
findProject(":Lab2ver2:Service")?.name = "Service"
include("Lab2ver2:DAO:ru.kaledin170317.entities")
findProject(":Lab2ver2:DAO:ru.kaledin170317.entities")?.name = "ru.kaledin170317.entities"

include("CatAPI")
include("CatAPI:Entities")
findProject(":CatAPI:Entities")?.name = "Entities"
include("CatAPI:Repository")
findProject(":CatAPI:Repository")?.name = "Repository"
include("CatAPI:Servicies")
findProject(":CatAPI:Servicies")?.name = "Servicies"
include("CatAPI:Controllers")
findProject(":CatAPI:Controllers")?.name = "Controllers"


include("App")
include("CatDB")
include("OwnersDB")
//include("App:Entities")
//findProject(":App:Entities")?.name = "AppEntities"
//include("App:Repository")
//findProject(":App:Repository")?.name = "AppRepository"
//include("App:Servicies")
//findProject(":App:Servicies")?.name = "AppServicies"
//include("App:Controllers")
//findProject(":App:Controllers")?.name = "AppControllers"
