package cz.stasimek.fakturaceeasypeasy.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.ResourceUtils
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset

@ControllerAdvice
class SinglePageApplication(
    @Value("\${app.default-file}") private val defaultFile: String
) {

    @ExceptionHandler(NoHandlerFoundException::class)
    fun renderDefaultPage(): ResponseEntity<String> {
        var inputStream: FileInputStream? = null
        try {
            val indexFile = ResourceUtils.getFile(defaultFile)
            inputStream = FileInputStream(indexFile)
            val body = StreamUtils.copyToString(inputStream, Charset.defaultCharset())
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(body)
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("There was an error completing the action.")
        } finally {
            // Input stream must be closed. Otherwise, the file would stay locked.
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (ex: IOException) {
                    // Ignore.
                }
            }
        }
    }
}