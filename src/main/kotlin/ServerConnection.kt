import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


object ServerConnection {

    fun sendGet(millenium: String, empire: String) {
        val params = mapOf("millenium" to millenium, "empire" to empire)
        val urlParams = params.map { (k, v) -> "${(k.utf8())}=${v.utf8()}" }
            .joinToString("&")
        val url = "http://localhost:9095/probability"
        val urlObj = URL(url)
        val conn = urlObj.openConnection() as HttpURLConnection
        try {
            conn.doOutput = true
            conn.requestMethod = "POST"
            conn.setRequestProperty("Accept-Charset", "UTF-8")
            conn.connect()
            val wr = DataOutputStream(conn.outputStream)
            wr.writeBytes(urlParams)
            wr.flush()
            wr.close()
            val `in`: InputStream = BufferedInputStream(conn.inputStream)
            val reader = BufferedReader(InputStreamReader(`in`))
            val result = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                result.append(line)
                println(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            conn.disconnect()
        }
    }
}

fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")