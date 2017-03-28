package Controller.Compiti;


import Controller.AnagraficaController;
import Controller.MessaggioController;
import Model.GestioneModel;
import Model.Persona;
import Model.Volontario;
import View.ListaggiView;
import View.BasicFrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class ListaggiController {


    private BasicFrameView basicframe;
    private ListaggiView view;
    private GestioneModel gestione;
    private JComboBox Box;
    private String utilizzatore;
    private String appoggio;
    private Volontario UtenteLoggato;

    private ArrayList<Persona> UTENTI;


    /*COSTRUTTORE*/
    public ListaggiController(BasicFrameView frame, String Utilizzatore, Volontario Utenteloggato) {

        basicframe = frame;
        utilizzatore = Utilizzatore;
        UtenteLoggato = Utenteloggato;
        view = new ListaggiView();
        Box = view.getBox1();

        SettaggioView();

        gestione = new GestioneModel(appoggio);
        UTENTI = gestione.Schede(Utilizzatore);

        basicframe.setdestra(view.getIntermedio0());
        stampalista();

    }

   private void SettaggioView(){

        switch(utilizzatore){

            case "listacandidati":{

                view.VisibilitaAccettaButton(true);
                view.VisibilitaVisionaSchedaButton(true);
                view.VisibilitaRitornaButton(true);
                view.VisibilitaInviomessaggio(true);
                view.setLabel("Lista candidati");
                appoggio = "vol_o_cand=0 and Conf_Archivista=0";
                RitornaAiCompitiDaArchivistaListener();
                VisionaSchedaListener();
                AccettaListener();
                InvioMessaggioListener();
                break;


            }

            case "listavolontari":{

                view.VisibilitaRitornaButton(true);
                view.VisibilitaVisionaSchedaButton(true);
               // view.VisibilitaInviomessaggio(true);
                view.setLabel("Lista volontari");
                view.VisibilitaStatoLabel(true);
                view.VisibilitaText(true);
                appoggio = "vol_o_cand=1";
                VisionaSchedaListener();
                RitornaAiCompitiDaArchivistaListener();
                Boxlistener();
                break;

            }

        }

   }


    public void stampalista() {


            for (Persona candidato : UTENTI)
                Box.addItem(candidato.getCognome() + "    -    " + candidato.getNome());



    }

    /**
     * Ascolto azioni dell utente
     */
    private void RitornaAiCompitiDaArchivistaListener() {

        /*Ritorna ai compiti*/
            JButton ritornaAiCompitiDaArchivista = view.getRitornaButton();
            ritornaAiCompitiDaArchivista.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ArchivistaHome controller;
                    controller = new ArchivistaHome(basicframe, UtenteLoggato);

                }
            });
    }

    private void VisionaSchedaListener() {

        if(Box.getSelectedItem() != null) {

            JButton visionaSchedaButton = view.getVisionaSchedaButton();
            visionaSchedaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    VisionaSchedaAction();

                }
            });
        }

    }

    private void AccettaListener(){

        if(Box.getSelectedItem() != null) {

            JButton AccettaButton = view.getAccettaButton();
            AccettaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    AccettaArchivistaAction();

                }
            });
        }

    }

    private void InvioMessaggioListener(){

        if(Box.getSelectedItem() != null) {

            JButton InvioMessaggio = view.getInviagliUnMessaggioButton();
            InvioMessaggio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int Indice;
                    Indice = Box.getSelectedIndex();

                    MessaggioController controller;
                    controller = new MessaggioController(basicframe, UTENTI.get(Indice).getCodice_Fiscale(), UtenteLoggato.getNome() + " " + UtenteLoggato.getCognome());

                }
            });
        }


    }


    private void VisionaSchedaAction() {

        int Indice;
        Indice = Box.getSelectedIndex();

            AnagraficaController controller;
            controller = new AnagraficaController(basicframe, UTENTI.get(Indice), view, utilizzatore);

    }

    private void Boxlistener(){


        Box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == view.getBox1()) {

                  int Indice =  Box.getSelectedIndex();

                   Volontario volontario = (Volontario) UTENTI.get(Indice);

                   view.setText(volontario.getStato());


                }

            }
        });
    }


    private void AccettaArchivistaAction() {

        int Indice;
        Indice = Box.getSelectedIndex();
        String[] appoggio = new String[3];


        if (basicframe.OpotionalMessage("Confermare scheda per " + UTENTI.get(Indice).getNome() + " ?") == 0) {

            appoggio[0] = "pass";
            appoggio[1] = "Conf_Archivista";
            appoggio[2] = "1";

            if (UTENTI.get(Indice).UpdateSQL(appoggio)) {

                basicframe.Message("Conferma effettuata con successo!");
                UTENTI.remove(Indice);
                Box.removeItemAt(Indice);

            }
        }


    }

    @Override
    public String toString() {

        return "ListaggiController{}";

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListaggiController that = (ListaggiController) o;

        return view != null ? view.equals(that.view) : that.view == null;

    }

}

