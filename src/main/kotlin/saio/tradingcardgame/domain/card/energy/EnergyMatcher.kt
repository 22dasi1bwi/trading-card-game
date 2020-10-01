package saio.tradingcardgame.domain.card.energy

internal sealed class EnergyTypeMatcher {
    abstract val matchingTypes: List<EnergyType>
    fun matches(energyType: EnergyType): Boolean {
        return matchingTypes.contains(energyType)
    }
}

internal object FireMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.FIRE, EnergyType.NORMAL)
}

internal object WaterMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.WATER, EnergyType.NORMAL)
}

internal object PsychoMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.PSYCHO, EnergyType.NORMAL)
}

internal object NatureMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.NATURE, EnergyType.NORMAL)
}

internal object ElectroMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.ELECTRO, EnergyType.NORMAL)
}

internal object CombatMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.COMBAT, EnergyType.NORMAL)
}

internal object NormalMatcher : EnergyTypeMatcher() {
    override val matchingTypes: List<EnergyType>
        get() = listOf(EnergyType.NORMAL)
}
