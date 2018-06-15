package com.joantolos.kata.rate.calculator.ui;

public class Console {

    public void prompt(){
        this.print("                 __                                          ");
        this.print("   _____ ____ _ / /_ ___                                     ");
        this.print("  / ___// __ `// __// _ \\                                   ");
        this.print(" / /   / /_/ // /_ /  __/                                    ");
        this.print("/_/    \\__,_/ \\__/ \\___/                                  ");
        this.print("                __              __        __                 ");
        this.print("  _____ ____ _ / /_____ __  __ / /____ _ / /_ ____   _____   ");
        this.print(" / ___// __ `// // ___// / / // // __ `// __// __ \\ / ___/  ");
        this.print("/ /__ / /_/ // // /__ / /_/ // // /_/ // /_ / /_/ // /       ");
        this.print("\\___/ \\__,_//_/ \\___/ \\__,_//_/ \\__,_/ \\__/ \\____//_/ ");
    }

    public void exit() {
        this.print("\nBye!\n\n");
    }

    public void print(String toPrint){
        System.out.println(toPrint);
    }

}
