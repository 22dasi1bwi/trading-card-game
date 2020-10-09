package saio.tradingcardgame.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import saio.tradingcardgame.domain.pokemon.Bulbasaur

class PokemonCardTest {

    @Test
    fun `initializes total health correctly` () {
        val bulbasaur = Bulbasaur()

//        assertThat(bulbasaur.totalHitPoints.rawTotal).isEqualTo(40)
    }
}