package saio.tradingcardgame.domain.combat

import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

internal object CoinFlip {

    private val random = Random(nextInt())
    //TODO: not sure yet if like that.
    fun random(times: Int): CoinFlipResult {
        val headHits = (1..times)
                .filter { random.nextBoolean() }
                .map { }
                .size

        return Head(headHits)
    }

    internal abstract class CoinFlipResult(val hits: Int)
    internal class Head(hits: Int) : CoinFlipResult(hits)
    internal class Tail(hits: Int) : CoinFlipResult(hits)
}
