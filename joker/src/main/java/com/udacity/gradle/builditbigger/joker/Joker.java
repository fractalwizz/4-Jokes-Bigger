package com.udacity.gradle.builditbigger.joker;

import java.util.Random;

public class Joker {
    public String getJoke() {
        Random rand = new Random();
        int id = rand.nextInt(10);

        switch(id) {
            case 0:
                return "If at first you don't succeed, call it version 1.0.";
            case 1:
                return "I used to think maths was useless, but then one day I realised that decimals had a point.";
            case 2:
                return "I know of no one who is happily married, except my husband.";
            case 3:
                return "There are 2 types of people in the world. Those that can extrapolate from incomplete data...";
            case 4:
                return "The 21st century: Deleting history is more important than making it.";
            case 5:
                return "I am a nobody, nobody is perfect; therefore I am perfect.";
            case 6:
                return "When the President pushes the big red button, Chuck Norris's cell phone rings.";
            case 7:
                return "A dyslexic man walks into a bra...";
            case 8:
                return "I'm on a whiskey diet. I've lost three days already!";
            case 9:
                return "I love pressing F5. It's so refreshing.";
        }

        return "HERP DERP";
    }
}