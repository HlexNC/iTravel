<h1 align="center">
  <img src="app/src/main/res/drawable/app_logo.png" alt="iTravel Logo" width="150"/>
  <br>
  iTravel
</h1>

<p align="center">
  <strong>Your personal travel companion — discover places, read reviews, and share your adventures</strong>
</p>

<p align="center">
  <em>Von Travelern für Traveler</em>
</p>

<p align="center">
  <a href="https://github.com/HlexNC/iTravel">
    <img src="https://img.shields.io/badge/GitHub-iTravel-blue?style=flat-square&logo=github" alt="GitHub Repository">
  </a>
  <a href="https://developer.android.com/">
    <img src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white" alt="Android 8.0+">
  </a>
  <a href="https://www.java.com/">
    <img src="https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 17">
  </a>
  <a href="https://developers.google.com/maps">
    <img src="https://img.shields.io/badge/Google_Maps-SDK-4285F4?style=flat-square&logo=googlemaps&logoColor=white" alt="Google Maps">
  </a>
  <a href="https://m3.material.io/">
    <img src="https://img.shields.io/badge/Material_Design-3-757575?style=flat-square&logo=materialdesign&logoColor=white" alt="Material Design 3">
  </a>
</p>

---

## What is iTravel?

iTravel is an Android app for anyone who loves to travel — or even just daydream about it. Browse a hand-picked collection of 20 destinations around the world, see them on an interactive map, check the live weather, and read what other travelers have to say. When you visit a place yourself, leave a review (the app checks that you're actually there!).

> [!NOTE]
> **This is V1** — a complete rewrite of the original prototype (V0). The legacy version was built by a different developer and is included as a reference in the `legacy/` folder.

---

## Features at a Glance

### Discover
- Browse 20 curated travel destinations on an interactive **Google Maps** view
- Filter locations by category: **Restaurants**, **Hotels**, **Attractions**, and **Nature**
- Tap any pin to see details, photos, and reviews

### Travel Feed
- Scroll through a beautiful feed of travel photos powered by **Unsplash**
- See **live weather** conditions for destinations around the world
- Get inspired for your next trip right from your phone

### Reviews & Ratings
- Read honest reviews from fellow travelers
- Write your own reviews — with a star rating, text, and photos
- **GPS verification** makes sure you're actually at the location before you can review it (no armchair critics allowed!)

### Your Profile
- Create an account with a secure login
- See all the reviews you've written in one place
- Log out when you're done — your data stays safe with **encrypted storage**

### Search
- Look up destinations by name, category, or country
- Quickly find exactly the place you're looking for

---

## How It Works

```
  Welcome Screen         Log In / Register         Main App
 ┌──────────────┐      ┌──────────────────┐      ┌──────────────────┐
 │              │      │                  │      │  Discover (Map)  │
 │   iTravel    │─────▶│  Email & Password │─────▶│  Feed            │
 │              │      │                  │      │  Profile         │
 └──────────────┘      └──────────────────┘      └──────────────────┘
                                                         │
                                                         ▼
                                                  ┌──────────────────┐
                                                  │ Location Details  │
                                                  │ Weather + Reviews │
                                                  │ Add Your Review   │
                                                  └──────────────────┘
```

1. **Open the app** — you'll see a branded splash screen
2. **Sign up or log in** — create an account with your email and a password (6+ characters)
3. **Explore the map** — the Discover tab shows all 20 destinations as pins on a map
4. **Filter by category** — tap a category chip to narrow down what you see
5. **Tap a location** — view details, current weather, and reviews from other users
6. **Leave a review** — if you're physically at the location, rate it and share your thoughts
7. **Check the Feed** — browse travel photos and weather info for inspiration
8. **View your Profile** — see your reviews and manage your account

---

## Getting the App Running

### What You Need

- An **Android phone** running Android 8.0 (Oreo) or newer — that covers most phones from 2017 onward
- Or **Android Studio** on your computer if you want to run it in a simulator

### Option A: Install from Android Studio

1. Download and install [Android Studio](https://developer.android.com/studio)
2. Clone the project:
   ```bash
   git clone --recurse-submodules https://github.com/HlexNC/iTravel.git
   ```
3. Open the project folder in Android Studio
4. Wait for Gradle to finish syncing (this may take a minute the first time)
5. Connect your phone via USB (with [USB debugging](https://developer.android.com/studio/debug/dev-options) enabled) or start an emulator
6. Hit the green **Run** button

### Option B: Build from the Command Line

```bash
git clone --recurse-submodules https://github.com/HlexNC/iTravel.git
cd iTravel
./gradlew assembleDebug
```

The APK will be at `app/build/outputs/apk/debug/app-debug.apk` — transfer it to your phone and install.

> [!IMPORTANT]
> **API Keys Required** — The app uses Google Maps, Unsplash, and OpenWeatherMap. To get full functionality, you'll need to add your own free API keys to a `local.properties` file in the project root:
> ```properties
> UNSPLASH_ACCESS_KEY=your_key_here
> WEATHER_API_KEY=your_key_here
> MAPS_API_KEY=your_key_here
> ```
> Without these keys, the app will still work but some features (map, photos, weather) may show placeholder data.

### Where to Get API Keys (all free)

| Service | Sign Up | What It Powers |
|---------|---------|----------------|
| **Google Maps** | [Google Cloud Console](https://console.cloud.google.com/) | The interactive map in Discover |
| **Unsplash** | [unsplash.com/developers](https://unsplash.com/developers) | Travel photos in the Feed |
| **OpenWeatherMap** | [openweathermap.org/api](https://openweathermap.org/api) | Live weather on location pages |

---

## App Permissions

When you first use iTravel, your phone may ask you to grant these permissions:

| Permission | Why It's Needed |
|-----------|-----------------|
| **Location** | To show your position on the map and verify you're at a location before reviewing |
| **Camera** | To take photos for your reviews |
| **Photos/Media** | To attach existing photos from your gallery to reviews |
| **Internet** | To load maps, weather data, and travel photos |

You can deny any permission — the app will still work, but some features won't be available.

---

## Design

iTravel uses a **teal and orange** color scheme inspired by travel and nature:

| Color | Hex | Used For |
|-------|-----|----------|
| Teal | `#1E7B7E` | Primary buttons, headers, map UI |
| Seafoam | `#2A9D8F` | Secondary accents, highlights |
| Orange | `#F4A261` | Warm accents, category chips |
| Coral | `#E76F51` | Action buttons, alerts |
| Dark Slate | `#264653` | Text, dark backgrounds |
| Off White | `#F1FAEE` | Page backgrounds, cards |

The interface follows [Material Design 3](https://m3.material.io/) guidelines for a clean, modern look.

---

## Built With

| Technology | What It Does |
|-----------|-------------|
| **Google Maps SDK** | Interactive map with location pins and categories |
| **Retrofit** | Fetches data from travel and weather APIs |
| **Room Database** | Stores your account, reviews, and location data locally |
| **Glide** | Loads and caches images smoothly |
| **Jetpack Navigation** | Handles moving between screens |
| **EncryptedSharedPreferences** | Keeps your login session secure |
| **Material Design 3** | Modern, polished UI components |

---

## Project History

| Version | Description |
|---------|-------------|
| **V0** (Legacy) | The original prototype, partially built by a different developer. Included in the `legacy/` folder for reference. |
| **V1** (Current) | A complete rewrite with modern architecture, new features, and a polished design. This is what you're looking at. |

---

## Troubleshooting

> [!TIP]
> **Most issues** can be solved by making sure your API keys are set up correctly in `local.properties`.

**The map is blank or shows a grey grid**
- Your Google Maps API key may be missing or invalid. Double-check it in `local.properties` and make sure the Maps SDK for Android is enabled in your Google Cloud Console.

**No photos in the Feed**
- Your Unsplash API key may be missing. The app will show a fallback feed, but for real photos you'll need a valid key.

**Weather isn't loading**
- Check your OpenWeatherMap API key. Free accounts allow 60 requests per minute, which is more than enough for normal use.

**"GPS verification failed" when writing a review**
- The app checks that you're within a certain distance of the location. Make sure your phone's location is turned on and set to high accuracy.

**The app crashes on startup**
- Make sure you're running Android 8.0 or newer. If building from source, ensure Java 17 and Android SDK 34 are installed.

---

## Documentation

For more detailed information, check out the [project wiki](https://github.com/HlexNC/iTravel/wiki):

| Page | What's Inside |
|------|--------------|
| [Home](https://github.com/HlexNC/iTravel/wiki) | Overview, quick start, and tech stack |
| [Architecture](https://github.com/HlexNC/iTravel/wiki/Architecture) | How the app is structured under the hood |
| [API Integration](https://github.com/HlexNC/iTravel/wiki/API-Integration) | Details on the external services used |
| [Development Guide](https://github.com/HlexNC/iTravel/wiki/Development-Guide) | Setup instructions for contributors |

---

## License

This project is licensed under the **GNU General Public License v3.0** — see the [LICENSE](LICENSE) file for details.

---

## Academic Project

> [!NOTE]
> This app was developed as part of the **Software Engineering** course at the **Deggendorf Institute of Technology (DIT)**.

---

<p align="center">
  <strong>Von Travelern für Traveler</strong><br>
  <a href="https://github.com/HlexNC/iTravel">GitHub</a> · <a href="https://github.com/HlexNC/iTravel/wiki">Wiki</a> · <a href="https://github.com/HlexNC/iTravel/issues">Report a Bug</a>
</p>
