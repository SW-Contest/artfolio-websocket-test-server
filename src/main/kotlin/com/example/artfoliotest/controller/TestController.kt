package com.example.artfoliotest.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller

@Controller
class TestController(private val simp : SimpMessageSendingOperations) {
    /* 버튼 가격 갱신 */

    // 클라이언트 구독 : /sub/channel/{auctionId}
    // 클라이언트 발행 : /pub/price
    @MessageMapping("/price")
    internal fun priceChange(map : Map<String, Long>)
    {
        simp.convertAndSend("/sub/channel/${map.get("auctionId")}", map.get("curPrice")!!)
    }
}