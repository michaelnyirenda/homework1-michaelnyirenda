package jetbrains.kotlin.course.alias.team

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory

@Service
class TeamService {
    // property to generate identifiers for teams
    val identifierFactory = IdentifierFactory()

    companion object {
        // storage for all teams
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = mutableListOf<Team>()

        repeat(teamsNumber) {
            // generate a new unique id using the identifierFactory
            val id = identifierFactory.uniqueIdentifier()
            // create a new team with the generated id
            val team = Team(id)
            // add the team to our list
            teams.add(team)
            // store the team in the teamsStorage map
            teamsStorage[id] = team
        }

        return teams
    }
}