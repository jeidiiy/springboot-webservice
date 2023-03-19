package io.jeidiiy.springbootwebservice.web.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloResponseDtoTest {
    @Test
    fun 롬복_기능_테스트() {
        //given
        val name = "test"
        val amount = 1000

        //when
        val dto = HelloResponseDto(name = name, amount = amount)

        //then
        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}
