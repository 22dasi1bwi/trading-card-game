package saio.tradingcardgame.domain.pokemon

import saio.tradingcardgame.domain.ability.Ability
import saio.tradingcardgame.domain.ability.AbilityCost
import saio.tradingcardgame.domain.ability.AbilityDamage
import saio.tradingcardgame.domain.ability.AbilityEffect
import saio.tradingcardgame.domain.card.energy.EnergyCard
import saio.tradingcardgame.domain.card.pokemon.Health
import saio.tradingcardgame.domain.card.pokemon.PokemonCard
import saio.tradingcardgame.domain.card.pokemon.Specification
import saio.tradingcardgame.domain.card.energy.EnergyType

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
    }
}