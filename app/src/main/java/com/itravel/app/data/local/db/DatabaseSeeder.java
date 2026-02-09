package com.itravel.app.data.local.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class DatabaseSeeder {

    public static void seed(AppDatabase db) {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (db.locationDao().getCount() > 0) return;

            List<LocationEntity> locations = new ArrayList<>();

            // Restaurants
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

            // Hotels
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

            // Attractions
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

            // Nature
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
