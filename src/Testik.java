import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.HashMap;

public class Testik {

    private Char gameCharacters;
    private WorldMap worldMap;
    private Item healingItem;
    private Item healingItem2;
    private Item healingItem3;

    private Location kitchen;
    private Location obyvak;
    private Location loznice;

    @BeforeEach
    public void unit() {

        worldMap = new WorldMap();


        kitchen = new Location("Kuchyn", 2, 0, 0, 0, 0);
        obyvak = new Location("Obyvak", 1, 0, 0, 0, 0);
        loznice = new Location("Loznice", 3, 0, 0, 0, 0);


        Map<Integer, Location> mapa = new HashMap<>();
        mapa.put(1, obyvak);
        mapa.put(2, kitchen);
        mapa.put(3, loznice);
        worldMap.setMapa(mapa);


        String characterData = "src/char";
        String dialogData = "src/dialog";
        gameCharacters = new Char(worldMap, characterData, dialogData);


        gameCharacters.getCharacterRoomMap().put("Bruce", loznice);
        gameCharacters.getCharacterRoomMap().put("Bazilisek", kitchen);
        gameCharacters.getCharacterRoomMap().put("VitekKvitek", obyvak);


        healingItem = new Item("dobry", 1, kitchen);
        healingItem2 = new Item("dobrous", 2, obyvak);
        healingItem3 = new Item("dobrousik", 5, loznice);
    }

    @Test
    public void TestcharPlace() {

        Map<String, Location> characterRooms = gameCharacters.getCharacterRoomMap();


        assertEquals(3, characterRooms.size());
        characterRooms.forEach((name, room) -> System.out.println(name + " je v místnosti: " + room.getName()));


        assertEquals(kitchen, characterRooms.get("Bazilisek"));
        assertEquals(obyvak, characterRooms.get("VitekKvitek"));
        assertEquals(loznice, characterRooms.get("Bruce"));
    }

    @Test
    public void TestCharDialog() {


        assertEquals("B", gameCharacters.getCharacterDialog("Bazilisek"));
        assertEquals("V", gameCharacters.getCharacterDialog("VitekKvitek"));
        assertEquals("B", gameCharacters.getCharacterDialog("Bruce"));
    }


@Test
    public void testHealing() {





        gameCharacters.useItem("Bazilisek", healingItem);
        gameCharacters.useItem("VitekKvitek", healingItem2);
        gameCharacters.useItem("Bruce", healingItem3);




        assertTrue(gameCharacters.isCharacterHealed("Bazilisek"));
        assertTrue(gameCharacters.isCharacterHealed("VitekKvitek"));
        assertTrue(gameCharacters.isCharacterHealed("Bruce"));

    }

    @Test
    public void TestItemloc() {


        assertEquals(kitchen, healingItem.getLocation());
    }

    @Test
    public void Testadd() {


        gameCharacters.toChar(healingItem);


        assertTrue(gameCharacters.getCharacterRoomMap().containsValue(kitchen));
    }

    @Test
    public void testLocation() {


        Location room = new Location("testovaci", 1, 0, 0, 0, 0);
        assertEquals("testovaci", room.getName());
        assertEquals(1, room.getId());
    }

    @Test
    public void   stUnlock() {


        Location lockedRoom = new Location(" locked room", 1, 0, 0, 0, 0);
        assertFalse(lockedRoom.isLocked());


        lockedRoom.lock("klickis");
        assertTrue(lockedRoom.isLocked());
        assertEquals("klickis", lockedRoom.getReqKez());


        lockedRoom.unlock();
        assertFalse(lockedRoom.isLocked());
        assertNull(lockedRoom.getReqKez());
    }


    @Test
    public void testSettingExit() {


        Location room = new Location("roomik", 1, 0, 0, 0, 0);
        room.setExit("north", 2);


        assertEquals(2, room.getExit("north"));
        assertEquals(0, room.getExit("south"));
    }
}