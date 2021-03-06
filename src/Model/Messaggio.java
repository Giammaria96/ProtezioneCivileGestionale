package Model;

import View.VolontarioDView;

import java.util.ArrayList;

/**
 * Classe che consente di interagire con il DB per l invio del messaggio
 */
public class Messaggio extends Model {

    private String Destinatario; //cf del destinatario

    private String Mittente;
    private String Messaggio;

    /*COSTRUTTORI*/

    /*costruttore vuoto*/
    public Messaggio() {

        return;

    }

    public Messaggio(String destinatario, String mittente, String messaggio) {

        Destinatario = destinatario;

        Mittente = mittente;
        Messaggio = messaggio;

    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {

        return false;

    }

    @Override
    public boolean SearchSQL() {

        return false;

    }

    /**
     * Inserimento nel DB del messaggio
     *
     * @return true ,inserimento effettuato con successo
     */
    @Override
    public boolean InsertSQL() {

        Boolean controllo = false;

        openConnection();

        String sql = "Insert messaggi(cf,Mittente,messaggio,letto) values('" +
                Destinatario                  + "','" +
                Mittente                      + "','" +
                Messaggio                     + "','" +
                "no"                          + "')";


        if(updateQuery(sql)) {
            controllo = true;   //operazione eseguita con successo
        }

        closeConnection();

        return controllo;

    }

    public void AggiornaBroadcast(Volontario Utente, VolontarioDView Dview){

        //avviso di broadcast in locale
        ArrayList<String> BROADCAST = Utente.getBROADCAST();

        BROADCAST.add("< "+Mittente+" > : "+Messaggio);

        Dview.setBroadcast(BROADCAST);

    }

    //GETTER e SETTERS
    public String getMessaggio() {

        return Messaggio;

    }

    public void setMessaggio(String messaggio) {

        Messaggio = messaggio;

    }


    @Override
    public String toString() {
        return "Messaggio{" +
                "Destinatario='" + Destinatario + '\'' +
                ", Mittente='" + Mittente + '\'' +
                ", Messaggio='" + Messaggio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Messaggio messaggio = (Messaggio) o;

        return Messaggio != null ? Messaggio.equals(messaggio.Messaggio) : messaggio.Messaggio == null;

    }

}
