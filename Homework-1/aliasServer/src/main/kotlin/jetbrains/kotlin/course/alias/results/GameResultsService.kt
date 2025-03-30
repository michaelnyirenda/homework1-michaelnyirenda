package jetbrains.kotlin.course.alias.results

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService

typealias GameResult = List<Team>

@Service
class GameResultsService {
    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            throw Error("Game result cannot be empty")
        }

        val allTeamIdsValid = result.all { team -> team.id in TeamService.teamsStorage }
        if (!allTeamIdsValid) {
            throw Error("Invalid team IDs found in game result")
        }

        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> = gameHistory.reversed()
}