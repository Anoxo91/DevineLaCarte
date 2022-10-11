package org.sio.slam.devine


import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

internal class PaquetTest {

    @Test
    fun cardinal2Cartes() {
        val paquet2Cartes = Paquet(
            listOf(
                Carte(NomCarte.VALET, CouleurCarte.COEUR),
                Carte(NomCarte.DIX, CouleurCarte.TREFLE),
            )
        )
        assertEquals(2, paquet2Cartes.cardinal())
    }

    @Test
    fun testToString2Cartes() {
        val paquet2Cartes = Paquet(
            listOf(
                Carte(NomCarte.VALET, CouleurCarte.COEUR),
                Carte(NomCarte.DIX, CouleurCarte.TREFLE),
            )
        )
        assertEquals("Paquet de 2 cartes", paquet2Cartes.toString())
    }

    @Test
    fun testGet52Cartes() {
        val cartes = createJeu52Cartes()
        val cartesSize = cartes.size
        assertEquals(52, cartesSize)
        val carte = Carte(NomCarte.DEUX, CouleurCarte.TREFLE)
        var cardFound = false
        for (testcarte in cartes) {
            if (testcarte == carte) {
                cardFound = true
            }
        }
        assertEquals(true, cardFound)
        assertEquals(52, cartesSize) //test si le paquet fait contient bien 52 cartes
    }

    @Test
    fun testGet32Cartes() {
        val cartes = createJeu32Cartes()
        val cartesSize = cartes.size
        val carte = Carte(NomCarte.DEUX, CouleurCarte.TREFLE)
        var cardFound = false
        for (testcarte in cartes) {
            if (testcarte == carte) {
                cardFound = true
            }
        }
        assertFalse(cardFound)
        assertEquals(32, cartesSize) //test si le paquet fait contient bien 32 cartes

    }

    @Test
    fun testGetCarteADeviner() {
        val paquet2Cartes = Paquet(
            listOf(
                Carte(NomCarte.VALET, CouleurCarte.COEUR),
                Carte(NomCarte.DIX, CouleurCarte.TREFLE),
                Carte(NomCarte.SEPT, CouleurCarte.PIQUE),
                Carte(NomCarte.DEUX, CouleurCarte.COEUR),
                Carte(NomCarte.ROI, CouleurCarte.CARREAU),
                Carte(NomCarte.AS, CouleurCarte.COEUR),
                Carte(NomCarte.DAME, CouleurCarte.COEUR),
                Carte(NomCarte.TROIS, CouleurCarte.CARREAU),
                Carte(NomCarte.QUATRE, CouleurCarte.PIQUE),
                Carte(NomCarte.CINQ, CouleurCarte.TREFLE),

                )
        )
        val randomCarte = paquet2Cartes.getCarteADeviner()
        val randomCarte2 = paquet2Cartes.getCarteADeviner()
        assertNotEquals(randomCarte, randomCarte2)

    }

    @Test
    fun testBatLesCartes() {
        val paquetDeuxCartes = Paquet(
            listOf(
                Carte(NomCarte.DEUX, CouleurCarte.COEUR),
                Carte(NomCarte.TROIS, CouleurCarte.TREFLE),
                Carte(NomCarte.QUATRE, CouleurCarte.PIQUE),
                Carte(NomCarte.CINQ, CouleurCarte.COEUR),
                Carte(NomCarte.SIX, CouleurCarte.CARREAU),
                Carte(NomCarte.SEPT, CouleurCarte.COEUR),
                Carte(NomCarte.HUIT, CouleurCarte.COEUR),
                Carte(NomCarte.NEUF, CouleurCarte.CARREAU),
                Carte(NomCarte.DIX, CouleurCarte.PIQUE),
            )
        )
        val paquetMixed = paquetDeuxCartes.batLesCartes(paquetDeuxCartes.cartes)
        assertNotEquals(paquetDeuxCartes.cartes[0], paquetMixed[0])// test si la fonction batLesCarte fonctionne bien

    }


}
