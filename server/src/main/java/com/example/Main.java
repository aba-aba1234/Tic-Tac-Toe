package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int risultato = 0;
        ServerSocket ss = new ServerSocket(3000);
        Socket s1 = ss.accept();
        System.out.println("Connected 1.");
        BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
        PrintWriter out = new PrintWriter(s1.getOutputStream(), true);
        out.println("WAIT");
        Socket s2 = ss.accept();
        System.out.println("Connected all.");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);

        out.println("READY");
        out2.println("READY");

        boolean vittoria = false;
        boolean pareggio = false;

        int turno = 1;

        int[] array = new int[9];

        do {
            PrintWriter outGiocatore;
            BufferedReader inGiocatore;

            String num = in.readLine();
            int n = Integer.parseInt(num);

            System.out.println("il client ha inviato => " + n);
            if (n >= 0 && n < 9 && array[n] == 0) {

                if (turno == 1) {
                    out.println("OK");
                    array[n] = 1;
                }
                if (turno == 2) {
                    out2.println("OK");
                    array[n] = 2;
                }
                for (int i = 0; i < 9; i++) {
                    if (turno == 1) {
                        out2.println(array[i]);
                    }
                    if (turno == 2) {
                        out.println(array[i]);
                    }

                }
            }
            if (array[n] != 0) {
                out.println("KO");
            }
            if (array[0] == 1 && array[1] == 1 && array[2] == 1 ||
                    array[3] == 1 && array[4] == 1 && array[5] == 1 ||
                    array[6] == 1 && array[7] == 1 && array[8] == 1 ||
                    array[0] == 1 && array[3] == 1 && array[6] == 1 ||
                    array[1] == 1 && array[4] == 1 && array[7] == 1 ||
                    array[2] == 1 && array[5] == 1 && array[8] == 1 ||
                    array[0] == 1 && array[4] == 1 && array[8] == 1 ||
                    array[2] == 1 && array[4] == 1 && array[6] == 1) {
                for (int i = 0; i < 9; i++) {
                    out2.println(array[i]);
                }
                out.println("W");
                out2.println("L");
                vittoria = true;
            }
            if (array[0] == 2 && array[1] == 2 && array[2] == 2 ||
                    array[3] == 2 && array[4] == 2 && array[5] == 2 ||
                    array[6] == 2 && array[7] == 2 && array[8] == 2 ||
                    array[0] == 2 && array[3] == 2 && array[6] == 2 ||
                    array[1] == 2 && array[4] == 2 && array[7] == 2 ||
                    array[2] == 2 && array[5] == 2 && array[8] == 2 ||
                    array[0] == 2 && array[4] == 2 && array[8] == 2 ||
                    array[2] == 2 && array[4] == 2 && array[6] == 2) {
                for (int i = 0; i < 9; i++) {
                    out.println(array[i]);
                }
                out.println("L");
                out2.println("w");
                vittoria = true;
            }
            boolean piena = true;
            for (int i = 0; i < 9; i++) {
                if (array[i] == 0) {
                    piena = false;
                    break;
                }
            }
            if (piena) {
                out.println("P");
                out2.println("P");
                pareggio = true;
            }

            turno = (turno == 1) ? 2 : 1;
        } while (pareggio = true || vittoria == true);

    }
}