package saio.tradingcardgame.domain.pokemon

import saio.tradingcardgame.domain.ability.Ability
import saio.tradingcardgame.domain.ability.AbilityCost
import saio.tradingcardgame.domain.ability.AbilityDamage
import saio.tradingcardgame.domain.ability.AbilityEffect
import saio.tradingcardgame.domain.card.energy.EnergyCard
import saio.tradingcardgame.domain.card.energy.EnergyType
import saio.tradingcardgame.domain.card.pokemon.Health
import saio.tradingcardgame.domain.card.pokemon.PokemonCard
import saio.tradingcardgame.domain.card.pokemon.Specification

internal class Bulbasaur : PokemonCard() {

    private val leech = Ability(
        name = "Leech",
        cost = AbilityCost(listOf(EnergyCard(EnergyType.NATURE), EnergyCard(EnergyType.NATURE))),
        damage = AbilityDamage(20),
        effect = AbilityEffect("After attacking Bulbasaur heals itself for one Token.")
    )

    override val totalHealth: Health
        get() = Health(initializeTotalHealth(4))

    override val specialization: Specification
        get() = Specification.NATURE

    override val weakness: Specification
        get() = Specification.FIRE

    override val resistance: Specification
        get() = Specification.NONE

    override val abilities: List<Ability>
        get() = listOf(leech)

    override fun performSpecificAbility(ability: Ability) {
    }
}