package saio.tradingcardgame.domain.pokemon

import saio.tradingcardgame.domain.ability.Ability
import saio.tradingcardgame.domain.ability.AbilityCost
import saio.tradingcardgame.domain.ability.AbilityDamage
import saio.tradingcardgame.domain.ability.AbilityEffect
import saio.tradingcardgame.domain.card.*
import saio.tradingcardgame.domain.card.EnergyCard
import saio.tradingcardgame.domain.card.Health
import saio.tradingcardgame.domain.card.PokemonCard
import saio.tradingcardgame.domain.card.Specification

internal class Charmander : PokemonCard() {

    private val scratch = Ability(
        name = "Scratch",
        cost = AbilityCost(listOf(EnergyCard(EnergyType.FIRE))),
        damage = AbilityDamage(10),
        effect = AbilityEffect()
    )

    override val totalHealth: Health
        get() = Health(initializeTotalHealth(5))

    override val specialization: Specification
        get() = Specification.FIRE

    override val weakness: Specification
        get() = Specification.WATER

    override val resistance: Specification
        get() = Specification.NONE

    override val abilities: List<Ability>
        get() = listOf(scratch) // TODO implement actual abilities

    override fun performSpecificAbility(ability: Ability) {
        // lets assume we're facing a Bulbasaur
//        val totalDamageOutcomeEffective = ability.damage.calculateTotalDamageOutcome(specialization, Type.NATURE)
//        val totalDamageOutcomeNormal = ability.damage.calculateTotalDamageOutcome(specialization, Type.ELECTRO)
//        val totalDamageOutcomeResistant = ability.damage.calculateTotalDamageOutcome(specialization, Type.WATER)
//
//        println("TOTAL DAMAGE OUTCOME EFFECTIVE $totalDamageOutcomeEffective")
//        println("TOTAL DAMAGE OUTCOME NORMAL $totalDamageOutcomeNormal")
//        println("TOTAL DAMAGE OUTCOME RESISTANT $totalDamageOutcomeResistant")
    }
}