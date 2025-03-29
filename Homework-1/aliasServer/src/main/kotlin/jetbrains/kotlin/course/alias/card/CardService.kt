package jetbrains.kotlin.course.alias.card

import org.springframework.stereotype.Service

@Service
class CardService {
    val identifierFactory: IdentifierFactory = IdentifierFactory()
    val cards: List<Card> = generateCards()

    companion object {
        const val WORDS_IN_CARD = 4
        val words: List<String> = listOf(
            "apple", "banana", "car", "dog", "elephant", "flower", "guitar", "house",
            "ice cream", "jacket", "kite", "laptop", "moon", "notebook", "orange",
            "pencil", "queen", "rabbit", "sun", "tree", "umbrella", "violin", "water",
            "xylophone", "yacht", "zebra", "airplane", "book", "cat", "dolphin",
            "eagle", "fish", "giraffe", "hat", "island", "jellyfish", "koala"
            // You can add more words if needed
        )
    }

    fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    private fun generateCards(cardsAmount: Int = 10): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk ->
                Card(
                    id = identifierFactory.uniqueIdentifier(),
                    words = chunk.toWords()
                )
            }
    }

    fun getCardByIndex(index: Int): Card {
        return cards.getOrElse(index) {
            throw IllegalArgumentException("Card with index $index does not exist")
        }
    }
}
