rootProject.name = "camunda-addons"


include(":camunda-data")
include(":camunda-dmn")


include(":_incubator:camunda-junit5")
include(":_incubator:camunda-bom")

//include("examples:data:java")

include("_examples:java:example-java-camunda-data")
include("_examples:kotlin:example-kotlin-camunda-data")

