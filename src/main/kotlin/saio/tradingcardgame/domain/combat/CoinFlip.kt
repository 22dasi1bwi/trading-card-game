package saio.tradingcardgame.domain.combat

import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

internal object CoinFlip {

    private val random = Random(nextInt())

    fun random(times: Int): CoinFlipOutcome {
        val headHits = (1..times)
                .filter { random.nextBoolean() }
                .size

        return Head(headHits)
    }

    internal abstract class CoinFlipOutcome(val hits: Int)
    internal class Head(hits: Int) : CoinFlipOutcome(hits)
    // TODO: Do we need Tail? We're not using it at the moment.
    internal class Tail(hits: Int) : CoinFlipOutcome(hits)
}