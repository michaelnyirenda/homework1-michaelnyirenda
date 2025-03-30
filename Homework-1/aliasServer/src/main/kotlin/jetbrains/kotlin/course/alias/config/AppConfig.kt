package jetbrains.kotlin.course.alias.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["jetbrains.kotlin.course.alias"])
class AppConfig