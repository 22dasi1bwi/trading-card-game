package saio.tradingcardgame.domain.card.pokemon

import saio.tradingcardgame.domain.ability.Ability
import saio.tradingcardgame.domain.card.energy.EnergyCard

internal abstract class PokemonCard {

    var incapacitationState = null

    val attachedEnergyCards = mutableListOf<EnergyCard>()

    abstract val totalHealth: Health

    abstract val specialization: Specification

    abstract val weakness: Specification

    abstract val resistance: Specification

    abstract val abilities: List<Ability>

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

    protected fun initializeTotalHealth(numberOfTokens: Int): List<Token> {
        var tokens = mutableListOf<Token>()
        repeat(numberOfTokens) {
            tokens.add(Token.new())
        }
        return tokens
    }

    private fun isIncapacitated() =
            this.incapacitationState == isConfused() ||
            this.incapacitationState == isParalyzed() ||
            this.incapacitationState == isAsleep()

    private fun isConfused() = this.incapacitationState == Incapacitation.CONFUSION

    private fun isParalyzed() = this.incapacitationState == Incapacitation.PARALYZE

    private fun isAsleep() = this.incapacitationState == Incapacitation.ASLEEP
}

internal data class Health(private val healthTokens: List<Token>)

internal class Token private constructor(val value: Int) {

    companion object {

        private const val singleTokenValue = 10

        fun new() = Token(singleTokenValue)
    }
}

enum class Incapacitation {
    ASLEEP,
    CONFUSION,
    PARALYZE,
}