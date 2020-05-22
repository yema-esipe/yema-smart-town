package client;

//import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import connection.PropertiesFileReader;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class FenCalculEC extends JFrame implements ActionListener, DocumentListener{

	/**
	 * 
	 */
	 
public void setDistances(HashMap<String, Double> values) {
		
		pieton.setDistance(values.get("pieton"));
		velo.setDistance(values.get("velo"));
		moto.setDistance(values.get("moto"));
		voiture.setDistance(values.get("voiture"));
		bus.setDistance(values.get("bus"));
		metro.setDistance(values.get("metro"));
		tram.setDistance(values.get("tram"));
		train.setDistance(values.get("train"));
	}
	
	public  void setNombres(HashMap<String, Integer> values) {
		pieton.setNombre(values.get("pieton"));
		velo.setNombre(values.get("velo"));
		moto.setNombre(values.get("moto"));
		voiture.setNombre(values.get("voiture"));
		bus.setNombre(values.get("bus"));
		metro.setNombre(values.get("metro"));
		tram.setNombre(values.get("tram"));
		train.setNombre(values.get("train"));
	}
	
	public void setCo2s(HashMap<String, Double> values) {
		pieton.setCo2(values.get("pieton"));
		velo.setCo2(values.get("velo"));
		moto.setCo2(values.get("moto"));
		voiture.setCo2(values.get("voiture"));
		bus.setCo2(values.get("bus"));
		metro.setCo2(values.get("metro"));
		tram.setCo2(values.get("tram"));
		train.setCo2(values.get("train"));
	}
	
	public void setNbpassengeravgs(HashMap<String, Integer> values) {
		pieton.setPassengeravg(values.get("pieton"));
		velo.setPassengeravg(values.get("velo"));
		moto.setPassengeravg(values.get("moto"));
		voiture.setPassengeravg(values.get("voiture"));
		bus.setPassengeravg(values.get("bus"));
		metro.setPassengeravg(values.get("metro"));
		tram.setPassengeravg(values.get("tram"));
		train.setPassengeravg(values.get("train"));
	}
	
	public void setNbcarmaxs(HashMap<String, Integer> values) {
		pieton.setNombremax(values.get("pieton"));
		velo.setNombremax(values.get("velo"));
		moto.setNombremax(values.get("moto"));
		voiture.setNombremax(values.get("voiture"));
		bus.setNombremax(values.get("bus"));
		metro.setNombremax(values.get("metro"));
		tram.setNombremax(values.get("tram"));
		train.setNombremax(values.get("train"));
	}
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/*declaration logger and port, address server*/
	
	private Logger LOGGER;
	@SuppressWarnings("unused")
	private PropertiesFileReader serveconfig;
	private final int SERVER_PORT;
	private final String SERVER_ADDRESS;
	private ClientCommunication client;
	
	/*champs col 1*/
	
	private JTextField txtdistancepieton;
	private JTextField txtdistancevelo;
	private JTextField txtdistancemoto;
	private JTextField txtdistancevoiture;
	private JTextField txtdistancebus;
	private JTextField txtdistancemetro;
	private JTextField txtdistancetram;
	private JTextField txtdistancetrain;
	
	/*champs col 2*/
	
	private JTextField txtfepieton;
	private JTextField txtfevelo;
	private JTextField txtfemoto;
	private JTextField txtfevoiture;
	private JTextField txtfebus;
	private JTextField txtfemetro;
	private JTextField txtfetram;
	private JTextField txtfetrain;
	
	/*champs col 3 cad nombre/nombre max */
	
	private JTextField txtnombrepieton;
	private JTextField txtnombrevelo;
	private JTextField txtnombremoto;
	private JTextField txtnombrevoiture;
	private JTextField txtnombrebus;
	private JTextField txtnombremetro;
	private JTextField txtnombretram;
	private JTextField txtnombretrain;

	private JTextField txtnombremaxpieton;
	private JTextField txtnombremaxvelo;
	private JTextField txtnombremaxmoto; 
	private JTextField txtnombremaxvoiture;
	private JTextField txtnombremaxbus;
	private JTextField txtnombremaxmetro;
	private JTextField txtnombremaxtram;
	private JTextField txtnombremaxtrain;
	
	/*champs col 4 cad empreinte carbone */
	
	private JTextField txtECpieton;
	private JTextField txtECvelo;
	private JTextField txtECmoto;
	private JTextField txtECvoiture;
	private JTextField txtECbus;
	private JTextField txtECmetro;
	private JTextField txtECtram;
	private JTextField txtECtrain;
	
	/*champs Empreinte carbone globale */
	private JTextField txtECglobale;
	
	/*les boutons */ 
	private JButton btEstimerEC;
	private JButton btRetour;
	
	/*the DateEC*/
	
	private DataEC pieton = new DataEC("pieton");
	private DataEC velo = new DataEC("velo");
	private DataEC moto = new DataEC("moto");
	private DataEC voiture = new DataEC("voiture");
	private DataEC bus = new DataEC("bus");
	private  DataEC metro = new DataEC("metro");
	private DataEC tram = new DataEC("tram");
	private DataEC train = new DataEC("train");
	
	private JTextField txtDatedebut;
	private JTextField txtDatefin;
	
	private boolean booltxtdistpieton = false;
	private boolean booltxtdistvelo = false;
	private boolean booltxtdistmoto = false;
	private boolean booltxtdistvoiture = false;
	private boolean booltxtdistbus = false;
	private boolean booltxtdistmetro = false;
	private boolean booltxtdisttram = false;
	private boolean booltxtdisttrain = false;
	
	private boolean booltxtnbpieton = false;
	private boolean booltxtnbvelo = false;
	private boolean booltxtnbmoto = false;
	private boolean booltxtnbvoiture = false;
	private boolean booltxtnbbus = false;
	private boolean booltxtnbmetro = false;
	private boolean booltxtnbtram = false;
	private boolean booltxtnbtrain = false;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenCalculEC frame = new FenCalculEC();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
			
		
		
	}

	/** 
	 * Create the frame.
	 */
	public FenCalculEC() {
		setResizable(false);
		setTitle("yema-smart-town");
		//Dimension ecran =  java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//int longueur = ecran.width;
		//int hauteur = ecran.height;
		//setSize(longueur, hauteur);
		setSize(1100, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		/*
		 *  Libellé début grand
		 */
		
		JLabel lblEstimateurDempreinteCarbone = new JLabel("Estimateur d'empreinte carbone de la ville");
		lblEstimateurDempreinteCarbone.setBackground(UIManager.getColor("CheckBox.light"));
		lblEstimateurDempreinteCarbone.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEstimateurDempreinteCarbone.setBounds(234, 21, 442, 52);
		contentPane.add(lblEstimateurDempreinteCarbone);
		
		
		/*
		 *  Libellé ligne d'infos
		 */
		
		JLabel lblidistance = new JLabel("Distance moyenne");
		lblidistance.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblidistance.setBounds(114, 84, 150, 31);
		contentPane.add(lblidistance);
		
		JLabel lblfe = new JLabel("Facteur d'émission CO2");
		lblfe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblfe.setBounds(329, 84, 175, 31);
		contentPane.add(lblfe);
		
		JLabel lblnombre = new JLabel("Nombre / Nombre Max");
		lblnombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnombre.setBounds(627, 84, 169, 31);
		contentPane.add(lblnombre);
		
		JLabel lblEC = new JLabel("Empreinte carbone");
		lblEC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEC.setBounds(893, 84, 171, 31);
		contentPane.add(lblEC);
		
		
		
		/*
		 *  Libellé colonne des modes de déplacements
		 */
		
		JLabel lblPieton = new JLabel("Piéton");
		lblPieton.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPieton.setBounds(20, 126, 80, 31);
		contentPane.add(lblPieton);
		
		JLabel lblvelo = new JLabel("Vélo");
		lblvelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblvelo.setBounds(20, 181, 80, 31);
		contentPane.add(lblvelo);
		
		JLabel lblmoto = new JLabel("Moto");
		lblmoto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmoto.setBounds(20, 235, 80, 31);
		contentPane.add(lblmoto);
		
		JLabel lblvoiture = new JLabel("Voiture");
		lblvoiture.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblvoiture.setBounds(20, 288, 80, 31);
		contentPane.add(lblvoiture);
		
		JLabel lblbus = new JLabel("Bus");
		lblbus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblbus.setBounds(20, 340, 80, 31);
		contentPane.add(lblbus);
		
		JLabel lblmetro = new JLabel("Métro");
		lblmetro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmetro.setBounds(20, 391, 80, 31);
		contentPane.add(lblmetro);
		
		JLabel lbltram = new JLabel("Tram");
		lbltram.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltram.setBounds(20, 443, 80, 31);
		contentPane.add(lbltram);
		
		JLabel lbltrain = new JLabel("Train");
		lbltrain.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltrain.setBounds(20, 493, 80, 31);
		contentPane.add(lbltrain);
		
		/*
		 *  Colonne de Textfield distance moyenne parcourues 
		 */
		
		txtdistancepieton = new JTextField();
		txtdistancepieton.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancepieton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancepieton.setColumns(10);
		txtdistancepieton.setBounds(110, 130, 110, 27);
		txtdistancepieton.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancepieton);
		
		JLabel lbldistancepietonunite = new JLabel("km");
		lbldistancepietonunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancepietonunite.setBounds(221, 130, 41, 27);
		contentPane.add(lbldistancepietonunite);
		
		txtdistancevelo = new JTextField();
		txtdistancevelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancevelo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancevelo.setColumns(10);
		txtdistancevelo.setBounds(110, 185, 110, 27);
		txtdistancevelo.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancevelo);
		 
		JLabel lbldistancevelounite = new JLabel("km");
		lbldistancevelounite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancevelounite.setBounds(221, 185, 41, 27);
		contentPane.add(lbldistancevelounite);
		
		txtdistancemoto = new JTextField();
		txtdistancemoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancemoto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancemoto.setColumns(10);
		txtdistancemoto.setBounds(108, 239, 110, 27);
		txtdistancemoto.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancemoto);
		
		JLabel lbldistancemotounite = new JLabel("km");
		lbldistancemotounite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancemotounite.setBounds(219, 239, 41, 27);
		contentPane.add(lbldistancemotounite);
		
		txtdistancevoiture = new JTextField();
		txtdistancevoiture.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancevoiture.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancevoiture.setColumns(10);
		txtdistancevoiture.setBounds(108, 292, 110, 27);
		txtdistancevoiture.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancevoiture);
		
		JLabel lbldistancevoitureunite = new JLabel("km");
		lbldistancevoitureunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancevoitureunite.setBounds(219, 292, 41, 27);
		contentPane.add(lbldistancevoitureunite);
		
		txtdistancebus = new JTextField();
		txtdistancebus.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancebus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancebus.setColumns(10);
		txtdistancebus.setBounds(108, 344, 110, 27);
		txtdistancebus.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancebus);
		
		JLabel lbldistancebusunite = new JLabel("km");
		lbldistancebusunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancebusunite.setBounds(219, 344, 41, 27);
		contentPane.add(lbldistancebusunite);
		
		txtdistancemetro = new JTextField();
		txtdistancemetro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancemetro.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancemetro.setColumns(10);
		txtdistancemetro.setBounds(108, 395, 110, 27);
		txtdistancemetro.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancemetro);
		
		JLabel lbldistancemetrounite = new JLabel("km");
		lbldistancemetrounite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancemetrounite.setBounds(219, 395, 41, 27);
		contentPane.add(lbldistancemetrounite);
		
		txtdistancetram = new JTextField();
		txtdistancetram.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancetram.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancetram.setColumns(10);
		txtdistancetram.setBounds(108, 447, 110, 27);
		txtdistancetram.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancetram);
		
		JLabel lbldistancetramunite = new JLabel("km");
		lbldistancetramunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancetramunite.setBounds(219, 447, 41, 27);
		contentPane.add(lbldistancetramunite);
		
		txtdistancetrain = new JTextField();
		txtdistancetrain.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdistancetrain.setHorizontalAlignment(SwingConstants.RIGHT);
		txtdistancetrain.setColumns(10);
		txtdistancetrain.setBounds(108, 497, 110, 27);
		txtdistancetrain.getDocument().addDocumentListener(this);
		contentPane.add(txtdistancetrain);
		
		JLabel lbldistancetrainunite = new JLabel("km");
		lbldistancetrainunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbldistancetrainunite.setBounds(219, 493, 41, 31);
		contentPane.add(lbldistancetrainunite);
		
		/*
		 *  Colonne de Textfield facteurs d'émissions 
		 */
		
		txtfepieton = new JTextField();
		txtfepieton.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfepieton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfepieton.setEditable(false);
		txtfepieton.setColumns(10);
		txtfepieton.setBounds(299, 130, 110, 27);
		contentPane.add(txtfepieton);
		
		JLabel lblfepietonunite = new JLabel("kg CO2/piéton.km");
		lblfepietonunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfepietonunite.setBounds(410, 127, 140, 31);
		contentPane.add(lblfepietonunite);
		
		txtfevelo = new JTextField();
		txtfevelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfevelo.setEditable(false);
		txtfevelo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfevelo.setColumns(10);
		txtfevelo.setBounds(299, 185, 110, 27);
		contentPane.add(txtfevelo);
		
		JLabel lblfevelounite = new JLabel("kg CO2/passager.km");
		lblfevelounite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfevelounite.setBounds(410, 182, 140, 31);
		contentPane.add(lblfevelounite);
		
		txtfemoto = new JTextField();
		txtfemoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfemoto.setEditable(false);
		txtfemoto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfemoto.setColumns(10);
		txtfemoto.setBounds(299, 239, 110, 27);
		contentPane.add(txtfemoto);
		
		JLabel lblfemotounite = new JLabel("kg CO2/passager.km");
		lblfemotounite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfemotounite.setBounds(410, 235, 140, 31);
		contentPane.add(lblfemotounite);
		
		txtfevoiture = new JTextField();
		txtfevoiture.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfevoiture.setEditable(false);
		txtfevoiture.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfevoiture.setColumns(10);
		txtfevoiture.setBounds(299, 292, 110, 27);
		contentPane.add(txtfevoiture);
		
		JLabel lblfevoitureunite = new JLabel("kg CO2/passager.km");
		lblfevoitureunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfevoitureunite.setBounds(410, 288, 140, 31);
		contentPane.add(lblfevoitureunite);
		
		txtfebus = new JTextField();
		txtfebus.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfebus.setEditable(false);
		txtfebus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfebus.setColumns(10);
		txtfebus.setBounds(299, 344, 110, 27);
		contentPane.add(txtfebus);
		
		JLabel lblfebusunite = new JLabel("kg CO2/passager.km");
		lblfebusunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfebusunite.setBounds(410, 340, 140, 31);
		contentPane.add(lblfebusunite);
		
		txtfemetro = new JTextField();
		txtfemetro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfemetro.setEditable(false);
		txtfemetro.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfemetro.setColumns(10);
		txtfemetro.setBounds(299, 395, 110, 27);
		contentPane.add(txtfemetro);
		
		JLabel lblfemetrounite = new JLabel("kg CO2/passager.km");
		lblfemetrounite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfemetrounite.setBounds(410, 391, 140, 31);
		contentPane.add(lblfemetrounite);
		
		txtfetram = new JTextField();
		txtfetram.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfetram.setEditable(false);
		txtfetram.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfetram.setColumns(10);
		txtfetram.setBounds(299, 447, 110, 27);
		contentPane.add(txtfetram);
		
		JLabel lblfetramunite = new JLabel("kg CO2/passager.km");
		lblfetramunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfetramunite.setBounds(410, 443, 140, 31);
		contentPane.add(lblfetramunite);
		
		txtfetrain = new JTextField();
		txtfetrain.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfetrain.setEditable(false);
		txtfetrain.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfetrain.setColumns(10);
		txtfetrain.setBounds(299, 497, 110, 27);
		contentPane.add(txtfetrain);
		
		JLabel lblfetrainunite = new JLabel("kg CO2/passager.km");
		lblfetrainunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfetrainunite.setBounds(410, 493, 140, 31);
		contentPane.add(lblfetrainunite);
		
		/*
		 *  Colonne de Textfield nombre / nombre Max
		 */
		
		txtnombrepieton = new JTextField();
		txtnombrepieton.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombrepieton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombrepieton.setColumns(10);
		txtnombrepieton.setBounds(576, 130, 110, 27);
		txtnombrepieton.getDocument().addDocumentListener(this);
		contentPane.add(txtnombrepieton);
		
		JLabel lblslash0 = new JLabel("/");
		lblslash0.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash0.setBounds(688, 126, 14, 31);
		contentPane.add(lblslash0);
		
		txtnombremaxpieton = new JTextField();
		txtnombremaxpieton.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxpieton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxpieton.setEditable(false);
		txtnombremaxpieton.setColumns(10);
		txtnombremaxpieton.setBounds(699, 130, 110, 27);
		contentPane.add(txtnombremaxpieton);
		
		txtnombrevelo = new JTextField();
		txtnombrevelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombrevelo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombrevelo.setColumns(10);
		txtnombrevelo.setBounds(576, 185, 110, 27);
		txtnombrevelo.getDocument().addDocumentListener(this);
		contentPane.add(txtnombrevelo);
		
		JLabel lblslash1 = new JLabel("/");
		lblslash1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash1.setBounds(688, 181, 14, 31);
		contentPane.add(lblslash1);
		
		txtnombremaxvelo = new JTextField();
		txtnombremaxvelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxvelo.setEditable(false);
		txtnombremaxvelo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxvelo.setColumns(10);
		txtnombremaxvelo.setBounds(699, 185, 110, 27);
		contentPane.add(txtnombremaxvelo);
		
		txtnombremoto = new JTextField();
		txtnombremoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremoto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremoto.setColumns(10);
		txtnombremoto.setBounds(576, 239, 110, 27);
		txtnombremoto.getDocument().addDocumentListener(this);
		contentPane.add(txtnombremoto);
		
		JLabel lblslash2 = new JLabel("/");
		lblslash2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash2.setBounds(688, 235, 14, 31);
		contentPane.add(lblslash2);
		
		txtnombremaxmoto = new JTextField();
		txtnombremaxmoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxmoto.setEditable(false);
		txtnombremaxmoto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxmoto.setColumns(10);
		txtnombremaxmoto.setBounds(699, 239, 110, 27);
		contentPane.add(txtnombremaxmoto);
		
		txtnombrevoiture = new JTextField();
		txtnombrevoiture.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombrevoiture.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombrevoiture.setColumns(10);
		txtnombrevoiture.setBounds(576, 292, 110, 27);
		txtnombrevoiture.getDocument().addDocumentListener(this);
		contentPane.add(txtnombrevoiture);
		
		JLabel lblslash3 = new JLabel("/");
		lblslash3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash3.setBounds(688, 288, 14, 31);
		contentPane.add(lblslash3);
		
		txtnombremaxvoiture = new JTextField();
		txtnombremaxvoiture.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxvoiture.setEditable(false);
		txtnombremaxvoiture.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxvoiture.setColumns(10);
		txtnombremaxvoiture.setBounds(699, 292, 110, 27);
		contentPane.add(txtnombremaxvoiture);
		
		txtnombrebus = new JTextField();
		txtnombrebus.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombrebus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombrebus.setColumns(10);
		txtnombrebus.setBounds(576, 344, 110, 27);
		txtnombrebus.getDocument().addDocumentListener(this);
		contentPane.add(txtnombrebus);
		
		JLabel lblslash4 = new JLabel("/");
		lblslash4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash4.setBounds(688, 340, 14, 31);
		contentPane.add(lblslash4);
		
		txtnombremaxbus = new JTextField();
		txtnombremaxbus.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxbus.setEditable(false);
		txtnombremaxbus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxbus.setColumns(10);
		txtnombremaxbus.setBounds(699, 344, 110, 27);
		contentPane.add(txtnombremaxbus);
		
		txtnombremetro = new JTextField();
		txtnombremetro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremetro.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremetro.setColumns(10);
		txtnombremetro.setBounds(576, 395, 110, 27);
		txtnombremetro.getDocument().addDocumentListener(this);
		contentPane.add(txtnombremetro);
		
		JLabel lblslash5 = new JLabel("/");
		lblslash5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash5.setBounds(688, 391, 14, 31);
		contentPane.add(lblslash5);
		
		txtnombremaxmetro = new JTextField();
		txtnombremaxmetro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxmetro.setEditable(false);
		txtnombremaxmetro.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxmetro.setColumns(10);
		txtnombremaxmetro.setBounds(699, 395, 110, 27);
		contentPane.add(txtnombremaxmetro);
		
		txtnombretram = new JTextField();
		txtnombretram.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombretram.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombretram.setColumns(10);
		txtnombretram.setBounds(574, 447, 110, 27);
		txtnombretram.getDocument().addDocumentListener(this);
		contentPane.add(txtnombretram);
		
		JLabel lblslash6 = new JLabel("/");
		lblslash6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash6.setBounds(686, 443, 14, 31);
		contentPane.add(lblslash6);
		
		txtnombremaxtram = new JTextField();
		txtnombremaxtram.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxtram.setEditable(false);
		txtnombremaxtram.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxtram.setColumns(10);
		txtnombremaxtram.setBounds(697, 447, 110, 27);
		contentPane.add(txtnombremaxtram);
		
		txtnombretrain = new JTextField();
		txtnombretrain.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombretrain.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombretrain.setColumns(10);
		txtnombretrain.setBounds(576, 497, 110, 27);
		txtnombretrain.getDocument().addDocumentListener(this);
		contentPane.add(txtnombretrain);
		
		JLabel lblslash7 = new JLabel("/");
		lblslash7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblslash7.setBounds(688, 493, 14, 31);
		contentPane.add(lblslash7);
		
		txtnombremaxtrain = new JTextField();
		txtnombremaxtrain.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtnombremaxtrain.setEditable(false);
		txtnombremaxtrain.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnombremaxtrain.setColumns(10);
		txtnombremaxtrain.setBounds(699, 497, 110, 27);
		contentPane.add(txtnombremaxtrain);
		
		/*
		 *  Colonne de Textfield Empreinte carbone
		 */
		
		txtECpieton = new JTextField();
		txtECpieton.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECpieton.setForeground(new Color(255, 51, 0));
		txtECpieton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECpieton.setEditable(false);
		txtECpieton.setColumns(10);
		txtECpieton.setBounds(853, 129, 150, 27);
		contentPane.add(txtECpieton);
		
		JLabel lblECpietonunite = new JLabel("kg de CO2");
		lblECpietonunite.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECpietonunite.setBounds(1004, 126, 80, 31);
		contentPane.add(lblECpietonunite);
		
		
		txtECvelo = new JTextField();
		txtECvelo.setEditable(false);
		txtECvelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECvelo.setForeground(new Color(255, 51, 0));
		txtECvelo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECvelo.setColumns(10);
		txtECvelo.setBounds(853, 184, 150, 27);
		contentPane.add(txtECvelo);
		
		JLabel lblECunitevelo = new JLabel("kg de CO2");
		lblECunitevelo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitevelo.setBounds(1004, 181, 80, 31);
		contentPane.add(lblECunitevelo);
		
		txtECmoto = new JTextField();
		txtECmoto.setEditable(false);
		txtECmoto.setForeground(new Color(255, 51, 0));
		txtECmoto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECmoto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECmoto.setColumns(10);
		txtECmoto.setBounds(853, 238, 150, 27);
		contentPane.add(txtECmoto);
		
		JLabel lblECunitemoto = new JLabel("kg de CO2");
		lblECunitemoto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitemoto.setBounds(1004, 235, 80, 31);
		contentPane.add(lblECunitemoto);
		
		txtECvoiture = new JTextField();
		txtECvoiture.setEditable(false);
		txtECvoiture.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECvoiture.setForeground(new Color(255, 51, 0));
		txtECvoiture.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECvoiture.setColumns(10);
		txtECvoiture.setBounds(853, 291, 150, 27);
		contentPane.add(txtECvoiture);
		
		JLabel lblECunitevoiture = new JLabel("kg de CO2");
		lblECunitevoiture.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitevoiture.setBounds(1004, 288, 80, 31);
		contentPane.add(lblECunitevoiture);
		
		txtECbus = new JTextField();
		txtECbus.setEditable(false);
		txtECbus.setForeground(new Color(255, 51, 0));
		txtECbus.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECbus.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECbus.setColumns(10);
		txtECbus.setBounds(853, 343, 150, 27);
		contentPane.add(txtECbus);
		
		JLabel lblECunitebus = new JLabel("kg de CO2");
		lblECunitebus.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitebus.setBounds(1004, 340, 80, 31);
		contentPane.add(lblECunitebus);
		
		txtECmetro = new JTextField();
		txtECmetro.setEditable(false);
		txtECmetro.setForeground(new Color(255, 51, 0));
		txtECmetro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECmetro.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECmetro.setColumns(10);
		txtECmetro.setBounds(853, 394, 150, 27);
		contentPane.add(txtECmetro);
		
		JLabel lblECunitemetro = new JLabel("kg de CO2");
		lblECunitemetro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitemetro.setBounds(1004, 391, 80, 31);
		contentPane.add(lblECunitemetro);
		
		txtECtram = new JTextField();
		txtECtram.setEditable(false);
		txtECtram.setForeground(new Color(255, 51, 0));
		txtECtram.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECtram.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECtram.setColumns(10);
		txtECtram.setBounds(853, 446, 150, 27);
		contentPane.add(txtECtram);
		
		JLabel lblECunitetram = new JLabel("kg de CO2");
		lblECunitetram.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitetram.setBounds(1004, 443, 80, 31);
		contentPane.add(lblECunitetram);
		
		txtECtrain = new JTextField();
		txtECtrain.setEditable(false);
		txtECtrain.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtECtrain.setForeground(new Color(255, 51, 0));
		txtECtrain.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECtrain.setColumns(10);
		txtECtrain.setBounds(853, 496, 150, 27);
		contentPane.add(txtECtrain);
		
		JLabel lblECunitetrain = new JLabel("kg de CO2");
		lblECunitetrain.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblECunitetrain.setBounds(1004, 493, 80, 31);
		contentPane.add(lblECunitetrain);
		
		/*
		 *  Champs EC Globale
		 */
		
		txtECglobale= new JTextField();
		txtECglobale.setEditable(false);
		txtECglobale.setHorizontalAlignment(SwingConstants.RIGHT);
		txtECglobale.setForeground(new Color(255, 51, 0));
		txtECglobale.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtECglobale.setColumns(10);
		txtECglobale.setBounds(853, 546, 211, 39);
		contentPane.add(txtECglobale);
		
		JLabel lblEmpreinteCarboneGlobale = new JLabel("Empreinte carbone globale :");
		lblEmpreinteCarboneGlobale.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmpreinteCarboneGlobale.setBounds(632, 546, 219, 39);
		contentPane.add(lblEmpreinteCarboneGlobale);
		
		JLabel lblECglobaleunite = new JLabel("(tonne de CO2)");
		lblECglobaleunite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblECglobaleunite.setBounds(910, 577, 110, 39);
		contentPane.add(lblECglobaleunite);
		

		/*
		 *  Les boutons 
		 */
		
		
		
		
		btRetour = new JButton("Retour");
		btRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent act) {
				try {
					@SuppressWarnings("unused")
					YemaWindows fen = new YemaWindows();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		btRetour.setFont(new Font("Tahoma", Font.BOLD, 14));
		btRetour.setBounds(20, 11, 104, 39);
		contentPane.add(btRetour);
		
		btEstimerEC = new JButton("Estimer empreinte");
		btEstimerEC.setFont(new Font("Tahoma", Font.BOLD, 15));
		btEstimerEC.setBounds(410, 599, 229, 45);
		contentPane.add(btEstimerEC);
		btEstimerEC.addActionListener(this);
		
		/*
		 *  Période (Les dates) 
		 */
		
		JLabel lblPeriode = new JLabel("Période :");
		lblPeriode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeriode.setBounds(748, 15, 70, 31);
		contentPane.add(lblPeriode);
		
		txtDatedebut = new JTextField();
		txtDatedebut.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatedebut.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDatedebut.setColumns(10);
		txtDatedebut.setBounds(820, 11, 110, 39);
		contentPane.add(txtDatedebut);
		
		JLabel lbldeuxpointdate = new JLabel(":");
		lbldeuxpointdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbldeuxpointdate.setBounds(940, 15, 5, 31);
		contentPane.add(lbldeuxpointdate);
		
		
		txtDatefin = new JTextField();
		txtDatefin.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatefin.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDatefin.setColumns(10);
		txtDatefin.setBounds(954, 11, 110, 39);
		contentPane.add(txtDatefin);
		
		/*
		 *  Initialisation pour la connexion) 
		 */
		
		LOGGER = Logger.getLogger(FenCalculEC.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		
		SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		
		client = new ClientCommunication();	
			

	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		        
 
		try {
			client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		} catch (IOException ex) {
			ex.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur de connexion client");
		}

		//String s = "";
		ObjectMapper mapper = new ObjectMapper();
		
		HashMap<String, String> requestdistance = new HashMap<>();
		requestdistance.put("operation_type", "avgdistance");
		requestdistance.put("target", "datapollution");
		requestdistance.put("date_debut", txtDatedebut.getText());
		requestdistance.put("date_fin", txtDatefin.getText());
		
		HashMap<String, String> requestnombre = new HashMap<>();
		requestnombre.put("operation_type", "countnb");
		requestnombre.put("target", "datapollution");
		requestnombre.put("date_debut", txtDatedebut.getText());
		requestnombre.put("date_fin", txtDatefin.getText());
		
		HashMap<String, String> requestco2 = new HashMap<>();
		requestco2.put("operation_type", "selectco2");
		requestco2.put("target", "typeoftravel");

		HashMap<String, String> requestnbpassenger = new HashMap<>();
		requestnbpassenger.put("operation_type", "selectnbpassengeravg");
		requestnbpassenger.put("target", "typeoftravel");
		
		HashMap<String, String> requestnbcarmax = new HashMap<>();
		requestnbcarmax.put("operation_type", "selectnbcarmax");
		requestnbcarmax.put("target", "deviceconfignbcar");
		
		HashMap<String, String> fin = new HashMap<>();
		fin.put("operation_type", "end");
		
		String jsonrequestdistance = "";
		String jsonrequestnombre = "";
		String jsonrequestco2 = "";
		String jsonrequestnbpassenger = "";
		String jsonrequestnbcarmax = "";
		String jsonfin = "";
		
		String jsonresponse = ""; 
		
		
		try {
			jsonrequestdistance = mapper.writeValueAsString(requestdistance);
			jsonrequestnombre = mapper.writeValueAsString(requestnombre );
			jsonrequestco2 = mapper.writeValueAsString(requestco2);
			jsonrequestnbpassenger = mapper.writeValueAsString(requestnbpassenger);
			jsonrequestnbcarmax = mapper.writeValueAsString(requestnbcarmax);
			jsonfin = mapper.writeValueAsString(fin);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur de sérialisation données client");
		}
		
		
		try {
			client.send(jsonrequestdistance);
			client.send(jsonrequestnombre);
			client.send(jsonrequestco2);
			client.send(jsonrequestnbpassenger);
			client.send(jsonrequestnbcarmax);
			client.send(jsonfin);
		} catch (IOException e1) {

			e1.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur d'envoie de réquetes client");
		}
		
		
		System.out.println("*** Fin d'envoi de requete client ***");
		HashMap<String , Object> response = new HashMap<String , Object>();
		
		try {
			while ((jsonresponse = client.get()) != null) { 
				response = mapper.readValue(jsonresponse, HashMap.class);
				
				if ((response.get("response_type")).equals("end")) {
					System.out.println(" *** fin de communication avec le serveur *** ");
					break;	
				}
				
				if((response.get("response_type")).equals("avgdistance")) {
					setDistances((HashMap<String, Double>)mapper.convertValue(response.get("values"), HashMap.class));
				}
				
				if((response.get("response_type")).equals("countnb")) {
					setNombres((HashMap<String, Integer>) response.get("values"));
				}
				
				if((response.get("response_type")).equals("selectco2")) {
					setCo2s((HashMap<String, Double>)mapper.convertValue(response.get("values"), HashMap.class));
				}
				
				if((response.get("response_type")).equals("selectnbpassengeravg")) {
					setNbpassengeravgs((HashMap<String, Integer>) response.get("values"));
				}
				
				if((response.get("response_type")).equals("selectnbcarmax")) {
					setNbcarmaxs((HashMap<String, Integer>) response.get("values"));
				}
					
				System.out.println(jsonresponse);
			}
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur de mapping dans la reception de la réponse ");
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur de conversion json dans la reception de la réponse");
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur d'argument illégal dans la reception de la réponse");
		} catch (IOException e1) {
			e1.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur dans la reception de la réponse");
		}
		
		System.out.println("fin de connexion");
		try {
			client.stopConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("***Affichage de l empreinte carbone de chaque mode déplacements ***");
		
		/* carbon footprint values display with toString*/
		

		
		pieton.setFe();
		pieton.setEc(); 
		
		velo.setFe();
		velo.setEc();
		
		moto.setFe();
		moto.setEc();
		
		voiture.setFe();
		voiture.setEc();
		
		bus.setFe();
		bus.setEc();
		
		metro.setFe();
		metro.setEc();
		
		tram.setFe();
		tram.setEc();
		
		train.setFe();
		train.setEc();
		
		/*affichage des fe*/
		txtfepieton.setText(Double.toString(Math.round(pieton.getFe()* 1000.0)/1000.0));
		txtfevelo.setText(Double.toString(Math.round(velo.getFe()* 1000.0)/1000.0));
		txtfemoto.setText(Double.toString(Math.round(moto.getFe()* 1000.0)/1000.0));
		txtfevoiture.setText(Double.toString(Math.round(voiture.getFe()* 1000.0)/1000.0));
		txtfebus.setText(Double.toString(Math.round(bus.getFe()* 1000.0)/1000.0));
		txtfemetro.setText(Double.toString(Math.round(metro.getFe()* 1000.0)/1000.0));
		txtfetram.setText(Double.toString(Math.round(tram.getFe()* 1000.0)/1000.0));
		txtfetrain.setText(Double.toString(Math.round(train.getFe()* 1000.0)/1000.0));
		
		/*affichage des nombres max*/
		
		txtnombremaxpieton.setText(Double.toString(Math.round(pieton.getNombremax()* 1000.0)/1000.0));
		txtnombremaxvelo.setText(Double.toString(Math.round(velo.getNombremax()* 1000.0)/1000.0));
		txtnombremaxmoto.setText(Double.toString(Math.round(moto.getNombremax()* 1000.0)/1000.0));
		txtnombremaxvoiture.setText(Double.toString(Math.round(voiture.getNombremax()* 1000.0)/1000.0));
		txtnombremaxbus.setText(Double.toString(Math.round(bus.getNombremax()* 1000.0)/1000.0));
		txtnombremaxmetro.setText(Double.toString(Math.round(metro.getNombremax()* 1000.0)/1000.0));
		txtnombremaxtram.setText(Double.toString(Math.round(tram.getNombremax()* 1000.0)/1000.0));
		txtnombremaxtrain.setText(Double.toString(Math.round(train.getNombremax()* 1000.0)/1000.0));
		
		/*affichage  des distances moyennes*/
		
		
		if(txtdistancepieton.getText().equals("")) {
			txtdistancepieton.setText(Double.toString(Math.round(pieton.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdistpieton == true) {
				pieton.setDistance(Double.valueOf(txtdistancepieton.getText()));
				txtdistancepieton.setText(Double.toString(Math.round(pieton.getDistance()* 1000.0)/1000.0));
				booltxtdistpieton = false;
			}
			else {
				txtdistancepieton.setText(Double.toString(Math.round(pieton.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		
		if(txtdistancevelo.getText().equals("")) {
			txtdistancevelo.setText(Double.toString(Math.round(velo.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdistvelo == true) {
				velo.setDistance(Double.valueOf(txtdistancevelo.getText()));
				txtdistancevelo.setText(Double.toString(Math.round(velo.getDistance()* 1000.0)/1000.0));
				booltxtdistvelo = false;
			}
			else {
				txtdistancevelo.setText(Double.toString(Math.round(velo.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		
		if(txtdistancemoto.getText().equals("")) {
			txtdistancemoto.setText(Double.toString(Math.round(moto.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdistmoto == true) {
				moto.setDistance(Double.valueOf(txtdistancemoto.getText()));
				txtdistancemoto.setText(Double.toString(Math.round(moto.getDistance()* 1000.0)/1000.0));
				booltxtdistmoto = false;
			}
			else {
				txtdistancemoto.setText(Double.toString(Math.round(moto.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		
		if(txtdistancevoiture.getText().equals("")) {
			txtdistancevoiture.setText(Double.toString(Math.round(voiture.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdistvoiture == true) {
				voiture.setDistance(Double.valueOf(txtdistancevoiture.getText()));
				txtdistancevoiture.setText(Double.toString(Math.round(voiture.getDistance()* 1000.0)/1000.0));
				booltxtdistvoiture = false;
			}
			else {
				txtdistancevoiture.setText(Double.toString(Math.round(voiture.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		if(txtdistancebus.getText().equals("")) {
			txtdistancebus.setText(Double.toString(Math.round(bus.getDistance()* 1000.0)/1000.0));
			
		}
		else {
			if(booltxtdistbus == true) {
				bus.setDistance(Double.valueOf(txtdistancebus.getText()));
				txtdistancebus.setText(Double.toString(Math.round(bus.getDistance()* 1000.0)/1000.0));
				booltxtdistbus = false;
			}
			else {
				txtdistancebus.setText(Double.toString(Math.round(bus.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		if(txtdistancemetro.getText().equals("")) {
			txtdistancemetro.setText(Double.toString(Math.round(metro.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdistmetro == true) {
				metro.setDistance(Double.valueOf(txtdistancemetro.getText()));
				txtdistancemetro.setText(Double.toString(Math.round(metro.getDistance()* 1000.0)/1000.0));
				booltxtdistmetro = false;
			}
			else {
				txtdistancemetro.setText(Double.toString(Math.round(metro.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		if(txtdistancetram.getText().equals("")) {
			txtdistancetram.setText(Double.toString(Math.round(tram.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdisttram == true) {
				tram.setDistance(Double.valueOf(txtdistancetram.getText()));
				txtdistancetram.setText(Double.toString(Math.round(tram.getDistance()* 1000.0)/1000.0));
				booltxtdisttram = false;
			}
			else {
				txtdistancetram.setText(Double.toString(Math.round(tram.getDistance()* 1000.0)/1000.0));
			}
			
		}
		
		if(txtdistancetrain.getText().equals("")) {
			txtdistancetrain.setText(Double.toString(Math.round(train.getDistance()* 1000.0)/1000.0));
		}
		else {
			if(booltxtdisttrain == true) {
				train.setDistance(Double.valueOf(txtdistancetrain.getText()));
				txtdistancetrain.setText(Double.toString(Math.round(train.getDistance()* 1000.0)/1000.0));
				booltxtdisttrain = false;
			}
			else {
				txtdistancetrain.setText(Double.toString(Math.round(train.getDistance()* 1000.0)/1000.0));
			}
			
		}

		
		
		/*affichage des nombres de véhicules*/
		
		if(txtnombrepieton.getText().equals("")) {
			txtnombrepieton.setText(Integer.toString(pieton.getNombre()));
		}
		else {
			if(booltxtnbpieton == true) {
				pieton.setNombre(Integer.valueOf(txtnombrepieton.getText()));
				txtnombrepieton.setText(Integer.toString(pieton.getNombre()));
				booltxtnbpieton = false;
			}
			else {
				txtnombrepieton.setText(Integer.toString(pieton.getNombre()));
			}
			
		}
		
		
		if(txtnombrevelo.getText().equals("")) {
			txtnombrevelo.setText(Integer.toString(velo.getNombre()));
		}
		else {
			if(booltxtnbvelo == true) {
				velo.setNombre(Integer.valueOf(txtnombrevelo.getText()));
				txtnombrevelo.setText(Integer.toString(velo.getNombre()));
				booltxtnbvelo = false;
			}
			else {
				txtnombrevelo.setText(Integer.toString(velo.getNombre()));
			}
			
		}
		
		
		if(txtnombremoto.getText().equals("")) {
			txtnombremoto.setText(Integer.toString(moto.getNombre()));
		}
		else {
			if(booltxtnbmoto == true) {
				moto.setNombre(Integer.valueOf(txtnombremoto.getText()));
				txtnombremoto.setText(Integer.toString(moto.getNombre()));
				booltxtnbmoto = false;
			}
			else {
				txtnombremoto.setText(Integer.toString(moto.getNombre()));
			}
			
		}
		
		
		if(txtnombrevoiture.getText().equals("")) {
			txtnombrevoiture.setText(Integer.toString(voiture.getNombre()));
		}
		else {
			if(booltxtnbvoiture == true) {
				voiture.setNombre(Integer.valueOf(txtnombrevoiture.getText()));
				txtnombrevoiture.setText(Integer.toString(voiture.getNombre()));
				booltxtnbvoiture = false;
			}
			else {
				txtnombrevoiture.setText(Integer.toString(voiture.getNombre()));
			}
			
		}
		
		if(txtnombrebus.getText().equals("")) {
			txtnombrebus.setText(Integer.toString(bus.getNombre()));
			
		}
		else {
			if(booltxtnbbus == true) {
				bus.setNombre(Integer.valueOf(txtnombrebus.getText()));
				txtnombrebus.setText(Integer.toString(bus.getNombre()));
				booltxtnbbus = false;
			}
			else {
				txtnombrebus.setText(Integer.toString(bus.getNombre()));
			}
			
		}
		
		if(txtnombremetro.getText().equals("")) {
			txtnombremetro.setText(Integer.toString(metro.getNombre()));
		}
		else {
			if(booltxtnbmetro == true) {
				metro.setNombre(Integer.valueOf(txtnombremetro.getText()));
				txtnombremetro.setText(Integer.toString(metro.getNombre()));
				booltxtnbmetro = false;
			}
			else {
				txtnombremetro.setText(Integer.toString(metro.getNombre()));
			}
			
		}
		
		if(txtnombretram.getText().equals("")) {
			txtnombretram.setText(Integer.toString(tram.getNombre()));
		}
		else {
			if(booltxtnbtram == true) {
				tram.setNombre(Integer.valueOf(txtnombretram.getText()));
				txtnombretram.setText(Integer.toString(tram.getNombre()));
				booltxtnbtram = false;
			}
			else {
				txtnombretram.setText(Integer.toString(tram.getNombre()));
			}
			
		}
		
		if(txtnombretrain.getText().equals("")) {
			txtnombretrain.setText(Integer.toString(train.getNombre()));
		}
		else {
			if(booltxtnbtrain == true) {
				train.setNombre(Integer.valueOf(txtnombretrain.getText()));
				txtnombretrain.setText(Integer.toString(train.getNombre()));
				booltxtnbtrain = false;
			}
			else {
				txtnombretrain.setText(Integer.toString(train.getNombre()));
			}
			
		}
		
		/*affichage des EC*/
		
		txtECpieton.setText(Double.toString( Math.round((Double.valueOf(txtdistancepieton.getText()) * Double.valueOf(txtfepieton.getText()) * Double.valueOf(txtnombrepieton.getText()))* 1000.0)/1000.0 ));
		txtECvelo.setText(Double.toString( Math.round((Double.valueOf(txtdistancevelo.getText()) * Double.valueOf(txtfevelo.getText()) * Double.valueOf(txtnombrevelo.getText()))* 1000.0)/1000.0 ));
		txtECmoto.setText(Double.toString( Math.round((Double.valueOf(txtdistancemoto.getText()) * Double.valueOf(txtfemoto.getText()) * Double.valueOf(txtnombremoto.getText()))* 1000.0)/1000.0 ));
		txtECvoiture.setText(Double.toString( Math.round((Double.valueOf(txtdistancevoiture.getText()) * Double.valueOf(txtfevoiture.getText()) * Double.valueOf(txtnombrevoiture.getText()))* 1000.0)/1000.0 ));
		txtECbus.setText(Double.toString( Math.round((Double.valueOf(txtdistancebus.getText()) * Double.valueOf(txtfebus.getText()) * Double.valueOf(txtnombrebus.getText()))* 1000.0)/1000.0 ));
		txtECmetro.setText(Double.toString( Math.round((Double.valueOf(txtdistancemetro.getText()) * Double.valueOf(txtfemetro.getText()) * Double.valueOf(txtnombremetro.getText()))* 1000.0)/1000.0 ));
		txtECtram.setText(Double.toString( Math.round((Double.valueOf(txtdistancetram.getText()) * Double.valueOf(txtfetram.getText()) * Double.valueOf(txtnombretram.getText()))* 1000.0)/1000.0 ));
		txtECtrain.setText(Double.toString( Math.round((Double.valueOf(txtdistancetrain.getText()) * Double.valueOf(txtfetrain.getText()) * Double.valueOf(txtnombretrain.getText()))* 1000.0)/1000.0 ));
		
		
		/*affichage EC globale*/
		
		txtECglobale.setText(Double.toString(Math.round((( Double.parseDouble(txtECpieton.getText()) +   Double.parseDouble(txtECvelo.getText()) +   Double.parseDouble(txtECmoto.getText()) +   Double.parseDouble(txtECvoiture.getText()) +  Double.parseDouble(txtECbus.getText()) +   Double.parseDouble(txtECmetro.getText()) +   Double.parseDouble(txtECtram.getText()) +   Double.parseDouble(txtECtrain.getText())) / 1000D)* 1000.0)/1000.0)); 
		
		contentPane.repaint();
		contentPane.revalidate();
		  
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void insertUpdate(DocumentEvent ev) {
		// TODO Auto-generated method stub
		
		Object src = ev.getDocument(); 
		
		if(src == txtdistancepieton.getDocument()) {
			pieton.setDistance(Double.valueOf(txtdistancepieton.getText()));
			booltxtdistpieton = true;
		}
		if(src == txtdistancevelo.getDocument()) {
			velo.setDistance(Double.valueOf(txtdistancevelo.getText()));
			booltxtdistvelo = true;
		}
		if(src == txtdistancemoto.getDocument()) {
			moto.setDistance(Double.valueOf(txtdistancemoto.getText()));
			booltxtdistmoto = true;
		}
		if(src == txtdistancevoiture.getDocument()) {
			voiture.setDistance(Double.valueOf(txtdistancevoiture.getText()));
			booltxtdistvoiture = true;
		}
		if(src == txtdistancebus.getDocument()) {
			bus.setDistance(Double.valueOf(txtdistancebus.getText()));
			booltxtdistbus = true;
		}
		if(src == txtdistancemetro.getDocument()) {
			metro.setDistance(Double.valueOf(txtdistancemetro.getText()));
			booltxtdistmetro = true;
		}
		if(src == txtdistancetram.getDocument()) {
			tram.setDistance(Double.valueOf(txtdistancetram.getText()));
			booltxtdisttram = true;
		}
		if(src == txtdistancetrain.getDocument()) {
			train.setDistance(Double.valueOf(txtdistancetrain.getText()));
			booltxtdisttrain = true;
		}
		
		if(src == txtnombrepieton.getDocument()) {
			pieton.setNombre(Integer.valueOf(txtnombrepieton.getText()));
			booltxtnbpieton = true;
		}
		if(src == txtnombrevelo.getDocument()) {
			velo.setNombre(Integer.valueOf(txtnombrevelo.getText()));
			booltxtnbvelo = true;
		}
		if(src == txtnombremoto.getDocument()) {
			moto.setNombre(Integer.valueOf(txtnombremoto.getText()));
			booltxtnbmoto = true;
		}
		if(src == txtnombrevoiture.getDocument()) {
			voiture.setNombre(Integer.valueOf(txtnombrevoiture.getText()));
			booltxtnbvoiture = true;
		}
		if(src == txtnombrebus.getDocument()) {
			bus.setNombre(Integer.valueOf(txtnombrebus.getText()));
			booltxtnbbus = true;
		}
		if(src == txtnombremetro.getDocument()) {
			metro.setNombre(Integer.valueOf(txtnombremetro.getText()));
			booltxtnbmetro = true;
		}
		if(src == txtnombretram.getDocument()) {
			tram.setNombre(Integer.valueOf(txtnombretram.getText()));
			booltxtnbtram = true;
		}
		if(src == txtnombretrain.getDocument()) {
			train.setNombre(Integer.valueOf(txtnombretrain.getText()));
			booltxtnbtrain = true;
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent eve) {
		// TODO Auto-generated method stub
	} 


}
