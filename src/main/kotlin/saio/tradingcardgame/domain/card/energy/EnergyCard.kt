package saio.tradingcardgame.domain.card.energy

import saio.tradingcardgame.domain.card.Card

internal data class EnergyCard(val type: EnergyType) : Card()
internal enum class EnergyType {
    PSYCHO,
    COMBAT,
    ELECTRO,
    FIRE,
    WATER,
    NATURE,
    NORMAL;

    fun matches(other: EnergyType) : Boolean {
        return when(this) {
            FIRE -> FireMatcher.matches(other)
            WATER -> WaterMatcher.matches(other)
            NATURE -> NatureMatcher.matches(other)
            COMBAT -> CombatMatcher.matches(other)
            PSYCHO -> PsychoMatcher.matches(other)
            ELECTRO -> ElectroMatcher.matches(other)
            NORMAL -> NormalMatcher.matches(other)
        }
    }
}


