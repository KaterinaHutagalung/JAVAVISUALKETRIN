
import java.util.ArrayList;
import java.util.List;

public class CharacterManager {
    private List<Character> characters = new ArrayList<>();

    // Menambah karakter dengan pencegahan duplikat nama[cite: 11]
    public boolean addCharacter(Character character) {
        for (Character c : characters) {
            if (c.equals(character)) {
                System.out.println("Gagal menambah: Karakter dengan nama '" + character.getName() + "' sudah ada (Duplikat ditolak).");
                return false;
            }
        }
        characters.add(character);
        return true;
    }

    public Character findByName(String name) {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        Character c = findByName(name);
        if (c != null) {
            characters.remove(c);
            return true;
        }
        return false;
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan salinan (new ArrayList) agar referensi asli aman[cite: 11]
    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // Bagian B: Mengembalikan rata-rata level, atau 0 jika list kosong (tanpa pembagian nol)[cite: 11]
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0.0;
        }
        int totalLevel = 0;
        for (Character c : characters) {
            totalLevel += c.getLevel();
        }
        return (double) totalLevel / characters.size();
    }

    // Bagian B: Mengembalikan ArrayList semua karakter dengan tipe tertentu[cite: 11]
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        for (Character c : characters) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }
    /**
     * Mencari dan mengembalikan karakter dengan hitPoints tertinggi (terkuat).
     * 
     * @return Objek Character yang memiliki hitPoints paling tinggi, 
     *         atau null jika daftar karakter kosong.
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }
        
        Character strongest = characters.get(0);
        for (Character c : characters) {
            if (c.getHitPoints() > strongest.getHitPoints()) {
                strongest = c;
            }
        }
        // Mengembalikan salinan/objek baru atau referensi aman 
        // tanpa membocorkan ArrayList asli secara langsung (Menjaga Encapsulation)
        return new Character(strongest.getName(), strongest.getType(), strongest.getLevel(), strongest.getHitPoints());
    }
}