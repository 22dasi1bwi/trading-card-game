package saio.tradingcardgame.domain.card.energy

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
