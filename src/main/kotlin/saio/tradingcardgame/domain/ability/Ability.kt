package saio.tradingcardgame.domain.ability

import saio.tradingcardgame.domain.card.EnergyCard
import saio.tradingcardgame.globalCombatContext

internal data class Ability(val name: String,
                            val cost: AbilityCost,
                            val damage: AbilityDamage,
                            val effect: AbilityEffect
) {

    fun canBeUsedWith(attachedEnergyCards: List<EnergyCard>): Boolean {
        val abilityActivationObjection = AbilityActivationObjection(cost.requiredEnergy)

        attachedEnergyCards.forEach { abilityActivationObjection.merge(it) }

        return abilityActivationObjection.none()
    }

    private data class AbilityActivationObjection(private val requiredEnergy: List<EnergyCard>) {

        private val objections = requiredEnergy.associateWith { ActivationObjection.MISSING_REQUIRED_ENERGY }.toMutableMap()

        fun merge(energyCard: EnergyCard) {
            objections
                    .filter { it.value != ActivationObjection.NONE }.keys
                    .firstOrNull { energyCard.type.matches(it.type) }
                    ?.let { objections[it] = ActivationObjection.NONE }
        }

        fun none() = objections.values.all { it == ActivationObjection.NONE }

        private enum class ActivationObjection(val description: String) {
            NONE("OK"),
            MISSING_REQUIRED_ENERGY("Not all required Energy has been attached")
        }
    }
}

internal class AbilityCost(val requiredEnergy: List<EnergyCard>)
internal data class AbilityDamage(private val baseDamage: Int) {

    val total: Int
        get() = this.applyDamageModifiers()

    private fun applyDamageModifiers(): Int {
        return baseDamage * WeaknessModifier - ResistanceModifier
    }

    private operator fun Int.times(modifier: DamageModifier) = this * modifier.value
    private operator fun Int.minus(modifier: DamageModifier) = this - modifier.value
}

internal interface DamageModifier {
    val value: Int
    fun isApplicable(): Boolean
}

internal object ResistanceModifier : DamageModifier {

    override val value: Int
        get() = if (isApplicable()) 30 else 0

    override fun isApplicable(): Boolean {
        return globalCombatContext.initiator.specialization == globalCombatContext.receiver.resistance
    }
}

internal object WeaknessModifier : DamageModifier {

    override val value: Int
        get() = if (isApplicable()) 2 else 1

    override fun isApplicable(): Boolean {
        return globalCombatContext.initiator.specialization == globalCombatContext.receiver.weakness
    }
}

internal data class AbilityEffect(val description: String = "")