package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

/**
 * un afficheur graphique associe au JeuTest fourni
 * 
 * @author vthomas
 */
public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;

	   public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		   this.jeu = spaceInvaders;
	   }

	   @Override
	   public void dessiner(BufferedImage im) {
		   if (this.jeu.recupererVaisseau() != null) {
			   Vaisseau vaisseau = this.jeu.recupererVaisseau();
			   this.dessinerUnSprite(vaisseau, im, vaisseau.getLocImage());
		   }
		   if (!this.jeu.recupererEnvahisseurs().isEmpty()) {
			   List <Envahisseur> envahisseurs = this.jeu.recupererEnvahisseurs();
			   for (Envahisseur envahisseur : envahisseurs) this.dessinerUnSprite(envahisseur, im, envahisseur.getLocImage());
		   }
		   if (!this.jeu.recupererMissilesVaisseau().isEmpty()) {
			   List <Missile> missilesVaisseau = this.jeu.recupererMissilesVaisseau();
			   for (Missile missile : missilesVaisseau) this.dessinerUnSprite(missile, im, missile.getColor());
		   }
		   
			if (!this.jeu.recupererMissilesEnvahisseur().isEmpty()) {
				List<Missile> missilesEnvahisseur = this.jeu.recupererMissilesEnvahisseur();
				for (Missile missile : missilesEnvahisseur)
					this.dessinerUnSprite(missile, im, missile.getColor());
			}
		this.dessinerScore(im);
		
		if (jeu.etreFini()) this.dessinerEcranFin(im);
		
	   }
	   
	   private void dessinerEcranFin(BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();
		   crayon.setColor(Color.black);
		   crayon.drawString("GAME OVER", jeu.getLongueur()/2, jeu.getHauteur()/2);
	   }
	   
	   private void dessinerScore(BufferedImage im) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();
		   crayon.setColor(Color.black);
		   crayon.drawString(String.valueOf(this.jeu.getScore()), 10, 10);
	   }
	   
	   private void dessinerUnSprite(Sprite sprite, BufferedImage im, Color couleur) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();
		   crayon.setColor(couleur);
		   crayon.fillRect(sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusBasse(), sprite.getDimension().longueur(), sprite.getDimension().hauteur());
	   }
	   
	   private void dessinerUnSprite(Sprite sprite, BufferedImage im, URL locImage) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();
		try {
			 crayon.drawImage(ImageIO.read(locImage), sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusBasse(), sprite.getDimension().longueur(), sprite.getDimension().hauteur(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }

}
