package saio.tradingcardgame.domain.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoinFlipTest {

    // TODO continue here : Can we mock [Random]?
    @Test
    fun `returns numbers of Head hits` () {
            val head = CoinFlip.random(3)

            assertThat(head.hits).isEqualTo(2)
    }

    @Test
    fun `returns head` () {

    }
}