package saio.tradingcardgame.domain.ability

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import saio.tradingcardgame.domain.ability.AbilityDamage
import saio.tradingcardgame.domain.combat.CombatContext
import saio.tradingcardgame.domain.pokemon.Bulbasaur
import saio.tradingcardgame.domain.pokemon.Charmander
import saio.tradingcardgame.domain.pokemon.Squirtle
import saio.tradingcardgame.globalCombatContext

class AbilityDamageTest {

    @Test
    fun `applies Weakness modifier`() {
        globalCombatContext = CombatContext(initiator = Charmander(), receiver = Bulbasaur())
        val abilityDamage = AbilityDamage(20)

        val total = abilityDamage.total

        assertThat(total).isEqualTo(40)
    }

    @Test
    fun `applies Resistance modifier`() {
        globalCombatContext = CombatContext(initiator = Charmander(), receiver = Squirtle())
        val abilityDamage = AbilityDamage(40)

        val total = abilityDamage.total

        assertThat(total).isEqualTo(10)
    }
}