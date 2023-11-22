package rmiClient;



import java.rmi.Naming;
import java.util.Scanner;

import metier.Compte;
import rmiService.IBanque;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            IBanque stub = (IBanque) Naming.lookup("rmi://localhost:1099/BanqueService");
            Scanner sc = new Scanner(System.in);
            String rep;

            do {
                System.out.print("choisissez l'une des options suivantes : \n"
                        + "1 - Créer un compte \n"
                        + "2 - Obtenir des informations sur le compte ");
                int choix = sc.nextInt();

                if (choix == 1) {
                    System.out.print("Donnez votre code : ");
                    int code = sc.nextInt();
                    System.out.print("Quel est le solde que vous voulez ajouter à votre compte : ");
                    double solde = sc.nextDouble();
                    Compte c = new Compte(code, solde);
                    System.out.println(stub.creerCompte(c));
                }

                if (choix == 2) {
                    System.out.println("Donnez le code de votre compte : ");
                    int code = sc.nextInt();
                    System.out.println(stub.getInfoCompte(code));
                }

                System.out.print("Voulez-vous continuer ? (Y) ou (N) : ");
                rep = sc.next();

            } while (rep.equals("Y") || !rep.equals("N"));
            System.out.print(" ||||||||||||| FIN ||||||||||");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}