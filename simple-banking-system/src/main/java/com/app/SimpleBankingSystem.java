package com.app;

import com.app.menu.MenuControl;
import com.app.menu.MenuImpl;
import com.app.menu.MenuInterface;

public class SimpleBankingSystem {
    public static void main(String[] args) {
        MenuInterface menu = new MenuImpl();
        MenuControl menuControl = new MenuControl(menu);
        menuControl.startMenu();
    }
}
