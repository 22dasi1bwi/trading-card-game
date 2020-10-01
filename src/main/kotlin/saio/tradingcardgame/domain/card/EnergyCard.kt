package saio.tradingcardgame.domain.card

import domain.card.Card

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

internal sealed class EnergyTypeMatcher {
    abstract val validTypes: List<EnergyType>
    fun matches(energyType: EnergyType): Boolean {
        return validTypes.contains(energyType)
    }
}

internal object FireMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.FIRE, EnergyType.NORMAL)

}

internal object WaterMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.WATER, EnergyType.NORMAL)
}

internal object PsychoMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.PSYCHO, EnergyType.NORMAL)
}

internal object NatureMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.NATURE, EnergyType.NORMAL)
}

internal object ElectroMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.ELECTRO, EnergyType.NORMAL)
}

internal object CombatMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.COMBAT, EnergyType.NORMAL)
}

internal object NormalMatcher : EnergyTypeMatcher() {
    override val validTypes: List<EnergyType>
        get() = listOf(EnergyType.NORMAL)
}

