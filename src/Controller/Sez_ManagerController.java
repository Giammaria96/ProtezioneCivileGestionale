package Controller;

import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sez_ManagerController {

   public BasicFrameView basicframe;
   public Sez_ManagerView sez_managerview;
   public LoginView loginview;
   public CandidatoDestraView Dview;
   private String Utilizzatore;

    /*COSTRUTTORI*/
    public Sez_ManagerController(BasicFrameView frame , LoginView view , String utilizzatore) {

        basicframe = frame;
        loginview = view;
        Utilizzatore = utilizzatore;

        InizializzazioneRegistrazione();

        sezmanagerListener();

    }

    public Sez_ManagerController(BasicFrameView frame , CandidatoDestraView view , String utilizzatore) {

        basicframe = frame;
        Dview = view;
        Utilizzatore = utilizzatore;

        InizializzazioneCandidato();

        sezmanagerListener();

    }


    private void InizializzazioneRegistrazione(){

        sez_managerview = new Sez_ManagerView();
        sez_managerview.setIbridoButton("Pagina Login");
        sez_managerview.setmodificaButton(false);
        sceltapannelli(Utilizzatore);
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(sez_managerview.getIntermedio0());
        sez_managerview.MessaggioBenvenuto(basicframe);

    }

    private void InizializzazioneCandidato(){

        sez_managerview = new Sez_ManagerView();
        sez_managerview.setIbridoButton("Home");
        sez_managerview.setmodificaButton(true);
        sceltapannelli(Utilizzatore);
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(sez_managerview.getIntermedio0());

    }


    /*
     *sceltapannelli gestisce i pannelli da inserire nella Sez_managerView.
     *La scelta è eseguita in base all utilizzatore--> Registrazione, Persona, Volontario
     */
    private void sceltapannelli(String utilizzatore){

        if(utilizzatore.equals("Registrazione")){

            sez_managerview.setSezA(new Sez_AView().getIntermedio0());
            sez_managerview.setSezB(new Sez_BView().getIntermedio0());
            sez_managerview.setSezC(new Sez_CView().getIntermedio0());

        }

        else if(utilizzatore.equals("Persona")){

            sez_managerview.setSezA(new Sez_AView().getIntermedio0());
            sez_managerview.setSezB(new Sez_BView().getIntermedio0());
            sez_managerview.setSezC(new Sez_CView().getIntermedio0());

        }

    }

    private void sezmanagerListener(){

        CardLayout CL=(CardLayout) sez_managerview.getIntermedio1().getLayout();

        /*Avanti*/
        JButton sez_managerviewAvanti = sez_managerview.getAvantiButton();
        sez_managerviewAvanti.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Pagine_Manager.getPagina_Corrente() < 3) {

                            CL.next(sez_managerview.getIntermedio1());
                            Pagine_Manager.addPagina_Corrente();

                        }

                    }

        });


        /*Indietro*/
        JButton sez_managerviewIndietro= sez_managerview.getIndietroButton();
        sez_managerviewIndietro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (Pagine_Manager.getPagina_Corrente() > 1) {

                        CL.previous(sez_managerview.getIntermedio1());
                        Pagine_Manager.subctractPagina_Corrente();
                    }

                }
        });


        /*ibrido*/
        JButton button = sez_managerview.getIbridoButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if(Utilizzatore.equals("Registrazione"))
                basicframe.setdestra(loginview.getIntermedio0());
               if(Utilizzatore.equals("Persona"))
                   basicframe.setdestra(Dview.getIntermedio0());



            }
        });
    }
}
