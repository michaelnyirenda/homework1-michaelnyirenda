package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service

@Service
class CardService {
    // generate unique card identifiers
    val identifierFactory = IdentifierFactory()

    // store used words
    val usedWords: MutableSet<String> = mutableSetOf()

    // store all cards
    val cards: List<Card> = generateCards()

    companion object {
        // number of words per card
        const val WORDS_IN_CARD = 4

        // words list
        val cardsAmount = words.size / WORDS_IN_CARD
    }

    // convert a list of strings to a list of words
    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    // generate all cards by shuffling words
    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords.chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk ->
                Card(identifierFactory.uniqueIdentifier(), chunk.toWords())
            }
    }

    // get a card by its index or throw an error if it doesn't exist+
--    fun getCardByIndex(index: Int): Card {
        return cards.getOrElse(index) {
            throw Error("Card with index $index does not exist")
        }
    }
}