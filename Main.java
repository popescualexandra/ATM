import java.util.*;

public class Main{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Client c1 = new Client("Popescu", "Ion", 523.5, "8536475986", "0000", true);
        Client c2 = new Client("Marinescu", "Mihai", 200.7, "5472395684", "1569", false);
        Client c3 = new Client("Pop", "Mircea", 4896, "5986324859", "5875", true);
        Client c4 = new Client("Cub", "Elena", 123, "4586932450", "5566", true);
        Client c5 = new Client("Petra", "Maria", 6698, "4478523956", "9999", false);
        Client corect = new Client();
        ArrayList<Client> bazaDeDate = new ArrayList<>();
        bazaDeDate.add(c1);
        bazaDeDate.add(c2);
        bazaDeDate.add(c3);
        bazaDeDate.add(c4);
        bazaDeDate.add(c5);
        int ok = 0;
        int ok1 = 0;
        int ok2;
        boolean isDone = true;
        while(isDone){
            while(ok == 0){
                System.out.print("Introduceti ID-ul: ");
                String id = sc.nextLine();
                for(Client c : bazaDeDate){
                    if(c.verificareId(id)){
                        ok = 1;
                        corect = c;
                        System.out.print("ID corect. Introdu PIN-ul: ");
                        break;
                    }else{
                        ok = 0;
                    }
                }
                if(ok == 0){
                    System.out.println("ID-ul este incorect sau nu se afla in baza de date.");
                }
            }
            while(ok1 == 0){
                String pin = sc.nextLine();
                if(corect.verificarePin(pin)){
                    ok1 = 1;
                    System.out.println("PIN corect.\n\nBuna " + corect.nume + " " + corect.prenume + ".\n");
                }else{
                    ok1 = 0;
                }
                if(ok1 == 0){
                    System.out.print("PIN gresit. Introdu PIN-ul: ");
                }
            }
            System.out.println(corect.alegeOperatiune());
            String comanda = sc.nextLine();
            switch(comanda){
                case "SCHIMBARE PIN":
                    System.out.print("Ai ales SCHIMBARE PIN.\nIntrodu PIN vechi: ");
                    ok = 0;
                    while(ok == 0){
                        String pin = sc.nextLine();
                        if(corect.verificarePin(pin)){
                            ok = 1;
                            System.out.print("PIN vechi corect. Introdu PIN nou: ");
                            String pinNou = sc.nextLine();
                            corect.schimbarePin(pinNou);
                        }else{
                            ok = 0;
                        }
                        if(ok == 0){
                            System.out.print("PIN vechi gresit. Introdu PIN vechi: ");
                        }
                    }
                    break;
                case "INTEROGARE SOLD":
                    System.out.print("Ai ales INTEROGARE SOLD.\n");
                    System.out.println(corect.interogareSold());
                    break;
                case "EXTRAGERE NUMERAR":
                    System.out.print(String.format("Ai ales EXTRADGERE NUMERAR.\nSold curent: %.1f lei.\nIntroduceti suma dorita: "
                                ,corect.sold));
                    double suma = Double.parseDouble(sc.nextLine());
                    corect.extragereNumerar(suma);
                    break;
                case "DETALII CONT":
                    System.out.print("Ai ales DETALII CONT.\n");
                    corect.detaliiCont();
                    break;
                case "TRANSFER BANCAR":
                    System.out.print(String.format("Ai ales TRANSFER BANCAR.\nSold curent: %.1f lei.\nSuma de transfer: ", corect.sold));
                    double sumaTransfer = Double.parseDouble(sc.nextLine());
                    ok2 = 0;
                    while(ok2 == 0){
                        System.out.print("Introdu ID-ul persoanei carei ii faci transferul: ");
                        String idTransfer = sc.nextLine();
                        for(Client c : bazaDeDate){
                            if(c.verificareId(idTransfer)){
                                ok2 = 1;
                                System.out.print("\nDetalii persoana care face transferul: \n");
                                if(corect.extragereNumerar(sumaTransfer) != corect.sold){
                                    System.out.print("Detalii persoana careia i se face transferul:");
                                    c.transferBani(idTransfer, sumaTransfer);
                                }
                                break;
                            }else{
                                ok2 = 0;
                            }
                        }
                        if(ok2 == 0){
                            System.out.println("ID-ul este incorect sau nu se afla in baza de date.");
                        }
                    }
                    
                    break;
                case "IESIRE":
                    System.out.print("Ai ales IESIRE");
                    isDone = false;
                    break;
                default:
                    System.out.println("Comanda gresita!\n");
            }
        }
    }
}