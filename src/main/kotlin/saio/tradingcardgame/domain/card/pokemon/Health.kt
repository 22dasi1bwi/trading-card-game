package saio.tradingcardgame.domain.card.pokemon

internal data class Health(private val totalRaw: Int) {

    val vitalRaw: Int get() = vital.size * Token.vitalValue

    val depletedRaw: Int get() = depleted.size * Token.vitalValue

    val vital : List<VitalToken> get() = tokens.filterIsInstance<VitalToken>()

    val depleted : List<DepletedToken> get() = tokens.filterIsInstance<DepletedToken>()

    val total : List<Token> get() = tokens.toList()

    private var tokens: MutableList<Token>

    init {
        require(totalRaw >= 10) { "Health total must be at least ${Token.vitalValue}, but was $totalRaw." }

        tokens = VitalToken.from(totalRaw).toMutableList()
    }

    fun subtract(damageTaken: Int) {
        val depletedTokens = DepletedToken.from(damageTaken)
        tokens.mapInPlace(depletedTokens.size) { it.switch() }
    }

    fun restore(amount: Int) {
        val vitalTokens = VitalToken.from(amount)
        tokens.mapInPlace(vitalTokens.size) { it.switch() }
    }

    private fun isFullyDepleted() = tokens.all { it is DepletedToken }
    private fun isFullyVital() = tokens.all { it is VitalToken }

    private inline fun MutableList<Token>.mapInPlace(amountOfSwitches: Int, mutator: (Token) -> Token) {
        val iterator = this.listIterator()
        var counter = 0
        while (iterator.hasNext() && counter < amountOfSwitches) {
            val oldValue = iterator.next()
            val newValue = mutator(oldValue)
            if (newValue !== oldValue) {
                iterator.set(newValue)
                counter++
            }
            if ( isFullyVital() || isFullyDepleted()) break
        }
    }
}

internal sealed class Token {
    abstract val value: Int
    abstract fun switch(): Token

    fun from(input: Int): List<Token> {
        require(input.rem(vitalValue) == 0) { "Input must be a multiple of $vitalValue but was $input." }
        val numberOfTokens = input / vitalValue
        return (1..numberOfTokens).map { this }
    }

    companion object {
        const val vitalValue = 10
        const val depletedValue = 0
    }
}

internal object VitalToken : Token() {

    override val value: Int
        get() = vitalValue

    override fun switch(): Token {
        return DepletedToken
    }
}

internal object DepletedToken : Token() {
    override val value: Int
        get() = depletedValue

    override fun switch(): Token {
        return VitalToken
    }
}

