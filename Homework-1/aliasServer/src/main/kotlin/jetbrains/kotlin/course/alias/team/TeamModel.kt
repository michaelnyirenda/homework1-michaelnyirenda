package jetbrains.kotlin.course.alias.team

//Team Data Class

import jetbrains.kotlin.course.alias.util.Identifier

data class Team(val id: Identifier, var points: Int = 0) {
    val name = "Team#${id + 1}"
}