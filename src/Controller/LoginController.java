package Controller;

import Model.LoginModel;
import View.BasicFrameView;
import View.LoginView;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginController --> Controller per la LoginView
 * Classe pubblica
 */

public class LoginController {

    public BasicFrameView basicframe;
    public LoginView loginview;


    /*COSTRUTTORE*/
    public LoginController(BasicFrameView view) {

        basicframe = view;
        loginview = new LoginView();
        //Il pannello di login va messo nella parte destra della basicframe
        basicframe.setdestra(loginview.getIntermedio0());

        loginListener();

    }

    /**
     * Metodo di servizio.
     * loginListener gestisce gli eventi scatenati dall utente interagendo con la LoginView
     */
    private void loginListener(){

        /*Registrazione*/
        JButton RegistratiButton = loginview.getRegistratiButton();
        RegistratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CFVerificaController verificaframe;
                verificaframe = new CFVerificaController(basicframe, loginview);

            }
        });

        /*Accesso*/
        JButton AccediButton = loginview.getAccediButton();
        AccediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               AccediAction();

            }
        });
    }

    /**
     * Metodo di servizio.
     * AccediAction controlla se:
     *      1)L'utente non ha completato uno dei due campi(Userame, Password) nella pagina Login
     *      2)Segnala all'utente,grazie ad una finestra di errore, se l username e la password inseriti sono non corretti
     */
    private void AccediAction(){

        String userInserito = loginview.getUsernametext();
        char[] passInserita = loginview.getPasswordField1();


        try{

            if(userInserito.length() == 0 || passInserita.length == 0)
                throw new Exception("Completare tutti i campi");


            LoginModel RichiestaDiAccesso = new LoginModel(userInserito, passInserita);
            if(!RichiestaDiAccesso.VerificaEntità())
                throw new Exception("Username o Password errati");


            if(RichiestaDiAccesso.getVolocand().equals("0")) {
                CandidatoController aaa;
                aaa = new CandidatoController(basicframe);


            }

        }
        catch (Exception e ){
            basicframe.ErrorMessage(e.getMessage());
        }
    }

    @Override
    public String toString() {

        return "Sono LoginController e mi occupo della gestione delle azioni scatenate dall utente interagendo con"
                +"la LoginView";
    }
}