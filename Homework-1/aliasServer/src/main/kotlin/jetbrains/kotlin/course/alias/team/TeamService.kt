package jetbrains.kotlin.course.alias.team

import org.springframework.stereotype.Service

@Service
class TeamService {
    val identifierFactory: IdentifierFactory = IdentifierFactory()

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = List(teamsNumber) {
            val id = identifierFactory.uniqueIdentifier()
            Team(id)
        }
        teams.forEach { team -> teamsStorage[team.id] = team }
        return teams
    }

}
