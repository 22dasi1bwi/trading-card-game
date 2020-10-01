package saio.tradingcardgame.domain.ability

import saio.tradingcardgame.globalCombatContext

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

