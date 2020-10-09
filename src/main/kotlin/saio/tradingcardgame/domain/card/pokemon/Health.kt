package saio.tradingcardgame.domain.card.pokemon

import java.lang.IllegalStateException

internal data class Health(private val healthTokens: MutableList<Token>) {

    val rawTotal: Int get() = FreshToken.value * healthTokens.size

    val rawRemainingHealth: Int get() = healthTokens.filterIsInstance<FreshToken>().size * FreshToken.value

    fun subtract(receivedDamage: Int) {
        require(receivedDamage % FreshToken.value == 0)
        {
            "Receiving damage modulo 10 must be 0, but received damage was $receivedDamage."
        }

        val numberOfTokens = receivedDamage / FreshToken.value

        deplete(numberOfTokens)
    }

    private fun deplete(numberOfTokens: Int) {
        repeat(numberOfTokens) {
            val freshToken = healthTokens.find { it is FreshToken } ?: throw IllegalStateException("ups")
            val depletedToken = freshToken.switch()
            healthTokens.remove(freshToken)
            healthTokens.add(depletedToken)
        }
    }
}

internal sealed class Token {
    abstract val value: Int
    abstract fun switch(): Token
}

internal object FreshToken : Token() {

    override val value: Int
        get() = 10

    override fun switch(): Token {
        return DepletedToken
    }
}

internal object DepletedToken : Token() {
    override val value: Int
        get() = 0

    override fun switch(): Token {
        return FreshToken
    }
}

