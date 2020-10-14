package ru.yandex.yandex_disk;

import help_services.AbstractPage;
import org.openqa.selenium.WebDriver;
import ru.yandex.yandex_disk.left_menu.LeftMenu;

public class YandexDiskPage extends AbstractPage {


    public YandexDiskPage(WebDriver driver) {
        super.driver = driver;
    }

    public LeftMenu goToLeftMenu() {
        return new LeftMenu(driver);
    }

}


