package saio.tradingcardgame.domain.ability

import saio.tradingcardgame.domain.card.energy.EnergyCard
import saio.tradingcardgame.domain.card.energy.EnergyType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AbilityTest {

    private val ability = Ability("Scratch", AbilityCost(listOf(EnergyCard(EnergyType.WATER))), AbilityDamage(10), AbilityEffect())

    @Nested
    inner class `Ability can be used` {

        @Nested
        inner class `if single NORMAL Energy is required` {

            private val singleNormalEnergyAbility = ability.copy(cost = AbilityCost(listOf(EnergyCard(EnergyType.NORMAL))))

            @Test
            fun `and single NORMAL Energy is attached`() {
                val canBeUsed = singleNormalEnergyAbility.canBeUsedWith(listOf(EnergyCard(EnergyType.NORMAL)))

                assertThat(canBeUsed).isTrue()
            }

            @Test
            fun `and single != NORMAL Energy is attached`() {
                val canBeUsed = singleNormalEnergyAbility.canBeUsedWith(listOf(EnergyCard(EnergyType.COMBAT)))

                assertThat(canBeUsed).isTrue()
            }
        }

        @Nested
        inner class `if multiple NORMAL Energy is required` {

            private val multiNormalEnergyAbility = ability.copy(cost = AbilityCost(listOf(EnergyCard(EnergyType.NORMAL), EnergyCard(EnergyType.NORMAL), EnergyCard(EnergyType.NORMAL))))

            @Test
            fun `and all attached Energy is of EnergyType NORMAL`() {
                val canBeUsed = multiNormalEnergyAbility.canBeUsedWith(
                        listOf(EnergyCard(EnergyType.NORMAL),
                                EnergyCard(EnergyType.NORMAL),
                                EnergyCard(EnergyType.NORMAL)
                        )
                )
                assertThat(canBeUsed).isTrue()
            }

            @Test
            fun `and all but one attached Energy is of EnergyType NORMAL`() {
                val canBeUsed = multiNormalEnergyAbility.canBeUsedWith(
                        listOf(EnergyCard(EnergyType.NORMAL),
                                EnergyCard(EnergyType.PSYCHO),
                                EnergyCard(EnergyType.NORMAL)
                        )
                )
                assertThat(canBeUsed).isTrue()
            }

            @Test
            fun `and all attached Energy is of EnergyType != NORMAL`() {
                val canBeUsed = multiNormalEnergyAbility.canBeUsedWith(
                        listOf(EnergyCard(EnergyType.NATURE),
                                EnergyCard(EnergyType.PSYCHO),
                                EnergyCard(EnergyType.ELECTRO)
                        )
                )
                assertThat(canBeUsed).isTrue()
            }
        }

        @Nested
        inner class `if single != NORMAL Energy is required` {

            private val singleNotNormalEnergyAbility = ability.copy(cost = AbilityCost(listOf(EnergyCard(EnergyType.WATER))))

            @Test
            fun `and single matching Energy is attached`() {
                val canBeUsed = singleNotNormalEnergyAbility.canBeUsedWith(listOf(EnergyCard(EnergyType.WATER)))

                assertThat(canBeUsed).isTrue()
            }

        }

        @Nested
        inner class `if multiple != NORMAL Energy is required` {

            private val multiNotNormalEnergyAbility = ability.copy(cost = AbilityCost(listOf(EnergyCard(EnergyType.WATER), EnergyCard(EnergyType.PSYCHO), EnergyCard(EnergyType.COMBAT))))

            @Test
            fun `and all attached Energy exactly matches`() {
                val canBeUsed = multiNotNormalEnergyAbility.canBeUsedWith(listOf(EnergyCard(EnergyType.WATER), EnergyCard(EnergyType.PSYCHO), EnergyCard(EnergyType.COMBAT)))

                assertThat(canBeUsed).isTrue()
            }

            @Test
            fun `and all attached Energy exactly matches ignoring order`() {
                val canBeUsed = multiNotNormalEnergyAbility.canBeUsedWith(listOf(EnergyCard(EnergyType.PSYCHO), EnergyCard(EnergyType.COMBAT), EnergyCard(EnergyType.WATER)))

                assertThat(canBeUsed).isTrue()
            }
        }
    }

    @Nested
    inner class `Ability cannot be used` {

        @Test
        fun `if amount of attached Energy is smaller than amount of required Energy`() {
            val canBeUsed = ability.canBeUsedWith(listOf())

            assertThat(canBeUsed).isFalse()
        }

        @Nested
        inner class `if single != NORMAL Energy is required` {

            private val singleNotNormalEnergyAbility = ability.copy(cost = AbilityCost(listOf(EnergyCard(EnergyType.WATER))))

            @Test
            fun `and a non-matching Energy is attached`() {
                val canBeUsed = singleNotNormalEnergyAbility.canBeUsedWith(listOf(EnergyCard(EnergyType.ELECTRO)))

                assertThat(canBeUsed).isFalse()
            }
        }

        @Nested
        inner class `if multiple Energy Types are required` {

            private val multiEnergyAbility = ability.copy(cost = AbilityCost(listOf(
                    EnergyCard(EnergyType.NORMAL),
                    EnergyCard(EnergyType.WATER),
                    EnergyCard(EnergyType.COMBAT),
                    EnergyCard(EnergyType.WATER)))
            )

            @Test
            fun `and at least one attached Energy does not match`() {
                val canBeUsed = multiEnergyAbility.canBeUsedWith(listOf(
                        EnergyCard(EnergyType.NORMAL),
                        EnergyCard(EnergyType.WATER),
                        EnergyCard(EnergyType.NATURE),
                        EnergyCard(EnergyType.WATER))
                )

                assertThat(canBeUsed).isFalse()
            }

            @Test
            fun `and a multi-required Energy is only attached once` () {
                val canBeUsed = multiEnergyAbility.canBeUsedWith(listOf(
                        EnergyCard(EnergyType.NORMAL),
                        EnergyCard(EnergyType.FIRE),
                        EnergyCard(EnergyType.NATURE),
                        EnergyCard(EnergyType.WATER))
                )

                assertThat(canBeUsed).isFalse()
            }
        }
    }
}