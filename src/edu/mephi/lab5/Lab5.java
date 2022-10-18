package edu.mephi.lab5;

import edu.mephi.lab5.classes.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.nio.charset.StandardCharsets;
import java.io.*;

class Lab5 {
    private static void printStart(Human[] people) {
        System.out.println("Choose one option:");
        System.out.println("0) exit");
        for (int i = 0; i < people.length; i++) {
            System.out.println((i + 1) + ") " + people[i].getName());
        }
    }

    private static void printHuman(Human human) {
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println(human.getName() + ": DCI = " + human.calc_dci());
        System.out.println(human.getName() + ": already eaten calory = " + human.getDayEatenCalory());
        System.out.println("-------------------------------");
        System.out.println("");
    }
    private static void printOptionHuman(Human human) {
        System.out.println("Choose one option for " + human.getName() + " :");
        System.out.println("0) go back");
        System.out.println("1) clear eaten for " + human.getName());
        System.out.println("2) show info about " + human.getName());
        System.out.println("3) add dish to " + human.getName());
    }
    private static void printOptionMenu(Human human, ArrayList<Food> lunch) {
        for (int i = 0; i < lunch.size(); i++) {
            System.out.println(i + ") add " + lunch.get(i).get_name());
        }
    }

    private static int firstLoop(Scanner in, Human[] people) {
        boolean flag = true;
        int     out = 0;
        int     num = 0;

        while (flag) {
            printStart(people);
            try {
              num = in.nextInt();
              if (num == 0) {
                  return 0;
              }
              try {
                  printHuman(people[num - 1]);
                  out = num;
                  flag = false;
              } catch (ArrayIndexOutOfBoundsException e) {
                  System.out.println("");
                  System.out.println("-------------------------------");
                  System.out.println("Wrong people number was choosen!");
                  System.out.println("-------------------------------");
                  System.out.println("");
                  in.nextLine();
              }
            } catch (InputMismatchException e) {
              System.out.println("");
              System.out.println("-------------------------------");
              System.out.println("Not a number!");
              System.out.println("-------------------------------");
              System.out.println("");
              in.nextLine();
            }
        }
        return out;
    }

    private static int newLoop(Scanner in, Human human) {
        boolean flag = true;
        int     out = 0;
        int     num = 0;

        while (flag) {
            printOptionHuman(human);
            try {
              num = in.nextInt();
              if (num == 0)
                  return 0;
              else if (num == 1)
                  human.setDayEatenCalory(0);
              else if (num == 2)
                  printHuman(human);
              else if (num == 3)
                  return 3;
              else {
                  System.out.println("");
                  System.out.println("-------------------------------");
                  System.out.println("Wrnog option numbernumber!");
                  System.out.println("-------------------------------");
                  System.out.println("");
              }
            } catch (InputMismatchException e) {
              System.out.println("");
              System.out.println("-------------------------------");
              System.out.println("Not a number!");
              System.out.println("-------------------------------");
              System.out.println("");
              printHuman(human);
              in.nextLine();
            }
        }
        return out;

    }
    private static int secondLoop(Scanner in, Human human, ArrayList<Food> lunch) {
        boolean flag = true;
        int     out = 0;
        int     num = 0;

        while (flag) {
            printOptionMenu(human, lunch);
            try {
              num = in.nextInt();
              try {
                  System.out.println("Input gr of " + lunch.get(num).get_name());
                  out = num;
                  return num;
              } catch (IndexOutOfBoundsException e) {
                  System.out.println("");
                  System.out.println("-------------------------------");
                  System.out.println("Wrong number was choosen!");
                  System.out.println("-------------------------------");
                  System.out.println("");
                  in.nextLine();
              }
            } catch (InputMismatchException e) {
              System.out.println("");
              System.out.println("-------------------------------");
              System.out.println("Not a number!");
              System.out.println("-------------------------------");
              System.out.println("");
              in.nextLine();
            }
        }
        return out;
    }

    private static int thirdLoop(Scanner in, Human human, Food food) {
        boolean flag = true;
        int     out = 0;
        double     num = 0;

        while (flag) {
            try {
                num = in.nextDouble();
                if (num <= 0)
                    throw new MyException("Not positive double!");
                human.addDayEatenCalory(food.calc_calory(num));
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("-------------------------------");
                System.out.println("Not a double number!");
                System.out.println("-------------------------------");
                System.out.println("");
                System.out.println("Input gr of " + food.get_name());
                in.nextLine();
            } catch (MyException e) {
                System.out.println("");
                System.out.println("-------------------------------");
                System.out.println("Not a positive number!");
                System.out.println("-------------------------------");
                System.out.println("");
                System.out.println("Input gr of " + food.get_name());
                in.nextLine();
            }
        }
        return out;
    }
    public static void main(String[] args) {
      Food[]  lunch = new Food[7];
      Human[] people = new Human[4];
      Scanner in = new Scanner(System.in);
      boolean   flag = true;
      boolean   flagfile = true;
      int   num = 0;
      int   num2 = 0;
      FileInputStream   fis = null;
      String test = "";

      try  {   
          fis = new FileInputStream("/Users/kirill/java/6lab/food.csv");
          InputStreamReader r = new InputStreamReader(fis, "cp1251");
          StringBuilder sb = new StringBuilder();
          int ch = r.read();
          while(ch >= 0) {
              sb.append((char) ch);
              ch = r.read();
          }
          test = sb.toString();
          flagfile = false;

      } catch (FileNotFoundException e) {
          System.out.println("File not found!\n BREAK");
      } catch (IOException e) {
          System.out.println("IO exception!\n BREAK");
      } finally {
          try {
              fis.close();
          } catch (IOException e)  {
              System.out.println("Can't close file");
          } catch (NullPointerException f) {
              System.out.println("Can't close file");
          }
      }

      if (flagfile)
          return;
      flagfile = true;
      ArrayList<Food> food = new ArrayList<>();
      String[] lines = test.split("\n");
      for (int i = 0; i < lines.length; i++) {
          String[] words = lines[i].split(";");
          try {
              food.add(new Food(words[0], Double.parseDouble(words[1]), Double.parseDouble(words[2]), Double.parseDouble(words[3])));
          } catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("Wrong input file format!\n BREAK");
              return;
          } catch (NumberFormatException e) {
              System.out.println("Wrong input file format!\n BREAK");
              return;
          }
      }
      people[0] = new Human("Nick", 182.0, 89.0, 25, true, 3);
      people[1] = new Human("Emma", 164.0, 48.0, 23, false, 1);
      people[2] = new Human("Sam", 191.0, 93.0, 30, true, 2);
      people[3] = new Human("Elena", 165.0, 50.0, 35, false, 4);

      for (int i = 0; i < people.length; i++) {
          people[i].setDayEatenCalory(2000);
      }


      int num3 = 0;
      boolean loopflag = true;
      while (flag) {
          if (loopflag) {
              num = firstLoop(in, people);
              loopflag = false;
          }
          if (num == 0)
              return;
          num2 = newLoop(in, people[num - 1]);
          if (num2 == 0) {
              loopflag = true;
              continue;
          }
          else 
              num3 = secondLoop(in, people[num - 1], food);
          thirdLoop(in, people[num - 1], food.get(num3));
      }
    }
}
