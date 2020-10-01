package saio.tradingcardgame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import saio.tradingcardgame.domain.combat.CombatContext

internal lateinit var globalCombatContext: CombatContext

@SpringBootApplication
class TradingCardGameApplication

fun main(args: Array<String>) {
    runApplication<TradingCardGameApplication>(*args)
}
