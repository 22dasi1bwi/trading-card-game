package saio.tradingcardgame.domain.combat

import saio.tradingcardgame.domain.card.pokemon.PokemonCard

internal class CombatContext(val initiator: PokemonCard, val receiver: PokemonCard)
