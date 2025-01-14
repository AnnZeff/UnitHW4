package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryFormTest {

    private String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shoudlBookCardDelivery() {
        String planningDate = generateDate(3, "dd.MM.yyyy");
        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] > span > span.input__box > input").setValue("Ставрополь");
        $("[data-test-id='date'] > span > span > span > span.input__box > input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] > span > span > span > span.input__box > input").setValue(planningDate);
        $("[data-test-id='name'] > span > span.input__box > input").setValue("Петров Иван");
        $("[data-test-id='phone'] > span > span.input__box > input").setValue("+71234567891");
        $("[data-test-id='agreement']").click();
        $("[role='button'].button").click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));

    }
}
