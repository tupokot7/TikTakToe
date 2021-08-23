package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //пустое поле
    public static final int FIELD_X = 10; //поле с крестиком
    public static final int FIELD_0 = 200; //поле с ноликом
    int[][] field; //объявляем наш массив игрового поля
    boolean isXturn;

    public TicTacToe() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3]; //выделяем память под массив при создании компонента
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD_EMPTY; //очищаем массив, заполняя его 0
            }
        }
        isXturn = true;
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) { //проверяем, что нажата левая клавиша
            int x = mouseEvent.getX(); //координата х клика
            int y = mouseEvent.getY(); //координата у клика
            //проверяем, что выбранная ячейка пуста и туда можно сходить
            int i = 0;
            int j = 0;
            if (field[i][j] == FIELD_EMPTY) {
                //проверка чей ход, если Х - ставим крестик, если 0 - ставим нолик
                field[i][j] = isXturn ? FIELD_X : FIELD_0;
                isXturn = !isXturn; //меняем флаг хода.
                repaint(); //перерисовка компонента, это вызовет метод paintComponent()
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //очищаем холст
        graphics.clearRect(0, 0, getWidth(), getHeight());
        //рисуем сетку из линий
        drawGrid(graphics);
        //рисуем текущие крестики и нолики
        drawX0(graphics);
    }

    void drawGrid(Graphics graphics) {
        int w = getWidth(); //ширина игрового поля
        int h = getHeight(); //высота игрового поля
        int dw = w / 3; //делим ширину на 3 - получаем ширину одной ячейки
        int dh = h / 3; // тоже, только с высотой
        graphics.setColor(Color.BLUE); //цвет линий
        for (int i = 1; i < 3; i++) { //i пробегает значения от 1 до 2 включительно (при 1=3) выход из цикла
            graphics.drawLine(0, dh * i, w, dh * i); //горизонтальная линия
            graphics.drawLine(dw * i, 0, dw * i, h); //вертикальная линия
        }
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        //линия от верхнего левого угла в правый нижний
        graphics.drawLine(x, y, x + dw, y + dh);
        //линия от левого нижнего угла до правого верхнего
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        //магия с непонятным умножением и делением для того, чтобы нолик был чуть вытянут по вертикали и не касался боковых "стенок" ячейки
        graphics.drawOval(x + 5 * dw / 100, y, dw * 9 / 10, dh);
    }

    void drawX0(Graphics graphics) {
        //вложенные циклы
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                //если в данной ячейке крестик - рисуем его
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                    //то же для нолика
                } else if (field[i][j] == FIELD_0) {
                    draw0(i, j, graphics);
                }
            }
        }
    }
}
