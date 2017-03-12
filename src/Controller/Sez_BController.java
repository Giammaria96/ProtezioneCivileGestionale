package Controller;

import View.BasicFrameView;
import View.Sez_BView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Sez_BController{

    private Sez_BView sez_Bview;
    private BasicFrameView basicframe;
    private int aggiorna;
    private ArrayList<String> listaCORSO;
    private ArrayList<String> listaABILITAZIONE;
    private ArrayList<String> listaPATENTE;

    public Sez_BController(Sez_BView view2, BasicFrameView frame) {

        sez_Bview = view2;
        basicframe = frame;
        listaCORSO = new ArrayList<>(25);
        listaABILITAZIONE = new ArrayList<>(25);
        listaPATENTE = new ArrayList<>(25);

        aggiorna = 1;
        sez_BcontrellerListener();

    }

    private void sez_BcontrellerListener(){


        /*Aggiorna*/
        JButton Aggiorna = sez_Bview.getAggiornaButton();

        Aggiorna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Controllo();
            }

        });

        JComboBox boxcertificazioni = sez_Bview.getCertif_Box();
        JComboBox boxlist = sez_Bview.getListBox();
        boxcertificazioni.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  sez_Bview.getCertif_Box()) {
                    if (boxcertificazioni.getSelectedItem().equals("ABILITAZIONE")) {

                        boxlist.removeAllItems();
                        boxlist.addItem("utilizzo piatt elev");
                        boxlist.addItem("anti incendio");
                        boxlist.addItem("anti inc bos");
                        boxlist.addItem("anti inquinamento");
                        boxlist.addItem("marino");
                        boxlist.addItem("bls");
                        boxlist.addItem("blsd");
                        boxlist.addItem("cinofilo");

                    }
                    else if(boxcertificazioni.getSelectedItem().equals("CORSO")){

                        boxlist.removeAllItems();
                        boxlist.addItem("corso base di radiocomunicazione");
                        boxlist.addItem("base protezione civil");
                        boxlist.addItem("fuoristrada");
                        boxlist.addItem("lingua ingles");
                        boxlist.addItem("logistica da campo");
                        boxlist.addItem("obbligatrion sic.vol. 81.08");
                        boxlist.addItem("rischio sanitari");
                        boxlist.addItem("sub");
                        boxlist.addItem("procedure amministrative");
                        boxlist.addItem("istruttore guida");
                        boxlist.addItem("vigli fuoco");




                    }
                    else if (boxcertificazioni.getSelectedItem().equals("PATENTE")){

                        boxlist.removeAllItems();
                        boxlist.addItem("ecdl");
                        boxlist.addItem("guida a");
                        boxlist.addItem("guida c");
                        boxlist.addItem("guida ce");
                        boxlist.addItem("guida d");
                        boxlist.addItem("guida k");
                        boxlist.addItem("guida kd");
                        boxlist.addItem("guida nautica");
                        boxlist.addItem("guida adr");
                        boxlist.addItem("guida mezzi pubblici");


                    }
                }

            }
        });


    }

    private void Controllo() {

        if(aggiorna == 5)
            basicframe.ErrorMessage("stop");
        if(sez_Bview.getEnte_r_Text().length()==0 || sez_Bview.getnDoc_Text().length()==0)
            basicframe.ErrorMessage("Errore! nessun ente e numero documento inserirti");


        else {

            aggiorna += 1;

            if (sez_Bview.getCertif_Box().getSelectedItem().equals("ABILITAZIONE")) {

                listaABILITAZIONE.add(sez_Bview.NomeCertificazione());
                listaABILITAZIONE.add(sez_Bview.getDataScadenza());
                listaABILITAZIONE.add(sez_Bview.getDataAcquisizone());
                listaABILITAZIONE.add(sez_Bview.getEnte_r_Text());
                listaABILITAZIONE.add(sez_Bview.getnDoc_Text());

                System.out.println(listaABILITAZIONE.get(0));

            }
            if (sez_Bview.getCertif_Box().getSelectedItem().equals("CORSO")) {

                listaCORSO.add(sez_Bview.NomeCertificazione());
                listaCORSO.add(sez_Bview.getDataScadenza());
                listaCORSO.add(sez_Bview.getDataAcquisizone());
                listaCORSO.add(sez_Bview.getEnte_r_Text());
                listaCORSO.add(sez_Bview.getnDoc_Text());

                System.out.println(listaCORSO.get(0));

            }
            if (sez_Bview.getCertif_Box().getSelectedItem().equals("PATENTE")) {

                listaPATENTE.add(sez_Bview.NomeCertificazione());
                listaPATENTE.add(sez_Bview.getDataScadenza());
                listaPATENTE.add(sez_Bview.getDataAcquisizone());
                listaPATENTE.add(sez_Bview.getEnte_r_Text());
                listaPATENTE.add(sez_Bview.getnDoc_Text());

                System.out.println(listaPATENTE.get(0));

            }
        }
    }

    public ArrayList<String> getListaCORSO() {

        return listaCORSO;

    }

    public ArrayList<String> getListaABILITAZIONE() {

        return listaABILITAZIONE;

    }

    public ArrayList<String> getListaPATENTE() {

        return listaPATENTE;

    }
}