package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MioThread extends Thread {

    Socket s;
    String str;

    public MioThread(Socket s, String str) {
        this.s = s;
        this.str = str;
    }

    public void run() {
        do {

            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                str = in.readLine();

                if (str == null) {
                    break;
                }
                if (str.equals("exit")) {
                    s.close();
                    return;
                }

                String[] parti = str.split(" ");

                String op = parti[0];
                double n1 = Double.parseDouble(parti[1]);
                double n2 = Double.parseDouble(parti[2]);

                double risultato = 0;

                switch (op) {
                    case "1":
                        risultato = n1 + n2;
                        break;
                    case "2":
                        risultato = n1 - n2;
                        break;
                    case "3":
                        risultato = n1 * n2;
                        break;
                    case "4":
                        if (n2 == 0) {
                            out.println("E0:INVALID");
                            break;
                        }
                        risultato = n1 / n2;
                        break;
                    case "5":
                        risultato = Math.pow(n1, n2);
                        break;
                    case "6":
                        risultato = Math.sqrt(n1);
                        break;
                    case "7":
                        risultato = Math.round(n1);
                        break;
                    default:
                        break;
                }

                out.println("RIS: " + risultato);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
