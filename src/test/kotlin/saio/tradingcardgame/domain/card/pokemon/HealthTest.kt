package saio.tradingcardgame.domain.card.pokemon

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

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

            assertThat(health.vitalRaw).isEqualTo(40)
        }

        @Test
        fun `converts to Tokens`() {
            val health = Health(40)

            assertThat(health.total)
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

        @Nested
        inner class `without over-kill` {

            @Test
            fun `calculates raw vital Health correctly`() {
                val health = Health(70)

                health.subtract(40)

                assertThat(health.vitalRaw).isEqualTo(30)
            }

            @Test
            fun `calculates raw depleted Health correctly`() {
                val health = Health(70)

                health.subtract(40)

                assertThat(health.depletedRaw).isEqualTo(40)
            }

            @Test
            fun `calculates VitalTokens correctly`() {
                val health = Health(70)

                health.subtract(40)

                assertThat(health.vital).hasSize(3)
            }

            @Test
            fun `calculates DepletedTokens correctly`() {
                val health = Health(70)

                health.subtract(40)

                assertThat(health.depleted).hasSize(4)
            }

            @Test
            fun `calculates Total Tokens correctly`() {
                val health = Health(70)

                health.subtract(40)

                assertThat(health.total)
                        .hasSize(7)
                        .hasOnlyElementsOfTypes(VitalToken::class.java, DepletedToken::class.java)
            }
        }

        @Nested
        inner class `with over-kill` {

            @Test
            fun `calculates raw vital Health correctly`() {
                val health = Health(70)

                health.subtract(80)

                assertThat(health.vitalRaw).isEqualTo(0)
            }

            @Test
            fun `calculates raw depleted Health correctly`() {
                val health = Health(70)

                health.subtract(80)

                assertThat(health.depletedRaw).isEqualTo(70)
            }

            @Test
            fun `calculates VitalTokens correctly`() {
                val health = Health(70)

                health.subtract(80)

                assertThat(health.vital).hasSize(0)
            }

            @Test
            fun `calculates DepletedTokens correctly`() {
                val health = Health(70)

                health.subtract(80)

                assertThat(health.depleted).hasSize(7)
            }

            @Test
            fun `calculates Total Tokens correctly`() {
                val health = Health(70)

                health.subtract(80)

                assertThat(health.total)
                        .hasSize(7)
                        .hasOnlyElementsOfTypes(DepletedToken::class.java)
            }
        }

    }

    @Nested
    inner class `restoring health` {

        @Test
        fun `throws if amount of restored Health cannot be divided by 10 restlessly`() {
            val health = Health(60)
            assertThatExceptionOfType(IllegalArgumentException::class.java)
                    .isThrownBy { health.restore(21) }
                    .withMessageContaining("Input must be a multiple of 10 but was 21.")
        }

        @Nested
        inner class `without over-healing` {

            @Test
            fun `calculates raw vital Health correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(20)

                assertThat(health.vitalRaw).isEqualTo(60)
            }

            @Test
            fun `calculates raw depleted Health correctly`() {
                val health = Health(70)
                health.subtract(50)

                health.restore(40)

                assertThat(health.depletedRaw).isEqualTo(10)
            }

            @Test
            fun `calculates VitalTokens correctly`() {
                val health = Health(70)
                health.subtract(50)

                health.restore(40)

                assertThat(health.vital).hasSize(6)
            }

            @Test
            fun `calculates DepletedTokens correctly`() {
                val health = Health(70)
                health.subtract(50)

                health.restore(40)

                assertThat(health.depleted).hasSize(1)
            }

            @Test
            fun `calculates Total Tokens correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(20)

                assertThat(health.total)
                        .hasSize(7)
                        .hasOnlyElementsOfTypes(VitalToken::class.java, DepletedToken::class.java)
            }
        }

        @Nested
        inner class `with over-healing` {

            @Test
            fun `calculates raw vital Health correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(40)

                assertThat(health.vitalRaw).isEqualTo(70)
            }

            @Test
            fun `calculates raw depleted Health correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(40)

                assertThat(health.depletedRaw).isEqualTo(0)
            }

            @Test
            fun `calculates VitalTokens correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(40)

                assertThat(health.vital).hasSize(7)
            }

            @Test
            fun `calculates DepletedTokens correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(40)

                assertThat(health.depleted).hasSize(0)
            }

            @Test
            fun `calculates Total Tokens correctly`() {
                val health = Health(70)
                health.subtract(30)

                health.restore(40)

                assertThat(health.total)
                        .hasSize(7)
                        .hasOnlyElementsOfTypes(VitalToken::class.java)
            }
        }
    }
}
