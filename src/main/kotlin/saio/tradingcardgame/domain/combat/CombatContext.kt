package saio.tradingcardgame.domain.combat

import saio.tradingcardgame.domain.card.pokemon.PokemonCard

/** This represents both active Pokemon fighting against each other. */
internal class CombatContext(val initiator: PokemonCard, val receiver: PokemonCard)

