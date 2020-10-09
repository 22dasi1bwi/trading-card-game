package saio.tradingcardgame.domain.card.pokemon


internal data class Health(private val totalRaw: Int) {

    var remaining: MutableList<Token>

    var remainingRaw: Int

    init {
        require(totalRaw >= 10) { "Health total must be at least ${VitalToken.value}, but was $totalRaw." }

        remaining = VitalToken.from(totalRaw).toMutableList()
        remainingRaw = remaining.size * VitalToken.value
    }

    fun subtract(damageTaken: Int) {
//        require(damageTaken < 0) { "Damage taken must be lower than zero, but was $damageTaken." }

        val depletedTokens = DepletedToken.from(damageTaken)
        remainingRaw -= damageTaken
        remaining.switchInPlace(depletedTokens.size) { it.switch() }
    }

    private inline fun <T> MutableList<T>.switchInPlace(amountOfSwitches: Int = this.size, mutator: (T) -> T) {
        val iterator = this.listIterator()
        var counter = 0
        while (iterator.hasNext() && counter < amountOfSwitches) {
            val oldValue = iterator.next()
            val newValue = mutator(oldValue)
            if (newValue !== oldValue) {
                iterator.set(newValue)
                counter++
            }
        }
    }
}

internal sealed class Token {
    abstract val value: Int
    abstract fun switch(): Token

    fun from(input: Int): List<Token> {
        require(input.rem(VitalToken.value) == 0) { "Input must be a multiple of ${VitalToken.value} but was $input." }
        val numberOfTokens = input / VitalToken.value
        return (1..numberOfTokens).map { this }
    }
}

internal object VitalToken : Token() {

    override val value: Int
        get() = 10

    override fun switch(): Token {
        return DepletedToken
    }
}

internal object DepletedToken : Token() {
    override val value: Int
        get() = 0

    override fun switch(): Token {
        return VitalToken
    }
}

