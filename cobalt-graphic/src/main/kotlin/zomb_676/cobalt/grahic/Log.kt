package zomb_676.cobalt.grahic

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.MarkerManager
import org.apache.logging.log4j.core.appender.ConsoleAppender
import org.apache.logging.log4j.core.config.Configurator
import org.apache.logging.log4j.core.config.DefaultConfiguration
import org.apache.logging.log4j.core.layout.PatternLayout
import org.lwjgl.opengl.GL43
import org.lwjgl.opengl.GLDebugMessageCallback
import java.awt.event.ContainerAdapter
import java.util.function.Supplier

object Log {
    val logger = LogManager.getLogger("Cobalt")

    fun configureLogger() {

        System.setProperty("log4j.skipJansi", "false")//support colorful output

        Configurator.setLevel(logger.name, Level.INFO)
        Configurator.reconfigure(object : DefaultConfiguration() {

            override fun setToDefault() {
                name = DEFAULT_NAME + "@" + Integer.toHexString(hashCode())
                val appender = ConsoleAppender.newBuilder()
                    .setName("appender")
                    .setLayout(PatternLayout.newBuilder()
                        .withPattern("%highlight{[%d{HH:mm:ss}][%level][%thread/%marker]:%msg%n}{STYLE=Logback}")
                        .build())

                    .build()
                appender.start()
                addAppender(appender)
                rootLogger.addAppender(appender, Level.ALL, null)
                rootLogger.level = Level.ALL
            }
        })
    }

    fun enableDebug() {
        GL43.glEnable(GL43.GL_DEBUG_OUTPUT)
        GL43.glDebugMessageCallback(object : GLDebugMessageCallback() {
            override fun invoke(
                source: Int,
                type: Int,
                id: Int,
                severity: Int,
                length: Int,
                message: Long,
                userParam: Long,
            ) {
                val messageSource = DebugMessageType.MessageSource.getDescriptionByValue(source)
                val messageType = DebugMessageType.MessageType.getDescriptionByValue(type)
                val messageSeverity = DebugMessageType.MessageSeverity.getDescriptionByValue(severity)
                val mess = getMessage(length, message)
                val sb = StringBuilder()
                sb.append("gl debug message called").append("\n")
                sb.append("source:\t${messageSource.name},detail:${messageSource.description}").append("\n")
                sb.append("type:\t${messageType.enumName},detail:${messageType.description}").append("\n")
                sb.append("severity:\t${messageSeverity.name},detail:${messageSeverity.description}").append("\n")
                sb.append("message:\t$mess")
                when (messageSeverity) {
                    DebugMessageType.MessageSeverity.HIGH -> logger.fatal(sb)
                    DebugMessageType.MessageSeverity.MEDIUM -> logger.error(sb)
                    DebugMessageType.MessageSeverity.LOW -> logger.info(sb)
                    DebugMessageType.MessageSeverity.NOTIFICATION -> logger.trace(sb)
                }
            }

        }, 1)
    }
}