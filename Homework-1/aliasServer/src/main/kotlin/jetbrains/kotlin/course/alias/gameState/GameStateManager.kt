package jetbrains.kotlin.course.alias.gameState

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResult
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import jetbrains.kotlin.course.alias.util.Identifier
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Service
class GameStateManager(
    private val teamService: TeamService,
    private val cardService: CardService,
    private val gameResultsService: GameResultsService
) {
    private val mapper = jacksonObjectMapper()

    private val gameHistoryFile = File("game_history.json")
    private val teamsStorageFile = File("teams_storage.json")
    private val teamIdentifierFile = File("team_identifier.json")
    private val cardIdentifierFile = File("card_identifier.json")
    private val usedWordsFile = File("used_words.json")

    @PostConstruct
    fun loadState() {
        try {
            // Load game history
            if (gameHistoryFile.exists()) {
                val gameHistory: List<GameResult> = mapper.readValue(gameHistoryFile)
                GameResultsService.gameHistory.clear()
                GameResultsService.gameHistory.addAll(gameHistory)
            }

            // Load teams storage
            if (teamsStorageFile.exists()) {
                val teamsStorage: Map<Identifier, Team> = mapper.readValue(teamsStorageFile)
                TeamService.teamsStorage.clear()
                TeamService.teamsStorage.putAll(teamsStorage)
            }

            // Load team identifier counter
            if (teamIdentifierFile.exists()) {
                val counter: Int = mapper.readValue(teamIdentifierFile)
                teamService.identifierFactory.counter = counter
            }

            // Load card identifier counter
            if (cardIdentifierFile.exists()) {
                val counter: Int = mapper.readValue(cardIdentifierFile)
                cardService.identifierFactory.counter = counter
            }

            // Load used words/cards
            if (usedWordsFile.exists()) {
                val usedWords: Set<String> = mapper.readValue(usedWordsFile)
                cardService.usedWords.clear()
                cardService.usedWords.addAll(usedWords)
            }
        } catch (e: Exception) {
            println("Error loading game state: ${e.message}")
        }
    }

    @PreDestroy
    fun saveState() {
        try {
            // Save game history
            mapper.writeValue(gameHistoryFile, GameResultsService.gameHistory)

            // Save teams storage
            mapper.writeValue(teamsStorageFile, TeamService.teamsStorage)

            // Save team identifier counter
            mapper.writeValue(teamIdentifierFile, teamService.identifierFactory.counter)

            // Save card identifier counter
            mapper.writeValue(cardIdentifierFile, cardService.identifierFactory.counter)

            // Save used words/cards
            mapper.writeValue(usedWordsFile, cardService.usedWords)
        } catch (e: Exception) {
            println("Error saving game state: ${e.message}")
        }
    }
}