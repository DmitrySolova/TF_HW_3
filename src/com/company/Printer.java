package com.company;

import java.util.TimerTask;

public class Printer {
    int printTime = 60; // время печати (в секундах)
    int paper = 0; // количество бумаги в принтере (в условных единицах)
    int ink = 0; // количество чернил в принтере (в условных единицах)
    boolean isLoaded = false; // есть ли в принтере как минимум 1 ед. чернил и 1 ед. бумаги?
    boolean printing = false; // на данным момент принтер печатает?
    int numberOfPagesPrinted = 0; // количество страниц, распечатанных принтером (счетчик)


    void fillPaper(int paper){ // загрузка бумаги в принтер
        if (this.printing == true & paper == 1) this.paper++;
        if (this.printing == true) {
            System.out.println("Вы можете заправить только 1 ед. бумаги во время печати принтера! Для загрузки большего количества бумаги дождитесь окончания печати");
        } else {
            this.paper = this.paper + paper;
        }
    }

    void fillInk(int ink){ // загрузка чернил в принтер
        if (this.printing == true) {
            System.out.println("Вы не можете заправить чернила во время печати принтера! Дождитесь окончания печати");
        } else {
            this.ink = this.ink + ink;
        }
    }

    void loadCheck(int numberOfPages){ // проверка наличия бумаги и чернил в принтере
        if (this.paper < numberOfPages){
            System.out.println("Заправьте " + (numberOfPages - this.paper) + " ед. бумаги для начала печати!");
            this.isLoaded = false;
        }
        if (this.ink < numberOfPages){
            System.out.println("Заправьте " + (numberOfPages - this.ink) + " ед. чернил для начала печати!");
            this.isLoaded = false;
        }
        if (this.paper >= numberOfPages & this.ink >= numberOfPages) this.isLoaded = true;
    }


    int print(int numberOfPages){
        if (this.printing == false){ // проверка, находится ли принтер в состоянии печати
            this.loadCheck(numberOfPages);
            if (this.isLoaded == true){
                this.printing = true; // принтер начинает печать

                for(int time = 0; time <= this.printTime; time++){ // псевдо-таймер
                    System.out.println("Печать завершиться через " + (this.printTime - time) + " секунд");
                    if (time == this.printTime) {
                        System.out.println("Печать завершена!");
                    }
                }

                this.numberOfPagesPrinted = numberOfPages; // добавляем в счетчик значение распечатанных страниц
                this.printing = false; // принтер заканчивает печать
                return numberOfPages; // возвращаем число распечатанных страниц
            }
        }
        return 0;
    }

    Printer(int printTime, int paper, int ink){
        this.printTime = printTime;
        this.paper = paper;
        this.ink = ink;
    }
}
