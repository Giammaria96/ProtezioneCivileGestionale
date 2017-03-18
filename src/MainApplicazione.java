import Controller.LoginController;
import View.BasicFrameView;


/**
 * Applicazione per la gestione dei dati personali dei volontari della Protezione Civile di Falconara Marittima
 */

public class MainApplicazione {

    public static void main(String[] args) {

        LoginController ApplicazioneStart;

        //Genero la frame principale(BasicFrameView) passandola al LoginController che porrà all interno il
        //pannello di login
        ApplicazioneStart=new LoginController(new BasicFrameView());

    }
}
