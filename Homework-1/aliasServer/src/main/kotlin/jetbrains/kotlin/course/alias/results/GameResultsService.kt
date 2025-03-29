package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import org.springframework.stereotype.Service

@Service
class GameResultsService {

    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        require(result.isNotEmpty()) { "The result cannot be empty" }
        require(result.none { it.points == 0 }) { "Each team must have at least one point" }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        return gameHistory.reversed()
    }

}