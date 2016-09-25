/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automate;

import java.util.Scanner;

/**
 *
 * @author l.IsSaM.l
 */
public class AUTOMATE {

    public static void Remplir(Integer[][] MAT, int ligne, int colonne) {
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                MAT[i][j] = -1;
            }
        }

    }

    public static void Init_Ope(Integer[][] MAT) {
        Remplir(MAT, 6, 255);
        //MAT[0][0] = 1;
        //MAT[1][(int) '='] = 2;
        for (int i = (int) '0'; i <= (int) '9'; i++) {
            MAT[2][i] = 3;
            MAT[3][i] = 3;
            MAT[4][i] = 4;
        }
        MAT[4][(int) ';'] = 5;
        char[] tab = {'*', '+', '-', '%', '/'};
        for (int i = 0; i < tab.length; i++) {
            MAT[3][(int) tab[i]] = 4;
        }
    }

    public static boolean isArith(String Str) {
        //  System.out.println(" Str.indexOf('=') < 0 "+Str.indexOf('='));
        if (Str.indexOf('=') < 0) {
            return false;
        }
        String Id = Str.substring(0, Str.lastIndexOf('='));
        //   System.out.println("Id = "+Id);
        if (Id.indexOf('=') >= 0) {
            return false;
        }
        //  System.out.println(" Id.indexOf('=') < 0 "+Id.indexOf('='));
        if (!Identificator(Id)) {
            return false;
        }
        Integer[][] MAT = new Integer[6][255];
        Init_Ope(MAT);
        String newS = Str.substring(Str.indexOf('=') + 1);
        int ETAT = 2;
        int i = 0;
        while (i < newS.length() && ETAT != -1) {
            ETAT = MAT[ETAT][newS.charAt(i)];
            i++;
        }
        if (ETAT == -1 || ETAT != 5 || newS.length() == 0) {
            return false;
        }
        return true;

    }

    public static void Init_ID(Integer[][] Trans) {

        Remplir(Trans, 2, 256);
        int j = 0;
        while (j < 2) {
            Trans[j][(int) '_'] = 1;
            for (int i = (int) 'a'; i <= (int) 'z'; i++) {
                Trans[j][i] = 1;
            }
            for (int i = 65; i <= 90; i++) {
                Trans[j][i] = 1;
            }
            if (j == 1) {
                for (int i = 48; i <= 59; i++) {
                    Trans[j][i] = 1;
                }
            }
            j++;
        }
    }

    public static boolean Identificator(String Id) {
        Integer[][] MAT = new Integer[2][256];
        Init_ID(MAT);
        //    boolean isTrue = false;
        int ETAT = 0;
        int i = 0;
        while (i < Id.length() && ETAT != -1) {
            ETAT = MAT[ETAT][(int) Id.charAt(i)];
            i++;
        }
        if (ETAT == -1 || Id.length() == 0) {
            return false;
        }

        return true;
    }

    public static void Init_Nbre(Integer[][] MAT) {
        Remplir(MAT, 4, 256);
        for (int i = (int) '0'; i <= (int) '9'; i++) {
            MAT[0][i] = 1;
            MAT[1][i] = 1;
            MAT[2][i] = 3;
            MAT[3][i] = 3;
        }
        MAT[1][(int) '.'] = 2;
    }

    public static boolean isNombre(String Nbre) {
        Integer[][] MAT = new Integer[4][256];
        Init_Nbre(MAT);
        int i = 0;
        int ETAT = 0;
        while (i < Nbre.length() && ETAT != -1) {
            ETAT = MAT[ETAT][(int) Nbre.charAt(i)];
            i++;
        }
        if (ETAT == 1 || ETAT == 3) {
            return true;
        }
        return false;
    }

    public static void Init_Comp_C(Integer[][] MAT) {
        Remplir(MAT, 6, 255);
        MAT[0][0] = 1;
        MAT[1][(int) '='] = 2;
        MAT[2][1] = 3;
        MAT[2][0] = 4;
        MAT[3][(int) ';'] = 5;
        MAT[4][(int) ';'] = 5;
        char[] tab = {'*', '+', '-', '%', '/'};
        for (int i = 0; i < tab.length; i++) {
            MAT[3][(int) tab[i]] = 2;
            MAT[4][(int) tab[i]] = 2;
        }

    }

    public static int Nature(String Str) {
        return isNombre(Str) ? 1 : Identificator(Str) ? 0 : -1;
    }

    public static int Index_Of_Op(String newSt) {
        int i = 0;
        while (i < newSt.length() && newSt.charAt(i) != '*' && newSt.charAt(i) != '/' && newSt.charAt(i) != '%' && newSt.charAt(i) != '+' && newSt.charAt(i) != '-') {
            i++;
        }
        if (i == newSt.length()) {
            return -1;
        }
        return i;
    }

    public static boolean isArithmetic(String Str) {

        if (Str.indexOf('=') < 0) {
            return false;
        }
        System.out.println("Str.indexOf('=') < 0" + Str.indexOf('='));
        String Id = Str.substring(0, Str.lastIndexOf('='));
        System.out.println("Id=" + Id);
        if (Id.indexOf('=') >= 0) {
            return false;
        }

        if (!Identificator(Id)) {
            return false;
        }
        Integer[][] MAT = new Integer[6][256];
        Init_Comp_C(MAT);
        String newS = Str.substring(Str.indexOf('=') + 1);
        System.out.println("newS=" + newS);
        int ETAT = 2;
        int i = 0;
        String Dinamic_Str = new String(newS);

        System.out.println("Dinamic_Str=" + Dinamic_Str);
        int depart = 0;
        while (i < newS.length() && ETAT != -1 && ETAT!=5) {
            if (newS.charAt(i) == ';') {
                ETAT = MAT[ETAT][(int) ';'];
            } else {

                int Ind_Op = Index_Of_Op(Dinamic_Str);
                System.out.println("Ind_Op=" + Ind_Op);
                if (Ind_Op == -1) {
                    if(Dinamic_Str.length() == 0) return false ;
                    if (Nature(Dinamic_Str.substring(0, Dinamic_Str.length()-1)) >= 0) {
                     //   System.out.println("Dinamic +++"+Dinamic_Str.substring(0, Dinamic_Str.length()-1));
                      //  ETAT = MAT[ETAT][Nature(Dinamic_Str.substring(0, Dinamic_Str.length() - 1))];
                        if(Dinamic_Str.contains(";")) return true;
                        else return false ;
                      
                    } 
                    }
                 else {
                    System.out.println("Ind_Op=" + Ind_Op);
                    String My_Str = Dinamic_Str.substring(0, Ind_Op);
                    System.out.println("My_Str=" + My_Str);
                    int Nature = Nature(My_Str);
                    System.out.println("Nature =  " + Nature);
                    if (Nature == -1) {
                        return false;
                    }
                    depart = Ind_Op + 1;
                    System.out.println(" depart =  " + depart);
                    Dinamic_Str = Dinamic_Str.substring(depart);
                    System.out.println("Dinamic_Str=" + Dinamic_Str);
                    ETAT = MAT[ETAT][Nature];
                    ETAT = MAT[ETAT][(int) '+'];

                }
            }
            i++;
        }
        if (ETAT == 5 && i==newS.length()) {
            return true;
        }

        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*       if(Identificator("z")) System.out.println(" VRAI \n");
         else System.out.println(" FALSE \n");
         System.out.println((int)'1');*/
        Scanner out = new Scanner(System.in);
        do {
            System.out.println("Donnez une op ");
            String Op = out.nextLine();
            if (isArithmetic(Op)) {
                System.out.println(" VALIDE ");
            } else {
                System.out.println(" NON VALIDE ");
            }

        } while (true);

        /*  int i = Index_Of_Op("dkzddzdzdz");
         System.out.println(Nature("545_154"));*/
    }

}
