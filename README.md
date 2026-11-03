# Character Manager — TP3 (Java Visual SIH-6406)

## Deskripsi Singkat
Aplikasi berbasis Java ini dirancang untuk mengelola data karakter game, termasuk manajemen atribut dasar, tipe karakter, level, serta status kesehatan (`hitPoints` dan `mana`). Program ini juga mengimplementasikan validasi data untuk mencegah nilai negatif serta pengecekan status hidup karakter secara dinamis menggunakan method `isAlive()`.

---

## Screenshot Output Terminal
*(Lampirkan tangkapan layar / screenshot hasil output program Anda di sini, pastikan menampilkan hasil pemanggilan method `isAlive()`)*

```text
--- Daftar Karakter Awal ---
Character{name='Arthas', type=WARRIOR, level=5, hitPoints=150, mana=80}
Character{name='Jaina', type=MAGE, level=4, hitPoints=90, mana=150}
Character{name='Sylvanas', type=ARCHER, level=6, hitPoints=120, mana=100}

--- Demo Level Up (Arthas naik level) ---
Character{name='Arthas', type=WARRIOR, level=6, hitPoints=170, mana=90}

--- Pengujian Fitur Baru (Bagian B) ---
Character{name='Illidan', type=ASSASSIN, level=10, hitPoints=200, mana=120}
Apakah Illidan masih hidup? true
Validasi Exception berhasil ditangkap: Mana tidak boleh negatif!
https://drive.google.com/drive/folders/1guILGLui4rd1lZX6_X30WM1CtS0vwNYU

