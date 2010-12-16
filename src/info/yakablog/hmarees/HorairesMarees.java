package info.yakablog.hmarees;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class HorairesMarees extends Activity {
	
	/**
	 * Permet de stocker l'état de l'application à tout moment (liste des
	 * ports favoris).
	 */
	public SharedPreferences prefs;
	
	/**
	 * Le menu déroulant qui permet de choisir un port à ajouter aux favoris.
	 */
	private Spinner mSpinnerPort;
	
	/**
	 * Contient les ports favoris. Chaque port est composé d'un TableRow
	 * contenant un label cliquable du nom du port et un bouton pour supprimer
	 * ce port de la liste.
	 */
	TableLayout tablePorts;
	
	/**
	 * Contient les numéros des ports selon la nomenclature du site
	 * frbateaux.net
	 */
	int[] mPortNumber = {-1, 75, 77, 74, 20, 70, 201, 136, 106, 27, 87, 105, 51,
			415, 404, 426, 21, 2, 32, 13, 101, 92, 9, 401, 58, 137, 140, 7, 82,
			73, 430, 5, 83, 48, 135, 38, 418, 11, 71, 33, 428, 93, 130, 26, 56,
			37, 14, 24, 85, 301, 3, 405, 55, 99, 17, 307, 424, 416, 36, 10,
			121, 122, 16, 432, 403, 133, 35, 29, 45, 4, 50, 96, 200, 22, 427,
			128, 78, 123, 60, 98, 112, 113, 79, 126, 86, 47, 30, 94, 53, 49,
			431, 406, 127, 103, 134, 423, 80, 114, 89, 19, 18, 57, 109, 115,
			42, 8, 12, 414, 40, 61, 46, 125, 90, 63, 420, 69, 91, 95, 300, 411,
			138, 84, 1, 120, 34, 205, 25, 62, 400, 110, 417, 88, 66, 67, 306,
			132, 43, 129, 119, 116, 64, 97, 104, 28, 39, 76, 102, 100, 44, 72,
			429, 131, 108, 54, 41, 118, 124, 141, 52, 117, 59, 31, 15, 402, 419,
			421, 302, 422, 202, 68, 65, 111, 81, 23, 107, 413, 139, 412, 425,
			410, 203, 6, 204};
	
	/**
	 * Contient les noms des ports (à supprimer, utiliser plutôt la ressource)
	 */
	String[] mPortName = {"No port selected", "Aber Benoit", "Aber Ildut",
			"Aber Wrac\'h", "Alpha-Baie de Seine", "Anse de Primel",
			"Antwerpen (Anvers)", "Arcachon (Jetée d\'Eyrac)", "Arradon",
			"Arromanches-Les-Bains", "Audierne", "Auray / Saint-Goustan",
			"Aurigny (Braye)", "Aveiro", "Avilés", "Ayamonte (Muelle)",
			"Balise A", "Banc du Sandettié", "Barfleur", "Bassurelle",
			"Belle-Ile (Le Palais)", "Benodet", "Berck", "Bilbao", "Binic",
			"Biscarrosse", "Boucau-Bayonne", "Boulogne-sur-Mer", "Brest",
			"Brignogan", "Cadiz", "Calais", "Camaret-sur-Mer", "Cancale",
			"Cap Ferret", "Carteret", "Cascais", "Cayeux-sur-Mer",
			"Chateau du Taureau", "Cherbourg", "Chipiona", "Concarneau",
			"Cordouan", "Courseulles-Sur-Mer", "Dahouet", "Dielette", "Dieppe",
			"Dives (embouchure)", "Douarnenez", "Dover", "Dunkerque",
			"El Ferrol", "Erquy", "Etel", "Etretat", "Falmouth", "Faro Olhão",
			"Figueira da Foz", "Flamanville", "Fort-Mahon", "Fromentine Bouee",
			"Fromentine Port", "Fécamp", "Gibraltar", "Gijon",
			"Gironde (Richard)", "Goury", "Grandcamp", "Granville",
			"Gravelines", "Guernesey (Saint Peter Port)", "Hennebont",
			"Hoek van Holland", "Honfleur", "Huelva (Barra)", "Ile d\'Aix",
			"Ile d\'Ouessant (Lampaul)", "Ile d\'Yeu (Port-Joinville)",
			"Ile de Bréhat (Port-Clos)", "Ile de Groix (Port-Tudy)",
			"Ile de Hoedic", "Ile de Houat", "Ile de Molène",
			"Ile de Ré (Saint-Martin)", "Ile de Sein", "Iles Chausey",
			"Iles Saint-Marcouf", "Iles de Glénan (Penfret)",
			"Iles des Hébihens", "Jersey (Saint-Hélier)", "La Carraca",
			"La Coruña", "La Rochelle-Pallice", "La Trinite-sur-Mer",
			"Lacanau", "Lagos", "Le Conquet", "Le Croisic", "Le Guilvinec",
			"Le Havre", "Le Havre-Antifer", "Le Legue", "Le Logeo",
			"Le Pouliguen", "Le Senequet", "Le Touquet", "Le Treport",
			"Leixões", "Les Ecrehou", "Les Heaux-de-Bréhat", "Les Minquiers",
			"Les Sables-d\'Olonne", "Lesconil", "Lezardrieux", "Lisboa",
			"Locquirec", "Loctudy", "Lorient", "Margate", "Marin", "Mimizan",
			"Morgat", "Nieuwpoort", "Noirmoutier (L\'Herbaudière)",
			"Omonville-La-Rogue", "Oostende", "Ouistreham", "Paimpol",
			"Pasajes", "Penerf", "Peniche", "Penmarc\'h / Saint Guénolé",
			"Perros-Guirec", "Ploumanac\'h", "Plymouth", "Pointe De Grave",
			"Pointe d\'Agon", "Pointe de Gatseau", "Pornic", "Pornichet",
			"Port-Beni", "Port-Louis (Locmalo)", "Port-Navalo",
			"Port-en-Bessin", "Portbail", "Portsall",
			"Quiberon (Port-Haliguen)", "Quiberon (Port-Maria)",
			"Regneville-sur-Mer", "Roscoff", "Rota", "Royan",
			"Saint-Armel (Le Passage)", "Saint-Cast", "Saint-Germain-sur-Ay",
			"Saint-Gildas", "Saint-Gilles-Croix-de-Vie", "Saint-Jean-de-Luz",
			"Saint-Malo", "Saint-Nazaire", "Saint-Quay-Portrieux",
			"Saint-Vaast-La-Hougue", "Saint-Valery-en-Caux", "Santander",
			"Sesimbra", "Setubal", "Shoreham", "Sines", "Terneuzen",
			"Trebeurden", "Treguier", "Trehiguier", "Trez-Hir",
			"Trouville / Deauville", "Vannes", "Viana do Castelo",
			"Vieux-Boucau", "Vigo", "Vila Real de Santo", "Villagarcia",
			"Vlissingen", "Wissant", "Zeebrugge"};
	
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On récupère les préférences déjà sauvegardées (s'il y en a)
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // On applique à cette activité le layout nommé main
        setContentView(R.layout.main);
        
        // On récupère la référence à la liste déroulante déclarée dans le
        // layout main.xml
        mSpinnerPort = (Spinner) findViewById(R.id.spinnerPort);
        // On crée la liste déroulante en la peuplant avec les ports déclarés
        // dans la liste de valeurs listePorts.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.ports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerPort.setAdapter(adapter);
        
        // On récupère la référence à la table contenant les ports favoris
        tablePorts = (TableLayout) findViewById(R.id.TablePorts);
        
        // On récupère depuis les préférences générales la liste des ports
        // et on splitte pour avoir un tableau
        String[] portsList = prefs.getString("listeports", "").split("#");
        // Pour chaque port de ce tableau, on recrée les TableRow correspondants
        for (String onePort : portsList) {
        	if (onePort != null && !onePort.equals(""))
        		addTableRow(Integer.parseInt(onePort));
		}
        
        // Lorsque l'on sélectionne un port dans la liste déroulante, cela
        // déclenche l'action suivante qui va ajouter le port à la liste des
        // ports favoris, enregistrer cette liste dans les préférences générales
        // de l'application et créer la TableRow correspondante.
        mSpinnerPort.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	SharedPreferences.Editor editor = prefs.edit();
        		// On récupère la liste des ports
            	String listePorts = prefs.getString("listeports", "");
            	// Permettra de dire si le port sélectionné est déjà dans la
            	// liste de ports favoris
            	boolean alreadyPresent = false;
            	// Pour chaque port dans la liste de favoris, on vérifie s'il
            	// correspond au port sélectionné et si oui, on positionne
            	// le booléen "alreadyPresent" à True
            	for (String thisPort : listePorts.split("#")) {
					if(thisPort.equals("" + mSpinnerPort.getPositionForView(selectedItemView))
							|| mSpinnerPort.getPositionForView(selectedItemView) == 0){
						alreadyPresent = true;
					}
				}
            	
            	// Si le port sélectionné n'est pas présent dans la liste de
            	// favoris, on l'ajoute, on sauvegarde la nouvelle liste de
            	// favoris dans les préférences et on ajoute la TableRow
            	// correspondante
            	if (!alreadyPresent) {
            		if(listePorts != null && !listePorts.equals("")) {
            			listePorts = listePorts + "#";
            		}
            		listePorts = listePorts + mSpinnerPort.getPositionForView(selectedItemView);
            		editor.putString("listeports", listePorts);
            		
            		// Ajout du bouton correspondant au nouveau port
            		addTableRow(mSpinnerPort.getPositionForView(selectedItemView));
            	}
            	//editor.putString("listeports", ""); // FOR DEBUG //
            	
            	// Surtout ne pas oublier de commiter les modifs dans les
            	// préférences générales!!!
            	editor.commit();
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                // Nothing to do here !
            }
        });
    }
    
    /**
     * Ajoute une nouvelle TableRow au TableLayout contenant la liste des
     * ports favoris.
     * 
     * @param selectedPort le port à ajouter
     */
    public void addTableRow(int selectedPort) {
    	TableRow tr = new TableRow(this);
    	tr.setId(1000 + selectedPort);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        
        TextView labelPort = new TextView(this);
        labelPort.setId(2000 + selectedPort);
        labelPort.setText(mPortName[selectedPort]);
        //labelPort.setTextColor(Color.RED);
        labelPort.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        tr.addView(labelPort);
        
        // Quand on clique sur le nom du port, on va lancer l'activité qui
        // affiche les marées pour le port en question
        labelPort.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// On récupère l'ID du port
				int port = v.getId() - 2000;
				
				// Le Bundle sert à stocker des valeurs que l'on souhaite
				// transmettre à la nouvelle activité. Ici le numéro du port
				// pour pouvoir construire l'URL du widget, et le nom du port
				// pour l'afficher dans la barre de titre (purement cosmétique).
				Bundle bundle = new Bundle();
				bundle.putInt("info.yakablog.hmarees.port", mPortNumber[port]);
				bundle.putString("info.yakablog.hmarees.portName", mPortName[port]);
				
				// On lance l'activité qui va afficher les horaires de marées.
				Intent myIntent = new Intent(v.getContext(), ViewTides.class);
				myIntent.putExtras(bundle);
                startActivity(myIntent);
			}
		});
        
        // On crée le bouton "supprimer" et on l'ajoute à la TableRow
        Button supprPort = new Button(this);
        supprPort.setId(3000 + selectedPort);
        supprPort.setText("[-]");
        supprPort.setLayoutParams(new LayoutParams(
        		LayoutParams.WRAP_CONTENT,
        		LayoutParams.WRAP_CONTENT));
        supprPort.setGravity(Gravity.RIGHT);
        tr.addView(supprPort);
        
        // On exécute l'action suivante lorsqu'on clique sur le bouton Supprimer
        supprPort.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				int port = v.getId() - 3000;
				int viewToRemoveIndex = tablePorts.indexOfChild((View) v.getParent());
				
				// Supprime la TableRow sélectionnée (donc le nom du port et
				// le bouton "supprimer" associé)
				tablePorts.removeViewAt(viewToRemoveIndex);
				// Supprime le séparateur suivant la TableRow qu'on vient de
				// supprimer (ce séparateur a maintenant le même index que la
				// TableRow)
				tablePorts.removeViewAt(viewToRemoveIndex);
				
				// Maintenant on reconstruit la liste des ports sans celui qui
				// vient d'être supprimé et on écrit cette liste dans les
				// préférences.
				SharedPreferences.Editor editor = prefs.edit();
            	String listePorts = prefs.getString("listeports", "");
            	String[] arrayListPortsSrc = listePorts.split("#");
            	ArrayList<String> arrayListPortsDest = new ArrayList<String>();
            	for (String thisPort : arrayListPortsSrc) {
					if(!thisPort.equals("" + port)){
						arrayListPortsDest.add(thisPort);
					}
				}
            	String newPortsList = "";
            	for (String newPort : arrayListPortsDest) {
					if (!newPortsList.equals("")) {
						newPortsList += "#";
					}
					newPortsList += newPort;
				}
        		editor.putString("listeports", newPortsList);
        		editor.commit();
			}
		});
        
        // On ajoute la TableRow enfin créée à la table des ports favoris
        tablePorts.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        
        // On ajoute également à la suite de la nouvelle TableRow un séparateur
        View separation = new View(this);
        separation.setLayoutParams(new LayoutParams(
        		LayoutParams.FILL_PARENT,
        		2));
        separation.setBackgroundColor(Color.parseColor("#FF909090"));
        tablePorts.addView(separation);
    }
    
}