package com.itravel.app.data.local.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.itravel.app.util.Constants;

@Database(entities = {UserEntity.class, LocationEntity.class, ReviewEntity.class, LikeEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract LocationDao locationDao();
    public abstract ReviewDao reviewDao();
    public abstract LikeDao likeDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Insert 40 new locations for existing users upgrading from v1
            // Restaurants (10 new)
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Noma', 'Restaurant', 'Pioneering New Nordic cuisine redefining modern gastronomy', 'Refshalevej 96, Copenhagen', 55.6832, 12.6102, 'https://images.unsplash.com/photo-1559339352-11d035aa65de?w=800', 'Denmark')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('La Colombe', 'Restaurant', 'French-Asian fusion with stunning Table Mountain views', 'Silvermist Estate, Constantia Nek, Cape Town', -33.9299, 18.3976, 'https://images.unsplash.com/photo-1544025162-d76694265947?w=800', 'South Africa')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Attica', 'Restaurant', 'Celebrating Australian native ingredients and culture', '74 Glen Eira Road, Ripponlea, Melbourne', -37.8557, 145.0142, 'https://images.unsplash.com/photo-1600891964092-4316c288032e?w=800', 'Australia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Central Restaurante', 'Restaurant', 'Peruvian biodiversity explored through altitude-based tasting menus', 'Av. Pedro de Osma 301, Barranco, Lima', -12.1505, -77.0225, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800', 'Peru')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Narisawa', 'Restaurant', 'Innovative Japanese cuisine inspired by nature and seasons', 'Minami-Aoyama 2-6-15, Minato, Tokyo', 35.6724, 139.7262, 'https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?w=800', 'Japan')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Eleven Madison Park', 'Restaurant', 'Plant-based fine dining in an Art Deco landmark', '11 Madison Avenue, New York', 40.7416, -73.9872, 'https://images.unsplash.com/photo-1559339352-11d035aa65de?w=800', 'USA')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Quintonil', 'Restaurant', 'Contemporary Mexican cuisine highlighting local ingredients', 'Av. Isaac Newton 55, Polanco, Mexico City', 19.4283, -99.1704, 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=800', 'Mexico')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Steirereck', 'Restaurant', 'Austrian fine dining in a stunning park pavilion', 'Am Heumarkt 2A, Stadtpark, Vienna', 48.2043, 16.3816, 'https://images.unsplash.com/photo-1466978913421-dad2ebd01d17?w=800', 'Austria')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Borago', 'Restaurant', 'Wild Chilean ingredients transformed into avant-garde cuisine', 'Av. Nueva Costanera 3467, Vitacura, Santiago', -33.4107, -70.5943, 'https://images.unsplash.com/photo-1476224203421-9ac39bcb3327?w=800', 'Chile')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Burnt Ends', 'Restaurant', 'Australian-style barbecue meets fine dining in Singapore', '20 Teck Lim Road, Singapore', 1.2801, 103.8441, 'https://images.unsplash.com/photo-1529692236671-f1f6cf9683ba?w=800', 'Singapore')");

            // Hotels (10 new)
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Singita Kruger National Park', 'Hotel', 'Ultra-luxury safari lodge in pristine African wilderness', 'Kruger National Park, Mpumalanga', -24.9857, 31.5841, 'https://images.unsplash.com/photo-1549294413-26f195200c16?w=800', 'South Africa')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Explora Atacama', 'Hotel', 'Desert oasis with guided explorations of the driest desert on Earth', 'Ayllu de Larache, San Pedro de Atacama', -22.9116, -68.2003, 'https://images.unsplash.com/photo-1571896349842-33c89424de2d?w=800', 'Chile')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Qualia Hamilton Island', 'Hotel', 'Secluded luxury resort on the Great Barrier Reef', '20 Whitsunday Boulevard, Hamilton Island', -20.3492, 148.9542, 'https://images.unsplash.com/photo-1540541338287-41700207dee6?w=800', 'Australia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Four Seasons Bora Bora', 'Hotel', 'Overwater bungalows in a turquoise lagoon paradise', 'Motu Tehotu, Bora Bora', -16.5167, -151.7500, 'https://images.unsplash.com/photo-1439066615861-d1af74d74000?w=800', 'French Polynesia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Royal Mansour', 'Hotel', 'Palatial riads within a walled medina of unmatched luxury', 'Rue Abou Abbas El Fassi, Marrakech', 31.6258, -7.9891, 'https://images.unsplash.com/photo-1582719508461-905c673771fd?w=800', 'Morocco')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Belmond Hotel Caruso', 'Hotel', '11th-century palazzo perched above the Amalfi Coast', 'Piazza San Giovanni del Toro 2, Ravello', 40.6492, 14.6117, 'https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800', 'Italy')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Giraffe Manor', 'Hotel', 'Boutique hotel where Rothschild giraffes visit at breakfast', '34 Gogo Falls Road, Karen, Nairobi', -1.3749, 36.7450, 'https://images.unsplash.com/photo-1564501049412-61c2a3083791?w=800', 'Kenya')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Ashford Castle', 'Hotel', 'Medieval castle turned five-star lakeside estate', 'Cong, County Galway', 53.5473, -9.3544, 'https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=800', 'Ireland')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Jade Mountain', 'Hotel', 'Open-air suites with infinity pools and Piton views', 'Anse Chastanet Road, Soufriere', 13.8562, -61.0653, 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=800', 'St. Lucia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Song Saa Private Island', 'Hotel', 'Eco-luxury resort on a private Cambodian island', 'Koh Rong Archipelago, Sihanoukville', 10.5955, 103.1977, 'https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?w=800', 'Cambodia')");

            // Attractions (10 new)
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Petra', 'Attraction', 'Ancient Nabataean city carved into rose-red sandstone cliffs', 'Wadi Musa, Ma''an Governorate', 30.3285, 35.4444, 'https://images.unsplash.com/photo-1579606032821-4e6161c81571?w=800', 'Jordan')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Angkor Wat', 'Attraction', 'Largest religious monument in the world, a Khmer masterpiece', 'Krong Siem Reap', 13.4125, 103.8670, 'https://images.unsplash.com/photo-1508159452718-d22ffd77d264?w=800', 'Cambodia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Christ the Redeemer', 'Attraction', 'Iconic Art Deco statue overlooking Rio de Janeiro', 'Parque Nacional da Tijuca, Alto da Boa Vista, Rio de Janeiro', -22.9519, -43.2105, 'https://images.unsplash.com/photo-1564659907532-6b5f98c8e70f?w=800', 'Brazil')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Sydney Opera House', 'Attraction', 'Architectural masterpiece and UNESCO World Heritage performing arts centre', 'Bennelong Point, Sydney', -33.8568, 151.2153, 'https://images.unsplash.com/photo-1523482580672-f109ba8cb9be?w=800', 'Australia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Chichen Itza', 'Attraction', 'Majestic Mayan pyramid and New Wonder of the World', 'Yucatan Peninsula', 20.6843, -88.5678, 'https://images.unsplash.com/photo-1518638150340-f706e86654de?w=800', 'Mexico')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Table Mountain', 'Attraction', 'Flat-topped mountain offering panoramic views of Cape Town', 'Table Mountain National Park, Cape Town', -33.9625, 18.4034, 'https://images.unsplash.com/photo-1580060839134-75a5edca2e99?w=800', 'South Africa')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Pyramids of Giza', 'Attraction', 'Last surviving Wonder of the Ancient World', 'Al Haram, Giza Governorate', 29.9792, 31.1342, 'https://images.unsplash.com/photo-1503177119275-0aa32b3a9368?w=800', 'Egypt')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Acropolis of Athens', 'Attraction', 'Ancient citadel with the iconic Parthenon temple', 'Athens 105 58', 37.9715, 23.7267, 'https://images.unsplash.com/photo-1555993539-1732b0258235?w=800', 'Greece')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Fushimi Inari Shrine', 'Attraction', 'Thousands of vermilion torii gates winding up a sacred mountain', '68 Fukakusa Yabunouchicho, Fushimi, Kyoto', 34.9671, 135.7727, 'https://images.unsplash.com/photo-1478436127897-769e1b3f0f36?w=800', 'Japan')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Neuschwanstein Castle', 'Attraction', 'Fairytale castle that inspired Disney''s Sleeping Beauty Castle', 'Neuschwansteinstrasse 20, Schwangau, Bavaria', 47.5576, 10.7498, 'https://images.unsplash.com/photo-1534313314376-a72289b6181e?w=800', 'Germany')");

            // Nature (10 new)
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Serengeti National Park', 'Nature', 'Endless plains hosting the Great Migration of millions of wildebeest', 'Mara Region, Tanzania', -2.3333, 34.8333, 'https://images.unsplash.com/photo-1516426122078-c23e76319801?w=800', 'Tanzania')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Milford Sound', 'Nature', 'Dramatic fjord with towering peaks, waterfalls, and dolphins', 'Fiordland National Park, Southland', -44.6718, 167.9268, 'https://images.unsplash.com/photo-1507699622108-4be3abd695ad?w=800', 'New Zealand')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Iguazu Falls', 'Nature', 'Thundering curtain of 275 waterfalls on the Argentina-Brazil border', 'Parque Nacional Iguazu, Misiones', -25.6953, -54.4367, 'https://images.unsplash.com/photo-1432405972618-c60b0225b8f9?w=800', 'Argentina')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Ha Long Bay', 'Nature', 'Emerald waters dotted with thousands of limestone karst islands', 'Quang Ninh Province', 20.9101, 107.1839, 'https://images.unsplash.com/photo-1528127269322-539801943592?w=800', 'Vietnam')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Wadi Rum', 'Nature', 'Mars-like desert landscape of sandstone arches and ancient petroglyphs', 'Aqaba Governorate', 29.5772, 35.4207, 'https://images.unsplash.com/photo-1547234935-80c7145ec969?w=800', 'Jordan')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Zhangjiajie National Forest Park', 'Nature', 'Towering sandstone pillars that inspired Avatar''s Pandora', 'Zhangjiajie, Hunan Province', 29.3249, 110.4343, 'https://images.unsplash.com/photo-1513415564515-763d91423bdd?w=800', 'China')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Sossusvlei', 'Nature', 'Iconic red sand dunes rising over 300 meters in the Namib Desert', 'Namib-Naukluft National Park', -24.7275, 15.2993, 'https://images.unsplash.com/photo-1509023464722-18d996393ca8?w=800', 'Namibia')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Galapagos Islands', 'Nature', 'Volcanic archipelago of unique wildlife that inspired Darwin', 'Galapagos Province, Ecuador', -0.9538, -90.9656, 'https://images.unsplash.com/photo-1544979590-37e9b47eb705?w=800', 'Ecuador')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Blue Lagoon', 'Nature', 'Milky-blue geothermal spa surrounded by black lava fields', 'Nordhursveit 2, Grindavik', 63.8804, -22.4495, 'https://images.unsplash.com/photo-1515238152791-8216bfcf7f00?w=800', 'Iceland')");
            database.execSQL("INSERT INTO locations (name, category, description, address, latitude, longitude, imageUrl, country) VALUES ('Maldives Atolls', 'Nature', 'Crystal-clear lagoons and coral reefs across 26 ring-shaped atolls', 'North Male Atoll', 3.2028, 73.2207, 'https://images.unsplash.com/photo-1514282401047-d79a71a590e8?w=800', 'Maldives')");
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            Constants.DB_NAME
                    )
                    .addMigrations(MIGRATION_1_2)
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            DatabaseSeeder.seed(INSTANCE);
                        }
                    })
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
