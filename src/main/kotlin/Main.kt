import kotlinx.cli.*

fun main(args: Array<String>) {
    if (args[0] == "give-me-the-odds") {
        ServerConnection.sendGet(args[1], args[2])
    } else {
        error(
            "Your command should start with give-me-the-odds followed by the name" +
                    " or path or the falcon empire file and then followed by the empire" +
                    " data file, e.g.: \n give-me-the-odds millenium-falcon.json test4.json"
        )
    }

}