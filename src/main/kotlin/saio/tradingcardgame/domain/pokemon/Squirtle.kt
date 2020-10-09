package saio.tradingcardgame.domain.pokemon

import saio.tradingcardgame.domain.ability.Ability
import saio.tradingcardgame.domain.card.pokemon.Health
import saio.tradingcardgame.domain.card.pokemon.PokemonCard
import saio.tradingcardgame.domain.card.pokemon.Specification
import saio.tradingcardgame.domain.card.pokemon.Stage

internal class Squirtle : PokemonCard() {

    override val totalHealth: Health
        get() = Health(initializeTotalHealth(4))
    override val specialization: Specification
        get() = Specification.WATER
    override val weakness: Specification?
        get() = Specification.ELECTRO
    override val resistance: Specification?
        get() = Specification.FIRE
    override val abilities: List<Ability>
        get() = listOf()
    override val stage: Stage
        get() = Stage.BASIC

    override fun performSpecificAbility(ability: Ability) {
    }
}