package org.sio.slam.devine

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.fail
import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte

class CarteTest {

    /**
     * Déclaration et définition d'un objet à tester, accessible par toutes les méthodes de test de cette classe
     * On nomme cet objet OUT (Object Under Test)
     * Les méthodes peuvent également créer localement d'autres objets à tester.
     */
    private var valetDeCoeur: Carte = Carte(NomCarte.VALET, CouleurCarte.COEUR)

    @Test
    fun getNom() {
        assertEquals("VALET", this.valetDeCoeur.nom.name)
        assertNotEquals("Valet", this.valetDeCoeur.nom.name)
    }

    @Test
    fun getCouleur() {
        // test sur le nom (String)
        assertEquals("COEUR", this.valetDeCoeur.couleur.name)

        // test sur la valeur énumérée
        assertEquals(CouleurCarte.COEUR, this.valetDeCoeur.couleur)

        // test sur mauvaise valeur énumérée
        assertNotEquals(CouleurCarte.PIQUE, this.valetDeCoeur.couleur)
    }

    @Test
    fun valeurCartes() {
        assertEquals(11, this.valetDeCoeur.valeur)

        val asDeCoeur: Carte = Carte(NomCarte.AS, CouleurCarte.COEUR)
        val roiDeCoeur: Carte = Carte(NomCarte.ROI, CouleurCarte.COEUR)
        val troisDePique: Carte = Carte(NomCarte.TROIS, CouleurCarte.PIQUE)

        assertEquals(14, asDeCoeur.valeur)
        assertEquals(13, roiDeCoeur.valeur)
        assertEquals(3, troisDePique.valeur)

        assertNotEquals(asDeCoeur.valeur, roiDeCoeur.valeur)
    }

    @Test
    fun compareCartesDeMemeCouleurMaisDeValeurDifferente() {
        val ASDeCoeur: Carte = Carte(NomCarte.AS, CouleurCarte.COEUR)
        val ROIDeCoeur: Carte = Carte(NomCarte.ROI, CouleurCarte.COEUR)
        val DAMEDeCoeur: Carte = Carte(NomCarte.DAME, CouleurCarte.COEUR)
        val VALETDeCoeur: Carte = Carte(NomCarte.VALET, CouleurCarte.COEUR)

        // test avec compareTo
        assertTrue(ASDeCoeur > ROIDeCoeur)
        assertTrue(ROIDeCoeur > DAMEDeCoeur)
        assertTrue(DAMEDeCoeur > VALETDeCoeur)

        assertTrue(ASDeCoeur > DAMEDeCoeur)

        // Finalement, si les objets sont *comparables*
        // alors les opérateurs binaires de comparaisons sont applicables
        // L'opérateur '>' appellera automatiquement la méthode compareTo (comme ci-dessus)
        // voir https://kotlinlang.org/docs/collection-ordering.html" - ordre naturel
    }

    @Test
    fun compareCartesDeCouleurDifferenteMaisDeMemeValeur() {
        val asDeCOEUR = Carte(NomCarte.AS, CouleurCarte.COEUR)
        val asDeCARREAU = Carte(NomCarte.AS, CouleurCarte.CARREAU)
        val asDePIQUE = Carte(NomCarte.AS, CouleurCarte.PIQUE)
        val asDeTREFLE = Carte(NomCarte.AS, CouleurCarte.TREFLE)

        // test l'ordre des couleurs est le suivant coeur>carreau>pique>trefle
        assertTrue(asDeCOEUR > asDeCARREAU)
        assertTrue(asDeCARREAU > asDePIQUE)
        assertTrue(asDePIQUE > asDeTREFLE)

        assertTrue(asDeCOEUR > asDePIQUE)
    }
}
