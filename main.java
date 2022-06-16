// Travis Waterman
// Oct 4th, 2021 - Oct 20th, 2021
// 2048.java
// an attempt at a replication of the game '2048'

//import java.util.*;
// above is not ideal, try and figure out what is used

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Random randomizer = new Random();

    HashMap<String,Integer> inputtable = new   HashMap<String,Integer>();
    inputtable.put("left",0);
    inputtable.put("up",1);
    inputtable.put("right",2);
    inputtable.put("down",3);

    int scale = 4; // the scale of the board (X by Y)
    int initval = 2; // the value added to new numbers
    //int goal = 2048; // the winning value


    // initial intro blob of text to let users assign preferences
    System.out.println("Welcome to 2048.java!");
    System.out.println("Please set your board size ("+ scale +" is default):");
    scale = input.nextInt();
    System.out.println("Board size has been set to "+ scale);
    System.out.println("Please set the value of spawing numbers (" + initval + " is default):");
    initval = input.nextInt();
    System.out.println("Number Value has been set to " + initval);
    //System.out.println("Please set the goal value (" + goal + " is default):");
    //goal = input.nextInt();
    //System.out.println("Goal value has been set to " + goal);
    System.out.println("Good Luck!");

    // creates board, fills with zeroes
    int[][] board = new int[scale][scale];
    for (int i1=0; i1<(scale^2); i1++) {
      board[(i1 / scale)][i1 % scale] = 0;
    }
    // generates starting numbers
    int random;
    for (int i2= 0; i2<scale/scale; i2++) {
        random = randomizer.nextInt(scale*scale);
        while (board[random / scale][random % scale] != 0){
          random = randomizer.nextInt(scale*scale);
        }
        board[random / scale][random % scale] = initval;
      //System.out.println(random);
    }
    // prints board formatted properly
    for (int i3=0; i3<scale; i3++) {
      System.out.println(Arrays.toString(board[i3]));
    }

    // game loop
    while (true) { // initial input parser
      String pressed = input.next();
      int output = inputtable.containsKey(pressed) ? inputtable.get(pressed) : (5);
      //System.out.println(output + " = " + pressed);
      if (output != 5) { // if input isn't invalid


        if (output == 0) { // left
          for (int i4=0;i4<scale;i4++) { 
            int valStore = 0;
            boolean mergedone = false;

            for(int i5=0;i5<scale;i5++) { // pulls non-zero to end
              if (board[i4][i5] != 0) {
                board[i4][valStore] = board[i4][i5];
                valStore++;
              }
            }
            for (int i6=valStore;i6<scale;i6++) { // rewrites zeroes at end
              board[i4][i6] = 0;
            }
            valStore = 0;
            for (int i7=0;i7<scale-1;i7++) { // find numbers next to each other, merge them
              if (mergedone == false && board[i4][i7] != 0 && board[i4][i7] == board[i4][i7 + 1]) {
                board[i4][i7] *= 2;
                System.out.println(board[i4][i7] + " at " + i4 + ", " + i7);
                board[i4][i7 + 1] = 0;
                mergedone = true;

                for(int i5=0;i5<scale;i5++) { // re-shift the board
                  if (board[i4][i5] != 0) {
                    board[i4][valStore] = board[i4][i5];
                    valStore++;
                  }
                }
                for (int i6=valStore;i6<scale;i6++) {
                  board[i4][i6] = 0;
                }
              }
            }
          }
        } 


       else if (output == 1) { // up
          for (int i4=0;i4<scale;i4++) { 
            int valStore = 0;
            boolean mergedone = false;

            for(int i5=0;i5<scale;i5++) { // pulls non-zero to end
              if (board[i5][i4] != 0) {
                board[valStore][i4] = board[i5][i4];
                valStore++;
              }
            }
            for (int i6=valStore;i6<scale;i6++) { // rewrites zeroes at end
              board[i6][i4] = 0;
            }
            valStore = 0;

            for (int i7=0;i7<scale-1;i7++) { // find numbers next to each other, merge them
              if (mergedone == false && board[i7][i4] != 0 && board[i7][i4] == board[i7 + 1][i4]) {
                board[i7][i4] *= 2;
                System.out.println(board[i7][i4] + " at " + i7 + ", " + i4);
                board[i7 + 1][i4] = 0;
                mergedone = true;

                for(int i5=0;i5<scale;i5++) { // re-shift the board
                  if (board[i5][i4] != 0) {
                    board[valStore][i4] = board[i5][i4];
                    valStore++;
                  }
                }
                for (int i6=valStore;i6<scale;i6++) {
                  board[i6][i4] = 0;
                }
              }
            }
          }
        }


       else if (output == 2) { // right
          for (int i4=scale-1;i4>-1;i4--) {
            int valStore = scale-1;
            boolean mergedone = false;

            for(int i5=scale-1;i5>-1;i5--) { // moves non-zero numbers to end
              if (board[i4][i5] != 0) {
                board[i4][valStore] = board[i4][i5];
                valStore--;
              }
            }
            for (int i6=valStore;i6>-1;i6--) { // rewrites zeroes to end
              board[i4][i6] = 0;
            }
            valStore = scale-1;

            for (int i7=scale-1;i7>0;i7--) { // finds numbers next to each other, merge them
              if (mergedone == false && board[i4][i7] != 0 && board[i4][i7] == board[i4][i7 - 1]) {
                board[i4][i7] *= 2;
                System.out.println(board[i4][i7] + " at " + i4 + ", " + i7);
                board[i4][i7 - 1] = 0;
                mergedone = true;

                for(int i5=scale-1;i5>-1;i5--) { // re-shifts the board
                  if (board[i4][i5] != 0) {
                    board[i4][valStore] = board[i4][i5];
                    valStore--;
                  }
                }
                for (int i6=valStore;i6>-1;i6--) {
                  board[i4][i6] = 0;
                }
              }
            }
          }
        }


       else if (output == 3) { // down
          for (int i4=scale-1;i4>-1;i4--) {
            int valStore = scale-1;
            boolean mergedone = false;

            for(int i5=scale-1;i5>-1;i5--) { // pulls non-zeroes to end
              if (board[i5][i4] != 0) {
                board[valStore][i4] = board[i5][i4];
                valStore--;
              }
            }
            for (int i6=valStore;i6>-1;i6--) { // rewrites zeroes at end
              board[i6][i4] = 0;
            }
            valStore = scale-1;

            for (int i7=scale-1;i7>0;i7--) { // finds numbers next to each other, merges them
              if (mergedone == false && board[i7][i4] != 0 && board[i7][i4] == board[i7 - 1][i4]) {
                board[i7][i4] *= 2;
                System.out.println(board[i7][i4] + " at " + i7 + ", " + i4);
                board[i7 - 1][i4] = 0;
                mergedone = true;

                for(int i5=scale-1;i5>-1;i5--) { // re-shifts the board
                  if (board[i5][i4] != 0) {
                    board[valStore][i4] = board[i5][i4];
                    valStore--;
                  }
                }
                for (int i6=valStore;i6>-1;i6--) {
                  board[i6][i4] = 0;
                }
              }
            }
          }
       }
        // adds new random number
        random = randomizer.nextInt(scale*scale);
        while (board[random / scale][random % scale] != 0){
          random = randomizer.nextInt(scale*scale);
        }
        board[random / scale][random % scale] = initval;
      
       // print board
        for (int i7=0; i7<scale; i7++) {
          System.out.println(Arrays.toString(board[i7]));
        }
      }
    }
  }
}
