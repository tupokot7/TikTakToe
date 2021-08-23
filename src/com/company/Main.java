package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Запускаем игру...");                       //Это тупо для консоли
        JFrame window = new JFrame("TikTakToe");                   //наше главное окно
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //добавим кнопку Х, закрывающую окно
        window.setSize(400, 400);                          //размер окна
        window.setLayout(new BorderLayout());                         //менеджер компановки
        window.setLocationRelativeTo(null);                          //чтобы окно было по центру экрана
        window.setVisible(true);                                    //включаем видимость окна
        TicTacToe game = new TicTacToe();
        window.add(game);
        System.out.println("Конец...");                          //Это тоже тупо для консоли
    }
}
