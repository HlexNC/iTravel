# iTravel Android App - Cursor Entwicklungs-Prompt

## Projekt-Übersicht
Entwickle eine native Android-App in Java für **iTravel** - eine authentische Reisebewertungs-Plattform "Von Travelern für Traveler", die sich durch 100% echte Bewertungen ohne kommerzielle Beeinflussung auszeichnet.

---

## Kern-Features & Funktionalität

### 1. Authentifizierungs-System
- User Registration mit E-Mail-Verifizierung
- Login/Logout mit sicherer Session-Verwaltung
- Traveler-Profile mit Profilbild, Bio und Reisehistorie
- Community-Verifizierungsstatus für Profile

### 2. Bewertungs-System
- **Echtzeit-Bewertungen**: Bewertungen können nur vor Ort (GPS-basiert) abgegeben werden
- Bewertungskategorien: Restaurants, Hotels, Hostels, Cafés, Sehenswürdigkeiten
- 5-Sterne-Rating mit Pflicht-Textkommentar (min. 50 Zeichen)
- Foto-Upload für Bewertungen (optional, max. 5 Bilder)
- Timestamp und GPS-Koordinaten für jede Bewertung
- **Anti-Manipulations-Logik**: 
  - Ein User kann einen Ort nur einmal bewerten
  - GPS-Validierung (User muss sich im Umkreis von 100m befinden)
  - Zeitliche Plausibilitätsprüfung

### 3. Locations & Suche
- Kartenansicht (Google Maps Integration)
- Suche nach Locations (Name, Kategorie, Stadt)
- Filter: Nach Rating, Entfernung, Kategorie, Preisspanne
- Location-Details: Durchschnittsbewertung, alle Reviews, Fotos, Adresse, Öffnungszeiten
- "In der Nähe"-Funktion basierend auf aktuellem Standort

### 4. Community-Features
- Follower/Following-System
- Feed mit Bewertungen von gefolgten Usern
- Like und Kommentar-Funktion für Reviews
- "Hilfreich"-Voting für Bewertungen
- Reporting-System für unangemessene Inhalte

### 5. Profil & Statistiken
- Persönliches Dashboard mit:
  - Anzahl der Bewertungen
  - Besuchte Länder/Städte
  - "Top Reviewer"-Badge bei vielen verifizierten Reviews
  - Traveler-Typ (Backpacker, Digitaler Nomade, etc.)
- Eigene Bewertungen verwalten (bearbeiten/löschen innerhalb 24h)

---

## Technische Architektur

### Android Stack
- **Sprache**: Java (min SDK 26, target SDK 34)
- **IDE**: Android Studio
- **Build**: Gradle
- **Architecture**: MVVM (Model-View-ViewModel)

### Wichtige Dependencies
```gradle
dependencies {
    // Android Core
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    
    // Lifecycle & ViewModel
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.7.0'
    
    // Navigation
    implementation 'androidx.navigation:navigation-fragment:2.7.7'
    implementation 'androidx.navigation:navigation-ui:2.7.7'
    
    // Google Maps & Location
    implementation 'com.google.android.gms:play-services-maps:18.2.0'
    implementation 'com.google.android.gms:play-services-location:21.1.0'
    
    // Networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.12.0'
    
    // Image Loading
    implementation 'com.github.bumptech.glide:glide:4.16.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.16.0'
    
    // Database
    implementation 'androidx.room:room-runtime:2.6.1'
    annotationProcessor 'androidx.room:room-compiler:2.6.1'
    
    // SharedPreferences Encryption
    implementation 'androidx.security:security-crypto:1.1.0-alpha06'
    
    // Camera & Image Picker
    implementation 'androidx.activity:activity:1.8.2'
    
    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
}
```

### Backend API (Mock/Placeholder)
Erstelle zunächst Mock-Endpoints mit Retrofit:
- `POST /api/auth/register` - User registrieren
- `POST /api/auth/login` - User login
- `GET /api/locations/nearby` - Locations in der Nähe
- `GET /api/locations/{id}` - Location Details
- `POST /api/reviews` - Review erstellen
- `GET /api/reviews/location/{id}` - Reviews für Location
- `GET /api/users/{id}` - User-Profil abrufen
- `POST /api/reviews/{id}/helpful` - Review als hilfreich markieren

Verwende zunächst lokale Room-Datenbank für Offline-First Approach.

---

## UI/UX Design-Richtlinien

### Farb-Palette (Einzigartig, kein generisches Material Design)
```java
// Primary Colors
#1E7B7E  // Teal - Hauptfarbe (Vertrauen, Reisen, Authentizität)
#F4A261  // Warm Orange - Akzent (Entdeckung, Abenteuer)
#2A9D8F  // Seafoam - Sekundär
#264653  // Dark Slate - Text & Backgrounds

// Supporting
#E76F51  // Coral - Call-to-Action
#F1FAEE  // Off-White - Backgrounds
#8AB4F8  // Light Blue - Highlights
```

### Typografie
- **Headers**: Roboto Bold, 24sp-32sp
- **Body**: Roboto Regular, 14sp-16sp
- **Captions**: Roboto Light, 12sp

### Screen-Struktur

#### 1. Splash Screen
- iTravel Logo (prozedural generiert mit Processing-Inspiration)
- Ladeanimation
- Fade-in zur Main Activity

#### 2. Authentication Flow
- **Welcome Screen**: Logo + "Von Travelern für Traveler" Slogan
- **Login Screen**: Email, Passwort, "Passwort vergessen?", "Registrieren"
- **Register Screen**: Name, Email, Passwort, Passwort wiederholen, Traveler-Typ Auswahl

#### 3. Main Navigation (Bottom Navigation Bar)
- **Discover** (Home): Karte + Liste mit nearby Locations
- **Search**: Suchleiste + Filter + Ergebnisse
- **Add Review**: Kamera/GPS-Check + Review-Formular
- **Feed**: Social Feed mit Reviews von gefolgten Usern
- **Profile**: User-Dashboard + Einstellungen

#### 4. Location Detail Screen
- Header Image (Carousel von User-Fotos)
- Durchschnittsbewertung (große Sterne-Anzeige)
- Kategorie, Adresse, Öffnungszeiten
- "Review schreiben"-Button (nur sichtbar wenn in der Nähe)
- Liste aller Reviews (RecyclerView mit Pagination)

#### 5. Review Writing Screen
- GPS-Check Indicator (grün = in der Nähe)
- Sterne-Rating (1-5)
- Textfeld (min. 50 Zeichen, Counter anzeigen)
- Foto-Upload (max. 5, Thumbnail-Vorschau)
- Kategorien-Tags (z.B. "WLAN-Qualität", "Preis-Leistung")
- "Veröffentlichen"-Button

#### 6. User Profile Screen
- Profilbild + Name + Bio
- Statistiken: # Reviews, # Followers, # Following, Besuchte Länder
- "Top Reviewer"-Badge (wenn >50 verifizierte Reviews)
- Tab-Layout: Meine Reviews | Favoriten | Über mich
- Follow/Unfollow Button (wenn fremdes Profil)

---

## Code-Struktur & Packages

```
com.itravel.app/
├── activities/
│   ├── SplashActivity.java
│   ├── AuthActivity.java
│   ├── MainActivity.java
│   └── LocationDetailActivity.java
│
├── fragments/
│   ├── DiscoverFragment.java
│   ├── SearchFragment.java
│   ├── AddReviewFragment.java
│   ├── FeedFragment.java
│   └── ProfileFragment.java
│
├── adapters/
│   ├── LocationAdapter.java
│   ├── ReviewAdapter.java
│   └── UserAdapter.java
│
├── models/
│   ├── User.java
│   ├── Location.java
│   ├── Review.java
│   └── Category.java
│
├── viewmodels/
│   ├── LocationViewModel.java
│   ├── ReviewViewModel.java
│   └── UserViewModel.java
│
├── repositories/
│   ├── LocationRepository.java
│   ├── ReviewRepository.java
│   └── UserRepository.java
│
├── database/
│   ├── AppDatabase.java
│   ├── dao/
│   │   ├── LocationDao.java
│   │   ├── ReviewDao.java
│   │   └── UserDao.java
│
├── network/
│   ├── ApiService.java
│   ├── RetrofitClient.java
│   └── models/
│       └── ApiResponse.java
│
├── utils/
│   ├── GPSValidator.java
│   ├── ImageCompressor.java
│   ├── ValidationUtils.java
│   └── Constants.java
│
└── services/
    └── LocationService.java (Background GPS Tracking)
```

---

## Sicherheits-Features

### 1. GPS-basierte Validierung
```java
// GPSValidator.java Beispiel
public class GPSValidator {
    private static final float MAX_DISTANCE_METERS = 100f;
    
    public static boolean isUserNearby(Location userLocation, 
                                      double locationLat, 
                                      double locationLng) {
        Location targetLocation = new Location("");
        targetLocation.setLatitude(locationLat);
        targetLocation.setLongitude(locationLng);
        
        float distance = userLocation.distanceTo(targetLocation);
        return distance <= MAX_DISTANCE_METERS;
    }
}
```

### 2. Anti-Spam Mechanismen
- Rate Limiting: Max. 10 Reviews pro Tag pro User
- Duplicate Check: Ein User kann eine Location nur 1x bewerten
- Text-Analyse: Mindestlänge, keine Spam-Keywords
- Zeitstempel-Validierung: Review-Zeit muss mit GPS-Zeit übereinstimmen

### 3. Verschlüsselte Daten
```java
// Verwendung von EncryptedSharedPreferences für Auth-Token
EncryptedSharedPreferences.create(
    context,
    "secure_prefs",
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
);
```

---

## Permissions (AndroidManifest.xml)

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" 
    android:maxSdkVersion="28" />
```

---

## Entwicklungs-Prioritäten

### Phase 1: MVP (Minimum Viable Product)
1. ✅ Authentifizierung (Login/Register)
2. ✅ Location-Liste mit Karte (Google Maps)
3. ✅ Location-Details anzeigen
4. ✅ Review erstellen (mit GPS-Check)
5. ✅ User-Profil anzeigen

### Phase 2: Community-Features
1. ✅ Follower-System
2. ✅ Social Feed
3. ✅ Like & Kommentare
4. ✅ "Hilfreich"-Voting

### Phase 3: Premium-Features
1. ✅ Offline-Karten (Google Maps Offline API)
2. ✅ KI-Empfehlungen (ML Kit Integration)
3. ✅ Erweiterte Filter & Suche

---

## Testing-Anforderungen

### Unit Tests
- GPS-Validierung
- Review-Validierung (Textlänge, Spam-Check)
- Distance Calculation
- Authentication Logic

### Instrumentation Tests
- Login/Register Flow
- Review Creation Flow
- Map Interaction
- Navigation zwischen Screens

### Test-Daten
Erstelle Mock-Daten für:
- 20 Locations (verschiedene Kategorien)
- 50 Reviews
- 10 User-Profile

---

## Code-Style & Best Practices

1. **Naming Conventions**: 
   - Activities: `LocationDetailActivity`
   - Fragments: `DiscoverFragment`
   - ViewModels: `LocationViewModel`
   - Layouts: `activity_location_detail.xml`, `fragment_discover.xml`

2. **Error Handling**:
   - Verwende try-catch für alle Netzwerk-Calls
   - Zeige User-freundliche Fehlermeldungen (Toast/Snackbar)
   - Logge Errors mit Tag-System

3. **Resources**:
   - Alle Strings in `strings.xml` (auch für Mehrsprachigkeit vorbereiten)
   - Farben in `colors.xml`
   - Dimensions in `dimens.xml`

4. **Performance**:
   - Bilder mit Glide komprimieren
   - RecyclerView mit ViewHolder Pattern
   - Lazy Loading für Listen
   - Background Threads für Netzwerk & DB-Operationen

---

## Zusätzliche Features (Nice-to-Have)

1. **Offline-First Approach**: Room-Datenbank synchronisiert mit Backend
2. **Push Notifications**: Bei neuen Followers, Likes, Kommentaren
3. **Share-Funktion**: Reviews auf Social Media teilen
4. **QR-Code Scanner**: QR-Codes an Locations scannen für Auto-Check-in
5. **Dark Mode**: Unterstützung für System-Theme
6. **Multi-Language**: Deutsch & Englisch
7. **Accessibility**: TalkBack-Support, Content Descriptions

---

## Start-Anweisungen für Cursor

1. Erstelle ein neues Android Projekt in Android Studio (Empty Views Activity)
2. Konfiguriere `build.gradle` mit allen Dependencies
3. Implementiere zunächst die Authentifizierung & Main Navigation
4. Baue die Location-Liste mit Google Maps auf
5. Füge GPS-basierte Review-Erstellung hinzu
6. Implementiere Anti-Manipulations-Logik
7. Teste alle Flows gründlich
8. Poliere UI/UX gemäß Design-Richtlinien

---

## Design-Inspiration & Unterscheidungsmerkmale

**Was iTravel NICHT ist:**
- ❌ Kein generisches Material Design
- ❌ Keine blaue Standard-Farbpalette
- ❌ Keine kommerziell beeinflussten Bewertungen

**Was iTravel IST:**
- ✅ Einzigartiges Teal/Orange Farbschema (Reisen & Vertrauen)
- ✅ Prozedural generierte Logos & Animationen (Processing-Inspiration)
- ✅ 100% authentische, GPS-verifizierte Bewertungen
- ✅ Community-first, kein "Pay-to-Play"
- ✅ Moderne, clean UI mit Fokus auf Usability

---

## Erfolgs-Kriterien

Die App ist erfolgreich implementiert, wenn:
1. ✅ User können sich registrieren/einloggen
2. ✅ GPS-basierte Location-Erkennung funktioniert zuverlässig
3. ✅ Reviews können nur vor Ort erstellt werden (GPS-Check)
4. ✅ Anti-Spam-Logik verhindert Fake-Reviews
5. ✅ Kartenansicht zeigt alle Locations korrekt an
6. ✅ UI ist intuitiv & responsiv
7. ✅ App läuft stabil ohne Crashes
8. ✅ Alle Core-Features der Phase 1 sind implementiert

---

## Kontakt & Feedback
- **Entwickler**: Santiago Cardona
- **E-Mail**: santiago.cardona@stud.th-deg.de
- **GitHub**: github.com/traveler-app

---

**Viel Erfolg bei der Entwicklung! 🚀**
**Von Travelern für Traveler.**
