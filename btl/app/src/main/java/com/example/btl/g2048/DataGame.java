package com.example.btl.g2048;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import com.example.btl.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class DataGame {
    private static DataGame datagame;
    private ArrayList<Integer> arr = new ArrayList<>();
    private int[][] arr2D = new int[4][4];
    private int[] arrColor;
    private int[][] arrTemp = new int[4][4];
    private ArrayList<Integer> arrr = new ArrayList<>();
    private Stack<int[][]> save = new Stack<>();
    private Random r = new Random();

    static {
        datagame = new DataGame();
    }

    public static DataGame getDataGame() {
        return datagame;
    }

    public void intt(Context context) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr2D[i][j] = 0;
                //arrTemp[i][j] = 0;
                arr.add(0);
                //arrr.add(0);
            }
        }
        TypedArray ta = context.getResources().obtainTypedArray(R.array.backgroundOfNumber);
        arrColor = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            arrColor[i] = ta.getColor(i, 0);

        }
        ta.recycle();
        makeNumber();
        Change();
    }

    public ArrayList<Integer> getArr() {

        return arr;
    }


    public int color(int value) {
        if (value == 0) {
            return Color.WHITE;
        } else {
            int a = (int) (Math.log(value) / Math.log(2));
            return arrColor[a - 1];
        }
    }

    public void makeNumber() {


        int n0 = 0;
        int makeNumber;
        for (int i = 0; i < 16; i++) {
            if (arr.get(i) == 0) {
                n0++;
            }
        }

        if (n0 > 1) {
            makeNumber = r.nextInt(2) + 1;

        } else {
            if (n0 == 1) {
                makeNumber = 1;
            } else {
                makeNumber = 0;
            }
        }
        while (makeNumber != 0) {
            int i = r.nextInt(4);
            int j = r.nextInt(4);
            if (arr2D[i][j] == 0) {
                arr2D[i][j] = 2;
                makeNumber--;
            }
        }
        int[][] arrSave = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arr2D[i][j];
                arrSave[i][j] = value;
            }
        }
        save.push(arrSave);
    }

    public void Change() {

        arr.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr.add(arr2D[i][j]);
            }
        }
    }

    public void toLeft(int flag) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arr2D[i][j];
                if (value == 0) continue;
                else {
                    for (int k = j + 1; k < 4; k++) {
                        int nX = arr2D[i][k];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arr2D[i][j] = value * 2;
                                arr2D[i][k] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arr2D[i][j];
                if (value == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int n1 = arr2D[i][k];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arr2D[i][j] = arr2D[i][k];
                            arr2D[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        if (flag == 1) {

            makeNumber();
            Change();
        }

    }

    public void toRight(int flag) {

        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arr2D[i][j];
                if (value == 0) continue;
                else {
                    for (int k = j - 1; k >= 0; k--) {
                        int nX = arr2D[i][k];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arr2D[i][j] = value * 2;
                                arr2D[i][k] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arr2D[i][j];
                if (value == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int n1 = arr2D[i][k];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arr2D[i][j] = arr2D[i][k];
                            arr2D[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        if (flag == 1) {

            makeNumber();
            Change();

        }

    }

    public void toDown(int flag) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arr2D[j][i];
                if (value == 0) continue;
                else {
                    for (int k = j + 1; k < 4; k++) {
                        int nX = arr2D[k][i];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arr2D[j][i] = value * 2;
                                arr2D[k][i] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arr2D[j][i];
                if (value == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int n1 = arr2D[k][i];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arr2D[j][i] = arr2D[k][i];
                            arr2D[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        if (flag == 1) {

            makeNumber();
            Change();
        }

    }

    public void toUp(int flag) {

        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arr2D[j][i];
                if (value == 0) continue;
                else {
                    for (int k = j - 1; k >= 0; k--) {
                        int nX = arr2D[k][i];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arr2D[j][i] = value * 2;
                                arr2D[k][i] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arr2D[j][i];
                if (value == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int n1 = arr2D[k][i];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arr2D[j][i] = arr2D[k][i];
                            arr2D[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        if (flag == 1) {

            makeNumber();
            Change();

        }
    }

    //check Lose
    public void toLeftCheck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int a = arr2D[i][j];
                arrTemp[i][j] = a;
            }
        }


        //Arrays.copyOf
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arrTemp[i][j];
                if (value == 0) continue;
                else {
                    for (int k = j + 1; k < 4; k++) {
                        int nX = arrTemp[i][k];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arrTemp[i][j] = value * 2;
                                arrTemp[i][k] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arrTemp[i][j];
                if (value == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int n1 = arrTemp[i][k];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arrTemp[i][j] = arrTemp[i][k];
                            arrTemp[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }


    }

    public void toRightCheck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int a = arr2D[i][j];
                arrTemp[i][j] = a;
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arrTemp[i][j];
                if (value == 0) continue;
                else {
                    for (int k = j - 1; k >= 0; k--) {
                        int nY = arrTemp[i][k];
                        if (nY == 0) continue;
                        else {
                            if (nY == value) {
                                arrTemp[i][j] = value * 2;
                                arrTemp[i][k] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arrTemp[i][j];
                if (value == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int n1 = arrTemp[i][k];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arrTemp[i][j] = arrTemp[i][k];
                            arrTemp[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }

    }

    public void toDownCheck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int a = arr2D[i][j];
                arrTemp[i][j] = a;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arrTemp[j][i];
                if (value == 0) continue;
                else {
                    for (int k = j + 1; k < 4; k++) {
                        int nX = arrTemp[k][i];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arrTemp[j][i] = value * 2;
                                arrTemp[k][i] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = arrTemp[j][i];
                if (value == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int n1 = arrTemp[k][i];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arrTemp[j][i] = arrTemp[k][i];
                            arrTemp[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void toUpCheck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int a = arr2D[i][j];
                arrTemp[i][j] = a;
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arrTemp[j][i];
                if (value == 0) continue;
                else {
                    for (int k = j - 1; k >= 0; k--) {
                        int nX = arrTemp[k][i];
                        if (nX == 0) continue;
                        else {
                            if (nX == value) {
                                arrTemp[j][i] = value * 2;
                                arrTemp[k][i] = 0;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int value = arrTemp[j][i];
                if (value == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int n1 = arrTemp[k][i];
                        if (n1 == 0) {
                            continue;
                        } else {
                            arrTemp[j][i] = arrTemp[k][i];
                            arrTemp[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public int getMax() {
        int result = arr2D[0][0];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (result < arr2D[i][j]) result = arr2D[i][j];

            }
        }
        return result;
    }

    public double getPoints() {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += arr2D[i][j];

            }
        }
        return result;
    }

    public double getTotal(int[][] array) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += array[i][j];

            }
        }
        return result;
    }

    public boolean checkLose() {
        int count = 0;

        toLeftCheck();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arrTemp[i][j] != arr2D[i][j]) {
                    count++;
                    break;
                }
            }
        }

        toUpCheck();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arrTemp[i][j] != arr2D[i][j]) {
                    count++;
                    break;
                }
            }
        }
        toDownCheck();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arrTemp[i][j] != arr2D[i][j]) {
                    count++;
                    break;
                }
            }
        }
        toRightCheck();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arrTemp[i][j] != arr2D[i][j]) {
                    count++;
                    break;
                }
            }
        }
        if (count > 0) {
            return false;
        }


        return true;
    }

    public boolean undoData() {
        if(save.size() > 1){
            int[][] arrCheck = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int value = arr2D[i][j];
                    arrCheck[i][j] = value;
                }
            }
            if(getTotal(arr2D) == getTotal(arrCheck)){
                arr2D = save.pop();
            }
            arr2D = save.pop();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(arr2D[i][j] + " ");
                }
                System.out.println();
            }
            Change();
            return true;
        }
        return false;
    }


}
