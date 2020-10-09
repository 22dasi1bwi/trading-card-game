package saio.tradingcardgame.domain.card.pokemon

import saio.tradingcardgame.domain.ability.Ability
import saio.tradingcardgame.domain.card.energy.EnergyCard

internal abstract class PokemonCard {

    private var incapacitationState = null

    private val attachedEnergyCards = mutableListOf<EnergyCard>()

    abstract val totalHealth: Health

    abstract val specialization: Specification

    abstract val weakness: Specification?

    abstract val resistance: Specification?

    abstract val abilities: List<Ability>

    abstract val stage: Stage

    abstract fun performSpecificAbility(ability: Ability)

    fun performAbility(ability: Ability) {
        require(!isIncapacitated()) { "Pokemon cannot attack when incapacitated. Pokemon in state: ${incapacitationState}." }

        require(ability.canBeUsedWith(attachedEnergyCards))
        {
            "Required ability costs ${ability.cost.requiredEnergy} for ${ability.name} are not reached. " +
                    "Attached are ${attachedEnergyCards.map { it.type }}"
        }
        performSpecificAbility(ability)
    }

    fun attachEnergyCard(energyCard: EnergyCard) {
        this.attachedEnergyCards.add(energyCard)
    }

    protected fun initializeTotalHealth(numberOfTokens: Int): MutableList<Token> {
        return (1..numberOfTokens).map { FreshToken }.toMutableList()
    }

    private fun isIncapacitated() =
            this.incapacitationState == isConfused() ||
            this.incapacitationState == isParalyzed() ||
            this.incapacitationState == isAsleep()

    private fun isConfused() = this.incapacitationState == Incapacitation.CONFUSION

    private fun isParalyzed() = this.incapacitationState == Incapacitation.PARALYZE

    private fun isAsleep() = this.incapacitationState == Incapacitation.ASLEEP
}

internal enum class Incapacitation {
    ASLEEP,
    CONFUSION,
    PARALYZE,
}

internal enum class Stage(val level: Int) {
    BASIC(1),
    INTERMEDIATE(2),
    ADVANCED(3);
}