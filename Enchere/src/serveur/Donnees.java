package serveur;

import java.util.Stack;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Donnees {

	private Stack<Objet> listeObjets = new Stack<Objet>();

	public Stack<Objet> getListeObjets() {
		return listeObjets;
	}

	public void setListeObjets(Stack<Objet> listeObjets) {
		this.listeObjets = listeObjets;
	}

	
	//TODO a virer par la suite
	public void initObjets(){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");


		/*Objet obj1 = new Objet("jarre","jarre de ramses 3", 250);
		Objet obj2 = new Objet("herisson","herisson des bois", 100);
		Objet obj3 = new Objet("lit","un lit tout doux avec lequel on n'a pas envie de se lever le matin", 300);
		
		listeObjets.push(obj1);
		listeObjets.push(obj2);
		listeObjets.push(obj3);*/
		Objet obj1 = (Objet) context.getBean("obj1");
		obj1.getDescription();
		obj1.getNom();
		obj1.getPrixBase();
		obj1.getPrixCourant();
		obj1.isDisponible();
		obj1.getGagnant();
		Objet obj2 = (Objet) context.getBean("obj2");
		obj2.getDescription();
		obj2.getNom();
		obj2.getPrixBase();
		obj2.getPrixCourant();
		obj2.isDisponible();
		obj2.getGagnant();
		Objet obj3 = (Objet) context.getBean("obj3");
		obj3.getDescription();
		obj3.getNom();
		obj3.getPrixBase();
		obj3.getPrixCourant();
		obj3.isDisponible();
		obj3.getGagnant();

		listeObjets.push(obj1);
		listeObjets.push(obj2);
		listeObjets.push(obj3);

		
	}
	
	
	
	
	/**
	 * Methode permettant l'ajout d'un nouvel objet aux enchere. Ajoute l'objet dans la liste des objets a vendre.
	 * @param objet l'objet a vendre.
	 * @throws Exception so l'objet est deja en vente ou si l'acheteur n'est pas encore inscrit.
	 */
	public void ajouterArticle(Objet objet) throws Exception{
		for(Objet each : this.listeObjets){
			if(each.equals(objet)){
				throw new Exception("Objet deja existant");
			}
		}

		this.listeObjets.add(objet);
	}
	
	
}
