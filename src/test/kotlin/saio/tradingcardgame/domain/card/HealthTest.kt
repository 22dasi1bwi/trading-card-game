package saio.tradingcardgame.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import saio.tradingcardgame.domain.card.pokemon.DepletedToken
import saio.tradingcardgame.domain.card.pokemon.Health
import saio.tradingcardgame.domain.card.pokemon.VitalToken

class HealthTest {

    @Nested
    inner class `initializing Health` {

        @Test
        fun `throws if Health total cannot be divided by 10 restlessly`() {
            assertThatExceptionOfType(IllegalArgumentException::class.java)
                    .isThrownBy { Health(61) }
                    .withMessageContaining("Input must be a multiple of 10 but was 61.")
        }

        @Test
        fun `throws if Health total is lower than 10`() {
            assertThatExceptionOfType(IllegalArgumentException::class.java)
                    .isThrownBy { Health(9) }
                    .withMessageContaining("Health total must be at least 10, but was 9.")
        }

        @Test
        fun `sets raw remaining Health equals total Health`() {
            val health = Health(40)

            assertThat(health.remainingRaw).isEqualTo(40)
        }

        @Test
        fun `converts to Tokens`() {
            val health = Health(40)

            assertThat(health.remaining)
                    .hasSize(4)
                    .hasOnlyElementsOfType(VitalToken::class.java)
        }
    }

    @Nested
    inner class `subtracting damage taken` {

        @Test
        fun `throws if damage taken cannot be divided by 10 restlessly`() {
            val health = Health(60)
            assertThatExceptionOfType(IllegalArgumentException::class.java)
                    .isThrownBy { health.subtract(21) }
                    .withMessageContaining("Input must be a multiple of 10 but was 21.")
        }

        @Test
        fun `calculates raw remaining Health correctly`() {
            val health = Health(70)

            health.subtract(40)

            assertThat(health.remainingRaw).isEqualTo(30)
        }

        @Test
        fun `calculates VitalTokens correctly`() {
            val health = Health(70)

            health.subtract(40)

            assertThat(health.remaining.filterIsInstance<VitalToken>()).hasSize(3)
        }

        @Test
        fun `calculates DepletedTokens correctly`() {
            val health = Health(70)

            health.subtract(40)

            assertThat(health.remaining.filterIsInstance<DepletedToken>()).hasSize(4)
        }

        @Test
        fun `calculates Total Tokens correctly`() {
            val health = Health(70)

            health.subtract(40)

            assertThat(health.remaining).hasSize(7)
        }
    }
}
