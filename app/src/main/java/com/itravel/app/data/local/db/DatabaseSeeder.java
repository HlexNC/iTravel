package com.itravel.app.data.local.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class DatabaseSeeder {

    public static void seed(AppDatabase db) {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (db.locationDao().getCount() > 0) return;

            List<LocationEntity> locations = new ArrayList<>();

            // ==================== RESTAURANTS (15) ====================
            locations.add(createLocation("Le Cinq", "Restaurant",
                    "Michelin-starred French cuisine in an elegant setting",
                    "31 Avenue George V, Paris", 48.8688, 2.3018,
                    "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=800", "France"));
            locations.add(createLocation("Sukiyabashi Jiro", "Restaurant",
                    "World-renowned sushi restaurant featured in Jiro Dreams of Sushi",
                    "Tsukamoto Building, Ginza, Tokyo", 35.6735, 139.7640,
                    "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=800", "Japan"));
            locations.add(createLocation("Osteria Francescana", "Restaurant",
                    "Three Michelin star Italian restaurant by Massimo Bottura",
                    "Via Stella 22, Modena", 44.6468, 10.9254,
                    "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?w=800", "Italy"));
            locations.add(createLocation("Gaggan Anand", "Restaurant",
                    "Progressive Indian cuisine with molecular gastronomy",
                    "68/1 Soi Langsuan, Bangkok", 13.7398, 100.5412,
                    "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?w=800", "Thailand"));
            locations.add(createLocation("The Ledbury", "Restaurant",
                    "Modern European cuisine in Notting Hill",
                    "127 Ledbury Road, London", 51.5155, -0.2009,
                    "https://images.unsplash.com/photo-1550966871-3ed3cdb51f3a?w=800", "United Kingdom"));
            // 10 new restaurants
            locations.add(createLocation("Noma", "Restaurant",
                    "Pioneering New Nordic cuisine redefining modern gastronomy",
                    "Refshalevej 96, Copenhagen", 55.6832, 12.6102,
                    "https://images.unsplash.com/photo-1559339352-11d035aa65de?w=800", "Denmark"));
            locations.add(createLocation("La Colombe", "Restaurant",
                    "French-Asian fusion with stunning Table Mountain views",
                    "Silvermist Estate, Constantia Nek, Cape Town", -33.9299, 18.3976,
                    "https://images.unsplash.com/photo-1544025162-d76694265947?w=800", "South Africa"));
            locations.add(createLocation("Attica", "Restaurant",
                    "Celebrating Australian native ingredients and culture",
                    "74 Glen Eira Road, Ripponlea, Melbourne", -37.8557, 145.0142,
                    "https://images.unsplash.com/photo-1600891964092-4316c288032e?w=800", "Australia"));
            locations.add(createLocation("Central Restaurante", "Restaurant",
                    "Peruvian biodiversity explored through altitude-based tasting menus",
                    "Av. Pedro de Osma 301, Barranco, Lima", -12.1505, -77.0225,
                    "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800", "Peru"));
            locations.add(createLocation("Narisawa", "Restaurant",
                    "Innovative Japanese cuisine inspired by nature and seasons",
                    "Minami-Aoyama 2-6-15, Minato, Tokyo", 35.6724, 139.7262,
                    "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?w=800", "Japan"));
            locations.add(createLocation("Eleven Madison Park", "Restaurant",
                    "Plant-based fine dining in an Art Deco landmark",
                    "11 Madison Avenue, New York", 40.7416, -73.9872,
                    "https://images.unsplash.com/photo-1559339352-11d035aa65de?w=800", "USA"));
            locations.add(createLocation("Quintonil", "Restaurant",
                    "Contemporary Mexican cuisine highlighting local ingredients",
                    "Av. Isaac Newton 55, Polanco, Mexico City", 19.4283, -99.1704,
                    "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=800", "Mexico"));
            locations.add(createLocation("Steirereck", "Restaurant",
                    "Austrian fine dining in a stunning park pavilion",
                    "Am Heumarkt 2A, Stadtpark, Vienna", 48.2043, 16.3816,
                    "https://images.unsplash.com/photo-1466978913421-dad2ebd01d17?w=800", "Austria"));
            locations.add(createLocation("Borago", "Restaurant",
                    "Wild Chilean ingredients transformed into avant-garde cuisine",
                    "Av. Nueva Costanera 3467, Vitacura, Santiago", -33.4107, -70.5943,
                    "https://images.unsplash.com/photo-1476224203421-9ac39bcb3327?w=800", "Chile"));
            locations.add(createLocation("Burnt Ends", "Restaurant",
                    "Australian-style barbecue meets fine dining in Singapore",
                    "20 Teck Lim Road, Singapore", 1.2801, 103.8441,
                    "https://images.unsplash.com/photo-1529692236671-f1f6cf9683ba?w=800", "Singapore"));

            // ==================== HOTELS (15) ====================
            locations.add(createLocation("Burj Al Arab", "Hotel",
                    "Iconic sail-shaped luxury hotel on its own island",
                    "Jumeirah Street, Dubai", 25.1412, 55.1853,
                    "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?w=800", "UAE"));
            locations.add(createLocation("Aman Tokyo", "Hotel",
                    "Urban sanctuary blending Japanese tradition with modern luxury",
                    "The Otemachi Tower, Tokyo", 35.6867, 139.7643,
                    "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800", "Japan"));
            locations.add(createLocation("Hotel & Spa Das Stue", "Hotel",
                    "Boutique hotel in a former Danish embassy building",
                    "Drakestrasse 1, Berlin", 52.5087, 13.3459,
                    "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=800", "Germany"));
            locations.add(createLocation("The Plaza", "Hotel",
                    "Historic landmark hotel overlooking Central Park",
                    "768 5th Avenue, New York", 40.7645, -73.9744,
                    "https://images.unsplash.com/photo-1564501049412-61c2a3083791?w=800", "USA"));
            locations.add(createLocation("Raffles Hotel", "Hotel",
                    "Colonial-era grand hotel and national monument",
                    "1 Beach Road, Singapore", 1.2949, 103.8545,
                    "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=800", "Singapore"));
            // 10 new hotels
            locations.add(createLocation("Singita Kruger National Park", "Hotel",
                    "Ultra-luxury safari lodge in pristine African wilderness",
                    "Kruger National Park, Mpumalanga", -24.9857, 31.5841,
                    "https://images.unsplash.com/photo-1549294413-26f195200c16?w=800", "South Africa"));
            locations.add(createLocation("Explora Atacama", "Hotel",
                    "Desert oasis with guided explorations of the driest desert on Earth",
                    "Ayllu de Larache, San Pedro de Atacama", -22.9116, -68.2003,
                    "https://images.unsplash.com/photo-1571896349842-33c89424de2d?w=800", "Chile"));
            locations.add(createLocation("Qualia Hamilton Island", "Hotel",
                    "Secluded luxury resort on the Great Barrier Reef",
                    "20 Whitsunday Boulevard, Hamilton Island", -20.3492, 148.9542,
                    "https://images.unsplash.com/photo-1540541338287-41700207dee6?w=800", "Australia"));
            locations.add(createLocation("Four Seasons Bora Bora", "Hotel",
                    "Overwater bungalows in a turquoise lagoon paradise",
                    "Motu Tehotu, Bora Bora", -16.5167, -151.7500,
                    "https://images.unsplash.com/photo-1439066615861-d1af74d74000?w=800", "French Polynesia"));
            locations.add(createLocation("Royal Mansour", "Hotel",
                    "Palatial riads within a walled medina of unmatched luxury",
                    "Rue Abou Abbas El Fassi, Marrakech", 31.6258, -7.9891,
                    "https://images.unsplash.com/photo-1582719508461-905c673771fd?w=800", "Morocco"));
            locations.add(createLocation("Belmond Hotel Caruso", "Hotel",
                    "11th-century palazzo perched above the Amalfi Coast",
                    "Piazza San Giovanni del Toro 2, Ravello", 40.6492, 14.6117,
                    "https://images.unsplash.com/photo-1566073771259-6a8506099945?w=800", "Italy"));
            locations.add(createLocation("Giraffe Manor", "Hotel",
                    "Boutique hotel where Rothschild giraffes visit at breakfast",
                    "34 Gogo Falls Road, Karen, Nairobi", -1.3749, 36.7450,
                    "https://images.unsplash.com/photo-1564501049412-61c2a3083791?w=800", "Kenya"));
            locations.add(createLocation("Ashford Castle", "Hotel",
                    "Medieval castle turned five-star lakeside estate",
                    "Cong, County Galway", 53.5473, -9.3544,
                    "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?w=800", "Ireland"));
            locations.add(createLocation("Jade Mountain", "Hotel",
                    "Open-air suites with infinity pools and Piton views",
                    "Anse Chastanet Road, Soufriere", 13.8562, -61.0653,
                    "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?w=800", "St. Lucia"));
            locations.add(createLocation("Song Saa Private Island", "Hotel",
                    "Eco-luxury resort on a private Cambodian island",
                    "Koh Rong Archipelago, Sihanoukville", 10.5955, 103.1977,
                    "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?w=800", "Cambodia"));

            // ==================== ATTRACTIONS (15) ====================
            locations.add(createLocation("Colosseum", "Attraction",
                    "Ancient amphitheatre and icon of Imperial Rome",
                    "Piazza del Colosseo, Rome", 41.8902, 12.4922,
                    "https://images.unsplash.com/photo-1552832230-c0197dd311b5?w=800", "Italy"));
            locations.add(createLocation("Machu Picchu", "Attraction",
                    "15th-century Inca citadel set high in the Andes Mountains",
                    "Aguas Calientes, Cusco", -13.1631, -72.5450,
                    "https://images.unsplash.com/photo-1587595431973-160d0d163571?w=800", "Peru"));
            locations.add(createLocation("Taj Mahal", "Attraction",
                    "Ivory-white marble mausoleum on the bank of the Yamuna river",
                    "Dharmapuri, Agra", 27.1751, 78.0421,
                    "https://images.unsplash.com/photo-1564507592333-c60657eea523?w=800", "India"));
            locations.add(createLocation("Sagrada Familia", "Attraction",
                    "Gaudi's unfinished masterpiece basilica",
                    "Carrer de Mallorca 401, Barcelona", 41.4036, 2.1744,
                    "https://images.unsplash.com/photo-1583779457711-ab081e5ef823?w=800", "Spain"));
            locations.add(createLocation("Great Wall of China", "Attraction",
                    "Ancient series of walls and fortifications spanning thousands of miles",
                    "Huairou District, Beijing", 40.4319, 116.5704,
                    "https://images.unsplash.com/photo-1508804185872-d7badad00f7d?w=800", "China"));
            // 10 new attractions
            locations.add(createLocation("Petra", "Attraction",
                    "Ancient Nabataean city carved into rose-red sandstone cliffs",
                    "Wadi Musa, Ma'an Governorate", 30.3285, 35.4444,
                    "https://images.unsplash.com/photo-1579606032821-4e6161c81571?w=800", "Jordan"));
            locations.add(createLocation("Angkor Wat", "Attraction",
                    "Largest religious monument in the world, a Khmer masterpiece",
                    "Krong Siem Reap", 13.4125, 103.8670,
                    "https://images.unsplash.com/photo-1508159452718-d22ffd77d264?w=800", "Cambodia"));
            locations.add(createLocation("Christ the Redeemer", "Attraction",
                    "Iconic Art Deco statue overlooking Rio de Janeiro",
                    "Parque Nacional da Tijuca, Alto da Boa Vista, Rio de Janeiro", -22.9519, -43.2105,
                    "https://images.unsplash.com/photo-1564659907532-6b5f98c8e70f?w=800", "Brazil"));
            locations.add(createLocation("Sydney Opera House", "Attraction",
                    "Architectural masterpiece and UNESCO World Heritage performing arts centre",
                    "Bennelong Point, Sydney", -33.8568, 151.2153,
                    "https://images.unsplash.com/photo-1523482580672-f109ba8cb9be?w=800", "Australia"));
            locations.add(createLocation("Chichen Itza", "Attraction",
                    "Majestic Mayan pyramid and New Wonder of the World",
                    "Yucatan Peninsula", 20.6843, -88.5678,
                    "https://images.unsplash.com/photo-1518638150340-f706e86654de?w=800", "Mexico"));
            locations.add(createLocation("Table Mountain", "Attraction",
                    "Flat-topped mountain offering panoramic views of Cape Town",
                    "Table Mountain National Park, Cape Town", -33.9625, 18.4034,
                    "https://images.unsplash.com/photo-1580060839134-75a5edca2e99?w=800", "South Africa"));
            locations.add(createLocation("Pyramids of Giza", "Attraction",
                    "Last surviving Wonder of the Ancient World",
                    "Al Haram, Giza Governorate", 29.9792, 31.1342,
                    "https://images.unsplash.com/photo-1503177119275-0aa32b3a9368?w=800", "Egypt"));
            locations.add(createLocation("Acropolis of Athens", "Attraction",
                    "Ancient citadel with the iconic Parthenon temple",
                    "Athens 105 58", 37.9715, 23.7267,
                    "https://images.unsplash.com/photo-1555993539-1732b0258235?w=800", "Greece"));
            locations.add(createLocation("Fushimi Inari Shrine", "Attraction",
                    "Thousands of vermilion torii gates winding up a sacred mountain",
                    "68 Fukakusa Yabunouchicho, Fushimi, Kyoto", 34.9671, 135.7727,
                    "https://images.unsplash.com/photo-1478436127897-769e1b3f0f36?w=800", "Japan"));
            locations.add(createLocation("Neuschwanstein Castle", "Attraction",
                    "Fairytale castle that inspired Disney's Sleeping Beauty Castle",
                    "Neuschwansteinstrasse 20, Schwangau, Bavaria", 47.5576, 10.7498,
                    "https://images.unsplash.com/photo-1534313314376-a72289b6181e?w=800", "Germany"));

            // ==================== NATURE (15) ====================
            locations.add(createLocation("Banff National Park", "Nature",
                    "Canada's oldest national park with stunning Rocky Mountain scenery",
                    "Banff, Alberta", 51.4968, -115.9281,
                    "https://images.unsplash.com/photo-1501785888041-af3ef285b470?w=800", "Canada"));
            locations.add(createLocation("Santorini Caldera", "Nature",
                    "Volcanic caldera with dramatic cliffs and stunning sunsets",
                    "Santorini, Cyclades", 36.3932, 25.4615,
                    "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?w=800", "Greece"));
            locations.add(createLocation("Plitvice Lakes", "Nature",
                    "Cascading lakes and waterfalls in a forested national park",
                    "Plitvicka Jezera, Lika-Senj", 44.8654, 15.5820,
                    "https://images.unsplash.com/photo-1555400038-63f5ba517a47?w=800", "Croatia"));
            locations.add(createLocation("Mount Fuji", "Nature",
                    "Japan's tallest peak and iconic volcanic mountain",
                    "Fujinomiya, Shizuoka", 35.3606, 138.7274,
                    "https://images.unsplash.com/photo-1490806843957-31f4c9a91c65?w=800", "Japan"));
            locations.add(createLocation("Torres del Paine", "Nature",
                    "Dramatic granite peaks, glaciers, and pristine wilderness",
                    "Torres del Paine, Magallanes", -50.9423, -73.4068,
                    "https://images.unsplash.com/photo-1531761535209-180857e963b9?w=800", "Chile"));
            // 10 new nature
            locations.add(createLocation("Serengeti National Park", "Nature",
                    "Endless plains hosting the Great Migration of millions of wildebeest",
                    "Mara Region, Tanzania", -2.3333, 34.8333,
                    "https://images.unsplash.com/photo-1516426122078-c23e76319801?w=800", "Tanzania"));
            locations.add(createLocation("Milford Sound", "Nature",
                    "Dramatic fjord with towering peaks, waterfalls, and dolphins",
                    "Fiordland National Park, Southland", -44.6718, 167.9268,
                    "https://images.unsplash.com/photo-1507699622108-4be3abd695ad?w=800", "New Zealand"));
            locations.add(createLocation("Iguazu Falls", "Nature",
                    "Thundering curtain of 275 waterfalls on the Argentina-Brazil border",
                    "Parque Nacional Iguazu, Misiones", -25.6953, -54.4367,
                    "https://images.unsplash.com/photo-1432405972618-c60b0225b8f9?w=800", "Argentina"));
            locations.add(createLocation("Ha Long Bay", "Nature",
                    "Emerald waters dotted with thousands of limestone karst islands",
                    "Quang Ninh Province", 20.9101, 107.1839,
                    "https://images.unsplash.com/photo-1528127269322-539801943592?w=800", "Vietnam"));
            locations.add(createLocation("Wadi Rum", "Nature",
                    "Mars-like desert landscape of sandstone arches and ancient petroglyphs",
                    "Aqaba Governorate", 29.5772, 35.4207,
                    "https://images.unsplash.com/photo-1547234935-80c7145ec969?w=800", "Jordan"));
            locations.add(createLocation("Zhangjiajie National Forest Park", "Nature",
                    "Towering sandstone pillars that inspired Avatar's Pandora",
                    "Zhangjiajie, Hunan Province", 29.3249, 110.4343,
                    "https://images.unsplash.com/photo-1513415564515-763d91423bdd?w=800", "China"));
            locations.add(createLocation("Sossusvlei", "Nature",
                    "Iconic red sand dunes rising over 300 meters in the Namib Desert",
                    "Namib-Naukluft National Park", -24.7275, 15.2993,
                    "https://images.unsplash.com/photo-1509023464722-18d996393ca8?w=800", "Namibia"));
            locations.add(createLocation("Galapagos Islands", "Nature",
                    "Volcanic archipelago of unique wildlife that inspired Darwin",
                    "Galapagos Province, Ecuador", -0.9538, -90.9656,
                    "https://images.unsplash.com/photo-1544979590-37e9b47eb705?w=800", "Ecuador"));
            locations.add(createLocation("Blue Lagoon", "Nature",
                    "Milky-blue geothermal spa surrounded by black lava fields",
                    "Nordhursveit 2, Grindavik", 63.8804, -22.4495,
                    "https://images.unsplash.com/photo-1515238152791-8216bfcf7f00?w=800", "Iceland"));
            locations.add(createLocation("Maldives Atolls", "Nature",
                    "Crystal-clear lagoons and coral reefs across 26 ring-shaped atolls",
                    "North Male Atoll", 3.2028, 73.2207,
                    "https://images.unsplash.com/photo-1514282401047-d79a71a590e8?w=800", "Maldives"));

            db.locationDao().insertAll(locations);
        });
    }

    private static LocationEntity createLocation(String name, String category,
            String description, String address, double lat, double lon,
            String imageUrl, String country) {
        LocationEntity location = new LocationEntity();
        location.name = name;
        location.category = category;
        location.description = description;
        location.address = address;
        location.latitude = lat;
        location.longitude = lon;
        location.imageUrl = imageUrl;
        location.country = country;
        return location;
    }
}
