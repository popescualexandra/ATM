import java.util.*;

public class Client{
    String nume;
    String prenume;
    double sold;
    String id;
    String pin;
    boolean banca;
    char ch;
    int ok = 1;
    
    Client(){
    }

    Client(String nume, String prenume, double sold, String id, String pin, boolean banca){
        this.nume = nume;
        this.prenume = prenume;
        this.sold = sold;
        this.id = id;
        this.pin = pin;
        this.banca = banca;
    }
    
    public String alegeOperatiune(){
        return "Ce operatiune alegi? (scrie una din urmatoarele optiuni):\n   SCHIMBARE PIN\n   INTEROGARE SOLD\n" + 
                            "   EXTRAGERE NUMERAR\n   DETALII CONT\n   TRANSFER BANCAR\n   IESIRE\n";
    }

    public boolean verificareId(String id){
        if(this.id.equals(id)){
            return true;
        }else{
            return false;
        }
    }

    public boolean verificarePin(String pin){
        if(this.pin.equals(pin)){
            return true;
        }else{
            return false;
        }
    }
    
    public void schimbarePin(String pinNou){
        char ch;
        int ok = 0;
        for(int i=0 ; i<pinNou.length() ; i++){
            ch = pinNou.charAt(i);
            if(Character.isDigit(ch) && pinNou.length() == 4){
                this.pin = pinNou;
                ok = 1;
            }else{
                ok = 0;
            }
        }
        if(ok == 1){
            System.out.print(String.format("Noul pin este setat: " + this.pin + ".\n\n"));
        }else{
            System.out.println(String.format("PIN-ul nu a fost schimbat, trebuie sa aiba 4 cifre.\n"));
        }
    }

    public String interogareSold(){
        return String.format("In prezent aveti in cont %.1f lei.\n", this.sold);
    }
    
    public void detaliiCont(){
        System.out.print(String.format("  Nume: %s\n  Prenume: %s\n  Sold: %.1f lei\n  ID: %s\n  PIN: %s\n", this.nume , this.prenume , 
                    this.sold, this.id, this.pin));
        if(this.banca == true){
            System.out.println("  De la aceasta banca: DA\n");
        }else{
            System.out.println("  De la aceasta banca: NU\n");
        }
    }
    
    public double extragereNumerar(double suma){
        double soldVechi = this.sold;
        if(this.banca == false){
            double comision = 0.02 * suma;
            if(suma + comision <= this.sold){
                this.sold = this.sold - suma - comision;
                System.out.println(String.format("Sold vechi: %.1f lei.\nAti extras suma de %.1f lei.\nComision: %.1f lei\nSold nou: %.1f lei.\n"
                            , soldVechi, suma, comision, this.sold));
            }else{
                System.out.println("Nu exista suficiente fonduri.\n");
            }   
        }else{
            if(suma <= this.sold) {
                this.sold = this.sold - suma;
                System.out.println(String.format("Sold vechi: %.1f lei.\nAti extras suma de %.1f lei.\nComision: 0 lei\nSold nou: %.1f lei.\n"
                            , soldVechi, suma, this.sold));
            }else{
                System.out.println("Nu exista suficiente fonduri.\n");
            }   
        }
        return soldVechi;
    }
    
    public void transferBani(String id, double suma){
        int ok = 0;
        if(this.id.equals(id)){
            ok = 1;
            double soldVechi = this.sold;
            this.sold = this.sold + suma;
            System.out.println(String.format("\nSold vechi: %.1f lei.\nAti extras suma de %.1f lei.\nSold nou: %.1f lei.\n"
                            , soldVechi, suma, this.sold));
        }else{
            ok = 0;
        }
    }
}