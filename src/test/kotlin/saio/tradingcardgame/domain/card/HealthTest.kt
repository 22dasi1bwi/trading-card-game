package saio.tradingcardgame.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import saio.tradingcardgame.domain.card.pokemon.FreshToken
import saio.tradingcardgame.domain.card.pokemon.Health
import saio.tradingcardgame.domain.card.pokemon.Token

class HealthTest {

    @Test
    fun `foo` () {
       val health = Health(initializeTotalHealth(10))

        health.subtract(40)

        assertThat(health.rawRemainingHealth).isEqualTo(60)
    }
}
internal fun initializeTotalHealth(numberOfTokens: Int): MutableList<Token> {
    return (1..numberOfTokens).map { FreshToken }.toMutableList()
}
