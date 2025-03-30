package jetbrains.kotlin.course.alias.util

typealias Identifier = Int

class IdentifierFactory {
    var counter: Int = 0

    fun uniqueIdentifier(): Identifier {
        return counter++
    }
}